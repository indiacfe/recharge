package com.cfeindia.b2bserviceapp.admin.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.admin.model.AdminFundTrasferReportDTO;
import com.cfeindia.b2bserviceapp.admin.model.RechargeReportDTO;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.dao.CommonDao;
import com.cfeindia.b2bserviceapp.dao.admin.AdminReportDao;
import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class AdminReportServiceImpl implements AdminReportService {

	@Autowired
	CommonDao commonDao;

	@Autowired
	AdminReportDao adminReportDao;

	public List<AdminFundTrasferReportDTO> generateFundTransferReport(
			String number, String fromDate, String toDate) {

		Timestamp fromdateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);

		List<AdminFundTrasferReportDTO> adminFundTrasferReportDTOList = new ArrayList<AdminFundTrasferReportDTO>();
		List<CompanyBalTransactionLog> companyBalTransactionLoglist;
		if (number == "") {
			companyBalTransactionLoglist = adminReportDao
					.generateFundTransferReport(null, fromdateTimeStamp,
							toDateTimeStamp);
		} else {
			Long userId = CyberTelUtil.getStrInLong(number);
			companyBalTransactionLoglist = adminReportDao
					.generateFundTransferReport(
							CyberTelUtil.getStrInLong(number),
							fromdateTimeStamp, toDateTimeStamp);
		}

		for (CompanyBalTransactionLog companyBalTransactionLog : companyBalTransactionLoglist) {
			AdminFundTrasferReportDTO adminFundTrasferReportDTO = new AdminFundTrasferReportDTO();
			if (number == "") {
				Users user = companyBalTransactionLog.getUserId();
				if (user != null) {
					UserDetail userDetail = user.getUserDetail();
					if (userDetail != null) {
								+ userDetail.getFirmName());
						adminFundTrasferReportDTO.setFirmName(userDetail
								.getFirmName());
						adminFundTrasferReportDTO.setMobileNumber(user
								.getUsername());
					}
				}
			} else {
				Users user = commonDao.searchUserBasedOnIdOrMobNum(number);
				if (user != null) {
					UserDetail userDetail = user.getUserDetail();
					adminFundTrasferReportDTO.setFirmName(userDetail
							.getFirmName());
					adminFundTrasferReportDTO.setMobileNumber(user
							.getUsername());
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

		List<CompanyDistributorTransactionLog> companyDistributorTransactionLogList;
		if (number == "") {
			companyDistributorTransactionLogList = adminReportDao
					.generateFundTransferReport1(null, fromdateTimeStamp,
							toDateTimeStamp);

		} else {
			companyDistributorTransactionLogList = adminReportDao
					.generateFundTransferReport1(
							CyberTelUtil.getStrInLong(number),
							fromdateTimeStamp, toDateTimeStamp);
		}

		for (CompanyDistributorTransactionLog companyDistributorTransactionLog : companyDistributorTransactionLogList) {
			AdminFundTrasferReportDTO adminFundTrasferReportDTO = new AdminFundTrasferReportDTO();
			if (number == "") {
				Users user = companyDistributorTransactionLog
						.getDistributorId();
				if (user != null) {
					UserDetail userDetail = user.getUserDetail();
					adminFundTrasferReportDTO.setFirmName(userDetail
							.getFirmName());
					adminFundTrasferReportDTO.setMobileNumber(user
							.getUsername());
				}
			} else {
				Users user = commonDao.searchUserBasedOnIdOrMobNum(number);
				if (user != null) {
					UserDetail userDetail = user.getUserDetail();
					adminFundTrasferReportDTO.setFirmName(userDetail
							.getFirmName());
					adminFundTrasferReportDTO.setMobileNumber(user
							.getUsername());
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
					.setTransactionId(companyDistributorTransactionLog
							.getTransId());
			adminFundTrasferReportDTO
					.setTransferAmount(companyDistributorTransactionLog
							.getTransferAmount());
			adminFundTrasferReportDTO
					.setTransferType(companyDistributorTransactionLog
							.getTransferType());
			adminFundTrasferReportDTO
					.setUserType(CommonConstants.ROLE_DISTRIBUTOR);
			adminFundTrasferReportDTOList.add(adminFundTrasferReportDTO);
		}
		return adminFundTrasferReportDTOList;
	}

	public List<RechargeReportDTO> generateRechargeReport(String number,
			String fromDate, String toDate) {

		return null;
	}

	public List<TransactionTransportBean> generateRechargeHistoryReport(
			String fromDate, String toDate, String sel,String status) 
	{
		Timestamp frmDate=TimeStampUtil.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDte=TimeStampUtil.getTimeStampFromStringTodate(toDate);
		List<TransactionTransportBean> transactionTransportBeansList=adminReportDao.generateRechargeHistoryReport(frmDate, toDte, sel,status);
		return transactionTransportBeansList;
	}

}
