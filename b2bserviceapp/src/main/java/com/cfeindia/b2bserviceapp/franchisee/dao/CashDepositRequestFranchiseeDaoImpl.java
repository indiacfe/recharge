package com.cfeindia.b2bserviceapp.franchisee.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;

@Repository
public class CashDepositRequestFranchiseeDaoImpl implements CashDepositRequestFranchiseeDao
{
	@Autowired
	SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void saveCashDepositRequest(FranchiseeCashDepositRequest franchiseeOrderProcessing) 
	{
		sessionFactory.getCurrentSession().save(franchiseeOrderProcessing);
	}

}
