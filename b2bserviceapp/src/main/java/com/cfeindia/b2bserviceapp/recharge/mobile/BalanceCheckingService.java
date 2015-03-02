package com.cfeindia.b2bserviceapp.recharge.mobile;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

public interface BalanceCheckingService {
	public FranchiseeCurrBal getFrachiseeCurrbalObj(long retailerId);
	public void reduceMobileBalance(long franchiseeId, double transAmount);
}
