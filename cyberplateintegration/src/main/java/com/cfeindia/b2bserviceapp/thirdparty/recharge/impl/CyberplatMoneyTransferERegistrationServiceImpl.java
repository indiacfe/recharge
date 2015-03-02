package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.cfeindia.apps.b2bserviceapp.thirdparty.cyberplate.CyberPlateIntegrationUtil;
import com.cfeindia.b2bserviceapp.exception.thirdparty.ThirdPartyRechargeCallException;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.MoneyTransERegistrationRequestService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public class CyberplatMoneyTransferERegistrationServiceImpl implements
		MoneyTransERegistrationRequestService {

	private static final String USER_AGENT = "Java/1.6";

	private static boolean flag = true;

	public void init(String secretKeyLocation, String secretKey,
			String publicKeyLocation, int pubKeys) throws Exception {

	}

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

	public void doRegistration(TransactionTransportBean transportBean)
			throws MalformedURLException {
	//	initializeCyberplateETransaction(transportBean);
		System.out.println("hit");
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
		if (finalData != null && !finalData.startsWith("PROBLEM")) {
			Map<String, String> dataMap = parseResponse(finalData);

			String errorValue = dataMap.get("ERROR");
			String resultVal = dataMap.get("RESULT");
			System.out.println("error if block  " + errorValue);
			if (!"0".equals(errorValue) || !"0".equals(resultVal)) {

				try {
					System.out.println("error in try " + errorValue);
					transportBean.setErrorCode(Integer.parseInt(errorValue));
				} catch (Exception e) {
					e.printStackTrace();
					transportBean.setErrorCode(2);
					System.out.println("error in catch " + errorValue);
				}
				transportBean.setErrorcode(errorValue);
				transportBean.setMessage("Error");
				transportBean.setErrorMessage("Error");

			} else if ("0".equals(errorValue) && "0".equals(resultVal)) {
				transportBean.setThirdpartytransid(dataMap.get("TRANSID"));

				StringBuffer values = new StringBuffer();
				values.append(dataMap.get("SESSION"));
				if (dataMap.get("AUTHCODE") != null) {
					values.append(":").append(dataMap.get("AUTHCODE"));
				}
				transportBean.setResponsecode(values.toString());
				if (!transportBean.getMessage().contains(
						"Your card has been credited by")) {
					transportBean.setMessage("Transaction Successful");
				}

				transportBean.setThirdPartyTransDateTime(dataMap.get("DATE"));
			}
		}

	}

	private Map<String, String> parseResponse(String finalData) {
		String response = finalData.substring(finalData.indexOf("BEGIN"));
		response = response.substring(0, finalData.indexOf("\nEND"));
		System.out.println("response in parseResponse" + response);
		Map<String, String> dataMap = new HashMap<String, String>();

		if (response != null) {
			String responseArr[] = response.split("\n");

			if (responseArr != null && responseArr.length > 0) {
				for (String row : responseArr) {
					String rowArr[] = row.split("=");
					if (rowArr != null && rowArr.length > 1) {
						System.out.println("Key " + rowArr[0] + "Value "
								+ rowArr[1]);
						dataMap.put(rowArr[0], rowArr[1]);
					}
				}
			}
		}
		return dataMap;
	}

	private String getDataFromThirdParty(
			TransactionTransportBean transactionTransport) throws Exception {

		String finalInput = null;

		StringBuilder sessionVal = new StringBuilder(
				transactionTransport.getRetailerId());
		int length = transactionTransport.getRetailerId().length();
		if (length < 10) {
			for (int i = 0; i < 8 - transactionTransport.getRetailerId()
					.length(); i++) {
				sessionVal.append("0");
			}
		}
		System.out.println("sessionVal" + sessionVal);
		String operatorCode = transactionTransport.getOperatorCode();
		String connectURL[] = new String[2];
		if (operatorCode != null) {
			StringTokenizer stringTokenizer = new StringTokenizer(operatorCode,
					";");
			int index = 0;
			while (stringTokenizer.hasMoreTokens()) {
				connectURL[index] = stringTokenizer.nextToken();
				index++;
			}
		}
		System.out.println("connectURL.length " + connectURL.length);
		if (connectURL.length > 1) {
			if (transactionTransport.getTransid() != null) {
				sessionVal.append(transactionTransport.getTransid().substring(
						13));
			}
			finalInput = inputMessageCreation(transactionTransport, sessionVal,
					true);
			String outputMessage = getDataFromURL(connectURL[0], finalInput);
			System.out.println("outputMessage in this block" + outputMessage);
			int errorIndex = outputMessage.indexOf("ERROR=");
			int resultIndex = outputMessage.indexOf("RESULT=");
			System.out.println("errorIndex " + errorIndex + "resultIndex "
					+ resultIndex);
			String errorVal = "1";
			String resultVal = "1";
			if (errorIndex != -1 && resultIndex != -1) {
				errorVal = outputMessage.substring(errorIndex + 6,
						errorIndex + 8);
				resultVal = outputMessage.substring(resultIndex + 7,
						resultIndex + 9);
				System.out.println("errorVal>" + errorVal + "resultVal >"
						+ resultVal);
				String[] line = outputMessage.split("\n");
				System.out.println("Line size " + line);
				String addInfo = "";
				for (String info : line) {
					if (info.contains("FIELDS=")) {
						addInfo = info.substring(8);
						break;
					}
				}
				System.out.println("addInfo  > " + addInfo);
				transactionTransport.setMessage(addInfo);

				/* } */
			}

			else {
				System.out.println("in else part " + errorVal);
				transactionTransport.setErrorCode(Integer.parseInt(errorVal
						.trim()));
				transactionTransport.setErrorcode(errorVal);
				transactionTransport.setMessage("Error");
				transactionTransport.setErrorMessage("Error");
				return "PROBLEM:" + errorVal;
			}
			return outputMessage;
		}
		return "PROBLEM:Operator Setup Error";
	}

	private String inputMessageCreation(
			TransactionTransportBean transactionTransport,
			StringBuilder sessionVal, boolean isCommentRequired)
			throws Exception, UnsupportedEncodingException {
		String finalInput;
		System.out.println("sessionVal=" + sessionVal);
		StringBuilder inputMessageTemp = new StringBuilder();
		// agentId need?
		String data = transactionTransport.getAgentId();
		ArrayList<String> list = new ArrayList<String>();
		if (data != null) {
			StringTokenizer stringTokenizer = new StringTokenizer(data, ":");
			while (stringTokenizer.hasMoreTokens()) {
				list.add(stringTokenizer.nextToken());
			}
		}
		System.out.println("After agentId>" + list);
		// op and ap will be change
		if (transactionTransport.getRemittanceUserDto().getType().equals("0")
				&& transactionTransport.getRemittanceUserDto().getStep()
						.equals("0")) {
			inputMessageTemp = getOTPRegistration(sessionVal, inputMessageTemp,
					transactionTransport, list);
		}

		if (transactionTransport.getRemittanceUserDto().getType().equals("0")
				&& transactionTransport.getRemittanceUserDto().getStep()
						.equals("1")) {
			inputMessageTemp = enterOTPRegistration(sessionVal,
					inputMessageTemp, transactionTransport, list);
		}
		if (transactionTransport.getRemittanceUserDto().getType().equals("0")) {
			inputMessageTemp = registrationRequestToProvider(sessionVal, inputMessageTemp,
					transactionTransport, list);
		}

		if (transactionTransport.getRemittanceUserDto().getType().equals("3")
				&& transactionTransport.getRemittanceUserDto().getStep()
						.equals("0")) {
			inputMessageTemp = enterOTPReload(sessionVal, inputMessageTemp,
					transactionTransport, list);
		}

		finalInput = CyberPlateIntegrationUtil.signMessage(inputMessageTemp
				.toString());
		System.out.println("After Signing finalInput=" + finalInput);
		finalInput = URLEncoder.encode(finalInput, "UTF-8");
		System.out.println("After Encoding finalInput=" + finalInput);

		finalInput = "inputmessage=" + finalInput;
		return finalInput;
	}

	

	public String getDataFromURL(String url, String finalInput)
			throws MalformedURLException, ThirdPartyRechargeCallException {
		String inputLine = null;
		System.out.println("URL>" + url);
		URL oracle = new URL(url);
		StringBuilder response = new StringBuilder();
		try {
			URLConnection urlCon = oracle.openConnection();
			HttpURLConnection con = null;
			if (urlCon instanceof HttpsURLConnection) {
				con = (HttpsURLConnection) oracle.openConnection();
			} else {
				con = (HttpURLConnection) oracle.openConnection();
			}

			// add reuqest header
			con.setRequestMethod("POST");
			// con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length",
					String.valueOf(finalInput.length()));

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(finalInput);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + finalInput);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine).append("\n");
			}
			response.delete(response.length() - 1, response.length());
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw new ThirdPartyRechargeCallException("IOException ");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ThirdPartyRechargeCallException("IOException ");
		}
		System.out.println("get data url " + response.toString());

		return response.toString();
	}

	public static void initializeCyberplateETransaction(
			TransactionTransportBean transactionTransportBean) {
		if (flag) {
			if (transactionTransportBean != null) {
				int pubkeys = 0;
				if (transactionTransportBean.getToken3() != null) {
					pubkeys = Integer.parseInt(transactionTransportBean
							.getToken3());
				}
				try {
					System.out.println("token 1 "
							+ transactionTransportBean.getToken());
					System.out.println("token 1 "
							+ transactionTransportBean.getToken1());
					System.out.println("token 1 "
							+ transactionTransportBean.getToken2());
					CyberPlateIntegrationUtil.init(
							transactionTransportBean.getToken(),
							transactionTransportBean.getToken1(),
							transactionTransportBean.getToken2(), pubkeys);
				} catch (Exception e) {
					flag = true;
					e.printStackTrace();
				}
			}
			flag = false;
		}
	}

	public static void main(String[] args) throws Exception {

	}

	public static String generateTransId(String param1, String param2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		StringBuilder builder = new StringBuilder();
		if (param1 != null) {
			builder.append(param1);
		}
		if (param2 != null) {
			builder.append(param2);
		}
		builder.append(formattedDate);
		return builder.toString();

	}

	private static String twoDecimalPlaceNumber(double amount) {
		DecimalFormat format = new DecimalFormat("0");
		return format.format(amount);
	}

	private StringBuilder getOTPRegistration(StringBuilder sessionVal,
			StringBuilder inputMessageTemp,
			TransactionTransportBean transactionTransport, List list) {

		inputMessageTemp.append("SESSION=").append(sessionVal).append("\n");
		inputMessageTemp.append("OP=").append(list.get(2)).append("\n");
		inputMessageTemp
				.append("emailId=")
				.append(transactionTransport.getRemittanceUserDto()
						.getEmailId()).append("\n");
		inputMessageTemp.append("SD=").append(list.get(0)).append("\n");
		inputMessageTemp.append("amountsend=").append(1).append("\n");
		inputMessageTemp
				.append("pinCode=")
				.append(transactionTransport.getRemittanceUserDto()
						.getPinCode()).append("\n");
		inputMessageTemp
				.append("middleName=")
				.append(transactionTransport.getRemittanceUserDto()
						.getMiddleName()).append("\n");
		inputMessageTemp.append("amount=").append(0.00).append("\n");
		inputMessageTemp.append("ACCEPT_KEYS=").append("40").append("\n");
		inputMessageTemp.append("NUMBER=")
				.append(transactionTransport.getMobileNo()).append("\n");
		inputMessageTemp.append("dop_kom=").append("0.00").append("\n");
		inputMessageTemp
				.append("lastName=")
				.append(transactionTransport.getRemittanceUserDto()
						.getLastName()).append("\n");
		inputMessageTemp.append("type=").append("0").append("\n");
		inputMessageTemp.append("STEP=").append("0").append("\n");
		inputMessageTemp.append("AP=").append(list.get(1)).append("\n");
		inputMessageTemp
				.append("dateOfBirth=")
				.append(transactionTransport.getRemittanceUserDto()
						.getDateOfBirth()).append("\n");
		inputMessageTemp
				.append("firstName=")
				.append(transactionTransport.getRemittanceUserDto()
						.getFirstName()).append("\n");
		return inputMessageTemp;
	}

	private StringBuilder enterOTPRegistration(StringBuilder sessionVal,
			StringBuilder inputMessageTemp,
			TransactionTransportBean transactionTransport,
			ArrayList<String> list) {
		inputMessageTemp.append("ACCEPT_KEYS=").append("40").append("\n");
		inputMessageTemp.append("NUMBER=")
				.append(transactionTransport.getMobileNo()).append("\n");
		inputMessageTemp
				.append("middleName=")
				.append(transactionTransport.getRemittanceUserDto()
						.getMiddleName()).append("\n");
		inputMessageTemp.append("dop_kom=").append("0.00").append("\n");
		inputMessageTemp
				.append("firstName=")
				.append(transactionTransport.getRemittanceUserDto()
						.getFirstName()).append("\n");
		inputMessageTemp.append("amount=").append(0.00).append("\n");
		inputMessageTemp
				.append("emailId=")
				.append(transactionTransport.getRemittanceUserDto()
						.getEmailId()).append("\n");

		inputMessageTemp.append("AP=").append(list.get(1)).append("\n");
		inputMessageTemp.append("OP=").append(list.get(2)).append("\n");
		inputMessageTemp
				.append("lastName=")
				.append(transactionTransport.getRemittanceUserDto()
						.getLastName()).append("\n");
		inputMessageTemp.append("STEP=").append("1").append("\n");
		inputMessageTemp
				.append("dateOfBirth=")
				.append(transactionTransport.getRemittanceUserDto()
						.getDateOfBirth()).append("\n");
		inputMessageTemp.append("smsPass=")
				.append(transactionTransport.getRemittanceUserDto().getOTP())
				.append("\n");
		inputMessageTemp.append("SD=").append(list.get(0)).append("\n");
		inputMessageTemp.append("amountsend=").append(1).append("\n");
		inputMessageTemp.append("SESSION=").append(sessionVal).append("\n");

		inputMessageTemp
				.append("pinCode=")
				.append(transactionTransport.getRemittanceUserDto()
						.getPinCode()).append("\n");
		inputMessageTemp.append("type=").append("0").append("\n");
		return inputMessageTemp;
	}
	
	private StringBuilder registrationRequestToProvider(
			StringBuilder sessionVal, StringBuilder inputMessageTemp,
			TransactionTransportBean transactionTransport,
			ArrayList<String> list) {
		inputMessageTemp.append("ACCEPT_KEYS=").append("40").append("\n");
		inputMessageTemp.append("NUMBER=")
				.append(transactionTransport.getMobileNo()).append("\n");
		inputMessageTemp
				.append("middleName=")
				.append(transactionTransport.getRemittanceUserDto()
						.getMiddleName()).append("\n");
		inputMessageTemp.append("dop_kom=").append("0.00").append("\n");
		inputMessageTemp
				.append("firstName=")
				.append(transactionTransport.getRemittanceUserDto()
						.getFirstName()).append("\n");
		inputMessageTemp.append("amount=").append(0.00).append("\n");
		inputMessageTemp
				.append("emailId=")
				.append(transactionTransport.getRemittanceUserDto()
						.getEmailId()).append("\n");

		inputMessageTemp.append("AP=").append(list.get(1)).append("\n");
		inputMessageTemp.append("OP=").append(list.get(2)).append("\n");
		inputMessageTemp
				.append("lastName=")
				.append(transactionTransport.getRemittanceUserDto()
						.getLastName()).append("\n");
		inputMessageTemp.append("STEP=").append("1").append("\n");
		inputMessageTemp
				.append("dateOfBirth=")
				.append(transactionTransport.getRemittanceUserDto()
						.getDateOfBirth()).append("\n");
		inputMessageTemp.append("smsPass=")
				.append(transactionTransport.getRemittanceUserDto().getOTP())
				.append("\n");
		inputMessageTemp.append("SD=").append(list.get(0)).append("\n");
		inputMessageTemp.append("amountsend=").append(1).append("\n");
		inputMessageTemp.append("SESSION=").append(sessionVal).append("\n");

		inputMessageTemp
				.append("pinCode=")
				.append(transactionTransport.getRemittanceUserDto()
						.getPinCode()).append("\n");
		inputMessageTemp.append("type=").append("0").append("\n");
		return inputMessageTemp;
	}

	private StringBuilder enterOTPReload(StringBuilder sessionVal,
			StringBuilder inputMessageTemp,
			TransactionTransportBean transactionTransport,
			ArrayList<String> list) {
		inputMessageTemp.append("SESSION=").append(sessionVal).append("\n");
		inputMessageTemp.append("OP=").append(list.get(2)).append("\n");
		inputMessageTemp.append("SD=").append(list.get(0)).append("\n");
		inputMessageTemp.append("amountsend=").append(11).append("\n");
		inputMessageTemp.append("amount=").append(0.00).append("\n");
		inputMessageTemp.append("ACCEPT_KEYS=").append("40").append("\n");
		inputMessageTemp.append("Details=").append("qqq").append("\n");
		inputMessageTemp.append("NUMBER=")
				.append(transactionTransport.getMobileNo()).append("\n");
		inputMessageTemp.append("dop_kom=").append("0.00").append("\n");
		inputMessageTemp.append("type=").append("3").append("\n");
		inputMessageTemp.append("STEP=").append("0").append("\n");
		inputMessageTemp.append("AP=").append(list.get(1)).append("\n");
		return inputMessageTemp;
	}

}
