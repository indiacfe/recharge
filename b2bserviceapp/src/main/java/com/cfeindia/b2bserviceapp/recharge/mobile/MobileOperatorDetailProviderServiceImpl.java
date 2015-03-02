package com.cfeindia.b2bserviceapp.recharge.mobile;

import com.cfeindia.b2bserviceapp.recharge.mobile.dto.MobileNumberDetails;

public class MobileOperatorDetailProviderServiceImpl implements
		MobileOperatorDetailProviderService {

	public MobileNumberDetails getAllDetailsForMobileNumber(String mobileNumber) {
		MobileNumberDetails mobileNumberDetails = new MobileNumberDetails();
		mobileNumberDetails.setMobileNumber(mobileNumber);
		return mobileNumberDetails;
	}

}
