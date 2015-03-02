package com.cfeindia.b2bserviceapp.thirdparty.recharge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Pattern;

import com.cfeindia.b2bserviceapp.thirdparty.recharge.model.BaseThirdPartyInput;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.model.EServiceDataItem;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.model.EServiceDataResponse;

public class URLConnectionReader {
	public static void main(String[] args) throws Exception {
		// String finalInput = getDataFromThirdParty();

		String finalInput = "1%$Reliance%$*%$All Circles%$%$%$0%$%$%$0%$0%$0%$<br>1%$Reliance%$1%$PUNJAB%$%$%$0%$%$%$0%$0%$0%$<br>1%$Reliance%$10%$UTTAR PRADESH EAST%$%$%$0%$%$%$0%$0%$0%$$$$";

		EServiceDataResponse eServiceDataResponse = new EServiceDataResponse();
		List<EServiceDataItem> eServiceDataList = eServiceDataResponse
				.getEserviceDataItem();
		
		String a[] = finalInput.split("<br>");
		for (String str : a) {
			EServiceDataItem eServiceDataItem = new EServiceDataItem();
			String[] sub = Pattern.compile("%$", Pattern.LITERAL).split(str);
			eServiceDataItem.setOperatorCode(sub[0]);
			eServiceDataItem.setOperatorName(sub[1]);
			eServiceDataItem.setCirCode(sub[2]);
			eServiceDataItem.setCirName(sub[3]);
			eServiceDataItem.setProduct(sub[4]);
			eServiceDataItem.setProductDescription(sub[5]);
			eServiceDataItem.setDomination(sub[6]);
			eServiceDataItem.setCustIdLabel(sub[7]);
			eServiceDataItem.setCustIdLength(sub[8]);
			eServiceDataItem.setTalkTime(sub[9]);
			eServiceDataItem.setAdminFee(sub[10]);
			eServiceDataItem.setServiceTax(sub[11]);
			if (sub.length == 13) {
				eServiceDataItem.setValidity(sub[12]);
			}
			//System.out.print(sub[3] + "  ");
			eServiceDataList.add(eServiceDataItem);
		}
		//System.out.println();
		for (EServiceDataItem eServiceDataItem2 : eServiceDataList) {
			//System.out.print(eServiceDataItem2.getCirName()  + "  ");
		}
		//System.out.println();

	}

	private static String getDataFromThirdParty() throws MalformedURLException,
			IOException {
		BaseThirdPartyInput baseThirdPartyInput = new BaseThirdPartyInput();
		baseThirdPartyInput.setLoginStatus("test");
		baseThirdPartyInput.setAgentId("1");
		baseThirdPartyInput.setAppver("3.38");
		String url = "http://220.226.204.98/mainlinkpos/purchase/pw_eservicedata.php3?loginstatus=";
		url = url + baseThirdPartyInput.getLoginStatus();
		url = url + "&agentid=";
		url = url + baseThirdPartyInput.getAgentId();
		url = url + "&appver=";
		url = url + baseThirdPartyInput.getAppver();
		//System.out.println(url);
		URL oracle = new URL(url);
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		StringBuilder inputLine = new StringBuilder();
		String input = "";
		while ((input = in.readLine()) != null) {
			inputLine.append(input);
		}
		in.close();
		//System.out.println();
		String finalInput = inputLine.toString();
		// //System.out.println(inputLine);
		return finalInput;
	}
}