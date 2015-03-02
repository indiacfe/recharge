package com.cfeindia.b2bserviceapp.franchisee.service;

import java.util.List;

import com.cfeindia.b2bserviceapp.accountstatement.Dto.FranchiseeAccountStatementDto;

public interface FranAccountStatmentService 
{
	public List<FranchiseeAccountStatementDto> generateAccountStatementReport(Long userId,
			 String fromDate, String toDate);
	
	public List<FranchiseeAccountStatementDto> generateRechargeHistoryReport(Long userId,
			 String fromDate, String toDate, String number);
			 
	
}
