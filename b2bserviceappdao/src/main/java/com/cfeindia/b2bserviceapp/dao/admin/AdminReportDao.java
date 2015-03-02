package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyCustomerTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface AdminReportDao {

	List<CompanyBalTransactionLog> generateFundTransferReport(Long userId,
			Timestamp fromdateTimeStamp, Timestamp toDateTimeStamp);

	public List<CompanyDistributorTransactionLog> generateFundTransferReport1(
			Long userId, Timestamp fromdateTimeStamp, Timestamp toDateTimeStamp);

	public List<TransactionTransportBean> generateRechargeHistoryReport(
			Timestamp fromDate, Timestamp toDate, String sel, String status);

	public List<TransactionTransportBean> generateFranTransactionReport(
			Timestamp sDate, Timestamp eDate, String franchiseeId,
			String serviceProvider);

	public List<CompanyCustomerTransactionLog> generateCustFundTransferReport(
			Long userId, Timestamp fromdateTimeStamp, Timestamp toDateTimeStamp);

	List<TransactionTransportBean> generateRechargeHistoryReportAll(
			Timestamp frmDate, Timestamp toDte, String sel, String status);

	public List<CustomerTransactionTransportBean> generateCustomerRechargeHistoryReport(
			Timestamp frmDate, Timestamp toDte, String customerId,
			String status, String thirdPartyName);

	public List<Users> getAllUserDetails(int pageNo );

	public Number getcountUserList();
	
	public List<TransactionTransportBean> generateOpertorWiseReport(
			Timestamp fromDate, Timestamp toDate, String sel, String status,String operator);
	
	public List<CustomerTransactionTransportBean> generateCustomerOperatorWiseRechargeReport(
			Timestamp frmDate, Timestamp toDte, String customerId,
			String status, String thirdPartyName,String operator);
	
	public List<CustomerTransactionTransportBean> generateElectricityRechargeReport(
			Timestamp frmDate, Timestamp toDte, String customerId,
			String status, String thirdPartyName,String operator);
	
	public List<TransactionTransportBean> generateElectricityWiseReport(
			Timestamp fromDate, Timestamp toDate, String sel, String status,String operator);

}
