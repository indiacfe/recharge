package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.SmsRechargeDetail;

@Repository
public class SmsDetailDaoImpl implements SmsDetailsDao {
	@Autowired
	private SessionFactory sessionFactory;

	public List<SmsRechargeDetail> getDetailsdao(Timestamp fromDate,
			Timestamp toDate) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(SmsRechargeDetail.class)
				.add(Restrictions.between("createdAt", fromDate, toDate))
				.addOrder(Order.desc("createdAt"));
		return (List<SmsRechargeDetail>) criteria.list();

	}
}
