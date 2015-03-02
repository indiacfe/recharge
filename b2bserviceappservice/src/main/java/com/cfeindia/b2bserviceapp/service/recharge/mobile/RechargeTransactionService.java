package com.cfeindia.b2bserviceapp.service.recharge.mobile;

import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface RechargeTransactionService {
	public void doRechargeService(TransactionTransportBean transactionTransport);

	public void doRegistrationService(
			TransactionTransportBean transactionTransport);

}
