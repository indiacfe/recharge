package com.cfeindia.b2bserviceapp.service.customer;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.accountstatement.CustomerAccountStatementDto;
import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;

public interface CustAccountStatementService {
	public List<CustomerAccountStatementDto> generateAccountStatementReport(
			Long userId, String fromDate, String toDate);

	public List<CustomerAccountStatementDto> generateRechargeHistoryReport(
			Long userId, String fromDate, String toDate, String number);
	
	public List<CustomerCommision> getCustomerCommisions(Long userId);
}
