package com.cfeindia.b2bserviceapp.recharge.mobile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CacheManager;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ApplicationContextUtils;

@Service("RechargeTransactionService")
@Transactional
public class RechargeTransactionServiceImpl implements
		RechargeTransactionService {

	ETransactionRequestService eTransactionRequestService;

	@Autowired
	BalanceCheckingService balanceCheckingService;

	@Autowired
	CommonService commonservice;

	@Autowired
	ApplicationContextUtils applicationContextUtils;

	public void doRechargeService(TransactionTransportBean transactionTransport) {
		ThirdPartyDetailObject thirdPartyDetailObject = null;
		FranchiseeCurrBal franchiseeCurrbal = null;
		try {

			franchiseeCurrbal = balanceCheckingService
					.getFrachiseeCurrbalObj(Long.valueOf(transactionTransport
							.getRetailerId()));
			boolean balanceAvailable = false;
			balanceAvailable = franchiseeCurrbal.getB2bcurrbal() >= transactionTransport
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
				System.out.println("TransactionName : "
						+ transactionTransport.getTransactionName());
				System.out.println("Connectionid : "
						+ transactionTransport.getConnectionid());
				System.out.println("Mobileid : "
						+ transactionTransport.getMobileNo());
				StringBuilder merchantTransIdBuilder = new StringBuilder();
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
				if (transactionTransport.getTransactionName() == "MOBILE_RECHARGE") {
					merchantTransIdBuilder.append("MOB");
					// merchantTransIdBuilder.append(transactionTransport.getMobileNo());
				} else if (transactionTransport.getTransactionName() == "DTH_RECHARGE") {
					merchantTransIdBuilder.append("DTH");
					// merchantTransIdBuilder.append(transactionTransport.getConnectionid());

				} else if (transactionTransport.getTransactionName() == "DATACARD_RECHARGE") {
					merchantTransIdBuilder.append("DTC");
					// merchantTransIdBuilder.append(transactionTransport.getConnectionid());
				} else if (transactionTransport.getTransactionName() == "POSTPAID_BILL") {
					merchantTransIdBuilder.append("PSB");
					// merchantTransIdBuilder.append(transactionTransport.getConnectionid());
				} else if (transactionTransport.getTransactionName() == "LANDLINE") {
					merchantTransIdBuilder.append("LAN");
					// merchantTransIdBuilder.append(transactionTransport.getConnectionid());
				} else if (transactionTransport.getTransactionName() == "ELECTRICITY") {
					merchantTransIdBuilder.append("ELC");
					// merchantTransIdBuilder.append(transactionTransport.getConnectionid());
				}
				merchantTransIdBuilder.append(transactionTransport
						.getFranchiseeMobileNum());
				merchantTransIdBuilder.append(sdf.format(new Date()));
				transactionTransport.setTransid(merchantTransIdBuilder
						.toString());

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
				if (eTransactionRequestService != null) {
					eTransactionRequestService
							.doTransaction(transactionTransport);
					System.out.println("error code "
							+ transactionTransport.getErrorCode());
					System.out.println("error message "
							+ transactionTransport.getMessage());
					if (transactionTransport.getErrorCode() == 0
							|| transactionTransport.getThirdpartytransid() != null) {
						transactionTransport.getThirdpartytransid();
						transactionTransport
								.setRetailerPreBal(franchiseeCurrbal
										.getB2bcurrbal());
						double transAmountActual = transactionTransport
								.getAmount();
						double commision = commonservice.getRetailerCommission(
								thirdPartyDetailObject
										.getThirpartyprovidername(),
								transactionTransport.getRechargeType(),
								transactionTransport.getOperator());
						commision = transAmountActual * commision / 100;
						transactionTransport
								.setCreditAmountFranchisee(commision);
						transAmountActual = transAmountActual - commision;
						transactionTransport
								.setRetailerNewBal(franchiseeCurrbal
										.getB2bcurrbal() - transAmountActual);
						balanceCheckingService.reduceMobileBalance(Long
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
		} catch (IOException e) {
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
				if(thirdPartyDetailObject != null && franchiseeCurrbal != null) {
					double transAmountActual = transactionTransport
							.getAmount();
					double commision = commonservice.getRetailerCommission(
							thirdPartyDetailObject
									.getThirpartyprovidername(),
							transactionTransport.getRechargeType(),
							transactionTransport.getOperator());
					commision = transAmountActual * commision / 100;
					transactionTransport
							.setCreditAmountFranchisee(commision);
					transAmountActual = transAmountActual - commision;
					transactionTransport
							.setRetailerNewBal(franchiseeCurrbal
									.getB2bcurrbal() - transAmountActual);
					balanceCheckingService.reduceMobileBalance(Long
							.valueOf(transactionTransport.getRetailerId()),
							transAmountActual);
					
				} 
			}
			catch(Exception e1) {
				transactionTransport.setErrorCode(2);
				transactionTransport.setErrorcode("2");
				transactionTransport.setMessage("System has Error");
				e1.printStackTrace();
			}
			transactionTransport.setStatus("ERROR");
		}
		commonservice.saveEntity(transactionTransport);
	}

}
