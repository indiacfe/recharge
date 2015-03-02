package com.cfeindia.b2bserviceapp.service.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.admin.TotalFundTransferDao;
import com.cfeindia.b2bserviceapp.dto.CompanyDistributorTransactionDto;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.model.admin.AdminReportDataReq;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class TotalFundTransferServiceImpl implements TotalFundTransferService {
	@Autowired
	private TotalFundTransferDao totalFundTransferDao;

	public List<CompanyDistributorTransactionDto> getTransferedDetails(
			AdminReportDataReq adminReportDataReq) {
		Timestamp startDate = TimeStampUtil
				.getTimeStampFromStringFromdate(adminReportDataReq
						.getFromDate());
		Timestamp endDate = TimeStampUtil
				.getTimeStampFromStringTodate(adminReportDataReq.getToDate()); 
		List<CompanyDistributorTransactionLog> logList = totalFundTransferDao
				.getAllUsers(startDate, endDate);
		List<CompanyDistributorTransactionDto> cdtlist = new ArrayList<CompanyDistributorTransactionDto>();

		for (CompanyDistributorTransactionLog log : logList) {

			CompanyDistributorTransactionDto cmpyDto = new CompanyDistributorTransactionDto();
			cmpyDto.setId(log.getId());
			cmpyDto.setUserId(log.getDistributorId().getUserId()); 
			cmpyDto.setUserName(log.getDistributorId().getUserDetail()
					.getFirmName());
			cmpyDto.setPreBalance(log.getPreBalance());
			cmpyDto.setNewBalance(log.getNewBalance());
			cmpyDto.setTransferAmount(log.getTransferAmount());
			cmpyDto.setTransId(log.getTransId());
			cmpyDto.setTotalAmount(totalFundTransferDao.getTotalAmount(log
					.getDistributorId().getUserId(), startDate, endDate));
			cmpyDto.setCreatedAt(log.getCreatedAt());
			cdtlist.add(cmpyDto);
		}

		return cdtlist;
	}

	@Override
	public List<CompanyDistributorTransactionDto> getTransferedDetails(
			Integer month, Integer year) {
		String sDate = month + "/" + 01 + "/" + year;
		String eDate = month + "/" + 31 + "/" + year;
		Timestamp startDate = TimeStampUtil
				.getTimeStampFromStringFromdate(sDate);
		Timestamp endDate = TimeStampUtil.getTimeStampFromStringTodate(eDate); 
		List<CompanyDistributorTransactionLog> logList = totalFundTransferDao
				.getAllUsers(startDate, endDate);
		List<CompanyDistributorTransactionDto> cdtlist = new ArrayList<CompanyDistributorTransactionDto>();

		for (CompanyDistributorTransactionLog log : logList) {

			CompanyDistributorTransactionDto cmpyDto = new CompanyDistributorTransactionDto();
			cmpyDto.setId(log.getId());
			cmpyDto.setUserId(log.getDistributorId().getUserId());
			cmpyDto.setUserName(log.getDistributorId().getUserDetail()
					.getFirmName());
			cmpyDto.setPreBalance(log.getPreBalance());
			cmpyDto.setNewBalance(log.getNewBalance());
			cmpyDto.setTransferAmount(log.getTransferAmount());
			cmpyDto.setTransId(log.getTransId());
			cmpyDto.setTotalAmount(totalFundTransferDao.getTotalAmount(log
					.getDistributorId().getUserId(), startDate, endDate));
			cmpyDto.setCreatedAt(log.getCreatedAt());
			cdtlist.add(cmpyDto);
		}

		return cdtlist;

	}
}
