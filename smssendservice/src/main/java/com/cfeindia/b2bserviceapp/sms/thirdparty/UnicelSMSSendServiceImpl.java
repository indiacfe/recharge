package com.cfeindia.b2bserviceapp.sms.thirdparty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class UnicelSMSSendServiceImpl implements SMSSendService {

	//http://api.unicel.in/SendSMS/sendmsg.php?uname=satyendr&pass=123456&send=Alerts&dest=919310858478&msg=TestingSMSAPI
	
	public SMSSendResponse sendSMS(SMSSendRequest smsSendRequest) {
		StringBuilder url = new StringBuilder(smsSendRequest.getApiUrl());
		url.append("?uname=").append(smsSendRequest.getUserName()).append("&pass=");
		url.append(smsSendRequest.getPassword()).append("&send=").append(smsSendRequest.getSenderId());
		url.append("&dest=").append(smsSendRequest.getDestination());
		url.append("&msg=").append(URLEncoder.encode(smsSendRequest.getMessage()));
		//System.out.println(url);
		SMSSendResponse smsSendResponse = new SMSSendResponse();
		smsSendResponse.setDestMobileNumber(smsSendRequest.getDestination());
		smsSendResponse.setMessage(smsSendRequest.getMessage());
		try {
			String inputLine = getDataFromURL(url);
			smsSendResponse.setResponseMessage(inputLine);
		} catch (MalformedURLException e) {
			smsSendResponse.setErrorCode("2");
			smsSendResponse.setErrorMessage(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			smsSendResponse.setErrorCode("2");
			smsSendResponse.setErrorMessage(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			smsSendResponse.setErrorCode("2");
			smsSendResponse.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		//System.out.println(smsSendResponse);
		return smsSendResponse;
	}
	
	private String getDataFromURL(StringBuilder url)
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
		return inputLine.toString();
	}
	
	public static void main(String[] args) {
		//http://api.unicel.in/SendSMS/sendmsg.php?uname=satyendr&pass=123456&send=Alerts&dest=919310858478&msg=TestingSMSAPI
		String finalInput ="http://api.unicel.in/SendSMS/sendmsg.php?uname=cyber123&pass=123456&send=cybrtl&dest=919650021874&msg=";
		SMSSendRequest smsSendRequest = new SMSSendRequest();
		smsSendRequest.setApiUrl("http://api.unicel.in/SendSMS/sendmsg.php");
		smsSendRequest.setUserName("cyber123");
		smsSendRequest.setPassword("satya123");
		smsSendRequest.setSenderId("cybrtl");
		smsSendRequest.setDestination("919650021874");
		smsSendRequest.setMessage(URLEncoder.encode("Cybertel Password has been reset Your New Password Is  ASDWQDVVVW1"));
		new UnicelSMSSendServiceImpl().sendSMS(smsSendRequest);
	}

}
