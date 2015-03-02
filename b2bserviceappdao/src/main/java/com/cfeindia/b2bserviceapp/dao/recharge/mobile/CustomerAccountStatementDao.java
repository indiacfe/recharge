package com.cfeindia.b2bserviceapp.dao.recharge.mobile;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;

public interface CustomerAccountStatementDao {
	public List<CustomerTransactionTransportBean> getCustomerTransactionTransportBeanListByCustomerId(
			Long userId, String fromDate, String toDate);

	public List<CustomerBalanceTransferLog> getCustomerBalTransferLogList(
			Long franchiseeId, String fromDate, String toDate);

	public List<CustomerTransactionTransportBean> getCustomerTransactionTransportBeanListCustomerByNumber(
			Long userId, String fromDate, String toDate, String number);
	
	public List<CustomerCommision> getCustomerCommisions(Long userId);
}
