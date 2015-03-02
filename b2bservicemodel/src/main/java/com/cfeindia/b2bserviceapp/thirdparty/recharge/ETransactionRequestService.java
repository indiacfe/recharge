package com.cfeindia.b2bserviceapp.thirdparty.recharge;

import java.io.IOException;
import java.net.MalformedURLException;

import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface ETransactionRequestService {
	
	void doTransaction(TransactionTransportBean transportBean) throws MalformedURLException, IOException;

}
