package com.cfeindia.b2bserviceapp.recharge.mobile;

import com.cfeindia.b2bserviceapp.recharge.mobile.dto.MobileNumberDetails;

public interface MobileOperatorDetailProviderService {
	public MobileNumberDetails getAllDetailsForMobileNumber(String mobileNumber);
}
