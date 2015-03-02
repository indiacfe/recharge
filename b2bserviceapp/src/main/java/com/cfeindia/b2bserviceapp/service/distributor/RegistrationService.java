package com.cfeindia.b2bserviceapp.service.distributor;

import com.cfeindia.b2bserviceapp.distributor.model.Registration;

public interface RegistrationService {

	public Long registerationProcess(Registration registration,Long curDistId);
	public Long registerationProcessByCompany(Registration registration,Long curComtId,String categery);
}
