package com.cfeindia.b2bserviceapp.service.distributor;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.UserDetail;

public interface DistBalTransStatementService 
{
	public List<DistributorBalanceTransferLog> getFranBalTransferStatement(String fromDate,String toDate,String distId);
	


}
