package com.cfeindia.b2bserviceapp.service.recharge.chain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CacheManager;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ApplicationContextUtils;

@Service("moneyTransTransactionGenerateTransactionIDChain")
@Transactional
public class MoneyTransTransactionGenerateTransactionIDChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {
	private final TransactionState transactionState = TransactionState.GENERATETRANSACTIONID;
	@Autowired
	private ApplicationContextUtils applicationContextUtils;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		ThirdPartyDetailObject thirdPartyDetailObject = CacheManager
				.getCurrentThirdPartyApi(transactionTransportBean
						.getThirdPartyServiceProvider());
		getThirdPartyDetails(transactionTransportBean, thirdPartyDetailObject);

		/*
		 * if (transactionTransportBean.getRemittanceUserDto().getOTP() != null)
		 * { generateTransactionId(transactionTransportBean); }
		 */
		if (thirdPartyDetailObject.getThirpartyprovidername().equalsIgnoreCase(
				"icash")
				|| transactionTransportBean.getOperator().equalsIgnoreCase(
						"SENDER_BALANCE_TOPUP_WALLET")) {
			generateTransactionId(transactionTransportBean);
		}

		transactionTransportBean.setOperatorCode(CacheManager.getOperatorCode(
				thirdPartyDetailObject.getThirpartyprovidername(),
				transactionTransportBean.getRechargeType(),
				transactionTransportBean.getOperator()));
		transactionTransportBean
				.setThirdPartyServiceProvider(thirdPartyDetailObject
						.getThirpartyprovidername());
		transactionResponse
				.seteTransactionRequestService(applicationContextUtils
						.getServiceProvierBean(thirdPartyDetailObject
								.getThirpartyprovidername() + "_moneytransfer"));
		/*
		 * transactionResponse
		 * .setMoneyTransERegistrationRequestService(applicationContextUtils
		 * .getServiceRegistrationProvierBean(thirdPartyDetailObject
		 * .getThirpartyprovidername() + "_moneytransfer"));
		 */
		formateDateOfBirth(transactionTransportBean);
		doSuccess(transactionResponse);

	}

	private void getThirdPartyDetails(
			TransactionTransportBean transactionTransportBean,
			ThirdPartyDetailObject thirdPartyDetailObject) {
		transactionTransportBean
				.setAgentId(thirdPartyDetailObject.getAgentid());
		transactionTransportBean.setToken(thirdPartyDetailObject.getToken());
		transactionTransportBean.setToken1(thirdPartyDetailObject.getToken1());
		transactionTransportBean.setToken2(thirdPartyDetailObject.getToken2());
		transactionTransportBean.setToken3(thirdPartyDetailObject.getToken3());
		transactionTransportBean.setAppver(thirdPartyDetailObject
				.getApiVersion() + "");
		transactionTransportBean.setLoginStatus(thirdPartyDetailObject
				.getLoginstatus());
		transactionTransportBean.setRecharge(transactionTransportBean
				.getAmount() + "");

		transactionTransportBean.setConnectURL(thirdPartyDetailObject
				.getConnectURL());
	}

	private void generateTransactionId(
			TransactionTransportBean transactionTransport) {
		StringBuilder merchantTransIdBuilder = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		if (transactionTransport.getTransactionName().equalsIgnoreCase(
				"MONEY_TRANSFER_USER_REGISTRATION")) {
			merchantTransIdBuilder.append("MON");
		}
		if (transactionTransport.getTransactionName().equalsIgnoreCase(
				"RELOAD_WALLET")) {
			merchantTransIdBuilder.append("MTR");
		}
		merchantTransIdBuilder.append(transactionTransport
				.getFranchiseeMobileNum());
		merchantTransIdBuilder.append(sdf.format(new Date()));
		transactionTransport.setTransid(merchantTransIdBuilder.toString());
	}

	private void formateDateOfBirth(
			TransactionTransportBean transactionTransport) {
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
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
