package com.cfeindia.b2bserviceapp.franchisee.dao;

import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface RefundRequestFranchiseeDao 
{
	public void requestRefund(FranchiseeRefundRequests franchiseeRefundRequests);
	public TransactionTransportBean checkTransactionId(String tId);
}
