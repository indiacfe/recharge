package com.cfeindia.b2bserviceapp.service.franchisee;

import com.cfeindia.b2bserviceapp.entity.ICashRecharge;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface ICashService {
	public ICashRecharge checkICashRegistration(String mobileNo);

	public TransactionTransportBean iCashRegistrationService(
			TransactionTransportBean transactionTransportBean,
			ICashRecharge iCashRecharge);

}
