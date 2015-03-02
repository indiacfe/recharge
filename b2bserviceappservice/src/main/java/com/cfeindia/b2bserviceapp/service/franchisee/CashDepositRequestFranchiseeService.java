package com.cfeindia.b2bserviceapp.service.franchisee;

import com.cfeindia.b2bserviceapp.model.franchisee.CashDepositRequestFran;

public interface CashDepositRequestFranchiseeService
{
		
	public void saveCashDepositRequest(String userId,CashDepositRequestFran cashDepositRequestFran);
}
