package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.ICashRecharge;

public interface ICashDao {
	public ICashRecharge checkICashRegistration(String mobileNo);

}
