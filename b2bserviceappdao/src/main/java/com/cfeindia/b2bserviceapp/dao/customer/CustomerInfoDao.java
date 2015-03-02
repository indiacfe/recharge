package com.cfeindia.b2bserviceapp.dao.customer;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.Users;

public interface CustomerInfoDao {

	public CustomerCurrentBalance getCustomerAccountBalance(Long customerIdLong);
	public List<Users> getUsers();
	public List<CustomerCommision> getCustomerCommisions(Long custId);


}
