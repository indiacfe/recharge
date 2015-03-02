package com.cfeindia.b2bserviceapp.service.customer;

import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;

public interface RequestValidationService {
	public String getValidate(String ip, String token, String agentId,
			String mobileNo);

	public CustomerCurrentBalance getBalance(String agentId);

}
