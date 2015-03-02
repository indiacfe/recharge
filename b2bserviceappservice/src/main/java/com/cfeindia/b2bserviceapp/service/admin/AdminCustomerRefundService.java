package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.RefundAmountDto;

public interface AdminCustomerRefundService {
	public RefundAmountDto searchcustomerTransactionId(String tid);

	public List<RefundAmountDto> getAllRefundRequests();

	public void rejectedRemark(Long id, String remark);

	public String refundAmount(Long id, String senderId, Double refundedAmount,
			String custId);

}
