package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;

@Repository
public class TotalFundTransferDaoImpl implements TotalFundTransferDao {
	@Autowired
	private SessionFactory sessionFactory;

	public List<CompanyDistributorTransactionLog> getAllUsers(
			Timestamp startDate, Timestamp endDate) {
		/*
		 * Criteria criteria = sessionFactory .getCurrentSession()
		 * .createCriteria(CompanyDistributorTransactionLog.class)
		 * .add(Restrictions.between("createdAt", startDate, endDate))
		 * .setProjection( Projections.distinct(Projections
		 * .property("distributorId.userId")));
		 */

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM CompanyDistributorTransactionLog as cdtl WHERE  cdtl.id IN(SELECT  MAX(id) FROM CompanyDistributorTransactionLog Where createdAt BETWEEN :sDate AND :eDate group by distributorId.userId)");
		query.setTimestamp("sDate", startDate);
		query.setTimestamp("eDate", endDate);
		return query.list();
	}

	@Override
	public Double getTotalAmount(Long userId,Timestamp startDate, Timestamp endDate) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"SELECT SUM(transferAmount) AS total FROM CompanyDistributorTransactionLog  WHERE distributorId=:distId AND createdAt BETWEEN :sDate AND :eDate" );
		query.setLong("distId", userId);
		query.setTimestamp("sDate", startDate);
		query.setTimestamp("eDate", endDate);
		return (Double) query.uniqueResult();
	}
}
