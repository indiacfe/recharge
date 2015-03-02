package com.cfeindia.b2bserviceapp.service.distributor;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.Users;

@Repository
public class DistributorSearchFranchiseeDaoImpl implements
		DistributorSearchFranchiseeDao {

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Users searchUserBasedOnIdOrMobNum(String userIdOrMobnum) {
		long userid = 0;
		try {
			userid = Long.parseLong(userIdOrMobnum);
		} catch (Exception e) {

		}
		Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(Users.class)
				.add(Restrictions.or(Restrictions.eq("userId", userid),
						Restrictions.eq("username", userIdOrMobnum)));
		return 	(Users) criteria.uniqueResult();
		
	}

}
