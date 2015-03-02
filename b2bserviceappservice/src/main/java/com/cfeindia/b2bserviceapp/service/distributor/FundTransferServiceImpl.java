package com.cfeindia.b2bserviceapp.service.distributor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.distributor.FundTransferDao;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.NewIdCreationBalanceLog;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service("fundTransferService")
@Transactional
public class FundTransferServiceImpl implements FundTransferService {
	@Autowired
	private FundTransferDao fundTransferDao;
	
	@Autowired
	CommonDao commonDao;

	public String fundToRetailerCurrService(Long retailerId, Long senderId,
			double amount) {
		FranchiseeCurrBal franchiseCurrBal = fundTransferDao
				.getCurrentDetail(retailerId);
		DistributorCurrbal distributorCurrbal = fundTransferDao
				.getDistCurrBal(senderId);
		double fCurrBal = franchiseCurrBal.getB2bcurrbal();
		double dCurrBal = distributorCurrbal.getB2bCurrAcBalance();
		if (dCurrBal >= amount) {
			FranchiseeBalanceTransferLog franchiseeBalanceTransferLog = new FranchiseeBalanceTransferLog();
			DistributorBalanceTransferLog distributorBalanceTransferLog = new DistributorBalanceTransferLog();
			Timestamp timeStamp = TimeStampUtil.getTimestamp();
			franchiseeBalanceTransferLog.setPreb2bcurrbal(fCurrBal);
			distributorBalanceTransferLog.setPreb2bcurrbal(dCurrBal);
			fCurrBal = fCurrBal + amount;
			dCurrBal = dCurrBal - amount;
			franchiseeBalanceTransferLog.setNewB2bCurrBal(fCurrBal);
			franchiseeBalanceTransferLog.setTransactionId(CyberTelUtil
					.generateTransId(CommonConstants.DISTRIBUTOR_TO_RETAILER,
							null));
			franchiseeBalanceTransferLog.setCreatedAt(timeStamp);
			franchiseeBalanceTransferLog.setFranchiseeId(retailerId);
			franchiseeBalanceTransferLog
					.setTransactionFrom(CommonConstants.DIST_TRANSFER_FROM);
			franchiseeBalanceTransferLog
					.settransactionTo(CommonConstants.DIST_TRANSFER_TO);
			franchiseeBalanceTransferLog.setTransferAmount(amount);
			franchiseeBalanceTransferLog
					.setTransactionType(CommonConstants.TRANSFER_TYPE_CREDIT);
			franchiseeBalanceTransferLog.setSenderId(Long.valueOf(senderId));
			distributorBalanceTransferLog.setNewB2bCurrBal(dCurrBal);
			distributorBalanceTransferLog.setTransactionId(CyberTelUtil
					.generateTransId(CommonConstants.DISTRIBUTOR_TO_RETAILER,
							null));
			distributorBalanceTransferLog.setCreatedAt(timeStamp);
			distributorBalanceTransferLog.setDistId(senderId);
			distributorBalanceTransferLog
					.setTransferFrom(CommonConstants.DIST_TRANSFER_FROM);
			distributorBalanceTransferLog
					.setTransferTo(CommonConstants.DIST_TRANSFER_TO);
			distributorBalanceTransferLog
					.setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
			distributorBalanceTransferLog.setTransferAmount(amount);
			distributorBalanceTransferLog.setDistId(Long.valueOf(senderId));
			UserDetail retailerDetail = commonDao.getUserDetail(retailerId);
			distributorBalanceTransferLog.setRetailerDetail(retailerDetail);
			franchiseCurrBal.setUpdatedAt(timeStamp);
			fundTransferDao.reduceDistributorB2bBal(Long.valueOf(senderId),
					amount);
			fundTransferDao.updateFranchiseeCurrBal(retailerId, amount);
			fundTransferDao
					.setDistributorBalTransferLog(distributorBalanceTransferLog);
			fundTransferDao
					.setFranchiseeBalTransferLog(franchiseeBalanceTransferLog);
			return CommonConstants.SUCCESS;
		} else {
			return CommonConstants.FAILURE;
		}
	}

