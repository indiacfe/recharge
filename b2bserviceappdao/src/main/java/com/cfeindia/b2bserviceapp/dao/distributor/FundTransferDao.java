package com.cfeindia.b2bserviceapp.dao.distributor;

import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CompanyCustomerTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.NewIdCreationBalanceLog;

public interface FundTransferDao {
	public String fundToRetailerDao();

	public FranchiseeCurrBal getCurrentDetail(Long franchID);

	public DistributorCurrbal getDistCurrBal(Long distID);

	public FranchiseeCurrBal getCurrentDetailWithoutLockingForDisplay(
			Long franchID);

	public DistributorCurrbal getDistCurrBalWithoutLockingForDisplay(
			Long distriID);

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

	public double updateFranchiseeCurrBal(Long fId, double amount);

	public double updateFranchiseeAddUnitBal(Long fId, double amount);
	
	public void reduceDistributorCurrBal(Long dId, double amount);

	public void reduceDistributorB2bBal(Long dId, double amount);

	public void reduceDistributorAdUnitBal(Long dId, Double amount);

	public void reduceFranchiseCurrBal(Long FId, Double amount,
			String paymentType);

	public void addDistributorCurrBal(Long dId, double amount);

	public void updateDistributorB2bToCurrBal(Long dId, double dCurrBal,
			double dAdBal);

	public void updateDistributorB2bAdunitBal(Long dId);

	public void addDistributorBusinessBal(Long dId, double amount);

	public void updateCompanyBalance(long cId, double amount);

	public NewIdCreationBalanceLog getValidUser(Long distId, Long retailerId);

	public void reduceDistributorAddUnitBal(Long dId, double amount);

	public boolean checkDuplicate(Long franchiseeId);

}
