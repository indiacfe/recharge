package com.cfeindia.b2bserviceapp.service.distributor;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.admin.HistoricalFundTransferReportDao;
import com.cfeindia.b2bserviceapp.dao.distributor.HistoricalAllReportDistDao;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class HistoricalAllReportDistServiceImpl implements HistoricalAllReportDistService{
	
	@Autowired
	HistoricalAllReportDistDao historicalAllReportDistDao;
	@Autowired
	HistoricalFundTransferReportDao historicalFundTransferReportDao;
	
	@Override
	public List<DistributorBalanceTransferLog> getFranBalTransferStatement(
			String fromDate, String toDate, String distId) {
		Timestamp fromdateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		List<DistributorBalanceTransferLog> transferlog = historicalAllReportDistDao
				.getHistoricalBalanceTransferStatement(fromdateTimeStamp,
						toDateTimeStamp, distId);
		for (DistributorBalanceTransferLog log : transferlog) {
			if (log.getFranchiseeid() != 0) {
				Long retailerId = log.getFranchiseeid();
				String generatedId = ExtractorUtil.generateIdFromString(
						retailerId.toString(), "R");
				log.setDisplayFranchiseeId(generatedId);
				int id=(int) log.getFranchiseeid();
				UserDetailDto detailDto =historicalFundTransferReportDao.getUserDetailById(id);
				log.setDisplayTransactionName(detailDto.getFirmName() + " - " + generatedId);
			} else {
				log.setDisplayTransactionName(log.getTransferFrom() + " To "
						+ log.getTransferTo());
			}
		}

		return transferlog;
	}

}
