package com.cfeindia.b2bserviceapp.service.franchisee;

import com.cfeindia.b2bserviceapp.entity.SmsRechargeDetail;

public interface SmsPayService {
	public String validateUser(String username);

	public String validateMessage(String msg);

	public void saveSmsDetails(SmsRechargeDetail smsRechargeDetail);
	public String validateMessageLevel2(String msg, String rechargeType);
	

}
