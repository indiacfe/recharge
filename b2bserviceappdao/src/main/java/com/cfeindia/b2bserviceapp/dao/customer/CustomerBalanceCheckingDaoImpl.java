package com.cfeindia.b2bserviceapp.dao.customer;

import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;

@Repository
public class CustomerBalanceCheckingDaoImpl implements
		CustomerBalanceCheckingDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CustomerCurrentBalance getBalance(Long custId) {
		CustomerCurrentBalance customerCurrentBalance = (CustomerCurrentBalance) sessionFactory
				.getCurrentSession()
				.createCriteria(CustomerCurrentBalance.class)
				.add(Restrictions.eq("customerId", custId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE)
				.lock(customerCurrentBalance);
		sessionFactory.getCurrentSession().refresh(customerCurrentBalance);
		return customerCurrentBalance;
	}

}
