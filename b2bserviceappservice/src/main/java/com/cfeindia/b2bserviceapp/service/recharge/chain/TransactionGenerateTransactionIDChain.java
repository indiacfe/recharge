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

@Service("transactionGenerateTransactionIDChain")
@Transactional
public class TransactionGenerateTransactionIDChain extends
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
		generateTransactionId(transactionTransportBean);
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
								.getThirpartyprovidername()));
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
		} else if (transactionTransport.getTransactionName() == "GAS_BILL") {
			merchantTransIdBuilder.append("GAS");
		} else if (transactionTransport.getTransactionName() == "INSURANCE_BILL") {
			merchantTransIdBuilder.append("ISB");
		} else if (transactionTransport.getTransactionName() == "CONNECTION") {
			merchantTransIdBuilder.append("DCN");
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
