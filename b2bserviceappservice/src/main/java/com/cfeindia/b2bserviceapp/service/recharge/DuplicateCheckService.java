package com.cfeindia.b2bserviceapp.service.recharge;

import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface DuplicateCheckService {
	boolean checkDuplicate(TransactionTransportBean transactionTransportBean);
}
