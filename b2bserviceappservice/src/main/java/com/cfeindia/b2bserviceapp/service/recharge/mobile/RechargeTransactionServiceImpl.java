package com.cfeindia.b2bserviceapp.service.recharge.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.service.recharge.chain.TransactionProcessorChain;
import com.cfeindia.b2bserviceapp.service.recharge.exception.TransactionalExceptionHandler;
import com.cfeindia.b2bserviceapp.service.recharge.handler.TransactionProcessorHandler;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ApplicationContextUtils;

@Service("RechargeTransactionService")
public class RechargeTransactionServiceImpl implements
		RechargeTransactionService {

	ETransactionRequestService eTransactionRequestService;

	@Autowired
	BalanceCheckingService balanceCheckingService;

	@Autowired
	CommonService commonservice;

	@Autowired
	ApplicationContextUtils applicationContextUtils;
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private TransactionalExceptionHandler transactionalExceptionHandler;
	@Autowired
	@Qualifier("transactionInitiatorChain")
	private TransactionProcessorChain transactionInitiatorChain;
	@Autowired
	@Qualifier("transactionRechargeApiConfiguredChain")
	private TransactionProcessorChain transactionRechargeApiConfiguredChain;
	@Autowired
	@Qualifier("transactionCommissionConfiguredChain")
	private TransactionProcessorChain transactionCommissionConfiguredChain;
	@Autowired
	@Qualifier("transactionDuplicateCheckChain")
	private TransactionProcessorChain transactionDuplicateCheckChain;
	@Autowired
	@Qualifier("transactionCompletionChain")
	private TransactionProcessorChain transactionCompletionChain;
	@Autowired
	@Qualifier("transactionGenerateTransactionIDChain")
	private TransactionProcessorChain transactionGenerateTransactionIDChain;
	@Autowired
	@Qualifier("transactionBalanceDeductChain")
	private TransactionProcessorChain transactionBalanceDeductChain;
	@Autowired
	@Qualifier("transactionCheckAirtelUserChain")
	private TransactionProcessorChain transactionCheckAirtelUserChain;
	@Autowired
	@Qualifier("transactionThirdPartyProcessingChain")
	private TransactionProcessorChain transactionThirdPartyProcessingChain;
	@Autowired
	@Qualifier("transactionBalanceManageChain")
	private TransactionProcessorChain transactionBalanceManageChain;

	@Autowired
	@Qualifier("transactionCheckRetailerEnabledChain")
	private TransactionProcessorChain transactionCheckRetailerEnabledChain;

	@Override
	public void doRechargeService(TransactionTransportBean transactionTransport) {
		// Add all the processor handlers in the correct order
		TransactionProcessorHandler transactionProcessorHandler = new TransactionProcessorHandler(
				transactionalExceptionHandler, transactionCompletionChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionCheckRetailerEnabledChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionInitiatorChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionRechargeApiConfiguredChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionCommissionConfiguredChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionDuplicateCheckChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionGenerateTransactionIDChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionCheckAirtelUserChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionBalanceDeductChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionThirdPartyProcessingChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionBalanceManageChain);
		transactionProcessorHandler
				.doTransactionProcessing(transactionTransport);
	}

	@Override
	public void doRegistrationService(
			TransactionTransportBean transactionTransport) {
		TransactionProcessorHandler transactionProcessorHandler = new TransactionProcessorHandler(
				transactionalExceptionHandler, transactionCompletionChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionCheckRetailerEnabledChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionRechargeApiConfiguredChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionCommissionConfiguredChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionDuplicateCheckChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionBalanceDeductChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionThirdPartyProcessingChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionBalanceManageChain);
		transactionProcessorHandler
				.doTransactionProcessing(transactionTransport);

	}

}