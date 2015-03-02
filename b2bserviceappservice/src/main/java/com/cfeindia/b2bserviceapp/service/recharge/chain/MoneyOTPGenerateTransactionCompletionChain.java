package com.cfeindia.b2bserviceapp.service.recharge.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service("moneyOTPGenerateTransactionCompletionChain")
public class MoneyOTPGenerateTransactionCompletionChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {
	@Autowired
	private CommonDao commonDao;

	private final TransactionState transactionState = TransactionState.COMPLETED;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		transactionTransportBean.setTransactionState(getTransactionState());
		doSuccess(transactionResponse);
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
