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
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ERegistrationRequestService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;

public class CyberplatERegistrationServiceImpl implements
		ERegistrationRequestService {

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
		initializeCyberplateETransaction(transportBean);
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

			if (!"0".equals(errorValue) || !"0".equals(resultVal)) {

				try {
					transportBean.setErrorCode(Integer.parseInt(errorValue));
				} catch (Exception e) {
					e.printStackTrace();
					transportBean.setErrorCode(2);
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
		if (connectURL.length > 1) {
			sessionVal.append(transactionTransport.getTransid().substring(13));
			finalInput = inputMessageCreation(transactionTransport, sessionVal,
					true);
			String outputMessage = getDataFromURL(connectURL[0], finalInput);
			int errorIndex = outputMessage.indexOf("ERROR=");
			int resultIndex = outputMessage.indexOf("RESULT=");
			String errorVal = "1";
			String resultVal = "1";
			if (errorIndex != -1 && resultIndex != -1) {
				errorVal = outputMessage.substring(errorIndex + 6,
						errorIndex + 8);
				resultVal = outputMessage.substring(resultIndex + 7,
						resultIndex + 9);
			}

			// https://in.cyberplat.com/cgi-bin/mm/mm_pay_check.cgi/212
			if ((resultVal != null && "0".equals(resultVal.trim()))
					&& (errorVal != null && "0".equals(errorVal.trim()))) {
				transactionTransport.setConnectURL(connectURL[1]);

				finalInput = inputMessageCreation(transactionTransport,
						sessionVal, false);
				outputMessage = getDataFromURL(
						transactionTransport.getConnectURL(), finalInput);
				// System.out.println("outputMessage:::" + outputMessage);
				String[] line = outputMessage.split("\n");
				String addInfo = "";
				for (String info : line) {
					if (info.contains("ADDINFO=")) {
						addInfo = info.substring(8);
						break;
					}
				}
				transactionTransport.setMessage(addInfo);

			} else {
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
		// System.out.println("sessionVal=" + sessionVal);
		StringBuilder inputMessageTemp = new StringBuilder();
		String data = transactionTransport.getAgentId();
		ArrayList<String> list = new ArrayList<String>();
		if (data != null) {
			StringTokenizer stringTokenizer = new StringTokenizer(data, ":");
			while (stringTokenizer.hasMoreTokens()) {
				list.add(stringTokenizer.nextToken());
			}
		}

		inputMessageTemp.append("SD=").append(list.get(0)).append("\n");
		inputMessageTemp.append("AP=").append(list.get(1)).append("\n");
		inputMessageTemp.append("OP=").append(list.get(2)).append("\n");
		inputMessageTemp.append("SESSION=").append(sessionVal).append("\n");
		if (transactionTransport.getTransactionName().equals("ICASH_RECHARGE")) {
			iCashRechargeUrl(inputMessageTemp, transactionTransport);
			isCommentRequired = false;
		} else if (transactionTransport.getTransactionName().equals(
				"MONEY_TRANSFER_USER_REGISTRATION")) {
			moneyTransferUserRegistration(inputMessageTemp,
					transactionTransport);
		} else if (transactionTransport.getTransactionName().equals(
				"ADD_BANK_DETAIL")) {
			moneyTransferAddBankDetail(inputMessageTemp, transactionTransport);
		} else if (transactionTransport.getTransactionName().equals(
				"GET_BANK_DETAIL")) {
			moneyTransferGetBankDetail(inputMessageTemp, transactionTransport);
		} else if (transactionTransport.getTransactionName().equals(
				"RELOAD_USER_ACCOUNT")) {
			moneyTransferUserReloadAccount(inputMessageTemp, transactionTransport);
		}

		else {
			inputMessageTemp.append("NUMBER=")
					.append(transactionTransport.getConnectionid())
					.append("\n");
			inputMessageTemp.append("ACCOUNT=").append("\n");
			if (transactionTransport.getTransactionName()
					.equals("DTH_RECHARGE")
					|| transactionTransport.getTransactionName().equals(
							"DATACARD_RECHARGE")) {
				inputMessageTemp.append("AMOUNT=")
						.append(transactionTransport.getAmount()).append("\n");
			} else {
				inputMessageTemp
						.append("AMOUNT=")
						.append(twoDecimalPlaceNumber(transactionTransport
								.getAmount())).append("\n");
			}
		}

		/*
		 * inputMessageTemp.append("AMOUNT_ALL=")
		 * .append(transactionTransport.getAmount()).append("\n");
		 */

		if (isCommentRequired) {
			// inputMessageTemp.append("PAY_TOOL=0").append("\n");
			// inputMessageTemp.append("REQ_TYPE=1").append("\n");
			inputMessageTemp
					.append("COMMENT=")
					.append("Retailer "
							+ transactionTransport.getFranchiseeMobileNum())
					.append("\n");
		}

		// System.out.println("Before sign in finalInput "
		// + inputMessageTemp.toString());

		finalInput = CyberPlateIntegrationUtil.signMessage(inputMessageTemp
				.toString());
		// System.out.println("After Signing finalInput=" + finalInput);
		finalInput = URLEncoder.encode(finalInput, "UTF-8");
		// System.out.println("After Encoding finalInput=" + finalInput);

		finalInput = "inputmessage=" + finalInput;
		return finalInput;
	}

	public String getDataFromURL(String url, String finalInput)
			throws MalformedURLException, ThirdPartyRechargeCallException {
		String inputLine = null;
		// System.out.println(url);
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
			// System.out.println("\nSending 'POST' request to URL : " + url);
			// System.out.println("Post parameters : " + finalInput);
			// System.out.println("Response Code : " + responseCode);

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
		// print result
		// System.out.println(response.toString());

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

		/*
		 * CyberplatETransactionServiceImpl cyberplateETransaction = new
		 * CyberplatETransactionServiceImpl(); String secretKeyLocation =
		 * "D:/CFE-Projects/b2bserviceapp/cyberplateintegration/cyberplatall/Kapi107838/secret.key"
		 * ; String secretKey = "satya@1234"; String publicKeyLocation =
		 * "D:/CFE-Projects/b2bserviceapp/cyberplateintegration/cyberplatall/Kapi107838/pubkeys.key"
		 * ; int pubKeys = 40; // 40
		 * CyberPlateIntegrationUtil.init(secretKeyLocation, secretKey,
		 * publicKeyLocation, pubKeys); String url =
		 * "https://in.cyberplat.com/cgi-bin/bu/bu_pay_check.cgi/239"; // SD
		 * 107611 // AP 107838 // OP 107839 // SD 1003829 // AP 1003851 // OP
		 * 1003852 MOB9650021874131009003710
		 * 
		 * TransactionTransportBean transactionTransport = new
		 * TransactionTransportBean(); transactionTransport.setAmount(50.0);
		 * transactionTransport.setRetailerId("100");
		 * transactionTransport.setMobileNo("01244240550");
		 * transactionTransport.setOperator("AIRTEL");
		 * transactionTransport.setOperatorCode("de");
		 * transactionTransport.setFranchiseeMobileNum("9650021874");
		 * transactionTransport.setTransactionName("MOBILE_RECHARGE");
		 * transactionTransport.setRechargeType("MOBILE_PREPAID");
		 * transactionTransport.setToken1("107611"); // SD
		 * transactionTransport.setToken2("107838"); // AP
		 * transactionTransport.setToken3("107839"); // OP
		 * transactionTransport.setTransid(generateTransId("MOB",
		 * "9650021874")); transactionTransport.setConnectURL(url);
		 * cyberplateETransaction.doTransaction(transactionTransport);
		 */
		// System.out.println(twoDecimalPlaceNumber(10.0));
		// String finalData =
		// "DATE=19.10.2013 16:51:03\nSESSION=10000000165055\nERROR=0\nRESULT=0\nTRANSID=1000253669075\n\nEND\nBEGIN SIGNATURE\niQCRAwkBAAAAKFJiax8BAWrjBAC6P+XFZiZplj1btzmQoIPkGeGS0SSY1oWEsATC\nxF5vQ76FRfP+0xBaKo9SChk8d1sSHeCbVeAd54gdVEAGw1pMyX0QGrY6l6s8uAqh\n0fjmnKDKVSueCCzaAMh/rf79nHS49QULVIo0mHTkrLj3IWmK0G6HhIZpOI3lURWO\nA5ez4LABxw==\n=SQni\nEND SIGNATURE";

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
		// Double val = Double.parseDouble(amount);
		DecimalFormat format = new DecimalFormat("0");
		return format.format(amount);
	}

	private StringBuilder iCashRechargeUrl(StringBuilder data,
			TransactionTransportBean bean) {
		data.append("USERNAME=").append(bean.getiCashRecharge().getUserName())
				.append("\n");
		data.append("PROOFDOCUMENTDETAILS=")
				.append(bean.getiCashRecharge().getDocumentDetail())
				.append("\n");
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		data.append("USERDATEOFBIRTH=")
				.append(sd.format(bean.getiCashRecharge().getDateOfBirth()))
				.append("\n");
		data.append("USERADDRESS=")
				.append(bean.getiCashRecharge().getAddress()).append("\n");
		data.append("PROOFDOCUMENTTYPE=")
				.append(bean.getiCashRecharge().getDocumentType()).append("\n");
		data.append("USERSTATE=").append(bean.getiCashRecharge().getState())
				.append("\n");
		data.append("USEREMAILID=")
				.append(bean.getiCashRecharge().getEmailId()).append("\n");
		data.append("USERMOTHERSMAIDENNAME=")
				.append(bean.getiCashRecharge().getMotherName()).append("\n");
		data.append("USERLASTNAME=")
				.append(bean.getiCashRecharge().getLastName()).append("\n");
		data.append("USERMIDDLENAME=")
				.append(bean.getiCashRecharge().getMiddleName()).append("\n");
		data.append("NUMBER=").append(bean.getMobileNo()).append("\n");
		data.append("AMOUNT=")
				.append(CyberTelUtil.twoDecimalPlaceNumber(bean.getAmount()))
				.append("\n");
		data.append("AMOUNT_ALL=")
				.append(CyberTelUtil.twoDecimalPlaceNumber(bean
						.getiCashRecharge().getAmountAll())).append("\n");
		data.append("COMMENT=").append(bean.getiCashRecharge().getComment())
				.append("\n");

		return data;
	}

	private StringBuilder moneyTransferUserRegistration(StringBuilder data,
			TransactionTransportBean bean) {
		data.append("emailId=")
				.append(bean.getRemittanceUserDto().getEmailId()).append("\n");
		data.append("amountsend=").append("1").append("\n");
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		data.append("pinCode=")
				.append(sd.format(bean.getRemittanceUserDto().getPinCode()))
				.append("\n");
		data.append("middleName=")
				.append(bean.getiCashRecharge().getMiddleName()).append("\n");
		data.append("amount=").append("0.00").append("\n");
		data.append("NUMBER=")
				.append(bean.getRemittanceUserDto().getMobileNO()).append("\n");
		data.append("dop_kom=").append("0.00").append("\n");
		data.append("lastName=")
				.append(bean.getRemittanceUserDto().getLastName()).append("\n");
		data.append("dateOfBirth=")
				.append(bean.getRemittanceUserDto().getDateOfBirth())
				.append("\n");
		if (bean.getRemittanceUserDto().getOTP() != null) {
			data.append("smsPass=")
					.append(bean.getRemittanceUserDto().getOTP()).append("\n");
		}
		data.append("firstName=").append(bean.getiCashRecharge().getUserName())
				.append("\n");

		return data;
	}

	private StringBuilder moneyTransferGetBankDetail(StringBuilder data,
			TransactionTransportBean bean) {
		data.append("amountsend=").append("1").append("\n");
		data.append("amount=").append("0.00").append("\n");
		data.append("NUMBER=")
				.append(bean.getRemittanceUserDto().getMobileNO()).append("\n");
		data.append("dop_kom=").append("0.00").append("\n");
		if (bean.getRemittanceUserDto().getOTP() != null) {
			data.append("smsPass=")
					.append(bean.getRemittanceUserDto().getOTP()).append("\n");
		}
		return data;
	}

	private StringBuilder moneyTransferAddBankDetail(StringBuilder data,
			TransactionTransportBean bean) {
		data.append("IFSCCode=")
				.append(bean.getRemittanceUserDto().getIFSCCode()).append("\n");
		data.append("amountsend=").append("1").append("\n");
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		data.append("nickName=")
				.append(sd.format(bean.getRemittanceUserDto().getNickName()))
				.append("\n");
		data.append("amount=").append("0.00").append("\n");
		data.append("NUMBER=")
				.append(bean.getRemittanceUserDto().getMobileNO()).append("\n");
		data.append("dop_kom=").append("0.00").append("\n");
		data.append("accountName=")
				.append(bean.getRemittanceUserDto().getAccountName())
				.append("\n");
		data.append("accountType=")
				.append(bean.getRemittanceUserDto().getAccountType())
				.append("\n");
		if (bean.getRemittanceUserDto().getOTP() != null) {
			data.append("smsPass=")
					.append(bean.getRemittanceUserDto().getOTP()).append("\n");
		}
		return data;
	}

	private StringBuilder moneyTransferUserReloadAccount(StringBuilder data,
			TransactionTransportBean bean) {
		data.append("IFSCCode=")
				.append(bean.getRemittanceUserDto().getIFSCCode()).append("\n");
		data.append("amountsend=").append("1").append("\n");
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		data.append("nickName=")
				.append(sd.format(bean.getRemittanceUserDto().getNickName()))
				.append("\n");
		data.append("amount=").append("0.00").append("\n");
		data.append("NUMBER=")
				.append(bean.getRemittanceUserDto().getMobileNO()).append("\n");
		data.append("dop_kom=").append("0.00").append("\n");
		data.append("accountName=")
				.append(bean.getRemittanceUserDto().getAccountName())
				.append("\n");
		data.append("accountType=")
				.append(bean.getRemittanceUserDto().getAccountType())
				.append("\n");
		if (bean.getRemittanceUserDto().getOTP() != null) {
			data.append("smsPass=")
					.append(bean.getRemittanceUserDto().getOTP()).append("\n");
		}
		return data;
	}

}
