package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface AdminReportDao {

	List<CompanyBalTransactionLog> generateFundTransferReport(Long userId,
			Timestamp fromdateTimeStamp, Timestamp toDateTimeStamp);

	public List<CompanyDistributorTransactionLog> generateFundTransferReport1(
			Long userId, Timestamp fromdateTimeStamp, Timestamp toDateTimeStamp);

	public List<TransactionTransportBean> generateRechargeHistoryReport(
			Timestamp fromDate, Timestamp toDate, String sel,String status);

}
