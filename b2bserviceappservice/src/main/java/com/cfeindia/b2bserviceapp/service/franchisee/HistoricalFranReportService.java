package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;

public interface HistoricalFranReportService {
	
	List<FranchiseeAccountStatementDto> getHistoricalFranBalTransferStatement(
			Long userId, String fromDate, String toDate, String number);
}
