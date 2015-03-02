package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

public class IcashETransactionServiceImpl implements ETransactionRequestService {
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
		System.out.println("hit on icasjh");
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

			Map<String, String> dataMap = parseResponse(finalData);
			System.out.println(dataMap);

		}

	}

	private String getDataFromThirdParty(TransactionTransportBean requestInput)
			throws ThirdPartyRechargeCallException, MalformedURLException,
			IOException {
		StringBuilder url = new StringBuilder();
		/*url.append(requestInput.getConnectURL());
		url.append("/REGISTRATION?");*/
		url.append("TERMINALID=").append(requestInput.getToken());
		url.append("&LOGINKEY=").append(requestInput.getToken1());
		url.append("&MERCHANTID=").append(requestInput.getToken2());
		url.append("&AGENTID=").append(requestInput.getAgentId());
		url.append("&TRANSACTIONID=").append("mon123456789");
		url.append("&KYCFLAG=").append("1");
		url.append("&USERNAME=").append(requestInput.getRemittanceUserDto().getFirstName());
		url.append("&USERMIDDLENAME=").append(requestInput.getRemittanceUserDto().getMiddleName());
		url.append("&USERLASTNAME=").append(requestInput.getRemittanceUserDto().getLastName());
		url.append("&USERMOTHERSMAIDENNAME=").append("");
		url.append("&USERDATEOFBIRTH=").append("");
		url.append("&USEREMAILID=").append("");
		url.append("&USERMOBILENO=").append(requestInput.getRemittanceUserDto().getMobileNO());
		url.append("&USERSTATE=").append(requestInput.getRemittanceUserDto().getState());
		url.append("&USERCITY=").append(requestInput.getRemittanceUserDto().getCity());
		url.append("&USERADDRESS=").append(requestInput.getRemittanceUserDto().getAddress());
		url.append("&PINCODE=").append(requestInput.getRemittanceUserDto().getPinCode());
		url.append("&USERIDPROOFTYPE=").append("");
		url.append("&USERIDPROOF=").append("");
		url.append("&IDPROOFURL=").append("");
		url.append("&USERADDRESSPROOFTYPE=").append("");
		url.append("&USERADDRESSPROOF=").append("");
		url.append("&ADDRESSPROOFURL=").append("");
		url.append("&PARAM1=").append("");
		url.append("&PARAM2=").append("");
		url.append("&PARAM3=").append("");
		url.append("&PARAM4=").append("");
		url.append("&PARAM5=").append("");
		

		String finalInput = URLEncoder.encode(url.toString());
		//System.out.println("After Encoding finalInput=" + finalInput);

		finalInput = "RequestData=" + finalInput;
		System.out.println(finalInput);
		String outputMessage = getDataFromURL("http://202.54.157.77/wsNPCI/IMPSMethods.asmx/REGISTRATION?", finalInput);
		System.out.println(outputMessage);
		return outputMessage;
	}

	public String getDataFromURL(String baseurl,String url)
			throws MalformedURLException, ThirdPartyRechargeCallException {
		String inputLine = null;
		System.out.println(url);
		URL oracle = new URL(baseurl);
		StringBuilder response = new StringBuilder();
		try {
			URLConnection urlCon = oracle.openConnection();
			HttpURLConnection con = null;
			if (urlCon instanceof HttpsURLConnection) {
				con = (HttpsURLConnection) oracle.openConnection();
			} else {
				con = (HttpURLConnection) oracle.openConnection();
			}

			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length",
					String.valueOf(url.length()));

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(url);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine).append("\n");
			}
			// response.delete(response.length() - 1, response.length());
			in.close();
			System.out.println("\n\nresponse: \n  " + response);

		} catch (IOException e) {
			e.printStackTrace();
			throw new ThirdPartyRechargeCallException("IOException ");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ThirdPartyRechargeCallException("IOException ");
		}
		// System.out.println("reponse" + response.toString());

		return response.toString();
	}

	private static String removeTrailingZeros(double d) {
		return String.valueOf(d).replaceAll("[0]*$", "").replaceAll(".$", "");
	}
	private Map<String, String> parseResponse(String finalData) {
		String response = finalData.substring(finalData.indexOf("DATE="));
		response = response.substring(0, finalData.indexOf("\nEND"));

		Map<String, String> dataMap = new HashMap<String, String>();

		if (response != null) {
			String responseArr[] = response.split("\n");

			if (responseArr != null && responseArr.length > 0) {
				for (String row : responseArr) {
					String rowArr[] = row.split("=");
					if (rowArr != null && rowArr.length > 1) {
						dataMap.put(rowArr[0], rowArr[1]);
					}
				}
			}
		}
		return dataMap;
	}
}
