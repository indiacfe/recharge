package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.entity.RetailerCommison;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface RefundRequestFranchiseeDao {
	public void requestRefund(FranchiseeRefundRequests franchiseeRefundRequests);

	public TransactionTransportBean checkTransactionId(String tId);

	public List<FranchiseeRefundRequests> getAllList(Long userId);

	public List<RetailerCommison> getRetailerComm();

	public TransactionTransportBean getransactionDetails(String tId);

	public FranchiseeRefundRequests getRefundRequestByTransactionId(String tId,long franchiseeId);
}
