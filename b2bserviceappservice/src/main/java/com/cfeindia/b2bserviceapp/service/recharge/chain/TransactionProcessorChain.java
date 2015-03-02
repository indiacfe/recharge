package com.cfeindia.b2bserviceapp.service.recharge.chain;

import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface TransactionProcessorChain {
	boolean validateCurrentState(TransactionTransportBean transactionTransportBean);
    void doProcess(TransactionTransportBean transactionTransportBean,
    						TransactionResponse  transactionResponse);
    TransactionState getTransactionState();
    void doSuccess(TransactionResponse transactionResponse);
    void doFail(TransactionResponse transactionResponse);
}
