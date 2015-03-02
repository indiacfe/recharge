package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.CompanyDistributorTransactionDto;
import com.cfeindia.b2bserviceapp.model.admin.AdminReportDataReq;

public interface TotalFundTransferService {
	public List<CompanyDistributorTransactionDto> getTransferedDetails(
			AdminReportDataReq adminReportDataReq);

	public List<CompanyDistributorTransactionDto> getTransferedDetails(
			Integer month, Integer year);

}
