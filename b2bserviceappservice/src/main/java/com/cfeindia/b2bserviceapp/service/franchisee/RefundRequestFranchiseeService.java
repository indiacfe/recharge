package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.entity.RetailerCommison;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface RefundRequestFranchiseeService {

	public void refundRequest(String tId,Long userId);
	public TransactionTransportBean checkTransactionId(String tId);
	public List<FranchiseeRefundRequests> getRefundAllList(Long userId);
	public List<RetailerCommison> getRetailerCommisions();
	public TransactionTransportBean getTransactionDetailsservice(String tId);
	public FranchiseeRefundRequests getRefundRequestByTransactionId(String tId,long franchiseeId);
}
