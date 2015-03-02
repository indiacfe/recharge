package com.cfeindia.b2bserviceapp.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.customer.CustomerInfoDao;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.model.customer.CustomerInfo;

@Service
@Transactional
public class CustomerInfoServiceImpl implements CustomerInfoService {

	@Autowired
	private CustomerInfoDao customerInfoDao;

	@Override
	public CustomerInfo getCustomerInfo(String customerId) {
		CustomerInfo customerInfo = new CustomerInfo();
		Long customerIdLong = Long.parseLong(customerId);
		CustomerCurrentBalance customerCurrentBalance = customerInfoDao
				.getCustomerAccountBalance(customerIdLong);
		if (customerCurrentBalance != null) {
			customerInfo.setCustomerId(customerId);
			customerInfo.setCurrentBalance(customerCurrentBalance.getCurrAcBalance());
		}
		return customerInfo;
	}
}
