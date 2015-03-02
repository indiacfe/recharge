package com.cfeindia.b2bserviceapp.service.customer;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CacheManager;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.BalanceCheckingService;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ApplicationContextUtils;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class CustomerRechargeServiceImpl implements CustomerRechargeService {
	@Autowired
	private CommonDao commonDao;

	ETransactionRequestService eTransactionRequestService;

	@Autowired
	BalanceCheckingService balanceCheckingService;

	@Autowired
	CommonService commonservice;

	@Autowired
	ApplicationContextUtils applicationContextUtils;

	public void doRechargeService(TransactionTransportBean transactionTransport) {
		ThirdPartyDetailObject thirdPartyDetailObject = null;
		CustomerCurrentBalance customercurrbal = null;
		try {

			customercurrbal = balanceCheckingService.getCustomerCurrbalObj(Long
					.valueOf(transactionTransport.getRetailerId()));
			boolean balanceAvailable = false;
			balanceAvailable = customercurrbal.getCurrAcBalance() >= transactionTransport
					.getAmount();
			if (!balanceAvailable) {
				transactionTransport.setErrorCode(1);
				transactionTransport
						.setMessage("No Balance Available in your account or Some unexpected error has come.");
			} else {
				String thirdPartyAPISelection = commonservice
						.getThirdPartyAPISelection(
								transactionTransport.getRechargeType(),
								transactionTransport.getOperator());
				thirdPartyDetailObject = CacheManager
						.getCurrentThirdPartyApi(thirdPartyAPISelection);
				transactionTransport.setAgentId(thirdPartyDetailObject
						.getAgentid());
				transactionTransport
						.setToken(thirdPartyDetailObject.getToken());
				transactionTransport.setToken1(thirdPartyDetailObject
						.getToken1());
				transactionTransport.setToken2(thirdPartyDetailObject
						.getToken2());
				transactionTransport.setToken3(thirdPartyDetailObject
						.getToken3());
				transactionTransport.setAppver(thirdPartyDetailObject
						.getApiVersion() + "");
				transactionTransport.setLoginStatus(thirdPartyDetailObject
						.getLoginstatus());
				transactionTransport.setRecharge(transactionTransport
						.getAmount() + "");

				transactionTransport.setConnectURL(thirdPartyDetailObject
						.getConnectURL());
				createTransId(transactionTransport);

				transactionTransport.setOperatorCode(CacheManager
						.getOperatorCode(thirdPartyDetailObject
								.getThirpartyprovidername(),
								transactionTransport.getRechargeType(),
								transactionTransport.getOperator()));
				transactionTransport
						.setThirdPartyServiceProvider(thirdPartyDetailObject
								.getThirpartyprovidername());
				eTransactionRequestService = applicationContextUtils
						.getServiceProvierBean(thirdPartyDetailObject
								.getThirpartyprovidername());

				if (transactionTransport.getDob() != null) {
					try {
						String newDob = transactionTransport.getDob();

						String changedDob = newDob.replaceAll("/", "-");

						String[] dobArray = changedDob.split("-");
						String finalDate = null;
						finalDate = (dobArray[1]) + "-" + dobArray[0] + "-"
								+ dobArray[2];

						transactionTransport.setDob(finalDate);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (eTransactionRequestService != null) {

					eTransactionRequestService
							.doTransaction(transactionTransport);
					if (transactionTransport.getErrorCode() == 0
							|| transactionTransport.getThirdpartytransid() != null) {
						transactionTransport.getThirdpartytransid();
						transactionTransport.setRetailerPreBal(customercurrbal
								.getCurrAcBalance());
						double transAmountActual = transactionTransport
								.getAmount();
						double commision = commonservice.getCustomerCommission(
								thirdPartyDetailObject
										.getThirpartyprovidername(),
								transactionTransport.getRechargeType(),
								transactionTransport.getOperator(), Long
										.parseLong(transactionTransport
												.getRetailerId()));

						commision = transAmountActual * commision / 100;
						transactionTransport
								.setCreditAmountFranchisee(commision);
						transAmountActual = transAmountActual - commision;
						transactionTransport.setRetailerNewBal(customercurrbal
								.getCurrAcBalance() - transAmountActual);
						balanceCheckingService.reducecustomerMobileBalance(Long
								.valueOf(transactionTransport.getRetailerId()),
								transAmountActual);
						transactionTransport.setStatus("SUCCESS");
					}
				}
			}
		} catch (MalformedURLException e) {
			transactionTransport.setErrorCode(2);
			transactionTransport.setErrorcode("2");
			transactionTransport.setMessage("System Error");
			e.printStackTrace();
		} catch (Exception e) {
			transactionTransport.setErrorCode(2);
			transactionTransport.setErrorcode("2");
			transactionTransport.setMessage("System Error");
			e.printStackTrace();
			try {
				if (thirdPartyDetailObject != null && customercurrbal != null) {
					double transAmountActual = transactionTransport.getAmount();
					double commision = commonservice.getCustomerCommission(
							thirdPartyDetailObject.getThirpartyprovidername(),
							transactionTransport.getRechargeType(),
							transactionTransport.getOperator(), Long
									.parseLong(transactionTransport
											.getRetailerId()));
					commision = transAmountActual * commision / 100;
					transactionTransport.setCreditAmountFranchisee(commision);
					transAmountActual = transAmountActual - commision;
					transactionTransport.setRetailerNewBal(customercurrbal
							.getCurrAcBalance() - transAmountActual);
					balanceCheckingService.reducecustomerMobileBalance(
							Long.valueOf(transactionTransport.getRetailerId()),
							transAmountActual);

				}
			} catch (Exception e1) {
				transactionTransport.setErrorCode(2);
				transactionTransport.setErrorcode("2");
				transactionTransport.setMessage("System has Error");
				e1.printStackTrace();
			}
			transactionTransport.setStatus("ERROR");
		}
		commonservice
				.saveEntity(getCustomerTransportConverter(transactionTransport));
	}

	private void createTransId(TransactionTransportBean transactionTransport) {
		StringBuilder merchantTransIdBuilder = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		if (transactionTransport.getTransactionName().equalsIgnoreCase(
				"MOBILE_RECHARGE")) {
			merchantTransIdBuilder.append("MOB");
			// merchantTransIdBuilder.append(transactionTransport.getMobileNo());
		} else if (transactionTransport.getTransactionName()
				.equalsIgnoreCase("DTH_RECHARGE")) {
			merchantTransIdBuilder.append("DTH");
			// merchantTransIdBuilder.append(transactionTransport.getConnectionid());

		} else if (transactionTransport.getTransactionName()
				.equalsIgnoreCase("DATACARD_RECHARGE")) {
			merchantTransIdBuilder.append("DTC");
			// merchantTransIdBuilder.append(transactionTransport.getConnectionid());
		} else if (transactionTransport.getTransactionName()
				.equalsIgnoreCase("POSTPAID_BILL")) {
			merchantTransIdBuilder.append("PSB");
			// merchantTransIdBuilder.append(transactionTransport.getConnectionid());
		} else if (transactionTransport.getTransactionName()
				.equalsIgnoreCase("LANDLINE")) {
			merchantTransIdBuilder.append("LAN");
			// merchantTransIdBuilder.append(transactionTransport.getConnectionid());
		} else if (transactionTransport.getTransactionName()
				.equalsIgnoreCase("ELECTRICITY")) {
			merchantTransIdBuilder.append("ELC");
			// merchantTransIdBuilder.append(transactionTransport.getConnectionid());
		} else if (transactionTransport.getTransactionName()
				.equalsIgnoreCase("GAS_BILL")) {
			merchantTransIdBuilder.append("GAS");
		} else if (transactionTransport.getTransactionName()
				.equalsIgnoreCase("INSURANCE_BILL")) {
			merchantTransIdBuilder.append("ISB");
		} else if (transactionTransport.getTransactionName()
				.equalsIgnoreCase("CONNECTION")) {
			merchantTransIdBuilder.append("DCN");
		}
		merchantTransIdBuilder.append(CacheManager
				.getcustmerUserName(Long.parseLong(transactionTransport
						.getRetailerId())));
		merchantTransIdBuilder.append(sdf.format(new Date()));
		transactionTransport.setCreatedAt(TimeStampUtil.getTimestamp());
		transactionTransport.setTransid(merchantTransIdBuilder
				.toString());
	}

	private CustomerTransactionTransportBean getCustomerTransportConverter(
			TransactionTransportBean transactionTransportBean) {
		CustomerTransactionTransportBean customerTransactionTransportBean = new CustomerTransactionTransportBean();
		customerTransactionTransportBean.setAmount(transactionTransportBean
				.getAmount());
		customerTransactionTransportBean
				.setConnectionid(transactionTransportBean.getConnectionid());

		customerTransactionTransportBean.setCreatedAt(transactionTransportBean
				.getCreatedAt());
		customerTransactionTransportBean.setErrorCode(transactionTransportBean
				.getErrorCode());
		customerTransactionTransportBean.setErrorcode(transactionTransportBean
				.getErrorcode());
		customerTransactionTransportBean.setMessage(transactionTransportBean
				.getMessage());
		customerTransactionTransportBean.setMobileNo(transactionTransportBean
				.getMobileNo());
		customerTransactionTransportBean.setOperator(transactionTransportBean
				.getOperator());
		customerTransactionTransportBean
				.setResponsecode(transactionTransportBean.getResponsecode());
		customerTransactionTransportBean.setCustomerId(transactionTransportBean
				.getRetailerId());
		customerTransactionTransportBean
				.setThirdpartytransid(transactionTransportBean
						.getThirdpartytransid());
		customerTransactionTransportBean
				.setThirdPartyTransDateTime(transactionTransportBean
						.getThirdPartyTransDateTime());
		customerTransactionTransportBean
				.setTransactionName(transactionTransportBean
						.getTransactionName());
		customerTransactionTransportBean
				.setThirdpartyoperatortransid(transactionTransportBean
						.getThirdpartyoperatortransid());
		customerTransactionTransportBean
				.setClientTransId(transactionTransportBean.getClientTransId());
		customerTransactionTransportBean.setTransid(transactionTransportBean
				.getTransid());
		customerTransactionTransportBean.setUpdatedAt(transactionTransportBean
				.getUpdatedAt());
		customerTransactionTransportBean
				.setCreditAmountFranchisee(transactionTransportBean
						.getCreditAmountFranchisee());
		customerTransactionTransportBean
				.setThirdpartyoperatortransid(transactionTransportBean
						.getThirdpartyoperatortransid());
		customerTransactionTransportBean
				.setThirdPartyServiceProvider(transactionTransportBean
						.getThirdPartyServiceProvider());

		customerTransactionTransportBean
				.setErrorMessage(transactionTransportBean.getErrorMessage());
		customerTransactionTransportBean
				.setRetailerPreBal(transactionTransportBean.getRetailerPreBal());
		customerTransactionTransportBean
				.setRetailerNewBal(transactionTransportBean.getRetailerNewBal());
		customerTransactionTransportBean.setStatus(transactionTransportBean
				.getStatus());
		customerTransactionTransportBean
				.setRefundAmount(transactionTransportBean.getRefundAmount());

		customerTransactionTransportBean
				.setAccountNumber(transactionTransportBean.getAccountNumber());
		customerTransactionTransportBean.setCanumber(transactionTransportBean
				.getCanumber());
		customerTransactionTransportBean.setDob(transactionTransportBean
				.getDob());

		customerTransactionTransportBean.setStdCode(transactionTransportBean
				.getStdCode());
		customerTransactionTransportBean.setSubservice(transactionTransportBean
				.getSubservice());
		customerTransactionTransportBean
				.setCustomerName(transactionTransportBean.getCustomerName());

		customerTransactionTransportBean
				.setCustomerAddress(transactionTransportBean
						.getCustomerAddress());
		customerTransactionTransportBean.setPinNumber(transactionTransportBean
				.getPinNumber());
		customerTransactionTransportBean
				.setCycleNumber(transactionTransportBean.getCycleNumber());

		return customerTransactionTransportBean;
	}
}