	public String fundToRetailerAdUnitService(Long retailerId, Long senderId,
			double amount) {
		FranchiseeCurrBal franchiseCurrBal = fundTransferDao
				.getCurrentDetail(retailerId);
		DistributorCurrbal distributorCurrbal = fundTransferDao
				.getDistCurrBal(senderId);
		double fCurrBal = franchiseCurrBal.getB2bcurrbaladunit();
		double dCurrBal = distributorCurrbal.getB2bCurrAcAdUnitBal();
		if (dCurrBal >= amount) {
			FranchiseeBalanceTransferLog franchiseeBalanceTransferLog = new FranchiseeBalanceTransferLog();
			DistributorBalanceTransferLog distributorBalanceTransferLog = new DistributorBalanceTransferLog();
			Timestamp timeStamp = TimeStampUtil.getTimestamp();
			franchiseeBalanceTransferLog.setPreB2bCurrAdUnitBal(fCurrBal);
			distributorBalanceTransferLog.setPreB2bCurrAdUnitBal(dCurrBal);
			fCurrBal = fCurrBal + amount;
			dCurrBal = dCurrBal - amount;
			franchiseeBalanceTransferLog.setNewB2bAdUnitBal(fCurrBal);
			franchiseeBalanceTransferLog.setTransactionId(CyberTelUtil
					.generateTransId(CommonConstants.DISTRIBUTOR_TO_RETAILER,
							null));
			franchiseeBalanceTransferLog.setCreatedAt(timeStamp);
			franchiseeBalanceTransferLog.setFranchiseeId(retailerId);
			franchiseeBalanceTransferLog
					.setTransactionFrom(CommonConstants.DIST_TRANSFER_FROM);
			franchiseeBalanceTransferLog
					.settransactionTo(CommonConstants.DIST_TRANSFER_TO);
			franchiseeBalanceTransferLog.setTransferAmount(amount);
			franchiseeBalanceTransferLog
					.setTransactionType(CommonConstants.TRANSFER_TYPE_CREDIT);
			franchiseeBalanceTransferLog.setSenderId(Long.valueOf(senderId));
			distributorBalanceTransferLog.setNewB2bAdUnitBal(dCurrBal);
			distributorBalanceTransferLog.setTransactionId(CyberTelUtil
					.generateTransId(CommonConstants.DISTRIBUTOR_TO_RETAILER,
							null));
			distributorBalanceTransferLog.setCreatedAt(timeStamp);
			distributorBalanceTransferLog.setDistId(senderId);
			distributorBalanceTransferLog
					.setTransferFrom(CommonConstants.DIST_TRANSFER_FROM);
			distributorBalanceTransferLog
					.setTransferTo(CommonConstants.DIST_TRANSFER_TO);
			distributorBalanceTransferLog
					.setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
			distributorBalanceTransferLog.setTransferAmount(amount);
			distributorBalanceTransferLog.setDistId(Long.valueOf(senderId));
			UserDetail retailerDetail = commonDao.getUserDetail(retailerId);
			distributorBalanceTransferLog.setRetailerDetail(retailerDetail);
			franchiseCurrBal.setUpdatedAt(timeStamp);
			fundTransferDao.reduceDistributorAddUnitBal(Long.valueOf(senderId),
					amount);
			fundTransferDao.updateFranchiseeAddUnitBal(retailerId, amount);
			fundTransferDao
					.setDistributorBalTransferLog(distributorBalanceTransferLog);
			fundTransferDao
					.setFranchiseeBalTransferLog(franchiseeBalanceTransferLog);
			return CommonConstants.SUCCESS;
		} else {
			return CommonConstants.FAILURE;
		}
	}

