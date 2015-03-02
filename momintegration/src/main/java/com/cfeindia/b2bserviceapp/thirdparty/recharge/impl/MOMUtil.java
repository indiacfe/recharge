package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.util.HashMap;
import java.util.Map;

public class MOMUtil {
	
	private static Map<String, String> rechargeServiceCode = new HashMap<String, String>();
	
	static {
		rechargeServiceCode.put("MOBILE_PREPAID","MOBRECH");
		rechargeServiceCode.put("DATACARD","MOBRECH");
		rechargeServiceCode.put("MOBILE_POSTPAID","UTILBILLPAY");
		rechargeServiceCode.put("DTH","DTHRECH");		
		rechargeServiceCode.put("ELECTRICITY","UTILBILLPAY");
		rechargeServiceCode.put("GAS","UTILBILLPAY");
		rechargeServiceCode.put("LANDLINE","UTILBILLPAY");
		rechargeServiceCode.put("INSURANCE","UTILBILLPAY");
		
	}
	
	
	public static String getMOMRechargeServiceCode(String rechargeType) {
		return rechargeServiceCode.get(rechargeType);
	}
}
