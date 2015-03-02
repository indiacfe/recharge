package com.cfeindia.b2bserviceapp.service.distributor;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;

public interface HistoricalAllReportDistService {

	List<DistributorBalanceTransferLog> getFranBalTransferStatement(
			String fromDate, String toDate, String distId);

}
