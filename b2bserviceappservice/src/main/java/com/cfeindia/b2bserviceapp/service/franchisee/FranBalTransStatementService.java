package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;

public interface FranBalTransStatementService 
{
	public List<FranchiseeBalanceTransferLog> getFranBalTransferStatement(String fromDate,String toDate,String franId);
}
