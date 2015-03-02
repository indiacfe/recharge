package com.cfeindia.b2bserviceapp.service.distributor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.distributor.DistBalTransStatementDao;
import com.cfeindia.b2bserviceapp.dto.CompanyDistributorTransactionDto;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
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
		for (DistributorBalanceTransferLog log : transferlog) {
			String dateTime = TimeStampUtil.getStringFromTimeStamp(log
					.getCreatedAt());
			log.setDateTime(dateTime);
			if (log.getRetailerDetail() != null) {
				Long retailerId = log.getRetailerDetail().getUsers()
						.getUserId();
				String generatedId = ExtractorUtil.generateIdFromString(
						retailerId.toString(), "R");
				log.setDisplayFranchiseeId(generatedId);
				log.setDisplayTransactionName(log.getRetailerDetail()
						.getFirmName() + " - " + generatedId);
			} else {
				log.setDisplayTransactionName(log.getTransferFrom() + " To "
						+ log.getTransferTo());
			}
		}

		return transferlog;
	}

	@Override
	public List<CompanyDistributorTransactionDto> getTransferedDetails(
			String date, String distId) {
		Timestamp startDate = TimeStampUtil
				.getTimeStampFromStringFromdate(date);
		Timestamp endDate = TimeStampUtil.getTimeStampFromStringTodate(date);
		System.out.println(startDate + " " + endDate);
		List<DistributorBalanceTransferLog> logList = distBalTransStatementDao
				.getTransferedDetails(startDate, endDate, Long.valueOf(distId));
		List<CompanyDistributorTransactionDto> companyDistributorTransactionDtoList = new ArrayList<CompanyDistributorTransactionDto>();
		for (DistributorBalanceTransferLog log : logList) {
			CompanyDistributorTransactionDto cmpyDto = new CompanyDistributorTransactionDto();
			cmpyDto.setId(log.getId());
			cmpyDto.setUserId(log.getRetailerDetail().getUsers().getUserId());
			cmpyDto.setUserName(log.getRetailerDetail().getFirmName());
			cmpyDto.setTransferAmount(log.getTransferAmount());
			cmpyDto.setTransId(log.getTransactionId());
			cmpyDto.setTotalAmount(distBalTransStatementDao.getTotalAmount(
					Long.valueOf(distId), startDate, endDate));
			companyDistributorTransactionDtoList.add(cmpyDto);
		}
		return companyDistributorTransactionDtoList;
	}

	@Override
	public List<CompanyDistributorTransactionDto> getTransferedDetails(
			Integer month, Integer year, String distId) {
		String sDate = month + "/" + 01 + "/" + year;
		String eDate = month + "/" + 31 + "/" + year;
		Timestamp startDate = TimeStampUtil
				.getTimeStampFromStringFromdate(sDate);
		Timestamp endDate = TimeStampUtil.getTimeStampFromStringTodate(eDate);
		System.out.println(startDate + " Dates " + endDate);
		List<DistributorBalanceTransferLog> logList = distBalTransStatementDao
				.getTransferedDetails(startDate, endDate, Long.valueOf(distId));
		List<CompanyDistributorTransactionDto> companyDistributorTransactionDtoList = new ArrayList<CompanyDistributorTransactionDto>();
		for (DistributorBalanceTransferLog log : logList) {
			CompanyDistributorTransactionDto cmpyDto = new CompanyDistributorTransactionDto();
			cmpyDto.setId(log.getId());
			cmpyDto.setUserId(log.getRetailerDetail().getUsers().getUserId());
			cmpyDto.setUserName(log.getRetailerDetail().getFirmName());
			cmpyDto.setTransferAmount(log.getTransferAmount());
			cmpyDto.setTransId(log.getTransactionId());
			cmpyDto.setTotalAmount(distBalTransStatementDao.getTotalAmount(
					Long.valueOf(distId), startDate, endDate));
			companyDistributorTransactionDtoList.add(cmpyDto);
		}
		return companyDistributorTransactionDtoList;
	}

}
