package com.cfeindia.b2bserviceapp.common.dao;

import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.Users;

public interface RegistrationDao 
{
	public long usersDataSave(Users users);

	public DistributorCurrbal loadDistributorCurrbal(Long currDistid);
	public CompanyBalance loadCompanyAccountBalance();
}
