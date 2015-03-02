package com.cfeindia.b2bserviceapp.recharge.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.recharge.mobile.dao.BalanceCheckingServiceDao;

@Service
@Transactional
public class BalanceCheckingServiceImpl implements BalanceCheckingService {

	@Autowired
	BalanceCheckingServiceDao balanceCheckingServiceDao;

	public BalanceCheckingServiceDao getBalanceCheckingServiceDao() {
		return balanceCheckingServiceDao;
	}

	public void setBalanceCheckingServiceDao(
			BalanceCheckingServiceDao balanceCheckingServiceDao) {
		this.balanceCheckingServiceDao = balanceCheckingServiceDao;
	}

	public void reduceMobileBalance(long franchiseeId, double transAmount) {
		balanceCheckingServiceDao.reduceMobileBalance(franchiseeId, transAmount);
		
	}

	public FranchiseeCurrBal getFrachiseeCurrbalObj(long retailerId) {
		return balanceCheckingServiceDao.getFrachiseeCurrbalObj(retailerId);
	}

}
