package com.cfeindia.b2bserviceapp.service.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.admin.HistoricalFundTransferReportDao;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.model.admin.AdminFundTrasferReportDTO;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class HistoricalFundTransferReportServiceImpl implements HistoricalFundTransferReportService {
	
	@Autowired
	HistoricalFundTransferReportDao historicalFundTransferReportDao;

	@Override
	public List<AdminFundTrasferReportDTO> generateHistoricalFundTransferReport(
			String number, String fromDate, String toDate) {

		Timestamp fromdateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);

		List<AdminFundTrasferReportDTO> adminFundTrasferReportDTOList = new ArrayList<AdminFundTrasferReportDTO>();
		List<AdminFundTrasferReportDTO> companyBalTransactionLoglist;
		if (number == "") {
			companyBalTransactionLoglist = historicalFundTransferReportDao
					.generateHistoricalFundTransferReport(null, fromdateTimeStamp,
							toDateTimeStamp);
		} else {
			Long userId = CyberTelUtil.getStrInLong(number);
			companyBalTransactionLoglist = historicalFundTransferReportDao
					.generateHistoricalFundTransferReport(userId, fromdateTimeStamp,toDateTimeStamp);
		}

	for (AdminFundTrasferReportDTO companyBalTransactionLog : companyBalTransactionLoglist) {
			AdminFundTrasferReportDTO adminFundTrasferReportDTO = new AdminFundTrasferReportDTO();
			if (number == "") {
				int user = companyBalTransactionLog.getUserId();
				if (user != 0) {
					UserDetailDto userDetail = historicalFundTransferReportDao.getUserDetailById(user);
					if (userDetail != null) {
						adminFundTrasferReportDTO.setFirmName(userDetail.getFirmName());
						adminFundTrasferReportDTO.setMobileNumber(userDetail.getUserName());
					}
				}
			} else {
				int user = historicalFundTransferReportDao.searchIdByMobNumofUser(number);
				if (user != 0) {
				UserDetailDto	userDetail =historicalFundTransferReportDao.getUserDetailById(user);
					if (userDetail != null)
					adminFundTrasferReportDTO.setFirmName(userDetail.getFirmName());
					adminFundTrasferReportDTO.setMobileNumber(userDetail.getUserName());
				}
			}
			adminFundTrasferReportDTO.setCreatedAt(companyBalTransactionLog
					.getCreatedAt());

			adminFundTrasferReportDTO.setNewBalance(companyBalTransactionLog
					.getNewBalance());
			adminFundTrasferReportDTO.setPreBalance(companyBalTransactionLog
					.getPreBalance());
			adminFundTrasferReportDTO.setTransactionId(companyBalTransactionLog
					.getTransactionId());
			adminFundTrasferReportDTO
					.setTransferAmount(companyBalTransactionLog
							.getTransferAmount());
			adminFundTrasferReportDTO.setTransferType(companyBalTransactionLog
					.getTransferType());
			adminFundTrasferReportDTO.setUserType(companyBalTransactionLog
					.getUserType());
			adminFundTrasferReportDTOList.add(adminFundTrasferReportDTO);
			
		}
		List<AdminFundTrasferReportDTO> companyDistributorTransactionLogList;
		if (number == "") {
			companyDistributorTransactionLogList = historicalFundTransferReportDao
					.generateHistoricalFundTransferReport1(null, fromdateTimeStamp,toDateTimeStamp);

		} else {
			companyDistributorTransactionLogList = historicalFundTransferReportDao
					.generateHistoricalFundTransferReport1(CyberTelUtil.getStrInLong(number),fromdateTimeStamp, toDateTimeStamp);
		}

		for (AdminFundTrasferReportDTO companyDistributorTransactionLog : companyDistributorTransactionLogList) {
			AdminFundTrasferReportDTO adminFundTrasferReportDTO = new AdminFundTrasferReportDTO();
			if (number == "") {
				int user = companyDistributorTransactionLog.getUserId();
				if (user != 0) {
					UserDetailDto userDetail = historicalFundTransferReportDao.getUserDetailById(user);
					if (userDetail != null)
						adminFundTrasferReportDTO.setFirmName(userDetail.getFirmName());
					adminFundTrasferReportDTO.setMobileNumber(userDetail.getUserName());
				}
			} else {
				int user = historicalFundTransferReportDao.searchIdByMobNumofUser(number);
				if (user != 0) {
					UserDetailDto userDetail = historicalFundTransferReportDao.getUserDetailById(user);
					if (userDetail != null)
						adminFundTrasferReportDTO.setFirmName(userDetail.getFirmName());
					adminFundTrasferReportDTO.setMobileNumber(userDetail.getUserName());
				}

			}
			adminFundTrasferReportDTO
			.setCreatedAt(companyDistributorTransactionLog
					.getCreatedAt());
	adminFundTrasferReportDTO
			.setNewBalance(companyDistributorTransactionLog
					.getNewBalance());
	adminFundTrasferReportDTO
			.setPreBalance(companyDistributorTransactionLog
					.getPreBalance());
	adminFundTrasferReportDTO
			.setTransactionId(companyDistributorTransactionLog.getTransactionId());
	adminFundTrasferReportDTO
			.setTransferAmount(companyDistributorTransactionLog
					.getTransferAmount());
	adminFundTrasferReportDTO
			.setTransferType(companyDistributorTransactionLog
					.getTransferType());
	adminFundTrasferReportDTO
			.setUserType(CommonConstants.ROLE_DISTRIBUTOR);
	adminFundTrasferReportDTO
			.setTransferFrom(companyDistributorTransactionLog
					.getTransferFrom());
	adminFundTrasferReportDTO
			.setTransferTo(companyDistributorTransactionLog
					.getTransferTo());
			adminFundTrasferReportDTO
					.setUserType(CommonConstants.ROLE_DISTRIBUTOR);

			adminFundTrasferReportDTOList.add(adminFundTrasferReportDTO);
		}
		return adminFundTrasferReportDTOList;
	}

}
