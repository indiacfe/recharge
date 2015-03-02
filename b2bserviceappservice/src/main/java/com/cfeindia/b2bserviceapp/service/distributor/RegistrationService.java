package com.cfeindia.b2bserviceapp.service.distributor;

import com.cfeindia.b2bserviceapp.model.distributor.Registration;

public interface RegistrationService {

	public Long registerationProcess(Registration registration, Long curDistId);

	public Long registerationProcessByCompany(Registration registration,
			Long curComtId, String categery);

	public Registration getUserDetail(Long userId);

	public String updateUser(Registration registration);

	public String userDeactivate(Long userId, int enabled);

	public String addNewAirtelUser(Registration registration);

	public boolean checkAirtelUser(String mobileNumber);

	public Registration getAirTelUser(String mobileNumberOrUserName);

	public String updateAirTelUser(Registration registration);

}
