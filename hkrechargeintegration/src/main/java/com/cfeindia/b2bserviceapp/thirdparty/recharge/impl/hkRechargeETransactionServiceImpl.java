package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.exception.thirdparty.ThirdPartyRechargeCallException;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public class hkRechargeETransactionServiceImpl implements
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

	@Override
	public void doTransaction(TransactionTransportBean transportBean)
			throws MalformedURLException, IOException {
		String finalData = null;
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

		if (finalData != null) {
			String a[] = finalData.split("~");
			if (a.length == 6) {
				if (CommonConstants.SUCCESS.equalsIgnoreCase(a[0])) {
					transportBean.setThirdpartytransid(a[1]);
					transportBean.setThirdPartyTransDateTime(a[3]);
					transportBean.setResponsecode(a[4]);
					transportBean.setMessage(a[5]);

				} else {
					transportBean.setErrorCode(2);
					transportBean.setErrorcode("ERROR");
					transportBean.setErrorMessage(a[4]);
					if (!(a[4].contains("Insufficient")
							|| a[4].contains("Invalid UserID")
							|| a[4].contains("suspended") || a[4]
								.contains("User")))
						transportBean.setMessage(a[4]);

				}
			} else {
				transportBean.setErrorCode(2);
				transportBean.setErrorcode("ERROR");
				transportBean.setMessage("Operator Error");
				transportBean.setErrorMessage(a[4]);
			}
		} else {
			transportBean.setErrorCode(2);
			transportBean.setErrorcode("ERROR");
			transportBean.setMessage("Operator Error");
			transportBean.setErrorMessage(finalData);
		}

	}

	private String getDataFromThirdParty(TransactionTransportBean requestInput)
			throws ThirdPartyRechargeCallException, MalformedURLException,
			IOException {
		StringBuilder url = new StringBuilder();
		url.append(requestInput.getConnectURL());
		url.append("?token=").append(requestInput.getToken());
		url.append("&agentId=").append(requestInput.getAgentId());
		if ("MOBILE_PREPAID".equalsIgnoreCase(requestInput.getRechargeType())) {

			url.append("&account=").append(requestInput.getMobileNo());
		} else {
			url.append("&account=").append(requestInput.getConnectionid());
		}
		url.append("&amount=")
				.append(removeTrailingZeros(Double.valueOf(requestInput
						.getRecharge())));
		url.append("&opCode=").append(requestInput.getOperatorCode());
		url.append("&orderId=").append(requestInput.getTransid());
		url.append("&stdCode=").append(requestInput.getStdCode());
		url.append("&caNumber=").append(requestInput.getCanumber());
		url.append("&accountNumber=").append(requestInput.getAccountNumber());
		url.append("&originator=").append("cybertel");
		System.out.println(url);
		return (getDataFromURL(url).toString());
	}

	private StringBuilder getDataFromURL(StringBuilder url)
			throws MalformedURLException, IOException {
		StringBuilder inputLine = new StringBuilder();
		URL oracle = new URL(url.toString());
		System.out.println("Request date time"
				+ new Timestamp(new Date().getTime()));
		Long sTime = System.currentTimeMillis();
		URLConnection yc = oracle.openConnection();
		yc.setConnectTimeout(300000);
		yc.setReadTimeout(300000);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));

		String input1 = "";
		while ((input1 = in.readLine()) != null) {
			inputLine.append(input1);
		}
		in.close();
		System.out.println("Response  date time"
				+ new Timestamp(new Date().getTime()));
		Long eTime = System.currentTimeMillis();
		System.out.println("Time difference between request and response "
				+ (eTime - sTime));
		System.out.println("Response " + inputLine);
		return inputLine;
	}

	private static String removeTrailingZeros(double d) {
		return String.valueOf(d).replaceAll("[0]*$", "").replaceAll(".$", "");
	}

}
