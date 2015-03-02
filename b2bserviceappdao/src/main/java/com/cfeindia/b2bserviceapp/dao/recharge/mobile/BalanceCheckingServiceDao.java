package com.cfeindia.b2bserviceapp.dao.recharge.mobile;

import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

public interface BalanceCheckingServiceDao {
	
	public FranchiseeCurrBal getFrachiseeCurrbalObj(long retailerId);
	public void reduceFranchiseeCurrentAccountBalance(long franchiseeId, double transAmount);
	public void reduceFranchiseeAdUnitAccountBalance(long franchiseeId, double transAmount);
	public CustomerCurrentBalance getCustomerCurrbalObj(long customerId);
	public void reduceCustomerMobileBalance(long userId, double transAmount);
	public Long addToDistributorAdUnitBalanceByFranchisee(long DistributorId, double amount);
	public DistributorCurrbal getDistributorCurrbalObj(long distributorId);
	public void addUnitDistributorPerBalance(long RetailerId, double transAmount);

}
