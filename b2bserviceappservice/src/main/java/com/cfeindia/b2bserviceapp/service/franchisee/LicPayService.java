package com.cfeindia.b2bserviceapp.service.franchisee;

import com.cfeindia.b2bserviceapp.entity.LicPremiumBean;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;


public interface LicPayService {

	public void LicPayActivityService (TransactionTransportBean transactionTransportBean);
	public String LicRefundActivityService(LicPremiumBean licPremiumBean);
}
