package com.cfeindia.b2bserviceapp.franchisee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.franchisee.dao.RefundRequestFranchiseeDao;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Transactional
@Service("refundRequestService")
public class RefundRequestFranchiseeServiceImpl implements RefundRequestFranchiseeService 
{
	@Autowired
	RefundRequestFranchiseeDao refundRequestFranchiseeDao;

	public void refundRequest(String tId)
	{
		FranchiseeRefundRequests franchiseeRefundRequests=new FranchiseeRefundRequests();
		franchiseeRefundRequests.setCreatedat(TimeStampUtil.getTimestamp());
		franchiseeRefundRequests.setTransId(tId);
		refundRequestFranchiseeDao.requestRefund(franchiseeRefundRequests);		
	}

	public TransactionTransportBean checkTransactionId(String tId) {

		TransactionTransportBean transactionTransportBean=refundRequestFranchiseeDao.checkTransactionId(tId);
		return transactionTransportBean;
	}

}
