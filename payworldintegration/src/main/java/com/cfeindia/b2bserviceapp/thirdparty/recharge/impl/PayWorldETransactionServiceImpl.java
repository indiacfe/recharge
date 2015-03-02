package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public class PayWorldETransactionServiceImpl implements
		ETransactionRequestService {

	public void doTransaction(
			TransactionTransportBean transportBean)
			throws MalformedURLException, IOException {
		String finalData = getDataFromThirdParty(transportBean);
		//System.out.println("Final Data " + finalData);
		if (finalData != null && finalData.contains("ERROR") ) {

			String a[] = finalData.split(":");
			transportBean.setErrorCode(2);
			if(a.length==4) {
				transportBean.setErrorcode(a[1]);
				transportBean.setMessage(a[3]);
				transportBean.setErrorMessage(a[3]);
			} else {
				transportBean.setErrorcode("ERROR");
				transportBean.setMessage(a[0]);
				transportBean.setErrorMessage(a[0]);
			} 

		} else if (finalData != null && finalData.contains("PENDING")) {
			Pattern p = Pattern.compile("%$", Pattern.LITERAL);
	        String[] a = p.split(finalData);
	        if(a.length == 5) {
		        transportBean.setThirdpartytransid(a[1]);
		        transportBean.setThirdPartyTransDateTime(a[2]);
		        transportBean.setResponsecode(a[3]);
		        transportBean.setMessage(a[4]);
	        } else {
	        	transportBean.setErrorCode(2);
				transportBean.setErrorcode("ERROR");
				transportBean.setMessage(a[0]);
				transportBean.setErrorMessage(a[0]);
			} 
		} else if (finalData != null) {
			Pattern p = Pattern.compile("%$", Pattern.LITERAL);
	        String[] a = p.split(finalData);
	        if(a.length == 5) {
		        transportBean.setThirdpartytransid(a[1]);
		        transportBean.setThirdPartyTransDateTime(a[2]);
		        transportBean.setResponsecode(a[3]);
		        transportBean.setMessage(a[4]);
	        } else {
	        	transportBean.setErrorCode(2);
				transportBean.setErrorcode("ERROR");
				transportBean.setMessage(a[0]);
				transportBean.setErrorMessage(a[0]);
			} 
		} else {
			transportBean.setErrorCode(2);
			transportBean.setErrorcode("ERROR");
			transportBean.setErrorMessage("No Message from Operator");
		}


	}

	private String getDataFromThirdParty(TransactionTransportBean requestInput)
			throws MalformedURLException, IOException {
		StringBuilder url = new StringBuilder(requestInput.getConnectURL());
		url.append("?loginstatus=").append(requestInput.getLoginStatus());
		// loginstatus=test&agentid=1&retailerid=123&transid=5657&operatorcode=7&circode=5&mobileno=9818631670&recharge=10&appver=3.38
		url.append("&agentid=").append(requestInput.getAgentId());
		url.append("&retailerid=").append(requestInput.getAgentId());
		//url.append("&retailerid=").append(requestInput.getRetailerId());
		url.append("&transid=").append(requestInput.getTransid());
		url.append("&operatorcode=").append(requestInput.getOperatorCode());
		if("MOBILE_RECHARGE".equalsIgnoreCase(requestInput.getTransactionName())) {
			url.append("&mobileno=").append(requestInput.getMobileNo());
			url.append("&circode=").append(requestInput.getCircleCode());
		} else {
			url.append("&custid=").append(requestInput.getConnectionid());
			url.append("&circode=").append("*");
		}		
		//url.append("&connectionid=").append(requestInput.getConnectionid());
		url.append("&recharge=").append(removeTrailingZeros(Double.valueOf(requestInput.getRecharge())));
		url.append("&appver=").append(requestInput.getAppver());
		//System.out.println(url);
		// StringBuilder inputLine = new StringBuilder();
		StringBuilder inputLine = getDataFromURL(url);
		String finalInput = inputLine.toString();
		// String finalInput =
		//"mob9818631670130827003300%$A01000000000000000137754377597788900%$2013-08-27 00:32:55%$A14.SUCCESS%$Successfully Completed the Transaction$$$";
		// //System.out.println(inputLine);
		return finalInput;
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
		////System.out.println();
		return inputLine;
	}
	
	public static void main(String[] args) {
		String finalInput ="1234%$A01000000000000000137759132297303600%$2013-08-27 13:45:22%$A14.SUCCESS%$Successfully Completed the Transaction$$$";
		Pattern p = Pattern.compile("%$", Pattern.LITERAL);
        String[] a = p.split(finalInput);
		//System.out.println(a[0]);
		//System.out.println(a[1]);
		//System.out.println(a[2]);
		//System.out.println(a[3]);
		//System.out.println(a[4]);
		
		//System.out.println(removeTrailingZeros(1234.23432400000));
		//System.out.println(removeTrailingZeros(12.0));

	}
	
	private static String removeTrailingZeros(double d) {
		  return String.valueOf(d).replaceAll("[0]*$", "").replaceAll(".$", "");
		}

}
