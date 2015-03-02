package com.cfeindia.b2bserviceapp.service.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.customer.CustomerRefundDao;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerRefundRequest;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class CustomerRefundServiceImpl implements CustomerRefundService {

	@Autowired
	CustomerRefundDao customerRefundDao;
	@Autowired
	private CommonDao commonDao;

	@Override
	public CustomerTransactionTransportBean checkTrasactionId(
			String clientTransId) {

		return customerRefundDao.checkTransactionId(clientTransId);
	}

	@Override
	public String refundRequest(String clientTransId, Long userId) {
		CustomerRefundRequest refundRequest = customerRefundDao
				.getRefundRequest(clientTransId);
		if (refundRequest == null) {
			CustomerRefundRequest customerRefundRequest = new CustomerRefundRequest();
			customerRefundRequest.setCreatedat(TimeStampUtil.getTimestamp());
			customerRefundRequest.setClientTransId(clientTransId);
			customerRefundRequest.setUserId(userId);
			customerRefundDao.refundRequest(customerRefundRequest);
			return CommonConstants.SUCCESS;
		} else {
			return CommonConstants.FAILURE;
		}

	}

	@Override
	public List<CustomerRefundRequest> getAllRefunds(Long userId) {

		return customerRefundDao.getAllRefunds(userId);
	}

	@Override
	public CustomerTransactionTransportBean getTransactionDetails(
			String clientTransId) {

		return customerRefundDao.getTransactionDetail(clientTransId);
	}

	@Override
	public Map<String, CustomerRefundRequest> getCustomerRefundRequest(
			long customerId) {

		List<CustomerRefundRequest> list = customerRefundDao
				.getAllRefunds(customerId);
		Map<String, CustomerRefundRequest> map = new HashMap<String, CustomerRefundRequest>();
		if (list.size() != 0) {
			for (CustomerRefundRequest requests : list) {
				map.put(requests.getClientTransId(), requests);
			}
		}
		return map;
	}
}
