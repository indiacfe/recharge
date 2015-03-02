package com.cfeindia.b2bserviceapp.dao.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.CustomerRefundRequest;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;

public interface AdminCustomerRefundDao {
	public CustomerTransactionTransportBean getEntityByKey(String tid);

	public CustomerRefundRequest getCustomerRefundRequestBean(String tId);

	public List<CustomerRefundRequest> getAllRefundRequest();

	public void setCustomerBalTransferLog(CustomerBalanceTransferLog log);

	public double updateCustomerCurrBal(long customerId, Double refundedAmount);
}
