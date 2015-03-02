package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Repository
public class FranEarningDaoImpl implements FranEarningDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Object> getEarningDetails(Timestamp fromDate, Timestamp toDate,
			Long franId) {
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery("SELECT SUM(credit_amount_franchisee) AS totalAmount, franchisee_id as retailerId, created_at as "
						+ "createdAt  FROM recharge_transaction "
						+ "WHERE retailerId=:franId AND DATE(createdAt) BETWEEN "
						+ "DATE(:fromDate) AND DATE(:toDate) GROUP BY DATE(createdAt)");
		query.setLong("franId", franId);
		query.setTimestamp("fromDate", fromDate);
		query.setTimestamp("toDate", toDate);
		List<Object> list = query.list();
		return  list;
	}
}
