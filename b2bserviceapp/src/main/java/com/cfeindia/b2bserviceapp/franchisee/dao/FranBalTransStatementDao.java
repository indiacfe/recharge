package com.cfeindia.b2bserviceapp.franchisee.dao;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;

public interface FranBalTransStatementDao 
{
	public List<FranchiseeBalanceTransferLog> getBalanceTransferStatement(Timestamp fromDate,Timestamp toDate,long franId);
}
