package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.RefundAmountDto;

public interface RefundAmountService {
	public RefundAmountDto searchTransactionId(String tid);
	public String refundAmount(Long id,String senderId,Double refundAmount,String franchiseeid,String thirdPartyServiceProviderName);
	public List<RefundAmountDto> getAllRefundRequests();
	public void rejectedRemark(Long id, String remark) ;
	public List<RefundAmountDto> getAllRejctedRefundRequests(String fromDate,String toDate);

}
