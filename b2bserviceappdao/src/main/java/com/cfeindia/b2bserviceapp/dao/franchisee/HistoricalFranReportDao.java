package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;

public interface HistoricalFranReportDao {
	
	public List<FranchiseeAccountStatementDto> getHistoricalTransactionTransportBeanListRetailerByNumber(
			Long userId, String fromDate, String toDate, String number);


}