	public String distAdUnitBalTransTodistCurrBal(Long distributorId,
			double transferAmount) {

		DistributorBalanceTransferLog distributorBalanceTransferLog = new DistributorBalanceTransferLog();
		// long distId=Long.parseLong(distributorId);
		DistributorCurrbal distributorCurrbal = fundTransferDao
				.getDistCurrBal(distributorId);
		double dCurrBal = distributorCurrbal.getB2bCurrAcBalance();
		distributorBalanceTransferLog.setPreb2bcurrbal(dCurrBal);
		double dAdBal = distributorCurrbal.getB2bCurrAcAdUnitBal();
		if (dAdBal >= transferAmount) {
			distributorBalanceTransferLog
					.setPreB2bCurrAdUnitBal(distributorCurrbal
							.getB2bCurrAcAdUnitBal());
			DistributorCommision distributorCommision = fundTransferDao
					.getDistributorCommision(distributorId);
			double distCommision = distributorCommision.getCommision();
			distCommision = transferAmount * distCommision / 100;
			dCurrBal = dCurrBal + transferAmount + distCommision;
			distributorBalanceTransferLog.setNewB2bCurrBal(dCurrBal);
			dAdBal = dAdBal - transferAmount;
			distributorBalanceTransferLog.setNewB2bAdUnitBal(dAdBal);
			distributorBalanceTransferLog
					.setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
			distributorBalanceTransferLog.setTransferAmount(transferAmount);
			distributorBalanceTransferLog.setDistId(distributorId);
			distributorBalanceTransferLog.setCreatedAt(TimeStampUtil
					.getTimestamp());
			distributorBalanceTransferLog
					.setTransactionId(CyberTelUtil
							.generateTransId(
									CommonConstants.DIST_CURR_AD_UNIT_TO_DIST_CURR_TRANSID_PREFIX,
									null));
			distributorBalanceTransferLog
					.setTransferFrom(CommonConstants.DIST_INTER_TRANSFER_FROM);
			distributorBalanceTransferLog
					.setTransferTo(CommonConstants.DIST_INTER_TRANSFER_TO);
			fundTransferDao.updateDistributorB2bToCurrBal(
					distributorCurrbal.getId(), dCurrBal, dAdBal);
			fundTransferDao
					.setDistributorBalTransferLog(distributorBalanceTransferLog);
			return CommonConstants.SUCCESS;
		} else
			return CommonConstants.FAILURE;

	}

	public String companyBalanceTransToDistService(Long distributorId,
			double transferAmount, String companyName) {

		CompanyDistributorTransactionLog companyDistributorTransactionLog = new CompanyDistributorTransactionLog();
		DistributorBalanceTransferLog distributorBalanceTransferLog = new DistributorBalanceTransferLog();
		// long distId=Long.parseLong(distributorId);
		distributorBalanceTransferLog.setDistId(distributorId);
		distributorBalanceTransferLog
				.setCreatedAt(TimeStampUtil.getTimestamp());
		DistributorCurrbal distributorCurrbal = fundTransferDao
				.getDistCurrBal(distributorId);
		double dCurrBal = distributorCurrbal.getB2bCurrAcAdUnitBal();
		distributorBalanceTransferLog.setPreB2bCurrAdUnitBal(dCurrBal);
		CompanyBalance companyBalance = fundTransferDao
				.getCompanyBalance(companyName);
		double compCurrBal = companyBalance.getCurrBalance();
		if (compCurrBal >= transferAmount) {
			companyDistributorTransactionLog.setPreBalance(compCurrBal);

			dCurrBal = dCurrBal + transferAmount;
			distributorBalanceTransferLog.setNewB2bAdUnitBal(dCurrBal);
			distributorBalanceTransferLog.setTransactionId(CyberTelUtil
					.generateTransId(
							CommonConstants.COMPANY_TO_DIST_TRANSID_PREFIX,
							null));
			distributorBalanceTransferLog.setTransferAmount(transferAmount);
			distributorBalanceTransferLog
					.setTransferFrom(CommonConstants.COMPANY_TRANSFER_FROM);
			distributorBalanceTransferLog
					.setTransferTo(CommonConstants.COMPANY_TRANSFER_TO);
			distributorBalanceTransferLog
					.setTransferType(CommonConstants.TRANSFER_TYPE_CREDIT);
			compCurrBal = compCurrBal - transferAmount;
			companyDistributorTransactionLog.setNewBalance(compCurrBal);
			distributorCurrbal.setB2bCurrAcAdUnitBal(dCurrBal);
			companyBalance.setCurrBalance(compCurrBal);
			Users user = new Users();
			user.setUserId(distributorId);
			companyDistributorTransactionLog.setDistributorId(user);
			companyDistributorTransactionLog.setTransferAmount(transferAmount);
			companyDistributorTransactionLog
					.setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
			companyDistributorTransactionLog.setTransId(CyberTelUtil
					.generateTransId(
							CommonConstants.COMPANY_TO_DIST_TRANSID_PREFIX,
							null));
			companyDistributorTransactionLog.setCreatedAt(TimeStampUtil
					.getTimestamp());
			companyDistributorTransactionLog.setTransferFrom("COMPCURRADUNIT");

			companyDistributorTransactionLog.setTransferTo("DISTCURRUNIT");

			commonDao.updateCompanybalance(companyBalance);
			fundTransferDao.updateDistributorB2bAdunitBal(distributorId);
			fundTransferDao
					.companyDistributorTransactionLog(companyDistributorTransactionLog);
			fundTransferDao
					.setDistributorBalTransferLog(distributorBalanceTransferLog);
			return CommonConstants.SUCCESS;
		} else {
			return CommonConstants.FAILURE;
		}

	}

