package com.cfeindia.b2bserviceapp.service.customer;

import java.util.List;
import java.util.Map;

import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerRefundRequest;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;

public interface CustomerRefundService {
	public CustomerTransactionTransportBean checkTrasactionId(String transId);

	public String refundRequest(String clientTransId, Long userId);

	public List<CustomerRefundRequest> getAllRefunds(Long userId);

	public CustomerTransactionTransportBean getTransactionDetails(
			String clientTransId);

	public Map<String, CustomerRefundRequest> getCustomerRefundRequest(
			long customerId);

}
