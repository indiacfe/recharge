package com.cfeindia.b2bserviceapp.dao.distributor;

import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

public interface FundTransferDao {
	public String fundToRetailerDao();

	public FranchiseeCurrBal getCurrentDetail(Long franchID);

	public DistributorCurrbal getDistCurrBal(Long distID);

	public DistributorCommision getDistributorCommision(Long disID);

	public String updateDistributorCommision(
			DistributorCommision distributorCommision);

	public CompanyBalance getCompanyBalance(String companyName);

	public long companyDistributorTransactionLog(
			CompanyDistributorTransactionLog companyDistributorTransactionLog);

	public void setDistributorBalTransferLog(
			DistributorBalanceTransferLog distributorBalanceTransferLog);

	public void setFranchiseeBalTransferLog(
			FranchiseeBalanceTransferLog franchiseeBalanceTransferLog);

	public void updateFranchiseeCurrBal(Long fId, double amount);

	public void reduceDistributorCurrBal(Long dId, double amount);
	
	public void addDistributorCurrBal(Long dId, double amount);

	public void updateDistributorB2bToCurrBal(Long dId, double dCurrBal,double dAdBal);
	
	public void updateCompanyBalance(long cId,double amount);
	
	public void updateDistributorB2bAdunitBal(Long dId);
	
	public void addDistributorBusinessBal(Long dId, double amount);
}
