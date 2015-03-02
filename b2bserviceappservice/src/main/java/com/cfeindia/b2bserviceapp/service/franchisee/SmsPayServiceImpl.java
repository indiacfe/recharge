package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.entity.SmsRechargeDetail;
import com.cfeindia.b2bserviceapp.transport.bean.SmsOperatorBean;

@Service
@Transactional
public class SmsPayServiceImpl implements SmsPayService {
	private static Map<Integer, SmsOperatorBean> operators = new HashMap<Integer, SmsOperatorBean>();
	@Autowired
	private CommonDao commonDao;

	public void saveSmsDetails(SmsRechargeDetail smsRechargeDetail) {

		commonDao.saveEntity(smsRechargeDetail);
	}

	public String validateUser(String username) {

		String result = "FAIL";
		Integer userId = commonDao.getUserId(username);
		if (userId != null) {
			String userRole = commonDao.checkTypeOfUser(userId.toString());

			if (CommonConstants.ROLE_FRANCHISEE.equalsIgnoreCase(userRole)) {
				result = CommonConstants.SUCCESS;
			} else {
				result = "FAIL";
			}
		} else {
			result = "FAIL";
		}
		return result;
	}

	public String validateMessage(String msg) {

		String result = "FAIL";
		String[] msgValue = msg.split("\\.");
		Integer opcode = Integer.parseInt(msgValue[0]);
		if (operators.containsKey(opcode)
				&& Float.parseFloat(msgValue[2]) <= 5000) {
			result = CommonConstants.SUCCESS;
		} else {
			result = "FAIL";
		}

		return result;
	}

	public String validateMessageLevel2(String msg, String rechargeType) {

		String result = "FAIL";
		String[] msgValue = msg.split("\\.");
		if ("MOBILE_PREPAID".equalsIgnoreCase(rechargeType)
				|| "MOBILE_POSPAID".equalsIgnoreCase(rechargeType)) {
			if (msgValue[1].length() == 10)
				result = CommonConstants.SUCCESS;
		} else if (!"MOBILE_PREPAID".equalsIgnoreCase(rechargeType)) {
			if (msgValue[1].length() >= 6 && msgValue[1].length() <= 12)
				result = CommonConstants.SUCCESS;
		} else {
			result = "FAIL";
		}

		return result;
	}

	public static Map<Integer, SmsOperatorBean> setValues() {

		// dishtv.setOperatorName("DISH TV");
		// dishtv.setRechargeType("DTH");
		operators.put(11, new SmsOperatorBean("AIRTEL", "MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(12, new SmsOperatorBean("AIRCEL", "MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(13, new SmsOperatorBean("BSNL - SPECIAL TARIFF",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(14, new SmsOperatorBean("BSNL - TALKTIME",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(15, new SmsOperatorBean("IDEA", "MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(16, new SmsOperatorBean("LOOP MOBILE", "MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(17, new SmsOperatorBean("MTNL DELHI SPECIAL",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(18, new SmsOperatorBean("MTNL DELHI TALKTIME",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(19, new SmsOperatorBean("MTNL MUMBAI SPECIAL",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(20, new SmsOperatorBean("MTNL MUMBAI TALKTIME",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(21, new SmsOperatorBean("MTS", "MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(22, new SmsOperatorBean("T24 MOBILE-SPECIAL TARIFF",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(23, new SmsOperatorBean("T24 MOBILE-TALKTIME",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(24, new SmsOperatorBean("TATA DOCOMO CDMA",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(25, new SmsOperatorBean("TATA DOCOMO GSM-SPECIAL T",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(26, new SmsOperatorBean("TATA DOCOMO GSM-TALKTIME",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(27, new SmsOperatorBean("UNINOR-SPECIAL TARIFF",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(28, new SmsOperatorBean("UNINOR-TALKTIME",
				"MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(29, new SmsOperatorBean("VODAFONE", "MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators
				.put(30, new SmsOperatorBean("RELIANCE GSM", "MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(31, new SmsOperatorBean("TATA_WALKY", "MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(32,
				new SmsOperatorBean("NORTON MOBILE", "MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(33,
				new SmsOperatorBean("RELIANCE CDMA", "MOBILE_PREPAID", "MOBILE_RECHARGE"));
		operators.put(34, new SmsOperatorBean("DISH TV", "DTH", "DTH_RECHARGE"));
		operators.put(35, new SmsOperatorBean("RELIANCE DIGITAL TV", "DTH", "DTH_RECHARGE"));
		operators.put(36, new SmsOperatorBean("SUN DIRECT", "DTH", "DTH_RECHARGE"));
		operators.put(37, new SmsOperatorBean("VIDEOCON D2H", "DTH", "DTH_RECHARGE"));
		operators.put(38, new SmsOperatorBean("AIRTEL DIGITAL TV", "DTH", "DTH_RECHARGE"));
		operators.put(39, new SmsOperatorBean("TATA SKY", "DTH", "DTH_RECHARGE"));
		operators.put(40, new SmsOperatorBean("Reliance NetConnect 3G",
				"DATACARD", "DATACARD_RECHARGE"));
		operators.put(41, new SmsOperatorBean("Reliance NetConnect+",
				"DATACARD", "DATACARD_RECHARGE"));
		operators.put(42,
				new SmsOperatorBean("Reliance NetConnect", "DATACARD", "DATACARD_RECHARGE"));
		operators.put(43, new SmsOperatorBean("MTS MBlaze", "DATACARD", "DATACARD_RECHARGE"));
		operators.put(44, new SmsOperatorBean("MTS MBrowse", "DATACARD", "DATACARD_RECHARGE"));
		operators.put(45, new SmsOperatorBean("Tata Photon+", "DATACARD", "DATACARD_RECHARGE"));
		operators.put(46, new SmsOperatorBean("Tata Photon Whiz", "DATACARD", "DATACARD_RECHARGE"));
		operators.put(47, new SmsOperatorBean("Vodafone 3G", "DATACARD", "DATACARD_RECHARGE"));
		operators.put(48, new SmsOperatorBean("Aircel Pocket Internet",
				"DATACARD", "DATACARD_RECHARGE"));
		operators.put(49, new SmsOperatorBean("BSNL", "DATACARD", "DATACARD_RECHARGE"));

		operators.put(50, new SmsOperatorBean("AIRTEL", "MOBILE_POSTPAID", "POSTPAID_BILL"));
		operators.put(51, new SmsOperatorBean("IDEA", "MOBILE_POSTPAID", "POSTPAID_BILL"));
		operators.put(52, new SmsOperatorBean("RELIANCE CDMA",
				"MOBILE_POSTPAID", "POSTPAID_BILL"));
		operators.put(53,
				new SmsOperatorBean("RELIANCE GSM", "MOBILE_POSTPAID", "POSTPAID_BILL"));
		operators.put(54, new SmsOperatorBean("TATA DOCOMO CDMA",
				"MOBILE_POSTPAID", "POSTPAID_BILL"));
		operators.put(55, new SmsOperatorBean("TATA DOCOMO GSM",
				"MOBILE_POSTPAID", "POSTPAID_BILL"));
		operators.put(56, new SmsOperatorBean("VODAFONE", "MOBILE_POSTPAID", "POSTPAID_BILL"));

		return operators;

	}
}
