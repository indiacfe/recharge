package com.cfeindia.b2bserviceapp.franchisee.service;

import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface RefundRequestFranchiseeService {

	public void refundRequest(String tId ,int userId);
	public TransactionTransportBean checkTransactionId(String tId);
}
