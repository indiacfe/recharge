package com.cfeindia.b2bserviceapp.dao.customer;

import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.CompanyCustomerTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Repository
public class CustomerFundTransferDaoImpl implements CustomerFundTransferDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public long companyCustomerTransactionLog(
			CompanyCustomerTransactionLog companyCustomerTransactionLog) {
		long id = (Long) sessionFactory.getCurrentSession().save(
				companyCustomerTransactionLog);
		return id;
	}

	@Override
	public void setCustomerBalTransferLog(
			CustomerBalanceTransferLog customerBalanceTransferLog) {
		sessionFactory.getCurrentSession().save(customerBalanceTransferLog);
		return;

	}

	@Override
	public void updateCustomerB2bAdunitBal(Long cId) {
		CustomerCurrentBalance customerCurrentBalance = (CustomerCurrentBalance) sessionFactory
				.getCurrentSession()
				.createCriteria(CustomerCurrentBalance.class)
				.add(Restrictions.eq("customerId", cId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE)
				.lock(customerCurrentBalance);
		sessionFactory.getCurrentSession().refresh(customerCurrentBalance);
		customerCurrentBalance.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(customerCurrentBalance);

	}

	@Override
	public void addCustomerBusinessBal(Long cId, double amount) {
		CustomerCurrentBalance customerCurrentBalance = (CustomerCurrentBalance) sessionFactory
				.getCurrentSession()
				.createCriteria(CustomerCurrentBalance.class)
				.add(Restrictions.eq("customerId", cId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE)
				.lock(customerCurrentBalance);
		sessionFactory.getCurrentSession().refresh(customerCurrentBalance);
		customerCurrentBalance.setCurrAcBalance(customerCurrentBalance
				.getCurrAcBalance() + amount);
		customerCurrentBalance.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(customerCurrentBalance);

	}

	@Override
	public CustomerCurrentBalance getCurrentDetailWithoutLockingForDisplay(
			Long custID) {
		CustomerCurrentBalance customerCurrentBalance = (CustomerCurrentBalance) sessionFactory
				.getCurrentSession()
				.createCriteria(CustomerCurrentBalance.class)
				.add(Restrictions.eq("customerId", custID)).uniqueResult();
		return customerCurrentBalance;
	}

	@Override
	public void deductCustomerBusinessBalance(Long customerId,
			double deductAmount) {
		CustomerCurrentBalance customerCurrentBalance = (CustomerCurrentBalance) sessionFactory
				.getCurrentSession()
				.createCriteria(CustomerCurrentBalance.class)
				.add(Restrictions.eq("customerId", customerId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE)
				.lock(customerCurrentBalance);
		sessionFactory.getCurrentSession().refresh(customerCurrentBalance);
		customerCurrentBalance.setCurrAcBalance(customerCurrentBalance
				.getCurrAcBalance() - deductAmount);
		customerCurrentBalance.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(customerCurrentBalance);

	}

}
