package com.cfeindia.b2bserviceapp.converter;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.AirTelUserDetail;
import com.cfeindia.b2bserviceapp.model.distributor.Registration;

public class RegistrationDtoToAirtelEntityConverter implements
		DtoToEntityConverter<Registration, AirTelUserDetail> {

	@Override
	public AirTelUserDetail convert(Registration registration) {
		AirTelUserDetail airTelUserDetail = new AirTelUserDetail();
		airTelUserDetail.setAirtelUserName(registration.getUserName());
		airTelUserDetail.setStoreCode(registration.getDocumentDetail());
		airTelUserDetail.setMobileNumber(registration.getMobileNo());
		airTelUserDetail.setAddress(registration.getPermanentAddress());
		airTelUserDetail.setCircleName(registration.getDocumentType());
		airTelUserDetail.setStateName(registration.getState());
		airTelUserDetail.setCityName(registration.getDistrict());
		airTelUserDetail.setPinCode(registration.getPincode());
		airTelUserDetail.setWalletName(CommonConstants.AIRTEL_WALLET_NAME);
		airTelUserDetail.setStatus(CommonConstants.AIRTEL_USER_CONFIGURED);
		return airTelUserDetail;
	}

}
