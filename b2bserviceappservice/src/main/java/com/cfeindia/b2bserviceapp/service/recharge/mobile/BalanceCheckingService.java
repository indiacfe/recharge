package com.cfeindia.b2bserviceapp.service.recharge.mobile;

import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

public interface BalanceCheckingService {
	public FranchiseeCurrBal getFrachiseeCurrbalObj(long retailerId);
	public void reduceFranchiseeCurrentAccountBalance(long franchiseeId, double transAmount);
	public CustomerCurrentBalance getCustomerCurrbalObj(long customerId);
	public void reducecustomerMobileBalance(long userId, double transAmount);
	public Long addToDistributorAdUnitBalanceByFranchisee(long DistributorId, double transAmoun);
	public void addUnitDistributorPerBalance(long DistributorId, double transAmount);
	
}
