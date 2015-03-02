package com.cfeindia.b2bserviceapp.service.distributor;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.distributor.DistBalTransStatementDao;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service("distBalTransStatementService")
@Transactional
public class DistBalTransStatementServiceImpl implements
		DistBalTransStatementService {
	@Autowired
	DistBalTransStatementDao distBalTransStatementDao;
	
	public DistBalTransStatementDao getDistBalTransStatementDao() {
		return distBalTransStatementDao;
	}

	public void setDistBalTransStatementDao(
			DistBalTransStatementDao distBalTransStatementDao) {
		this.distBalTransStatementDao = distBalTransStatementDao;
	}

	public List<DistributorBalanceTransferLog> getFranBalTransferStatement(
			String fromDate, String toDate, String distId) {
		Timestamp fromdateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		List<DistributorBalanceTransferLog> transferlog = distBalTransStatementDao
				.getBalanceTransferStatement(fromdateTimeStamp,
						toDateTimeStamp, distId);
		for(DistributorBalanceTransferLog log : transferlog)
		{
			String dateTime=TimeStampUtil.getStringFromTimeStamp(log.getCreatedAt());
			log.setDateTime(dateTime);
			if(log.getRetailerDetail() != null) {
				Long retailerId=log.getRetailerDetail().getUsers().getUserId();
				String generatedId=ExtractorUtil.generateIdFromString(retailerId.toString(), "R");
				log.setDisplayFranchiseeId(generatedId);
				log.setDisplayTransactionName(log.getRetailerDetail().getFirmName() + " - " + generatedId);
			} else {
				log.setDisplayTransactionName(log.getTransferFrom() + " To " + log.getTransferTo());
			}
		}	
		
		return transferlog;
	}

}
