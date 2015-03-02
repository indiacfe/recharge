package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.model.admin.AdminFundTrasferReportDTO;
import com.cfeindia.b2bserviceapp.model.admin.AdminReportDataReq;
import com.cfeindia.b2bserviceapp.model.admin.RechargeReportDTO;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface AdminReportService {

	public List<AdminFundTrasferReportDTO> generateFundTransferReport(
			String number, String fromDate, String toDate);

	public List<RechargeReportDTO> generateRechargeReport(String number,
			String fromDate, String toDate);

	public List<TransactionTransportBean> generateRechargeHistoryReport(
			String fromDate, String toDate, String sel, String status);

	public List<TransactionTransportBean> generateFranTransactionReportService(
			AdminReportDataReq adminReportDataReq);

	public List<AdminFundTrasferReportDTO> getCustFundTransferReport(String id,
			String fromDate, String toDate);

	public List<CustomerTransactionTransportBean> generateCustomerRechargeReport(
			String fromDate, String toDate, String customerId, String status,
			String thirdPartyName);

	public List<ReportBeanDto> generateMergeRechargeHistoryReport(
			String fromDate, String toDate, String customerId, String status,
			String thirdPartyName);

	public List<ReportBeanDto> operatorWiseRechargeReport(String fromDate,
			String toDate,  String customerId, String operator,String status,
			String thirdPartyName);
	public List<CustomerTransactionTransportBean> generateCustomerOperatorWiseRechargeReport(
			String fromDate, String toDate, String customerId, String status,String operator,
			String thirdPartyName);

	public List<CustomerTransactionTransportBean> generateElectricityRechargeReport(
			String fromDate, String toDate, String customerId, String status,String operator,
			String thirdPartyName);
	
	public List<ReportBeanDto> electricityRechargeReport(String fromDate,
			String toDate,  String customerId, String operator,String status,
			String thirdPartyName);
	
	public List<TransactionTransportBean> generateElectricityWiseReport(
			String fromDate, String toDate, String sel, String status,String operator);
}
