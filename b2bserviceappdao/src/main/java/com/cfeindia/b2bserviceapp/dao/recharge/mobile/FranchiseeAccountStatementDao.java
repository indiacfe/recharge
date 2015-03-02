package com.cfeindia.b2bserviceapp.dao.recharge.mobile;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface FranchiseeAccountStatementDao {
	public List<TransactionTransportBean> getTransactionTransportBeanListByRetailerId(
			Long userId, String fromDate, String toDate);
	public List<TransactionTransportBean> newgenerateAccountStatementReport(Long userId,
			 String fromDate, String toDate,String thirdPartyServiceProvider);

	public List<TransactionTransportBean> getTransactionTransportBeanListRetailerByNumber(
			Long userId, String fromDate, String toDate, String number);

	public List<FranchiseeBalanceTransferLog> getFranchiseeBalTransferLogList(
			Long franchiseeId, String fromDate, String toDate);

	public FranchiseeRefundRequests getRefundDetails(String transId);

	public List<TransactionTransportBean> getTransactionTransportBeanListForRefundByRetailerId(
			Long userId, String fromDate, String toDate);
}
