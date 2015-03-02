package com.cfeindia.b2bserviceapp.service.recharge.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.recharge.mobile.BalanceCheckingServiceDao;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

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

	public Long addToDistributorAdUnitBalanceByFranchisee(long distributorId,
			double transAmount) {
		return balanceCheckingServiceDao
				.addToDistributorAdUnitBalanceByFranchisee(distributorId,
						transAmount);

	}

	public void addUnitDistributorPerBalance(long distributorId,
			double transAmount) {
		balanceCheckingServiceDao.addUnitDistributorPerBalance(distributorId,
				transAmount);

	}

	public void reduceFranchiseeCurrentAccountBalance(long franchiseeId, double transAmount) {
		balanceCheckingServiceDao
				.reduceFranchiseeCurrentAccountBalance(franchiseeId, transAmount);

	}

	public FranchiseeCurrBal getFrachiseeCurrbalObj(long retailerId) {
		return balanceCheckingServiceDao.getFrachiseeCurrbalObj(retailerId);
	}

	@Override
	public CustomerCurrentBalance getCustomerCurrbalObj(long customerId) {
		return balanceCheckingServiceDao.getCustomerCurrbalObj(customerId);
	}

	@Override
	public void reducecustomerMobileBalance(long userId, double transAmount) {
		balanceCheckingServiceDao.reduceCustomerMobileBalance(userId,
				transAmount);

	}

}
