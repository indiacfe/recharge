package com.cfeindia.b2bserviceapp.admin.service;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.RefundAmountDto;

public interface RefundAmountService {
	public RefundAmountDto searchTransactionId(String tid);
	public String refundAmount(Long id,String senderId,Double refundAmount,String franchiseeid);
	public List<RefundAmountDto> getAllRefundRequests();

}
