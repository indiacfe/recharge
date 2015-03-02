package com.cfeindia.b2bserviceapp.service.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.converter.TranTransportBeanToDtoConverter;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.common.FranchiseeInfoDao;
import com.cfeindia.b2bserviceapp.dao.common.RegistrationDao;
import com.cfeindia.b2bserviceapp.dao.distributor.FundTransferDao;
import com.cfeindia.b2bserviceapp.dto.RefundAmountDto;
import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service("refundAmountService")
@Transactional
public class RefundAmountServiceImpl implements RefundAmountService {
	@Autowired
	private CommonDao commonDao;
	@Autowired
	RegistrationDao registrationDao;
	@Autowired
	FundTransferDao fundTransferDao;
	@Autowired
	FranchiseeInfoDao franchiseeInfoDao;

	public RefundAmountDto searchTransactionId(String tid) {
		TranTransportBeanToDtoConverter tranTransportBeanToDtoConverter = new TranTransportBeanToDtoConverter();
		RefundAmountDto refundAmountDto = new RefundAmountDto();
		TransactionTransportBean transactionTransportBean = commonDao
				.getEntityByKey(tid);
		FranchiseeRefundRequests franchiseeRefundRequests = commonDao
				.getFranchiseeRefundRequestBean(tid);
		if (transactionTransportBean != null
				&& franchiseeRefundRequests != null) {
			String retailerid = transactionTransportBean.getRetailerId();
			UserDetail userDetail = commonDao.getUserDetail(Long
					.parseLong(retailerid));
			Users user = commonDao.getUsers(Long.parseLong(retailerid));
			refundAmountDto = tranTransportBeanToDtoConverter
					.convert(transactionTransportBean);
			refundAmountDto.setRetailerName(userDetail.getUserName());
			refundAmountDto.setRetailerMobNo(user.getUsername());
			return refundAmountDto;
		} else {
			return refundAmountDto;
		}
	}

	public List<RefundAmountDto> getAllRefundRequests() {
		TranTransportBeanToDtoConverter tranTransportBeanToDtoConverter = new TranTransportBeanToDtoConverter();
		RefundAmountDto refundAmountDto = null;
		List<RefundAmountDto> refundAmountDtoList = new ArrayList<RefundAmountDto>();
		List<FranchiseeRefundRequests> franchiseeRefundRequestsList = (List<FranchiseeRefundRequests>) commonDao
				.getFranchiseeRefundRequestsList();
		for (FranchiseeRefundRequests franchiseeRefundRequests : franchiseeRefundRequestsList) {
			TransactionTransportBean transactionTransportBean = commonDao
					.getEntityByKey(franchiseeRefundRequests.getTransId());
			String retailerId = transactionTransportBean.getRetailerId();
			UserDetail userDetail = commonDao.getUserDetail(Long
					.parseLong(retailerId));
			Users user = commonDao.getUsers(Long.parseLong(retailerId));
			refundAmountDto = tranTransportBeanToDtoConverter
					.convert(transactionTransportBean);
			refundAmountDto.setRetailerName(userDetail.getFirmName());
			refundAmountDto.setRetailerMobNo(user.getUsername());
			// refundAmountDto.setRetailerId(ExtractorUtil.extractIdFromString(retailerId));
			refundAmountDto.setCreatedAt(transactionTransportBean
					.getCreatedAt());
			refundAmountDto.setUpdatedAt(franchiseeRefundRequests
					.getCreatedat());
			refundAmountDtoList.add(refundAmountDto);

		}
		return refundAmountDtoList;
	}

	public String refundAmount(Long id, String senderId, Double refundedAmount,
			String franchiseeId,String thirdPartyServiceProviderName) {
		updateCompanyBalance(refundedAmount, franchiseeId,thirdPartyServiceProviderName);
		double newB2bBal = updateFranchiseAccount(franchiseeId, senderId,
				refundedAmount);
		updateTranBean(id, refundedAmount, newB2bBal);
		return CommonConstants.SUCCESS;
	}

	private void updateFranchiseeRefundRequests(String transId, String status,
			String remark) {
		FranchiseeRefundRequests franchiseeRefundRequests = commonDao
				.getFranchiseeRefundRequestBean(transId);
		franchiseeRefundRequests.setRemark(remark);
		franchiseeRefundRequests.setStatus(status);
		franchiseeRefundRequests.setUpdatedAt(TimeStampUtil.getTimestamp());
		commonDao.updateEntity(franchiseeRefundRequests);

	}

	private void updateTranBean(Long id, Double refundedAmount, double newB2bBal) {
		TransactionTransportBean transactionTransportBean = (TransactionTransportBean) commonDao
				.getEntityByPrimaryKey(TransactionTransportBean.class, id);
		transactionTransportBean.setStatus(CommonConstants.REFUND);
		updateFranchiseeRefundRequests(transactionTransportBean.getTransid(),
				CommonConstants.REFUND, "");
		transactionTransportBean.setUpdatedAt(TimeStampUtil.getTimestamp());
		transactionTransportBean.setRefundAmount(refundedAmount);
		transactionTransportBean.setRetailerNewBal(newB2bBal);
		commonDao.updateEntity(transactionTransportBean);
	}

