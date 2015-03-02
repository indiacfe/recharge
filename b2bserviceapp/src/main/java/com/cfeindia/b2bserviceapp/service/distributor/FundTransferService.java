package com.cfeindia.b2bserviceapp.service.distributor;

import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;


public interface FundTransferService {
	
	String fundToRetailerCurrService(Long retailerId,Long senderId,double amount);
    String distAdUnitBalTransTodistCurrBal(Long distributorId, double transferAmount);
    String companyBalanceTransToDistService(Long distributorId, double transferAmount, String companyName);
    public String companyBalanceTransToDistServiceBusiness(Long distributorId,
			double transferAmount, String companyName);
	public FranchiseeCurrBal getCurrentDetail(Long franchID);
	public DistributorCurrbal getDistCurrBal(Long distID);
}
