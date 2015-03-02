package com.cfeindia.b2bserviceapp.service.admin;

import com.cfeindia.b2bserviceapp.model.distributor.Registration;

public interface ApiRegisterService {
	public Long registerationProcessByCompany(Registration registration,
			Long curComtId, String category);

}
