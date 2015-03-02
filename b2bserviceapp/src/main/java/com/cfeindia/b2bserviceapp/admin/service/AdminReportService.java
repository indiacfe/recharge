package com.cfeindia.b2bserviceapp.admin.service;

import java.util.List;

import com.cfeindia.b2bserviceapp.admin.model.AdminFundTrasferReportDTO;
import com.cfeindia.b2bserviceapp.admin.model.RechargeReportDTO;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface AdminReportService {

	public List<AdminFundTrasferReportDTO> generateFundTransferReport(String number, String fromDate, String toDate);
	public List<RechargeReportDTO> generateRechargeReport(String number, String fromDate, String toDate);
    public List<TransactionTransportBean> generateRechargeHistoryReport(String fromDate, String toDate, String sel,String status);
}
