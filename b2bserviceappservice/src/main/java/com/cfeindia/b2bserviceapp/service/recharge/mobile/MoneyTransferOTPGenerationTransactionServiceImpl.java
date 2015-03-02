package com.cfeindia.b2bserviceapp.service.recharge.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cfeindia.b2bserviceapp.service.recharge.chain.MoneyOTPGenerateTransactionCompletionChain;
import com.cfeindia.b2bserviceapp.service.recharge.chain.TransactionProcessorChain;
import com.cfeindia.b2bserviceapp.service.recharge.exception.TransactionalExceptionHandler;
import com.cfeindia.b2bserviceapp.service.recharge.handler.MoneyTransOTPTransactionProcessorHandler;
import com.cfeindia.b2bserviceapp.service.recharge.handler.MoneyTransTransactionProcessorHandler;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ApplicationContextUtils;

@Service
public class MoneyTransferOTPGenerationTransactionServiceImpl implements
		MoneyTransferOTPGenerationTransactionService {

	ETransactionRequestService eTransactionRequestService;

	@Autowired
	ApplicationContextUtils applicationContextUtils;
	@Autowired
	private TransactionalExceptionHandler transactionalExceptionHandler;
	@Autowired
	@Qualifier("moneyOTPGenerateTransactionCompletionChain")
	// no entry will save on table recharge_transaction
	private MoneyOTPGenerateTransactionCompletionChain moneyOTPGenerateTransactionCompletionChain;
	@Autowired
	@Qualifier("transactionCheckRetailerEnabledChain")
	private TransactionProcessorChain transactionCheckRetailerEnabledChain;
	@Autowired
	@Qualifier("transactionRechargeApiConfiguredChain")
	private TransactionProcessorChain transactionRechargeApiConfiguredChain;
	@Autowired
	@Qualifier("transactionDuplicateCheckChain")
	private TransactionProcessorChain transactionDuplicateCheckChain;
	@Autowired
	@Qualifier("moneyTransTransactionGenerateTransactionIDChain")
	private TransactionProcessorChain moneyTransTransactionGenerateTransactionIDChain;
	@Autowired
	@Qualifier("moneyTransTransactionThirdPartyRegistrationProcessingChain")
	private TransactionProcessorChain moneyTransTransactionThirdPartyRegistrationProcessingChain;

	@Autowired
	@Qualifier("transactionInitiatorChain")
	// Create id in recharge_transaction
	private TransactionProcessorChain transactionInitiatorChain;
	@Autowired
	@Qualifier("transactionCompletionChain")
	private TransactionProcessorChain transactionCompletionChain;// for save
																	// recharge_transaction
																	// table
	@Autowired
	@Qualifier("moneyTransTransactionCommissionConfiguredChain")
	private TransactionProcessorChain moneyTransTransactionCommissionConfiguredChain;
	@Autowired
	@Qualifier("transactionBalanceDeductChain")
	private TransactionProcessorChain transactionBalanceDeductChain;
	@Autowired
	@Qualifier("transactionBalanceManageChain")
	private TransactionProcessorChain transactionBalanceManageChain;
	@Autowired
	@Qualifier("transactionCheckAirtelUserChain")
	private TransactionProcessorChain transactionCheckAirtelUserChain;

	@Override
	public void getOTPRegistrationService(
			TransactionTransportBean transactionTransportBean) {
		MoneyTransOTPTransactionProcessorHandler transactionProcessorHandler = new MoneyTransOTPTransactionProcessorHandler(
				transactionalExceptionHandler,
				moneyOTPGenerateTransactionCompletionChain);
		System.out.println("transactionTransport"
				+ transactionTransportBean.getOperator());

		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionCheckRetailerEnabledChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionRechargeApiConfiguredChain);
		/*
		 * transactionProcessorHandler
		 * .addTransactionProcessorHandler(transactionDuplicateCheckChain);
		 */
		transactionProcessorHandler
		.addTransactionProcessorHandler(moneyTransTransactionGenerateTransactionIDChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(moneyTransTransactionGenerateTransactionIDChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(moneyTransTransactionThirdPartyRegistrationProcessingChain);
		transactionProcessorHandler
				.doTransactionProcessing(transactionTransportBean);

	}

	@Override
	public void getreloadOTP(TransactionTransportBean transactionTransportBean) {
		MoneyTransTransactionProcessorHandler transactionProcessorHandler = new MoneyTransTransactionProcessorHandler(
				transactionalExceptionHandler, transactionCompletionChain);

		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionCheckRetailerEnabledChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionInitiatorChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionRechargeApiConfiguredChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(moneyTransTransactionCommissionConfiguredChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionDuplicateCheckChain);

		transactionProcessorHandler
				.addTransactionProcessorHandler(moneyTransTransactionGenerateTransactionIDChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionCheckAirtelUserChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionBalanceDeductChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(moneyTransTransactionThirdPartyRegistrationProcessingChain);
		transactionProcessorHandler
				.addTransactionProcessorHandler(transactionBalanceManageChain);
		transactionProcessorHandler
				.doTransactionProcessing(transactionTransportBean);

	}

}
