package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.codehaus.jackson.map.ObjectMapper;

import com.cfeindia.b2bserviceapp.exception.thirdparty.ThirdPartyRechargeCallException;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.model.InstapayRechargeTransactionJSonResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public class InstaPayETransactionServiceImpl implements
		ETransactionRequestService {

	{
		/*
		 * fix for Exception in thread "main"
		 * javax.net.ssl.SSLHandshakeException:
		 * sun.security.validator.ValidatorException: PKIX path building failed:
		 * sun.security.provider.certpath.SunCertPathBuilderException: unable to
		 * find valid certification path to requested target
		 */
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs,
					String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs,
					String authType) {
			}

		} };

		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		/*
		 * end of the fix
		 */
	}

	public void doTransaction(TransactionTransportBean transportBean)
			throws MalformedURLException {
		InstapayRechargeTransactionJSonResponse finalData = null;
		try {
			finalData = getDataFromThirdParty(transportBean);
		} catch (ThirdPartyRechargeCallException e) {
			e.printStackTrace();
			transportBean
					.setMessage("Transaction pending from thirdparty: ThirdPartyRechargeCallException");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof MalformedURLException)
				throw (MalformedURLException) e;
			transportBean.setMessage("Transaction pending from thirdparty");
			return;
		}
		//System.out.println("Final Data " + finalData);
		if (finalData != null
				&& ("SUCCESS".equalsIgnoreCase(finalData.getStatus()) || "PENDING"
						.equalsIgnoreCase(finalData.getStatus()))) {
			transportBean.setTransid(finalData.getMerchantTransId());
			transportBean.setThirdpartytransid(finalData.getIpayId());
			transportBean.setThirdPartyTransDateTime(finalData.getDatetime());
			transportBean.setResponsecode(finalData.getResCode());
			transportBean.setMessage(finalData.getStatus() + " : "
					+ finalData.getResMsg());
			transportBean.setThirdpartyoperatortransid(finalData
					.getOperatorTransactionId());

			if ("PENDING".equalsIgnoreCase(finalData.getStatus())) {
				transportBean.setErrorcode(finalData.getIpayErrorcode());
				transportBean.setErrorMessage(finalData.getIpayErrordesc());
			}
		} else if (finalData != null && finalData.getIpayErrorcode() != null) {
			transportBean.setErrorCode(2);
			transportBean.setErrorcode(finalData.getIpayErrorcode());
			if (!(finalData.getIpayErrordesc() != null && finalData
					.getIpayErrordesc().contains("Agent")))
				transportBean.setMessage(finalData.getIpayErrordesc());
			else
				transportBean.setMessage("Problem from operator");
			transportBean.setErrorMessage(finalData.getIpayErrordesc());

		}

	}

	private InstapayRechargeTransactionJSonResponse getDataFromThirdParty(
			TransactionTransportBean requestInput)
			throws ThirdPartyRechargeCallException,
			UnsupportedEncodingException {
		StringBuilder url = new StringBuilder();
		url.append(requestInput.getConnectURL());
		url.append("?token=").append(requestInput.getToken());
		//System.out.println(requestInput.getOutletId());
		if ("MOBILE_RECHARGE".equalsIgnoreCase(requestInput
				.getTransactionName())) {
			url.append("&account=").append(requestInput.getMobileNo());
		} else if ("GAS_BILL".equalsIgnoreCase(requestInput
				.getTransactionName())) {

			url.append("&account=").append(requestInput.getConnectionid());
		} else if ("ELECTRICITY".equalsIgnoreCase(requestInput
				.getTransactionName())) {

			url.append("&account=").append(requestInput.getConnectionid());
		} else if ("INSURANCE_BILL".equalsIgnoreCase(requestInput
				.getTransactionName())) {
			url.append("&account=").append(requestInput.getConnectionid());
			url.append("&optional1=").append(requestInput.getDob());
		} else if ("LANDLINE".equalsIgnoreCase(requestInput
				.getTransactionName())) {
			url.append("&account=").append(requestInput.getConnectionid());
			url.append("&optional1=").append(requestInput.getStdCode());
		} else if ("CONNECTION".equalsIgnoreCase(requestInput
				.getTransactionName())) {
			url.append("&account=").append(requestInput.getConnectionid());
			url.append("&optional1=").append(requestInput.getPackageKey());
			url.append("&optional2=").append(requestInput.getPinNumber());
			url.append("&optional3=").append(
					URLEncoder.encode(requestInput.getCustomerName(), "UTF-8"));
			url.append("&optional4=").append(
					URLEncoder.encode(requestInput.getCustomerAddress(),
							"UTF-8"));

		}

		else {
			url.append("&account=").append(requestInput.getConnectionid());
		}
		
		url.append("&amount=")
				.append(removeTrailingZeros(Double.valueOf(requestInput
						.getRecharge())));
		url.append("&agentid=").append(requestInput.getTransid());
		url.append("&spkey=").append(requestInput.getOperatorCode());

		if (requestInput.getOutletId() != null)
		{
			url.append("&optional5=").append(requestInput.getOutletId());
		}
		url.append("&format=").append("json");
		//System.out.println(url);
		// String finalInput =
		// "mob9818631670130827003300%$A01000000000000000137754377597788900%$2013-08-27 00:32:55%$A14.SUCCESS%$Successfully Completed the Transaction$$$";
		// //System.out.println(inputLine);
		ObjectMapper obj = new ObjectMapper();
		InstapayRechargeTransactionJSonResponse jSonResponse = null;
		try {
			jSonResponse = obj.readValue(getDataFromURL(url).toString(),
					InstapayRechargeTransactionJSonResponse.class);
			//System.out.println(jSonResponse);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ThirdPartyRechargeCallException(
					"Thirdparty call exception");
		}
		return jSonResponse;
	}

	private StringBuilder getDataFromURL(StringBuilder url)
			throws MalformedURLException, IOException {
		StringBuilder inputLine = new StringBuilder();
		URL oracle = new URL(url.toString());
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));

		String input1 = "";
		while ((input1 = in.readLine()) != null) {
			inputLine.append(input1);
		}
		in.close();
		//System.out.println("output from instantpay " + inputLine);
		return inputLine;
	}

	public static void main(String[] args) {
		String finalInput = "1234%$A01000000000000000137759132297303600%$2013-08-27 13:45:22%$A14.SUCCESS%$Successfully Completed the Transaction$$$";
		Pattern p = Pattern.compile("%$", Pattern.LITERAL);
		String[] a = p.split(finalInput);
		//System.out.println(a[0]);
		//System.out.println(a[1]);
		//System.out.println(a[2]);
		//System.out.println(a[3]);
		//System.out.println(a[4]);
	}

	private static String removeTrailingZeros(double d) {
		return String.valueOf(d).replaceAll("[0]*$", "").replaceAll(".$", "");
	}

}
