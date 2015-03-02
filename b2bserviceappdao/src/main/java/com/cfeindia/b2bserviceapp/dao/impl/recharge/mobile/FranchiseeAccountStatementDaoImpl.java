package com.cfeindia.b2bserviceapp.dao.impl.recharge.mobile;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.recharge.mobile.FranchiseeAccountStatementDao;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Repository
public class FranchiseeAccountStatementDaoImpl implements
		FranchiseeAccountStatementDao {
	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<TransactionTransportBean> getTransactionTransportBeanListByRetailerId(
			Long userId, String fromDate, String toDate) {
		Timestamp fromDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.add(Restrictions.between("createdAt", fromDateTimeStamp,
						toDateTimeStamp))
				.addOrder(Order.desc("createdAt"))
				.add(Restrictions.or(
						Restrictions.eq("status", CommonConstants.SUCCESS),
						Restrictions.eq("status", CommonConstants.REFUND)))
				.add(Restrictions.eq("retailerId", userId.toString()));

		List<TransactionTransportBean> transactionTransportBeanList = criteria
				.list();
		return transactionTransportBeanList;
	}
	
	public List<TransactionTransportBean> getTransactionTransportBeanListForRefundByRetailerId(
			Long userId, String fromDate, String toDate) {
		Timestamp fromDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.add(Restrictions.between("updatedAt", fromDateTimeStamp,
						toDateTimeStamp)).addOrder(Order.desc("createdAt"))
				.add(Restrictions.eq("status", CommonConstants.REFUND))
				.add(Restrictions.eq("retailerId", userId.toString()));

		List<TransactionTransportBean> transactionTransportBeanList = criteria
				.list();
		return transactionTransportBeanList;
	}

	@SuppressWarnings("unchecked")
	public List<TransactionTransportBean> getTransactionTransportBeanListRetailerByNumber(
			Long userId, String fromDate, String toDate, String number) {
		Timestamp fromDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.add(Restrictions.between("createdAt", fromDateTimeStamp,
						toDateTimeStamp)).addOrder(Order.desc("createdAt"))
				.add(Restrictions.eq("retailerId", userId.toString()));
		if (number != null && number.length() > 0)
			criteria = criteria.add(Restrictions.or(
					Restrictions.eq("mobileNo", number),
					Restrictions.eq("connectionid", number)));

		List<TransactionTransportBean> transactionTransportBeanList = criteria
				.list();
		return transactionTransportBeanList;
	}

	public List<FranchiseeBalanceTransferLog> getFranchiseeBalTransferLogList(
			Long franchiseeId, String fromDate, String toDate) {
		Timestamp fromDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(FranchiseeBalanceTransferLog.class)
				.add(Restrictions.between("createdAt", fromDateTimeStamp,
						toDateTimeStamp)).addOrder(Order.desc("createdAt"))
				.add(Restrictions.eq("franchiseeId", franchiseeId));
		List<FranchiseeBalanceTransferLog> franchiseeBalanceTransferLog = criteria
				.list();
		return franchiseeBalanceTransferLog;
	}

	public FranchiseeRefundRequests getRefundDetails(String transId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				FranchiseeRefundRequests.class);
		criteria.add(Restrictions.eq("transId", transId));
		return (FranchiseeRefundRequests) criteria.uniqueResult();
	}

	@Override
	public List<TransactionTransportBean> newgenerateAccountStatementReport(
			Long userId, String fromDate, String toDate,
			String thirdPartyServiceProvider) {
		Timestamp fromDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.add(Restrictions.eq("thirdPartyServiceProvider",
						thirdPartyServiceProvider))
				.add(Restrictions.between("createdAt", fromDateTimeStamp,
						toDateTimeStamp))
				.addOrder(Order.desc("createdAt"))
				.add(Restrictions.or(
						Restrictions.eq("status", CommonConstants.SUCCESS),
						Restrictions.eq("status", CommonConstants.REFUND)))
				.add(Restrictions.eq("retailerId", userId.toString()));

		List<TransactionTransportBean> transactionTransportBeanList = criteria
				.list();
		return transactionTransportBeanList;
	}
}
