package com.cfeindia.b2bserviceapp.recharge.mobile.dao;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

public interface BalanceCheckingServiceDao {
	
	public FranchiseeCurrBal getFrachiseeCurrbalObj(long retailerId);
	public void reduceMobileBalance(long franchiseeId, double transAmount);

}
