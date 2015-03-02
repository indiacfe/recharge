package com.cfeindia.b2bserviceapp.dao.customer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerRefundRequest;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.RetailerCommison;

@Repository
public class CustomerRefundDaoImpl implements CustomerRefundDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public CustomerTransactionTransportBean checkTransactionId(
			String clientTransId) {
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(CustomerTransactionTransportBean.class)
				.add(Restrictions.eq("clientTransId", clientTransId))
				.add(Restrictions.or(
						Restrictions.eq("status", CommonConstants.SUCCESS),
						Restrictions.eq("status", CommonConstants.ERROR)));
		return (CustomerTransactionTransportBean) criteria.uniqueResult();
	}

	@Override
	public void refundRequest(CustomerRefundRequest customerRefundRequest) {
		sessionFactory.getCurrentSession().save(customerRefundRequest);
	}

	@Override
	public List<CustomerRefundRequest> getAllRefunds(Long userId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(CustomerRefundRequest.class)
				.add(Restrictions.eq("userId", userId));
		return criteria.list();
	}

	@Override
	public CustomerTransactionTransportBean getTransactionDetail(
			String clientTransId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(CustomerTransactionTransportBean.class)
				.add(Restrictions.eq("clientTransId", clientTransId));
		return (CustomerTransactionTransportBean) criteria.uniqueResult();
	}

	

	@Override
	public CustomerRefundRequest getRefundRequest(String clientTransId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(CustomerRefundRequest.class)
				.add(Restrictions.eq("clientTransId", clientTransId));
		return (CustomerRefundRequest) criteria.uniqueResult();
	}
}
