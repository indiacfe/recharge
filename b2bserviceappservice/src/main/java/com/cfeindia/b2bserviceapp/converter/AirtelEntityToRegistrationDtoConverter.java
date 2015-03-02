package com.cfeindia.b2bserviceapp.converter;

import com.cfeindia.b2bserviceapp.entity.AirTelUserDetail;
import com.cfeindia.b2bserviceapp.model.distributor.Registration;

public class AirtelEntityToRegistrationDtoConverter implements
		EntityToDTOConverter<AirTelUserDetail, Registration> {

	@Override
	public Registration convert(AirTelUserDetail airTelUserDetail) {
		Registration registration = new Registration();
		registration.setUserName(airTelUserDetail.getAirtelUserName());
		registration.setDocumentDetail(airTelUserDetail.getStoreCode());
		registration.setMobileNo(airTelUserDetail.getMobileNumber());
		registration.setPermanentAddress(airTelUserDetail.getAddress());
		registration.setDocumentType(airTelUserDetail.getCircleName());
		registration.setState(airTelUserDetail.getStateName());
		registration.setDistrict(airTelUserDetail.getCityName());
		registration.setPincode(airTelUserDetail.getPinCode());
		return registration; 
	}

}
