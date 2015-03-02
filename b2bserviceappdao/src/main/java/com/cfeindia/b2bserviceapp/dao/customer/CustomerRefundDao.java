package com.cfeindia.b2bserviceapp.dao.customer;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerRefundRequest;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;

public interface CustomerRefundDao {
	public CustomerTransactionTransportBean checkTransactionId(
			String clientTransId);

	public void refundRequest(CustomerRefundRequest customerRefundRequest);

	public List<CustomerRefundRequest> getAllRefunds(Long userId);

	public CustomerTransactionTransportBean getTransactionDetail(
			String clientTransId);
 
	public CustomerRefundRequest getRefundRequest(String clientTransId);
}
