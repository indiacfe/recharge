package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.model.admin.AdminFundTrasferReportDTO;

public interface HistoricalFundTransferReportService {

	List<AdminFundTrasferReportDTO> generateHistoricalFundTransferReport(
			String number, String fromDate, String toDate);

}
