package com.cfeindia.b2bserviceapp.service.recharge.chain;

import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public abstract class AbstractTransactionProcessorChain implements
		TransactionProcessorChain {

	@Override
	public boolean validateCurrentState(
			TransactionTransportBean transactionTransportBean) {
		return transactionTransportBean.getTransactionState().equals(
				getTransactionState());
	}

	public final void doSuccess(TransactionResponse transactionResponse) {
		transactionResponse.setSuccessful(true);
	}

	public final void doFail(TransactionResponse transactionResponse) {
		transactionResponse.setSuccessful(false);
	}
}
