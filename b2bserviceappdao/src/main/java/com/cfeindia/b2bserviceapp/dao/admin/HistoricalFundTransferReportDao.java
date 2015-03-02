package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.model.admin.AdminFundTrasferReportDTO;

public interface HistoricalFundTransferReportDao {

	List<AdminFundTrasferReportDTO> generateHistoricalFundTransferReport(Long userId,Timestamp fromdateTimeStamp,
			Timestamp toDateTimeStamp);

	public UserDetailDto getUserDetailById(int user);

	int searchIdByMobNumofUser(String number);

	List<AdminFundTrasferReportDTO> generateHistoricalFundTransferReport1(Long userId,Timestamp fromdateTimeStamp,
			Timestamp toDateTimeStamp);

}
