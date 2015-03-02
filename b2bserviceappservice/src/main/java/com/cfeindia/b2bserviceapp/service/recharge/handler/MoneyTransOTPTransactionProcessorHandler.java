package com.cfeindia.b2bserviceapp.service.recharge.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.service.recharge.chain.MoneyOTPGenerateTransactionCompletionChain;
import com.cfeindia.b2bserviceapp.service.recharge.chain.TransactionProcessorChain;
import com.cfeindia.b2bserviceapp.service.recharge.exception.TransactionalExceptionHandler;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public class MoneyTransOTPTransactionProcessorHandler {
	private static final Logger LOG = LoggerFactory
			 .getLogger(MoneyTransOTPTransactionProcessorHandler.class);
	private final TransactionalExceptionHandler transactionalExceptionHandler;
	private final MoneyOTPGenerateTransactionCompletionChain mCompletionChain;
	private List<TransactionProcessorChain> processorChains = new ArrayList<TransactionProcessorChain>();
	public MoneyTransOTPTransactionProcessorHandler(
			TransactionalExceptionHandler transactionalExceptionHandler,
			TransactionProcessorChain transactionCompletionChain) {
		this.transactionalExceptionHandler = transactionalExceptionHandler;
		this.mCompletionChain = (MoneyOTPGenerateTransactionCompletionChain) transactionCompletionChain;
		 
	}

	public void doTransactionProcessing(
			TransactionTransportBean transactionTransportBean) {
		TransactionResponse transactionResponse = new TransactionResponse();
		for (TransactionProcessorChain transactionProcessorChain : processorChains) {
			 try { 
				transactionProcessorChain.doProcess(transactionTransportBean,
						transactionResponse);
				if (!transactionResponse.isSuccessful()) {
					break;
				}
				transactionResponse.setSuccessful(false);
		 	} catch (Throwable e) {

				LOG.error("Exception in transaction processing ", e);
				processException(transactionTransportBean, transactionResponse,
						e);
				transactionTransportBean.setErrorCode(2);
				transactionTransportBean.setErrorMessage("System Error");
				if (transactionTransportBean.getTransactionState().compareTo(
						TransactionState.THIRDPARTYCALLED) < 0) {
					transactionTransportBean.setStatus(CommonConstants.FAILED);
					transactionTransportBean
							.setMessage("Your Transaction Failed");
				} else {
					transactionTransportBean.setStatus(CommonConstants.SUCCESS);
				}
				break;
			}
		}
		if (!TransactionState.PREAUTHORIZATION.equals(transactionTransportBean
				.getTransactionState())) {
			mCompletionChain.doProcess(transactionTransportBean,
					transactionResponse);
		}

	}
	private void validateChainSequence(
			TransactionProcessorChain transactionProcessorChain,
			TransactionTransportBean transactionTransportBean) {
		if (!transactionProcessorChain
				.validateCurrentState(transactionTransportBean)) {
			 /*throw new IllegalStateException(String.format(
					"Current state %s not valid for the chain handler %s",
					transactionTransportBean.getTransactionState(),
					transactionProcessorChain.getClass().getSimpleName()));*/
		}
	}
	private void processException(
			TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse, Throwable e) {
		try {
			transactionalExceptionHandler.processException(
					transactionTransportBean, transactionResponse, e);
		} catch (Throwable ex) {
			LOG.error("Exception in processing Exception ", ex);
			ex.printStackTrace();
		}
	}
	
	public void addTransactionProcessorHandler(
			TransactionProcessorChain transactionProcessorChain) {
		processorChains.add(transactionProcessorChain);
	}


}
