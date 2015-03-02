package com.cfeindia.b2bserviceapp.dao.recharge;

import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface DuplicateCheckServiceDao {
	boolean checkDuplicate(TransactionTransportBean transactionTransportBean);
}
