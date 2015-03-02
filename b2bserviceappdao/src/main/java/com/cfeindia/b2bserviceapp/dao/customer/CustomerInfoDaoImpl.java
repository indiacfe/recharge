package com.cfeindia.b2bserviceapp.dao.customer;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.Users;

@Repository
public class CustomerInfoDaoImpl implements CustomerInfoDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public CustomerCurrentBalance getCustomerAccountBalance(Long customerId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(CustomerCurrentBalance.class)
				.add(Restrictions.eq("customerId", customerId));
		CustomerCurrentBalance customerCurrentBalance = (CustomerCurrentBalance) criteria
				.uniqueResult();
		// getSessionFactory().getCurrentSession().buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		// getSessionFactory().getCurrentSession().refresh(franchiseeCurrBal);
		return customerCurrentBalance;
	}

	@Override
	public List<Users> getUsers() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Users as u where u.userRole.authority=:urole");

		query.setString("urole", CommonConstants.ROLE_CUSTOMER);
		return (query.list());

	}

	@Override
	public List<CustomerCommision> getCustomerCommisions(Long custId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(CustomerCommision.class)
				.add(Restrictions.eq("userId", custId));
		return criteria.list();
	}

}
