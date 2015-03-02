package com.cfeindia.b2bserviceapp.service.distributor;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.CompanyDistributorTransactionDto;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.model.distributor.FundTransferReportDist;

public interface DistBalTransStatementService 
{
	public List<DistributorBalanceTransferLog> getFranBalTransferStatement(String fromDate,String toDate,String distId);
	

	public List<CompanyDistributorTransactionDto> getTransferedDetails(
			String date,String distId);

	public List<CompanyDistributorTransactionDto> getTransferedDetails(
			Integer month, Integer year,String distId);
}
