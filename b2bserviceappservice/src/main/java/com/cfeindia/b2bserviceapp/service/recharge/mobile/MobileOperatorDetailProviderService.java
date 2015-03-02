package com.cfeindia.b2bserviceapp.service.recharge.mobile;

import com.cfeindia.b2bserviceapp.dto.recharge.mobile.MobileNumberDetails;

public interface MobileOperatorDetailProviderService {
	public MobileNumberDetails getAllDetailsForMobileNumber(String mobileNumber);
}
