package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
@Repository
public class FranBalTransStatementDaoImpl implements FranBalTransStatementDao {

	@Autowired
	SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings(value = { "unchecked" })
	public List<FranchiseeBalanceTransferLog> getBalanceTransferStatement(Timestamp fromDate, Timestamp toDate,long franId) 
	{
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(FranchiseeBalanceTransferLog.class)
		.add(Restrictions.eq("franchiseeid", franId))
		.add(Restrictions.between("createdAt", fromDate, toDate));
		List<FranchiseeBalanceTransferLog> transferLog=criteria.list();	
		return transferLog;
	}

	
}
