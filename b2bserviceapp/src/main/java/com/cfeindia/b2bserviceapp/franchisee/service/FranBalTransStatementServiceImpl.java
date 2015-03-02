package com.cfeindia.b2bserviceapp.franchisee.service;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.franchisee.dao.FranBalTransStatementDao;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service("franBalTransStatementService")
@Transactional
public class FranBalTransStatementServiceImpl implements FranBalTransStatementService
{
	@Autowired
	FranBalTransStatementDao franBalTransStatementDao;
	
	
	public FranBalTransStatementDao getFranBalTransStatementDao() {
		return franBalTransStatementDao;
	}


	public void setFranBalTransStatementDao(FranBalTransStatementDao franBalTransStatementDao) {
		this.franBalTransStatementDao = franBalTransStatementDao;
	}

	public List<FranchiseeBalanceTransferLog> getFranBalTransferStatement(String fromDate,String toDate,String franId ) 
	{
		long franchiseeId=CyberTelUtil.getStrInLong(franId);
		Timestamp fromdateTimeStamp=TimeStampUtil.getTimeStampFromString(fromDate);
		Timestamp toDateTimeStamp=TimeStampUtil.getTimeStampFromString(toDate);
		List<FranchiseeBalanceTransferLog> transferlog=franBalTransStatementDao.getBalanceTransferStatement(fromdateTimeStamp, toDateTimeStamp,franchiseeId);	
		Iterator<FranchiseeBalanceTransferLog> itr=transferlog.iterator();
		while(itr.hasNext())
		{
			FranchiseeBalanceTransferLog obj=itr.next();
		}
		return transferlog;
	}
}
