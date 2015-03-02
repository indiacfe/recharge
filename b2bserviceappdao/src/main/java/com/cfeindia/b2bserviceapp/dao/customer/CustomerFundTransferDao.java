package com.cfeindia.b2bserviceapp.dao.customer;

import com.cfeindia.b2bserviceapp.entity.CompanyCustomerTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

public interface CustomerFundTransferDao {

	public long companyCustomerTransactionLog(
			CompanyCustomerTransactionLog companyCustomerTransactionLog);

	public void setCustomerBalTransferLog(
			CustomerBalanceTransferLog customerBalanceTransferLog);

	public void updateCustomerB2bAdunitBal(Long cId);

	public void addCustomerBusinessBal(Long cId, double amount);

	public void deductCustomerBusinessBalance(Long customerId,
			double deductAmount);

	public CustomerCurrentBalance getCurrentDetailWithoutLockingForDisplay(
			Long custID);

}
