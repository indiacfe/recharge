package com.cfeindia.b2bserviceapp.service.recharge.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.mysql.jdbc.StringUtils;

@Service("transactionRechargeApiConfiguredChain")
@Transactional
public class TransactionRechargeApiConfiguredChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {
	private final TransactionState transactionState = TransactionState.RECHARGEAPICONFIGURED;
	@Autowired
	private CommonService commonservice;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		String thirdPartyAPISelection = commonservice
				.getThirdPartyAPISelection(
						transactionTransportBean.getRechargeType(),
						transactionTransportBean.getOperator());
		if (StringUtils.isNullOrEmpty(thirdPartyAPISelection)) {
			System.out.println("hiihikh");
			doFail(transactionResponse);
			transactionResponse.setMessage("Product not configured correctly.");
			transactionTransportBean.setErrorCode(1);
			transactionTransportBean
					.setMessage("Product is not configured, Please contact to admin.");
		} else {
			transactionTransportBean
					.setThirdPartyServiceProvider(thirdPartyAPISelection);
			doSuccess(transactionResponse);
		}
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
