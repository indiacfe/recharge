package com.cfeindia.b2bserviceapp.franchisee.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Repository
public class RefundRequestFranchiseeDaoImpl implements
		RefundRequestFranchiseeDao {

	@Autowired
	SessionFactory sessionFactory;

	public void requestRefund(FranchiseeRefundRequests franchiseeRefundRequests) {

		sessionFactory.getCurrentSession().save(franchiseeRefundRequests);
	}

	public TransactionTransportBean checkTransactionId(String tId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.add(Restrictions.eq("transid", tId))
				.add(Restrictions.eq("status", CommonConstants.SUCCESS));
		return (TransactionTransportBean) criteria.uniqueResult();
	}

}
