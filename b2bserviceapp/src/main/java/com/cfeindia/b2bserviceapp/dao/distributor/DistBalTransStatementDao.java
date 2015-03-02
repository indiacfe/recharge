package com.cfeindia.b2bserviceapp.dao.distributor;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.UserDetail;

public interface DistBalTransStatementDao 
{
	public List<DistributorBalanceTransferLog> getBalanceTransferStatement(Timestamp fromDate,Timestamp toDate,String distId);
	public List<UserDetail> getMultipleUserDetail(long userId);

}