	public String companyBalanceTransToDistServiceBusiness(Long distributorId,
			double transferAmount, String companyName) {

		CompanyDistributorTransactionLog companyDistributorTransactionLog = new CompanyDistributorTransactionLog();
		DistributorBalanceTransferLog distributorBalanceTransferLog = new DistributorBalanceTransferLog();
		// long distId=Long.parseLong(distributorId);
		distributorBalanceTransferLog.setDistId(distributorId);
		distributorBalanceTransferLog
				.setCreatedAt(TimeStampUtil.getTimestamp());
		DistributorCurrbal distributorCurrbal = fundTransferDao
				.getDistCurrBal(distributorId);
		double dCurrBal = distributorCurrbal.getCurrAcBalance();
		distributorBalanceTransferLog.setPreBusinessBal(dCurrBal);
		CompanyBalance companyBalance = fundTransferDao
				.getCompanyBalance(companyName);
		double compCurrBal = companyBalance.getCurrBalance();
		if (compCurrBal >= transferAmount) {
			companyDistributorTransactionLog.setPreBalance(compCurrBal);
			dCurrBal = dCurrBal + transferAmount;
			distributorBalanceTransferLog.setNewBusinessBal(dCurrBal);
			distributorBalanceTransferLog.setTransactionId(CyberTelUtil
					.generateTransId(
							CommonConstants.COMPANY_TO_DIST_TRANSID_PREFIX,
							null));
			distributorBalanceTransferLog.setTransferAmount(transferAmount);
			distributorBalanceTransferLog
					.setTransferFrom(CommonConstants.COMPANY_TRANSFER_FROM);
			distributorBalanceTransferLog
					.setTransferTo(CommonConstants.COMP_TO_DIST_CURRBAL);
			distributorBalanceTransferLog
					.setTransferType(CommonConstants.TRANSFER_TYPE_CREDIT);
			compCurrBal = compCurrBal - transferAmount;
			companyDistributorTransactionLog.setNewBalance(compCurrBal);
			companyBalance.setCurrBalance(compCurrBal);
			Users user = new Users();
			user.setUserId(distributorId);
			companyDistributorTransactionLog.setDistributorId(user);
			companyDistributorTransactionLog.setTransferAmount(transferAmount);
			companyDistributorTransactionLog
					.setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
			companyDistributorTransactionLog.setTransId(CyberTelUtil
					.generateTransId(
							CommonConstants.COMPANY_TO_DIST_TRANSID_PREFIX,
							null));
			companyDistributorTransactionLog.setCreatedAt(TimeStampUtil
					.getTimestamp());
			companyDistributorTransactionLog.setTransferFrom("COMP_BUSINESS");
			companyDistributorTransactionLog.setTransferTo("DIST_BUSSINESS");
			commonDao.updateCompanybalance(companyBalance);
			fundTransferDao.addDistributorBusinessBal(distributorId,
					transferAmount);
			fundTransferDao
					.companyDistributorTransactionLog(companyDistributorTransactionLog);
			fundTransferDao
					.setDistributorBalTransferLog(distributorBalanceTransferLog);
			return CommonConstants.SUCCESS;
		} else {
			return CommonConstants.FAILURE;
		}

	}

	public FranchiseeCurrBal getCurrentDetail(Long franchID) {
		return fundTransferDao.getCurrentDetail(franchID);
	}

	public DistributorCurrbal getDistCurrBal(Long distID) {
		return fundTransferDao.getDistCurrBal(distID);
	}

	@Override
	public NewIdCreationBalanceLog getCreator(String distrId, String retailerId) {
		Users user = null;
		NewIdCreationBalanceLog balanceLog = null;
		if (retailerId.startsWith("R")) {

			user = commonDao.searchUserBasedOnIdOrMobNum(ExtractorUtil
					.extractIdFromString(retailerId));

		} else {
			user = commonDao.searchUserBasedOnIdOrMobNum(retailerId);

		}
		if (user != null) {
			balanceLog = fundTransferDao.getValidUser(Long.parseLong(distrId),
					user.getUserId());
		}

		return balanceLog;
	}
	
	public boolean checkDuplicate(Long franchiseeId){
		return fundTransferDao.checkDuplicate(franchiseeId);
	}

	
	
}
