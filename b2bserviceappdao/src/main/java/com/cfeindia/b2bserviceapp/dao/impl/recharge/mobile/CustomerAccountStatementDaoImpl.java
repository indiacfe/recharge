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
import com.cfeindia.b2bserviceapp.dao.recharge.mobile.CustomerAccountStatementDao;
import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Repository
public class CustomerAccountStatementDaoImpl implements
		CustomerAccountStatementDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<CustomerTransactionTransportBean> getCustomerTransactionTransportBeanListByCustomerId(
			Long userId, String fromDate, String toDate) {
		Timestamp fromDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(CustomerTransactionTransportBean.class)
				.add(Restrictions.between("createdAt", fromDateTimeStamp,
						toDateTimeStamp))
				.addOrder(Order.desc("createdAt"))
				.add(Restrictions.or(
						Restrictions.eq("status", CommonConstants.SUCCESS),
						Restrictions.eq("status", CommonConstants.REFUND)))
				.add(Restrictions.eq("customerId", userId.toString()));

		List<CustomerTransactionTransportBean> customertransactionTransportBeanList = criteria
				.list();
		return customertransactionTransportBeanList;
	}

	@Override
	public List<CustomerBalanceTransferLog> getCustomerBalTransferLogList(
			Long customerId, String fromDate, String toDate) {
		Timestamp fromDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(CustomerBalanceTransferLog.class)
				.add(Restrictions.between("createdAt", fromDateTimeStamp,
						toDateTimeStamp)).addOrder(Order.desc("createdAt"))
				.add(Restrictions.eq("customerId", customerId));
		List<CustomerBalanceTransferLog> customerBalanceTransferLog = criteria
				.list();
		return customerBalanceTransferLog;
	}

	@Override
	public List<CustomerTransactionTransportBean> getCustomerTransactionTransportBeanListCustomerByNumber(
			Long userId, String fromDate, String toDate, String number) {
		Timestamp fromDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(CustomerTransactionTransportBean.class)
				.add(Restrictions.between("createdAt", fromDateTimeStamp,
						toDateTimeStamp)).addOrder(Order.desc("createdAt"))
				.add(Restrictions.eq("customerId", userId.toString()));
		if (number != null && number.length() > 0)
			criteria = criteria.add(Restrictions.or(
					Restrictions.eq("mobileNo", number),
					Restrictions.eq("connectionid", number)));

		List<CustomerTransactionTransportBean> customerTransactionTransportBeanList = criteria
				.list();
		return customerTransactionTransportBeanList;
	}
	@Override
	public List<CustomerCommision> getCustomerCommisions(Long userId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(CustomerCommision.class)
				.add(Restrictions.eq("userId", userId));
		return (List<CustomerCommision>) criteria.list();
	}
}
