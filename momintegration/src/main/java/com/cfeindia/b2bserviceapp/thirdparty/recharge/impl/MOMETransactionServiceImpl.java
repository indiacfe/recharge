package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Date;

import com.cfeindia.b2bserviceapp.apps.thirdparty.momapi.BillObjectsDHBVNBillPaymentResult;
import com.cfeindia.b2bserviceapp.apps.thirdparty.momapi.MOMResponseSerializer;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

public class MOMETransactionServiceImpl implements ETransactionRequestService {

	private static final String SUCCESS_CODE = "0";
	private static final String PENDING_CODE = "2";

	// private OperationClient operationClient = new OperationClient();
	// private ObjectFactory objectFactory = new ObjectFactory();

	public void doTransaction(TransactionTransportBean transportBean)
			throws MalformedURLException, IOException {
		String xmlResponse = null;
		// getElectricBillPayment(transportBean);
		if ("Electricity".equalsIgnoreCase(transportBean.getRechargeType())) {
			xmlResponse = getElectricBillPayment(transportBean);
			MOMResponseSerializer responseSerializer = new MOMResponseSerializer();
			xmlResponse = xmlResponse.replaceAll(" xmlns(?:.*?)?=\".*?\"", "")
					.replaceAll(" i:nil=\"true\"", "");
			BillObjectsDHBVNBillPaymentResult result = responseSerializer
					.deserialize(xmlResponse);
			// System.out.println(result.getResponseAction());
			// System.out.println(result.getResponseMessage());
			// System.out.println(result.getStatusCode());
			if (result != null) {
				if (0 == result.getStatusCode() || 2 == result.getStatusCode()) {
					if (result.getMMPLTransactionID() != null)
						transportBean.setThirdpartytransid(result
								.getMMPLTransactionID().toString());
					transportBean.setThirdPartyTransDateTime(new Date()
							.toString());
					transportBean.setThirdpartyoperatortransid(result
							.getOperatorTransactionID());
					transportBean.setResponsecode(String.valueOf(result
							.getStatusCode()));
					transportBean.setMessage(result.getResponseMessage());
				} else {

					transportBean.setErrorCode(2);
					transportBean.setErrorcode("ERROR");
					transportBean.setErrorMessage(result.getResponseMessage());
					if (!(result.getResponseMessage().contains("Insufficient")
							|| result.getResponseMessage().contains(
									"Invalid UserID")
							|| result.getResponseMessage()
									.contains("suspended") || result
							.getResponseMessage().contains("User")))
						transportBean.setMessage(result.getResponseMessage());

				}
			}
		} else {

			String finalData = getDataFromThirdParty(transportBean);
			if (finalData != null) {
				String a[] = finalData.split("~");
				if (a.length == 7) {
					if (SUCCESS_CODE.equalsIgnoreCase(a[0])
							|| PENDING_CODE.equalsIgnoreCase(a[0])) {
						transportBean.setThirdpartytransid(a[2]);
						transportBean.setThirdPartyTransDateTime(new Date()
								.toString());
						transportBean.setThirdpartyoperatortransid(a[3]);
						transportBean.setResponsecode(a[0]);
						transportBean.setMessage(a[5]);
					} else {
						transportBean.setErrorCode(2);
						transportBean.setErrorcode("ERROR");
						transportBean.setErrorMessage(a[5]);
						if (!(a[5].contains("Insufficient")
								|| a[5].contains("Invalid UserID")
								|| a[5].contains("suspended") || a[5]
									.contains("User")))
							transportBean.setMessage(a[5]);

					}
				} else {
					transportBean.setErrorCode(2);
					transportBean.setErrorcode("ERROR");
					transportBean.setMessage("Operator Error");
					transportBean.setErrorMessage(finalData);
				}
			} else {
				transportBean.setErrorCode(2);
				transportBean.setErrorcode("ERROR");
				transportBean.setMessage("Operator Error");
				transportBean.setErrorMessage(finalData);
			}
		}
	}

