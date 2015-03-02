package com.cfeindia.b2bserviceapp.dao.distributor;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;

public interface HistoricalAllReportDistDao {

	List<DistributorBalanceTransferLog> getHistoricalBalanceTransferStatement(
			Timestamp fromDate, Timestamp toDate, String distId);

}
