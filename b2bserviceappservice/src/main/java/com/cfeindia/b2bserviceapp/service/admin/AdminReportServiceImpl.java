package com.cfeindia.b2bserviceapp.service.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.admin.AdminReportDao;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;
import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyCustomerTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.model.admin.AdminFundTrasferReportDTO;
import com.cfeindia.b2bserviceapp.model.admin.AdminReportDataReq;
import com.cfeindia.b2bserviceapp.model.admin.RechargeReportDTO;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
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
					.generateFundTransferReport(userId, fromdateTimeStamp,
							toDateTimeStamp);
		}

		for (CompanyBalTransactionLog companyBalTransactionLog : companyBalTransactionLoglist) {
			AdminFundTrasferReportDTO adminFundTrasferReportDTO = new AdminFundTrasferReportDTO();
			if (number == "") {
				Users user = companyBalTransactionLog.getUserId();
				if (user != null) {
					UserDetail userDetail = user.getUserDetail();
					if (userDetail != null) {
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
					if (userDetail != null)
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
			adminFundTrasferReportDTO
					.setThirdPartyServiceProviderName(companyBalTransactionLog
							.getRemark());
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
					if (userDetail != null)
						adminFundTrasferReportDTO.setFirmName(userDetail
								.getFirmName());
					adminFundTrasferReportDTO.setMobileNumber(user
							.getUsername());
				}
			} else {
				Users user = commonDao.searchUserBasedOnIdOrMobNum(number);
				if (user != null) {
					UserDetail userDetail = user.getUserDetail();
					if (userDetail != null)
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
			adminFundTrasferReportDTO
					.setTransferFrom(companyDistributorTransactionLog
							.getTransferFrom());
			adminFundTrasferReportDTO
					.setTransferTo(companyDistributorTransactionLog
							.getTransferTo());

			adminFundTrasferReportDTOList.add(adminFundTrasferReportDTO);
		}
		return adminFundTrasferReportDTOList;
	}

	public List<RechargeReportDTO> generateRechargeReport(String number,
			String fromDate, String toDate) {

		return null;
	}

	public List<TransactionTransportBean> generateRechargeHistoryReport(
			String fromDate, String toDate, String sel, String status) {
		Timestamp frmDate = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDte = TimeStampUtil.getTimeStampFromStringTodate(toDate);
		List<TransactionTransportBean> transactionTransportBeansList = null;
		transactionTransportBeansList = adminReportDao
				.generateRechargeHistoryReport(frmDate, toDte, sel, status);
		return transactionTransportBeansList;
	}

	public List<TransactionTransportBean> generateFranTransactionReportService(
			AdminReportDataReq adminReportDataReq) {
		Timestamp fromDate = TimeStampUtil
				.getTimeStampFromStringFromdate(adminReportDataReq
						.getFromDate());
		Timestamp toDate = TimeStampUtil
				.getTimeStampFromStringTodate(adminReportDataReq.getToDate());
		List<TransactionTransportBean> transactionTransportBeansList = adminReportDao
				.generateFranTransactionReport(fromDate, toDate,
						adminReportDataReq.getNumber(),
						adminReportDataReq.getServiceProvider());
		return transactionTransportBeansList;
	}

	@Override
	public List<AdminFundTrasferReportDTO> getCustFundTransferReport(String id,
			String fromDate, String toDate) {
		Timestamp fromdateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);

		List<AdminFundTrasferReportDTO> adminFundTrasferReportDTOList = new ArrayList<AdminFundTrasferReportDTO>();
		List<CompanyCustomerTransactionLog> companyCustomerTransactionLogList;
		if (id == "") {
			companyCustomerTransactionLogList = adminReportDao
					.generateCustFundTransferReport(null, fromdateTimeStamp,
							toDateTimeStamp);
		} else {
			Long userId = CyberTelUtil.getStrInLong(id);
			companyCustomerTransactionLogList = adminReportDao
					.generateCustFundTransferReport(userId, fromdateTimeStamp,
							toDateTimeStamp);
		}

		for (CompanyCustomerTransactionLog companyCustomerTransactionLog : companyCustomerTransactionLogList) {
			AdminFundTrasferReportDTO adminFundTrasferReportDTO = new AdminFundTrasferReportDTO();
			if (id == "") {
				Users user = companyCustomerTransactionLog.getCustomerId();
				if (user != null) {
					UserDetail userDetail = user.getUserDetail();
					if (userDetail != null) {
						adminFundTrasferReportDTO.setFirmName(userDetail
								.getFirmName());
						adminFundTrasferReportDTO.setMobileNumber(user
								.getUsername());
					}
				}
			} else {
				Users user = commonDao.searchUserBasedOnIdOrMobNum(id);
				if (user != null) {
					UserDetail userDetail = user.getUserDetail();
					if (userDetail != null)
						adminFundTrasferReportDTO.setFirmName(userDetail
								.getFirmName());
					adminFundTrasferReportDTO.setMobileNumber(user
							.getUsername());
				}

			}

			adminFundTrasferReportDTO
					.setCreatedAt(companyCustomerTransactionLog.getCreatedAt());

			adminFundTrasferReportDTO
					.setNewBalance(companyCustomerTransactionLog
							.getNewBalance());
			adminFundTrasferReportDTO
					.setPreBalance(companyCustomerTransactionLog
							.getPreBalance());
			adminFundTrasferReportDTO
					.setTransactionId(companyCustomerTransactionLog
							.getTransId());
			adminFundTrasferReportDTO
					.setTransferAmount(companyCustomerTransactionLog
							.getTransferAmount());
			adminFundTrasferReportDTO
					.setTransferType(companyCustomerTransactionLog
							.getTransferType());

			adminFundTrasferReportDTOList.add(adminFundTrasferReportDTO);
		}

		return adminFundTrasferReportDTOList;

	}

	@Override
	public List<CustomerTransactionTransportBean> generateCustomerRechargeReport(
			String fromDate, String toDate, String customerId, String status,
			String thirdPartyName) {
		Timestamp frmDate = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDte = TimeStampUtil.getTimeStampFromStringTodate(toDate);
		String custId = null;
		if ("ALL".equalsIgnoreCase(customerId)) {
			custId = customerId;
		} else {
			custId = ExtractorUtil.extractIdFromString(customerId);
		}

		List<CustomerTransactionTransportBean> bean = adminReportDao
				.generateCustomerRechargeHistoryReport(frmDate, toDte, custId,
						status, thirdPartyName);
		return bean;
	}

	@Override
	public List<ReportBeanDto> generateMergeRechargeHistoryReport(
			String fromDate, String toDate, String customerId, String status,
			String thirdPartyName) {

		List<CustomerTransactionTransportBean> cList = generateCustomerRechargeReport(
				fromDate, toDate, customerId, status, thirdPartyName);
		List<TransactionTransportBean> transportBeans = generateRechargeHistoryReport(
				fromDate, toDate, thirdPartyName, status);
		List<ReportBeanDto> margeList = new ArrayList<ReportBeanDto>();

		for (CustomerTransactionTransportBean cBean : cList) {
			ReportBeanDto first = new ReportBeanDto();
			first.setCreatedAt(cBean.getCreatedAt());
			first.setClientId(ExtractorUtil.generateIdFromString(
					cBean.getCustomerId(), "C"));
			first.setThirdPartyServiceProvider(cBean
					.getThirdPartyServiceProvider());
			first.setMobileNo(cBean.getMobileNo());
			first.setConnectionid(cBean.getConnectionid());
			first.setOperator(cBean.getOperator());
			first.setTransactionName(cBean.getTransactionName());
			first.setAmount(cBean.getAmount());
			first.setCreditAmountFranchisee(cBean.getCreditAmountFranchisee());
			first.setStatus(cBean.getStatus());
			first.setThirdpartytransid(cBean.getClientTransId());
			first.setTransid(cBean.getTransid());
			margeList.add(first);
		}
		for (TransactionTransportBean tBean : transportBeans) {
			ReportBeanDto second = new ReportBeanDto();
			second.setCreatedAt(tBean.getCreatedAt());
			second.setClientId(ExtractorUtil.generateIdFromString(
					tBean.getRetailerId(), "R"));
			second.setThirdPartyServiceProvider(tBean
					.getThirdPartyServiceProvider());
			second.setMobileNo(tBean.getMobileNo());
			second.setConnectionid(tBean.getConnectionid());
			second.setOperator(tBean.getOperator());
			second.setTransactionName(tBean.getTransactionName());
			second.setAmount(tBean.getAmount());
			second.setCreditAmountFranchisee(tBean.getCreditAmountFranchisee());
			second.setStatus(tBean.getStatus());
			second.setThirdpartytransid(tBean.getTransid());
			// second.setTransid(tBean.getThirdpartytransid());
			margeList.add(second);
		}
		return margeList;
	}

	public List<ReportBeanDto> operatorWiseRechargeReport(String fromDate,
			String toDate,  String customerId, String operator,String status,
			String thirdPartyName) {
		List<CustomerTransactionTransportBean> cList = generateCustomerOperatorWiseRechargeReport(
				fromDate, toDate, customerId, status, thirdPartyName,operator);
		List<TransactionTransportBean> transportBeans = generateOperatorWiseReport(
				fromDate, toDate, thirdPartyName, status,operator);
		List<ReportBeanDto> margeList = new ArrayList<ReportBeanDto>();
		for (CustomerTransactionTransportBean cBean : cList) {
			ReportBeanDto first = new ReportBeanDto();
			first.setCreatedAt(cBean.getCreatedAt());
			first.setClientId(ExtractorUtil.generateIdFromString(
					cBean.getCustomerId(), "C"));
			first.setThirdPartyServiceProvider(cBean
					.getThirdPartyServiceProvider());
			first.setMobileNo(cBean.getMobileNo());
			first.setConnectionid(cBean.getConnectionid());
			first.setOperator(cBean.getOperator());
			first.setTransactionName(cBean.getTransactionName());
			first.setAmount(cBean.getAmount());
			first.setCreditAmountFranchisee(cBean.getCreditAmountFranchisee());
			first.setStatus(cBean.getStatus());
			first.setThirdpartytransid(cBean.getClientTransId());
			first.setTransid(cBean.getTransid());
			margeList.add(first);
		}
		for (TransactionTransportBean tBean : transportBeans) {
			ReportBeanDto second = new ReportBeanDto();
			second.setCreatedAt(tBean.getCreatedAt());
			second.setClientId(ExtractorUtil.generateIdFromString(
					tBean.getRetailerId(), "R"));
			second.setThirdPartyServiceProvider(tBean
					.getThirdPartyServiceProvider());
			second.setMobileNo(tBean.getMobileNo());
			second.setConnectionid(tBean.getConnectionid());
			second.setOperator(tBean.getOperator());
			second.setTransactionName(tBean.getTransactionName());
			second.setAmount(tBean.getAmount());
			second.setCreditAmountFranchisee(tBean.getCreditAmountFranchisee());
			second.setStatus(tBean.getStatus());
			second.setThirdpartytransid(tBean.getTransid());
			// second.setTransid(tBean.getThirdpartytransid());
			margeList.add(second);
		}
		return margeList;
	}

	public List<TransactionTransportBean> generateOperatorWiseReport(
			String fromDate, String toDate, String sel, String status,String operator) {
		Timestamp frmDate = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDte = TimeStampUtil.getTimeStampFromStringTodate(toDate);
		List<TransactionTransportBean> transactionTransportBeansList = null;
		transactionTransportBeansList = adminReportDao
				.generateOpertorWiseReport(frmDate, toDte, sel, status,operator);
		return transactionTransportBeansList;
	}
	
	@Override
	public List<CustomerTransactionTransportBean> generateCustomerOperatorWiseRechargeReport(
			String fromDate, String toDate, String customerId, String status,String operator,
			String thirdPartyName) {
		Timestamp frmDate = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDte = TimeStampUtil.getTimeStampFromStringTodate(toDate);
		String custId = null;
		if ("ALL".equalsIgnoreCase(customerId)) {
			custId = customerId;
		} else {
			custId = ExtractorUtil.extractIdFromString(customerId);
		}

		List<CustomerTransactionTransportBean> bean = adminReportDao
				.generateCustomerOperatorWiseRechargeReport(frmDate, toDte, custId,
						status, thirdPartyName,operator);
		return bean;
	}
	
	@Override
	public List<CustomerTransactionTransportBean> generateElectricityRechargeReport(
			String fromDate, String toDate, String customerId, String status,String operator,
			String thirdPartyName) {
		Timestamp frmDate = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDte = TimeStampUtil.getTimeStampFromStringTodate(toDate);
		String custId = null;
		if ("ALL".equalsIgnoreCase(customerId)) {
			custId = customerId;
		} else {
			custId = ExtractorUtil.extractIdFromString(customerId);
		}

		List<CustomerTransactionTransportBean> bean = adminReportDao
				.generateElectricityRechargeReport(frmDate, toDte, custId,
						status, thirdPartyName,operator);
		return bean;
	}
	public List<ReportBeanDto> electricityRechargeReport(String fromDate,
			String toDate,  String customerId, String operator,String status,
			String thirdPartyName) {
		List<CustomerTransactionTransportBean> cList = generateElectricityRechargeReport(
				fromDate, toDate, customerId, status, thirdPartyName,operator);
		List<TransactionTransportBean> transportBeans = generateElectricityWiseReport(
				fromDate, toDate, thirdPartyName, status,operator);
		List<ReportBeanDto> margeList = new ArrayList<ReportBeanDto>();
		if(cList !=null){
		for (CustomerTransactionTransportBean cBean : cList) {
			ReportBeanDto first = new ReportBeanDto();
			first.setCreatedAt(cBean.getCreatedAt());
			first.setClientId(ExtractorUtil.generateIdFromString(
					cBean.getCustomerId(), "C"));
			first.setThirdPartyServiceProvider(cBean
					.getThirdPartyServiceProvider());
			first.setMobileNo(cBean.getMobileNo());
			first.setConnectionid(cBean.getConnectionid());
			first.setOperator(cBean.getOperator());
			first.setTransactionName(cBean.getTransactionName());
			first.setAmount(cBean.getAmount());
			first.setCreditAmountFranchisee(cBean.getCreditAmountFranchisee());
			first.setStatus(cBean.getStatus());
			first.setThirdpartytransid(cBean.getClientTransId());
			first.setTransid(cBean.getTransid());
			margeList.add(first);
		}
		}
		for (TransactionTransportBean tBean : transportBeans) {
			ReportBeanDto second = new ReportBeanDto();
			second.setCreatedAt(tBean.getCreatedAt());
			second.setClientId(ExtractorUtil.generateIdFromString(
					tBean.getRetailerId(), "R"));
			second.setThirdPartyServiceProvider(tBean
					.getThirdPartyServiceProvider());
			second.setMobileNo(tBean.getMobileNo());
			second.setConnectionid(tBean.getConnectionid());
			second.setOperator(tBean.getOperator());
			second.setTransactionName(tBean.getTransactionName());
			second.setAmount(tBean.getAmount());
			second.setCreditAmountFranchisee(tBean.getCreditAmountFranchisee());
			second.setStatus(tBean.getStatus());
			second.setThirdpartytransid(tBean.getTransid());
			// second.setTransid(tBean.getThirdpartytransid());
			margeList.add(second);
		}
		return margeList;
	}
	
	public List<TransactionTransportBean> generateElectricityWiseReport(
			String fromDate, String toDate, String sel, String status,String operator) {
		Timestamp frmDate = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDte = TimeStampUtil.getTimeStampFromStringTodate(toDate);
		List<TransactionTransportBean> transactionTransportBeansList = null;
		transactionTransportBeansList = adminReportDao
				.generateElectricityWiseReport(frmDate, toDte, sel, status,operator);
		return transactionTransportBeansList;
	}
}
