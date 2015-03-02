package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.List;
import java.util.Map;

import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;

public interface FranAccountStatmentService {
	public List<FranchiseeAccountStatementDto> generateAccountStatementReport(
			Long userId, String fromDate, String toDate);

	public List<FranchiseeAccountStatementDto> generateRechargeHistoryReport(
			Long userId, String fromDate, String toDate, String number);

	public List<FranchiseeAccountStatementDto> newgenerateAccountStatementReport(
			Long userId, String fromDate, String toDate,
			String thirdPartyServiceProvider);
	
	public Map<String, FranchiseeRefundRequests> getFranchiseeRefundRequest(long franchiseeId);

}
