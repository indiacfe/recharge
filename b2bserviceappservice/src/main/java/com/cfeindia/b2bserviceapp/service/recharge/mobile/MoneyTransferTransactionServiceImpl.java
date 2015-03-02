package com.cfeindia.b2bserviceapp.service.recharge.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.service.recharge.chain.TransactionProcessorChain;
import com.cfeindia.b2bserviceapp.service.recharge.exception.TransactionalExceptionHandler;
import com.cfeindia.b2bserviceapp.service.recharge.handler.TransactionProcessorHandler;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ApplicationContextUtils;

@Service
public class MoneyTransferTransactionServiceImpl implements
		MoneyTransferTransactionService {

	// ETransactionRequestService eTransactionRequestService;

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
	@Qualifier("transactionCheckRetailerEnabledChain")
	private TransactionProcessorChain transactionCheckRetailerEnabledChain;
	@Autowired
	@Qualifier("transactionBalanceManageChain")
	private TransactionProcessorChain transactionBalanceManageChain;
	@Autowired
	@Qualifier("transactionInitiatorChain")
	// Create id in recharge_transaction
	private TransactionProcessorChain transactionInitiatorChain;
	@Autowired
	@Qualifier("transactionRechargeApiConfiguredChain")
	// we need to entry in curret_third party _service _provider>
	private TransactionProcessorChain transactionRechargeApiConfiguredChain;
	@Autowired
	@Qualifier("transactionCommissionConfiguredChain")
	private TransactionProcessorChain transactionCommissionConfiguredChain;
	@Autowired
	@Qualifier("transactionDuplicateCheckChain")
	// ok try after 5 min
	private TransactionProcessorChain transactionDuplicateCheckChain;
	@Autowired
	@Qualifier("transactionCompletionChain")
	private TransactionProcessorChain transactionCompletionChain;

	@Autowired
	@Qualifier("moneyTransTransactionGenerateTransactionIDChain")
	private TransactionProcessorChain moneyTransTransactionGenerateTransactionIDChain;
	/*
	 * @Autowired
	 * 
	 * @Qualifier("transactionGenerateTransactionIDChain") private
	 * TransactionProcessorChain transactionGenerateTransactionIDChain;
	 */
	@Autowired
	@Qualifier("transactionCheckAirtelUserChain")
	private TransactionProcessorChain transactionCheckAirtelUserChain;
	@Autowired
	@Qualifier("transactionBalanceDeductChain")
	private TransactionProcessorChain transactionBalanceDeductChain;
	@Autowired
	@Qualifier("moneyTransTransactionThirdPartyRegistrationProcessingChain")
	private TransactionProcessorChain moneyTransTransactionThirdPartyRegistrationProcessingChain;

	@Override
	public void submitOTPRegistrationService(
			TransactionTransportBean transactionTransportBean) {
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
