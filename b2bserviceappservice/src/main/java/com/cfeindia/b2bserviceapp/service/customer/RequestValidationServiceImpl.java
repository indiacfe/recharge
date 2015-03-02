package com.cfeindia.b2bserviceapp.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.customer.CustomerBalanceCheckingDao;
import com.cfeindia.b2bserviceapp.dao.customer.TokenDao;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.TokenDetails;

@Service
@Transactional
public class RequestValidationServiceImpl implements RequestValidationService {

	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private CustomerBalanceCheckingDao customerBalanceCheckingDao;

	@Override
	public String getValidate(String ip, String token, String userId,
			String account) {
		String result = CommonConstants.FAILURE;
		TokenDetails tokenDetails = tokenDao.getToken(ip, token);
		if (tokenDetails != null) {
			if (account.length() >= 10 || account.length() <= 15) {
				result = CommonConstants.SUCCESS;
			}
		} else {
			result = CommonConstants.FAILURE;
		}
		return result;
	}

	@Override
	public CustomerCurrentBalance getBalance(String userId) {

		return customerBalanceCheckingDao.getBalance(Long.parseLong(userId));
	}

}