	private String getMOMRechargeType(String operator) {
		String momRechargeType = "Talktime";

		if (operator.toLowerCase().contains("validity")) {
			momRechargeType = "Validity";
		} else if (operator.toLowerCase().contains("special")) {
			momRechargeType = "SpecialRecharge";
		} else if (operator.toLowerCase().contains("talktime")) {
			momRechargeType = "TalkTime";
		}
		return momRechargeType;
	}

	/*
	 * http://180.179.67.72/momapitest_http/PrepaidRecharge.ashx?
	 * Auth_AuthenticationKey
	 * =E836261630434E4DBA74FBB04A55CD8DCFE8792C4CED47718C5046B63F5DC851&
	 * Auth_CustomerID=XXXXXXXX&Auth_Password=XXXXXXX
	 * &Auth_CompanyID=184&Amount=
	 * 10&ConsumerNumber=9167774825&RechargeType=Talktime&
	 * RequestID=TEST0001116&
	 * ServiceCode=MOBRECH&SPCode=REL&SSPCode=AL&TCFlag=true
	 */

	public String getElectricBillPayment(TransactionTransportBean requestInput)
			throws MalformedURLException, IOException {
		String finalInput = null;
		StringBuilder url = new StringBuilder(requestInput.getOperatorCode());
		// "http://180.179.67.72/UtilityWebAPI/WebServices/Electricity.svc/PayDHBVNBill");
		url.append("?CustomerID=").append(requestInput.getToken2());
		url.append("&CompanyID=").append(requestInput.getAgentId());
		url.append("&Password=").append(requestInput.getToken1());
		url.append("&KNumber=").append(requestInput.getConnectionid());
		url.append("&BillAmount=").append(
				removeTrailingZeros(requestInput.getAmount()));
		url.append("&ClientTransactionID=").append(
				requestInput.getTransid().substring(13));
		url.append("&ConsumerMobileNumber=").append(requestInput.getMobileNo());
		url.append("&AuthenticationKey=").append(requestInput.getToken3());
		// "FDCFB935C64D45F5B934EAA31236961B");
		url.append("&MGLCANumber=").append(requestInput.getConnectionid());
		url.append("&CANumber=").append(requestInput.getConnectionid());
		url.append("&AccountNumber=").append(requestInput.getConnectionid());
		url.append("&BestAccountNumber=")
				.append(requestInput.getConnectionid());
		url.append("&RelianceEnergyCANumber=").append(
				requestInput.getConnectionid());
		url.append("&RelianceEnergyCANumber=").append(
				requestInput.getConnectionid());
		// System.out.println(url);
		StringBuilder inputLine = getDataFromURL(url);
		finalInput = inputLine.toString();

		return finalInput;
	}

	private String getDataFromThirdParty(TransactionTransportBean requestInput)
			throws MalformedURLException, IOException {
		String finalInput = null;

		StringBuilder url = new StringBuilder(requestInput.getConnectURL());
		url.append("?Auth_AuthenticationKey=").append(requestInput.getToken());
		url.append("&Auth_CustomerID=").append(requestInput.getToken2());
		url.append("&Auth_Password=").append(requestInput.getToken1());
		url.append("&Auth_CompanyID=").append(requestInput.getAgentId());
		url.append("&Amount=")
				.append(removeTrailingZeros(Double.valueOf(requestInput
						.getRecharge())));
		if ("MOBILE_RECHARGE".equalsIgnoreCase(requestInput
				.getTransactionName())) {
			url.append("&ConsumerNumber=").append(requestInput.getMobileNo());
		} else {
			url.append("&ConsumerNumber=").append(
					requestInput.getConnectionid());
		}
		url.append("&RechargeType=").append(
				getMOMRechargeType(requestInput.getOperator()));

		StringBuilder requestID = new StringBuilder(
				requestInput.getRetailerId());
		int length = requestInput.getRetailerId().length();
		if (length < 10) {
			for (int i = 0; i < 8 - requestInput.getRetailerId().length(); i++) {
				requestID.append("0");
			}
		}
		requestID.append(requestInput.getTransid().substring(13));

		url.append("&RequestID=").append(requestID);
		url.append("&ServiceCode=").append(
				MOMUtil.getMOMRechargeServiceCode(requestInput
						.getRechargeType()));
		url.append("&SPCode=").append(requestInput.getOperatorCode());
		if (requestInput.getAirtelUserName() != null) {
			url.append("&SSPCode=").append(requestInput.getAirtelUserName());
		} else {
			url.append("&SSPCode=AL");
		}

		url.append("&TCFlag=").append("true");
		// System.out.println(url);
		// StringBuilder inputLine = new StringBuilder();
		StringBuilder inputLine = getDataFromURL(url);
		finalInput = inputLine.toString();

		return finalInput;

	}

