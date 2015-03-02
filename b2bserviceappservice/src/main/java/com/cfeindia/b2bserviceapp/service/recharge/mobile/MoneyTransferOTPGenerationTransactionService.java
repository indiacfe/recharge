package com.cfeindia.b2bserviceapp.service.recharge.mobile;

import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface MoneyTransferOTPGenerationTransactionService {

	public void getOTPRegistrationService(
			TransactionTransportBean transactionTransport);

	public void getreloadOTP(TransactionTransportBean transactionTransportBean);
}
