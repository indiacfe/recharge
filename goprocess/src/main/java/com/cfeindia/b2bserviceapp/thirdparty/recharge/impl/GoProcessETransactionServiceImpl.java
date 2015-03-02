package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jackson.map.ObjectMapper;

import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.model.GoprocessRechargeTransactionJSonResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public class GoProcessETransactionServiceImpl implements
		ETransactionRequestService {

	public void doTransaction(TransactionTransportBean transportBean)
			throws MalformedURLException, IOException {
		GoprocessRechargeTransactionJSonResponse finalData = getDataFromThirdParty(transportBean);
		if (finalData != null && ("SUCCESS".equalsIgnoreCase(finalData.getStatus()) || "PENDING".equalsIgnoreCase(finalData.getStatus()))) {
			transportBean.setTransid(finalData.getMerchantTransId());
	        transportBean.setThirdpartytransid(finalData.getGoProcessId());
	        transportBean.setThirdPartyTransDateTime(finalData.getDatetime());
	        transportBean.setResponsecode(finalData.getResponseCode());
	        transportBean.setMessage(finalData.getStatus());
	        transportBean.setThirdpartyoperatortransid(finalData.getOperatorTransactionId());
	        
	        if("PENDING".equalsIgnoreCase(finalData.getStatus())) {
		        transportBean.setErrorcode(finalData.getErrorCode());
		        transportBean.setErrorMessage(finalData.getErrorMessage());
	        }
		} else if (finalData != null && finalData.getErrorCode() !=null) {
			transportBean.setErrorCode(2);
			transportBean.setErrorcode(finalData.getErrorCode());
			transportBean.setMessage(finalData.getErrorMessage());
			transportBean.setErrorMessage(finalData.getErrorMessage());

		} else if(finalData == null){
			transportBean.setErrorCode(2);
			transportBean.setErrorcode("FAILED");
			transportBean.setMessage("Could not recharge.");
			transportBean.setErrorMessage("Could not recharge.");
			
		} else {
			transportBean.setErrorCode(2);
			transportBean.setErrorcode("FAILED");
			transportBean.setMessage("System Error.");
			transportBean.setErrorMessage("System Error.");
		}
		
	}
	

	private GoprocessRechargeTransactionJSonResponse getDataFromThirdParty(TransactionTransportBean requestInput)
			throws MalformedURLException, IOException {
		/*
		 * http://letitgo.asia/api/serviceTrans.go?goid=5011016023&apikey=9tp3I25pwfwlY24
		 * &rtype=json&service_family=1&apimode=test&msisdn=9717131580&amount=10&client_trans_id=SA12&operator_code=1
		 */
		StringBuilder url = new StringBuilder();
		url.append(requestInput.getConnectURL());
		url.append("?goid=").append(requestInput.getAgentId());		
		if("MOBILE_RECHARGE".equalsIgnoreCase(requestInput.getTransactionName())) {
			url.append("&msisdn=").append(requestInput.getMobileNo());
			url.append("&service_family=1");
			url.append("&apikey=").append(requestInput.getToken1());
		} else if("DTH_RECHARGE".equalsIgnoreCase(requestInput.getTransactionName())) {
			url.append("&custid=").append(requestInput.getConnectionid());
			url.append("&service_family=2");
			url.append("&apikey=").append(requestInput.getToken2());
		} else if("DATACARD_RECHARGE".equalsIgnoreCase(requestInput.getTransactionName())) {
			url.append("&msisdn=").append(requestInput.getConnectionid());
			url.append("&service_family=3");
			url.append("&apikey=").append(requestInput.getToken3());
		} else if("POSTPAID_BILL".equalsIgnoreCase(requestInput.getTransactionName())) {
			url.append("&msisdn=").append(requestInput.getConnectionid());
			url.append("&service_family=1");
			url.append("&apikey=").append(requestInput.getToken1());
		}	
		url.append("&apimode=").append(requestInput.getLoginStatus());
		url.append("&amount=").append(removeTrailingZeros(Double.valueOf(requestInput.getRecharge())));		
		url.append("&client_trans_id=").append(requestInput.getTransid());		
		url.append("&operator_code=").append(requestInput.getOperatorCode());		
		url.append("&rtype=").append("json");
		//System.out.println(url);
		ObjectMapper obj = new ObjectMapper();
		GoprocessRechargeTransactionJSonResponse jSonResponse = obj.readValue(getDataFromURL(url).toString(), GoprocessRechargeTransactionJSonResponse.class);
		//System.out.println(jSonResponse);
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
		//System.out.println("output from goprocess " + inputLine);
		return inputLine;
	}

	private static String removeTrailingZeros(double d) {
		  return String.valueOf(d).replaceAll("[0]*$", "").replaceAll(".$", "");
		}
}