	private StringBuilder getDataFromURL(StringBuilder url)
			throws MalformedURLException, IOException {
		StringBuilder inputLine = new StringBuilder();
		//System.out.println(url);
		URL oracle = new URL(url.toString());
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));

		String input1 = "";
		while ((input1 = in.readLine()) != null) {
			inputLine.append(input1);
		}
		in.close();
		// System.out.println(inputLine);
		return inputLine;
	}

	private static String twoDecimalPlaceNumber(double amount) {
		// Double val = Double.parseDouble(amount);
		DecimalFormat format = new DecimalFormat(SUCCESS_CODE);
		return format.format(amount);
	}

	private static String removeTrailingZeros(double d) {
		return String.valueOf(d).replaceAll("[0-9]*$", "").replaceAll(".$", "");
	}

	/*
	 * public void doTransaction(TransactionTransportBean transportBean) throws
	 * MalformedURLException { BoIncomingParametersRecharge rechargeRequest =
	 * objectFactory.createBoIncomingParametersRecharge();
	 * rechargeRequest.setAmount
	 * (Double.valueOf(removeTrailingZeros(transportBean.getAmount())));
	 * 
	 * BoAuthHeader boAuthHeader = createAuthHeader(transportBean.getAgentId(),
	 * transportBean.getToken(), transportBean.getToken1(),
	 * transportBean.getToken2());
	 * rechargeRequest.setAuthenticationHeader(objectFactory
	 * .createBoAuthHeader(boAuthHeader)); if(transportBean.getMobileNo()!=null)
	 * rechargeRequest.setConsumerNumber(objectFactory.
	 * createBoIncomingParametersRechargeConsumerNumber
	 * (transportBean.getMobileNo())); else
	 * rechargeRequest.setConsumerNumber(objectFactory
	 * .createBoIncomingParametersRechargeConsumerNumber
	 * (transportBean.getConnectionid()));
	 * rechargeRequest.setRechargeType(objectFactory
	 * .createBoIncomingParametersRechargeRechargeType
	 * (getMOMRechargeType(transportBean.getOperator())));
	 * rechargeRequest.setRequestID
	 * (objectFactory.createBoIncomingParametersRechargeRequestID
	 * (transportBean.getTransid()));
	 * rechargeRequest.setServiceCode(objectFactory
	 * .createBoIncomingParametersRechargeServiceCode
	 * (MOMUtil.getMOMRechargeServiceCode(transportBean.getRechargeType())));
	 * rechargeRequest
	 * .setSPCode(objectFactory.createBoIncomingParametersRechargeSPCode
	 * (transportBean.getOperatorCode()));
	 * 
	 * BoOutputParametersRecharge boOutputParametersRecharge = operationClient
	 * .getBasicHttpBindingIOperationClient().prepaidRecharge( rechargeRequest);
	 * 
	 * if(boOutputParametersRecharge.getStatusCode() != null &&
	 * SUCCESS_CODE.equalsIgnoreCase
	 * (boOutputParametersRecharge.getStatusCode().getValue())) {
	 * transportBean.setResponsecode
	 * (boOutputParametersRecharge.getStatusCode().getValue());
	 * transportBean.setThirdPartyTransDateTime(new Date().toString());
	 * 
	 * if(boOutputParametersRecharge.getMMPLTransactionID()!=null)
	 * transportBean.
	 * setThirdpartytransid(boOutputParametersRecharge.getMMPLTransactionID
	 * ().getValue());
	 * if(boOutputParametersRecharge.getOperatorTransactionID()!=null)
	 * transportBean.setThirdpartyoperatortransid(boOutputParametersRecharge.
	 * getOperatorTransactionID().getValue());
	 * if(boOutputParametersRecharge.getResponseMessage()!=null)
	 * transportBean.setMessage
	 * (boOutputParametersRecharge.getResponseMessage().getValue());
	 * 
	 * } else { transportBean.setErrorCode(2);
	 * if(boOutputParametersRecharge.getResponseMessage()!=null) {
	 * transportBean.
	 * setErrorMessage(boOutputParametersRecharge.getResponseMessage
	 * ().getValue());
	 * if(!(boOutputParametersRecharge.getResponseMessage().getValue
	 * ().contains("Insufficient") ||
	 * boOutputParametersRecharge.getResponseMessage
	 * ().getValue().contains("Invalid UserID") ||
	 * boOutputParametersRecharge.getResponseMessage
	 * ().getValue().contains("suspended") ||
	 * boOutputParametersRecharge.getResponseMessage
	 * ().getValue().contains("User") ))
	 * transportBean.setMessage(boOutputParametersRecharge
	 * .getResponseMessage().getValue()); }
	 * if(boOutputParametersRecharge.getStatusCode() != null)
	 * transportBean.setResponsecode
	 * (boOutputParametersRecharge.getStatusCode().getValue());
	 * 
	 * }
	 * 
	 * }
	 * 
	 * private BoAuthHeader createAuthHeader(String agentId, String token,
	 * String token1, String token2) { BoAuthHeader boAuthHeader =
	 * objectFactory.createBoAuthHeader();
	 * boAuthHeader.setAuthenticationKey(objectFactory
	 * .createBoAuthHeaderAuthenticationKey(token));
	 * boAuthHeader.setPassword(objectFactory
	 * .createBoAuthHeaderPassword(token1));
	 * boAuthHeader.setUserCompanyID(objectFactory
	 * .createBoAuthHeaderUserCompanyID(agentId));
	 * boAuthHeader.setUserCustomerID(objectFactory
	 * .createBoAuthHeaderUserCustomerID(token2)); return boAuthHeader; }
	 */

	public static void main(String[] args) {
		String xml = "<BillObjects.DHBVNBillPaymentResult xmlns=\"http://schemas.datacontract.org/2004/07/MoneyOnMobile.BEST.WebAPI_V2.BusinessObjects\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\"><BillAmount i:nil=\"true\"/><ClientTransactionID i:nil=\"true\">sadfagasdgasgda</ClientTransactionID><CurrentBalance i:nil=\"true\"/><KNumber i:nil=\"true\"/><MMPLTransactionID i:nil=\"true\"/><OperatorTransactionID i:nil=\"true\"/><ResponseAction>The transaction is failed. Please don't debit the customer account.</ResponseAction><ResponseMessage>Transaction ID has been already used. Please try with different transaction ID.</ResponseMessage><StatusCode>1</StatusCode></BillObjects.DHBVNBillPaymentResult>";
		xml = xml.replaceAll(" xmlns(?:.*?)?=\".*?\"", "").replaceAll(
				" i:nil=\"true\"", "");
		// System.out.println(xml);
		MOMResponseSerializer responseSerializer = new MOMResponseSerializer();
		BillObjectsDHBVNBillPaymentResult result = responseSerializer
				.deserialize(xml);
		System.out.println(result.getBillAmount());
		System.out.println(result.getClientTransactionID());
		System.out.println(result.getCurrentBalance());
		System.out.println(result.getKNumber());
		System.out.println(result.getOperatorTransactionID());
		System.out.println(result.getResponseAction());
		System.out.println(result.getResponseMessage());
		System.out.println(result.getStatusCode());

		BillObjectsDHBVNBillPaymentResult result1 = new BillObjectsDHBVNBillPaymentResult();
		/*
		 * result1.setBillAmount("100");
		 * result1.setClientTransactionID("asdsafdaf");
		 * result1.setCurrentBalance(10.0f); result1.setKNumber("123456677");
		 */
		System.out.println(responseSerializer.serialize(result1));
	}

}