	public void rejectedRemark(Long id, String remark) {
		TransactionTransportBean transactionTransportBean = (TransactionTransportBean) commonDao
				.getEntityByPrimaryKey(TransactionTransportBean.class, id);
		transactionTransportBean.setStatus(CommonConstants.SUCCESS);
		updateFranchiseeRefundRequests(transactionTransportBean.getTransid(),
				CommonConstants.REFUND_REJECT, remark);
		transactionTransportBean.setUpdatedAt(TimeStampUtil.getTimestamp());

		commonDao.updateEntity(transactionTransportBean);
	}

	private void updateCompanyBalance(Double refundedAmount, String userId,String thirdPartyServiceProviderName) {
		CompanyBalance companyBalance = registrationDao
				.loadCompanyAccountBalance();
		CompanyBalTransactionLog cLog = new CompanyBalTransactionLog();
		Users users = new Users();
		users.setUserId(CyberTelUtil.getStrInLong(userId));
		cLog.setUserType(CommonConstants.ROLE_FRANCHISEE);
		cLog.setUserId(users);
		cLog.setTransferAmount(refundedAmount);
		cLog.setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
		cLog.setCreatedAt(TimeStampUtil.getTimestamp());
		cLog.setTransactionId(CyberTelUtil.generateTransId(
				CommonConstants.COMP_TO_FRAN, null));
		cLog.setPreBalance(companyBalance.getCurrBalance());
		cLog.setNewBalance(companyBalance.getCurrBalance() - refundedAmount);
		cLog.setRemark(thirdPartyServiceProviderName);
		companyBalance.setCurrBalance(companyBalance.getCurrBalance()
				- refundedAmount);
		commonDao.updateEntity(companyBalance);
		commonDao.saveEntity(cLog);

	}

	private double updateFranchiseAccount(String id, String userId,
			Double refundedAmount) {
		long franchID = Long.parseLong(id);
		FranchiseeCurrBal franchiseeCurrBal = fundTransferDao
				.getCurrentDetail(franchID);
		FranchiseeBalanceTransferLog log = new FranchiseeBalanceTransferLog();
		log.setFranchiseeId(franchID);
		log.setTransactionType(CommonConstants.TRANSFER_TYPE_CREDIT);
		log.setTransactionFrom(CommonConstants.COMPANY_TRANSFER_FROM);
		log.setTransactionTo(CommonConstants.DIST_TRANSFER_TO);
		log.setCreatedAt(TimeStampUtil.getTimestamp());
		log.setTransactionId(CyberTelUtil.generateTransId(
				CommonConstants.COMP_TO_FRAN, null));
		log.setSenderId(Long.valueOf(userId));
		log.setNewB2bAdUnitBal(franchiseeCurrBal.getB2bcurrbaladunit());
		log.setPreB2bCurrAdUnitBal(franchiseeCurrBal.getB2bcurrbaladunit());
		log.setPreb2bcurrbal(franchiseeCurrBal.getB2bcurrbal());
		log.setTransferAmount(refundedAmount);
		log.setNewB2bCurrBal(franchiseeCurrBal.getB2bcurrbal() + refundedAmount);

		fundTransferDao.setFranchiseeBalTransferLog(log);
		double finalAmount = fundTransferDao.updateFranchiseeCurrBal(
				franchiseeCurrBal.getFranchiseeid(), refundedAmount);
		return finalAmount;
	}

	public List<TransactionTransportBean> getrefundDetail(String id,
			String rechargeType, String fromDate, String toDate) {
		Timestamp fromdateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		List<TransactionTransportBean> transportBeanslist = franchiseeInfoDao
				.getRefundDetail(id, rechargeType, fromdateTimeStamp,
						toDateTimeStamp);
		return transportBeanslist;
	}

	public List<RefundAmountDto> getAllRejctedRefundRequests(String fromDate,
			String toDate) {
		TranTransportBeanToDtoConverter tranTransportBeanToDtoConverter = new TranTransportBeanToDtoConverter();
		RefundAmountDto refundAmountDto = null;
		Timestamp frmDate = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDte = TimeStampUtil.getTimeStampFromStringTodate(toDate);
		List<RefundAmountDto> rejectedRefundDtoList = new ArrayList<RefundAmountDto>();
		List<FranchiseeRefundRequests> franchiseeRefundRequestsList = (List<FranchiseeRefundRequests>) commonDao
				.getFranchiseeRejectedRefundRequestsList(frmDate, toDte);
		for (FranchiseeRefundRequests franchiseeRefundRequests : franchiseeRefundRequestsList) {
			TransactionTransportBean transactionTransportBean = commonDao
					.getEntityByKey(franchiseeRefundRequests.getTransId());
			if (transactionTransportBean == null) {
				continue;
			}
			String retailerId = transactionTransportBean.getRetailerId();
			UserDetail userDetail = commonDao.getUserDetail(Long
					.parseLong(retailerId));
			Users user = commonDao.getUsers(Long.parseLong(retailerId));
			refundAmountDto = tranTransportBeanToDtoConverter
					.convert(transactionTransportBean);
			refundAmountDto.setRetailerName(userDetail.getFirmName());
			refundAmountDto.setRetailerMobNo(user.getUsername());
			// refundAmountDto.setRetailerId(ExtractorUtil.extractIdFromString(retailerId));
			refundAmountDto.setCreatedAt(transactionTransportBean
					.getCreatedAt());
			refundAmountDto.setUpdatedAt(franchiseeRefundRequests
					.getCreatedat());
			refundAmountDto.setRemark(franchiseeRefundRequests.getRemark());
			rejectedRefundDtoList.add(refundAmountDto);

		}
		return rejectedRefundDtoList;
	}

}
