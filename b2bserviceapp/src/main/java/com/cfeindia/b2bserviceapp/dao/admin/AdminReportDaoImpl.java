package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.dao.BaseDao;
import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Repository
public class AdminReportDaoImpl extends BaseDao implements AdminReportDao {

	public List<CompanyBalTransactionLog> generateFundTransferReport(
			Long userId, Timestamp fromdateTimeStamp, Timestamp toDateTimeStamp) {
		Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(CompanyBalTransactionLog.class)
				.add(Restrictions.between("createdAt", fromdateTimeStamp,
						toDateTimeStamp)).addOrder(Order.desc("createdAt"));
		System.out.println("user id in dao" + userId);
		if (userId != null) {
			criteria = criteria.add(Restrictions.eq("userId.userId", userId));
		}
		return (List<CompanyBalTransactionLog>) criteria.list();
	}

	public List<CompanyDistributorTransactionLog> generateFundTransferReport1(
			Long userId, Timestamp fromdateTimeStamp, Timestamp toDateTimeStamp) {
		Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(CompanyDistributorTransactionLog.class)
				.add(Restrictions.between("createdAt", fromdateTimeStamp,
						toDateTimeStamp)).addOrder(Order.desc("createdAt"));
		if (userId != null) {
			criteria = criteria.add(Restrictions.eq("distributorId.userId", userId));
		}
		return (List<CompanyDistributorTransactionLog>) criteria.list();
	}

	public List<TransactionTransportBean> generateRechargeHistoryReport(
			Timestamp fromDate, Timestamp toDate, String sel,String status) {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(TransactionTransportBean.class)
				.add(Restrictions.eq("thirdPartyServiceProvider", sel))
				.addOrder(Order.desc("createdAt"))
				.add(Restrictions.eq("status", status))
				.add(Restrictions.between("createdAt", fromDate, toDate));
		List<TransactionTransportBean> transactionTransportBeansList=criteria.list();
		return transactionTransportBeansList;
	}
}
