package com.cfeindia.b2bserviceapp.service.franchisee;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.common.RegistrationDao;
import com.cfeindia.b2bserviceapp.dao.franchisee.LicPayRefundDao;
import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.LicPremiumBean;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.BalanceCheckingService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Transactional
@Service
public class LicPayServiceImp implements LicPayService {

	@Autowired
	LicPayRefundDao licPayRefundDao;

	@Autowired
	BalanceCheckingService balanceCheckingService;

	@Autowired
	CommonService commonservice;
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private RegistrationDao registrationDao;

	public void LicPayActivityService(
			TransactionTransportBean transactionTransport) {

		FranchiseeCurrBal franchiseeCurrbal = null;
		// String result = null;

		double comissionCheck = 0.0f;
		boolean balanceAvailable = false;
		franchiseeCurrbal = balanceCheckingService.getFrachiseeCurrbalObj(Long
				.valueOf(transactionTransport.getRetailerId()));
		if (franchiseeCurrbal != null) {

			comissionCheck = commonservice.getLicRetailerCommission("FLAT",
					"INSURANCE", "LIC");
			Double percentAmount = comissionCheck
					* transactionTransport.getAmount() / 100;
			balanceAvailable = franchiseeCurrbal.getB2bcurrbal() >= (transactionTransport
					.getAmount() + percentAmount);
			Double balanceAddwithComission = transactionTransport.getAmount()
					+ percentAmount;
			if (!balanceAvailable) {
				transactionTransport.setStatus("REJECTED");
				transactionTransport.setErrorCode(1);
				transactionTransport
						.setMessage("No Balance Available in your account or Some unexpected error has come.");
			} else if (franchiseeCurrbal.getB2bcurrbal() < balanceAddwithComission) {

				transactionTransport.setStatus("REJECTED");
				transactionTransport.setErrorCode(1);
				transactionTransport
						.setMessage("No Balance Available in your account or Some unexpected error has come.");

			} else {

				transactionTransport.setRetailerPreBal(franchiseeCurrbal
						.getB2bcurrbal());

				String transId = CyberTelUtil.generateTransId("LIC",
						transactionTransport.getConnectionid());

				Date date = new Date();
				Timestamp dateTime = new Timestamp(date.getTime());
				transactionTransport.setCreatedAt(dateTime);
				transactionTransport.setTransid(transId);
				transactionTransport.setThirdPartyServiceProvider("CYBERTEL");
				transactionTransport.setOperator("LIC");
				transactionTransport.setRetailerNewBal(franchiseeCurrbal
						.getB2bcurrbal() - balanceAddwithComission);
				balanceCheckingService.reduceFranchiseeCurrentAccountBalance(
						Long.valueOf(transactionTransport.getRetailerId()),
						balanceAddwithComission);

				transactionTransport.setStatus("SUCCESS");
				transactionTransport.setRechargeType("LIC_PREMIUM");
				transactionTransport.setTransactionName("LIC_PREMIUM");

			}

		}

		commonservice.saveEntity(transactionTransport);
	}

	public String LicRefundActivityService(LicPremiumBean licPremiumBean) {
		TransactionTransportBean transactionTransportBean = commonDao
				.getEntityByKey(licPremiumBean.getTransactionId());

		FranchiseeCurrBal franchiseeCurrbal = null;
		double comission = commonservice.getLicRetailerCommission("FLAT",
				"INSURANCE", "LIC");
		double percentamount = licPremiumBean.getAmount() * comission / 100;
		franchiseeCurrbal = balanceCheckingService.getFrachiseeCurrbalObj(Long
				.valueOf(licPremiumBean.getUserId()));
		transactionTransportBean.setRetailerPreBal(franchiseeCurrbal
				.getB2bcurrbal());
		transactionTransportBean.setRetailerNewBal((franchiseeCurrbal
				.getB2bcurrbal())
				+ (licPremiumBean.getAmount())
				+ (percentamount));
		transactionTransportBean.setStatus(CommonConstants.REFUND);
		Date date = new Date();
		Timestamp dateTime = new Timestamp(date.getTime());
		transactionTransportBean.setCreatedAt(dateTime);

		String thirdPartyServiceProvider = "CYBERTEL";
		transactionTransportBean
				.setThirdPartyServiceProvider(thirdPartyServiceProvider);

		transactionTransportBean.setAmount(licPremiumBean.getAmount());
		transactionTransportBean.setConnectionid(licPremiumBean
				.getPolicyNumber());
		Double transAmount = licPremiumBean.getAmount();
		Long retailerId = Long.valueOf(licPremiumBean.getUserId());
		licPayRefundDao.refundUpdate(retailerId, transAmount + percentamount);
		commonDao.updateEntity(transactionTransportBean);
		updateCompanyBalance(transAmount + percentamount, retailerId);
		return CommonConstants.SUCCESS;

	}

	private void updateCompanyBalance(Double refundedAmount, Long userId) {
		CompanyBalance companyBalance = registrationDao
				.loadCompanyAccountBalance();
		CompanyBalTransactionLog cLog = new CompanyBalTransactionLog();
		Users users = new Users();
		users.setUserId(userId);
		cLog.setUserType(CommonConstants.ROLE_FRANCHISEE);
		cLog.setUserId(users);
		cLog.setTransferAmount(refundedAmount);
		cLog.setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
		cLog.setCreatedAt(TimeStampUtil.getTimestamp());
		cLog.setTransactionId(CyberTelUtil.generateTransId(
				CommonConstants.COMP_TO_FRAN, null));
		cLog.setPreBalance(companyBalance.getCurrBalance());
		cLog.setNewBalance(companyBalance.getCurrBalance() - refundedAmount);
		companyBalance.setCurrBalance(companyBalance.getCurrBalance()
				- refundedAmount);
		commonDao.updateEntity(companyBalance);
		commonDao.saveEntity(cLog);

	}

}
