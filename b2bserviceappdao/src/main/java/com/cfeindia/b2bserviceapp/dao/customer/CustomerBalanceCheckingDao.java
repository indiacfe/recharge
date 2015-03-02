package com.cfeindia.b2bserviceapp.dao.customer;

import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;

public interface CustomerBalanceCheckingDao {
	public CustomerCurrentBalance getBalance(Long custId);

	
}
