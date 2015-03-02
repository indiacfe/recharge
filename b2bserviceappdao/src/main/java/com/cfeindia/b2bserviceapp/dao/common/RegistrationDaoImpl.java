package com.cfeindia.b2bserviceapp.dao.common;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.Users;

@Repository
public class RegistrationDaoImpl implements RegistrationDao
{

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public long usersDataSave(Users users)
	{
		long userId=(Long) sessionFactory.getCurrentSession().save(users);
		return userId;
	}

	public DistributorCurrbal loadDistributorCurrbal(Long currDistid) {
		return (DistributorCurrbal)sessionFactory.getCurrentSession().createCriteria(DistributorCurrbal.class).add(Restrictions.eq("distributorId", currDistid)).uniqueResult();
	}
	
	public CompanyBalance loadCompanyAccountBalance(){
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(CompanyBalance.class)
				.add(Restrictions.eq("companyName", CommonConstants.companyName));
		return (CompanyBalance) criteria.uniqueResult();
	}
}
