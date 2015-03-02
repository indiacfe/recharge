package com.cfeindia.b2bserviceapp.service.recharge.mobile;

import com.cfeindia.b2bserviceapp.dto.recharge.mobile.MobileNumberDetails;

public class MobileOperatorDetailProviderServiceImpl implements
		MobileOperatorDetailProviderService {

	public MobileNumberDetails getAllDetailsForMobileNumber(String mobileNumber) {
		MobileNumberDetails mobileNumberDetails = new MobileNumberDetails();
		mobileNumberDetails.setMobileNumber(mobileNumber);
		return mobileNumberDetails;
	}

}
