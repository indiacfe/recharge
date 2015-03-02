package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.franchisee.RefundRequestFranchiseeDao;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.entity.RetailerCommison;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Transactional
@Service("refundRequestService")
public class RefundRequestFranchiseeServiceImpl implements
		RefundRequestFranchiseeService {
	@Autowired
	RefundRequestFranchiseeDao refundRequestFranchiseeDao;

	public void refundRequest(String tId, Long userId) {
		FranchiseeRefundRequests franchiseeRefundRequests = new FranchiseeRefundRequests();
		franchiseeRefundRequests.setCreatedat(TimeStampUtil.getTimestamp());
		franchiseeRefundRequests.setTransId(tId);
		franchiseeRefundRequests.setUserId(userId);
		refundRequestFranchiseeDao.requestRefund(franchiseeRefundRequests);
	}

	public TransactionTransportBean checkTransactionId(String tId) {

		TransactionTransportBean transactionTransportBean = refundRequestFranchiseeDao
				.checkTransactionId(tId);
		return transactionTransportBean;
	}

	public TransactionTransportBean getTransactionDetailsservice(String tId) {

		TransactionTransportBean transactionTransportBean = refundRequestFranchiseeDao
				.getransactionDetails(tId);
		return transactionTransportBean;
	}

	public List<FranchiseeRefundRequests> getRefundAllList(Long userId) {

		return refundRequestFranchiseeDao.getAllList(userId);
	}

	public List<RetailerCommison> getRetailerCommisions() {

		return refundRequestFranchiseeDao.getRetailerComm();
	}

	@Override
	public FranchiseeRefundRequests getRefundRequestByTransactionId(String tId,long franchiseeId) {

		return refundRequestFranchiseeDao.getRefundRequestByTransactionId(tId,franchiseeId);
	}

}
