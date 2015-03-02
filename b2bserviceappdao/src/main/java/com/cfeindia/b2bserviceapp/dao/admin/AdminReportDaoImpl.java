package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.BaseDao;
import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyCustomerTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
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
			criteria = criteria.add(Restrictions.eq("distributorId.userId",
					userId));
		}
		return (List<CompanyDistributorTransactionLog>) criteria.list();
	}

	public List<TransactionTransportBean> generateRechargeHistoryReport(
			Timestamp fromDate, Timestamp toDate, String sel, String status) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(TransactionTransportBean.class);
		if (!"ALL".equalsIgnoreCase(sel)) {
			criteria.add(Restrictions.eq("thirdPartyServiceProvider", sel));
		}
		if (!"ALL".equalsIgnoreCase(status)) {
			criteria.add(Restrictions.eq("status", status));
		}
		criteria.addOrder(Order.desc("createdAt")).add(
				Restrictions.between("createdAt", fromDate, toDate));
		return criteria.list();
	}

	public List<TransactionTransportBean> generateFranTransactionReport(
			Timestamp sDate, Timestamp eDate, String franchiseeId,
			String serviceProvider) {
		Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.add(Restrictions.eq("retailerId", franchiseeId))
				.add(Restrictions.eq("thirdPartyServiceProvider",
						serviceProvider))
				.add(Restrictions.between("createdAt", sDate, eDate));
		List<TransactionTransportBean> transactionTransportBeansList = criteria
				.list();

		return transactionTransportBeansList;
	}

	@Override
	public List<CompanyCustomerTransactionLog> generateCustFundTransferReport(
			Long userId, Timestamp fromdateTimeStamp, Timestamp toDateTimeStamp) {
		Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(CompanyCustomerTransactionLog.class)
				.add(Restrictions.between("createdAt", fromdateTimeStamp,
						toDateTimeStamp)).addOrder(Order.desc("createdAt"));
		if (userId != null) {
			criteria = criteria.add(Restrictions
					.eq("customerId.userId", userId));
		}
		return (List<CompanyCustomerTransactionLog>) criteria.list();
	}

	@Override
	public List<TransactionTransportBean> generateRechargeHistoryReportAll(
			Timestamp frmDate, Timestamp toDte, String sel, String status) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.addOrder(Order.desc("createdAt"))
				.add(Restrictions.eq("thirdPartyServiceProvider", sel))
				.add(Restrictions.between("createdAt", frmDate, toDte));
		return (criteria.list());
	}

	@Override
	public List<CustomerTransactionTransportBean> generateCustomerRechargeHistoryReport(
			Timestamp frmDate, Timestamp toDte, String customerId,
			String status, String thirdPartyName) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CustomerTransactionTransportBean.class)
				.addOrder(Order.desc("createdAt"))
				.add(Restrictions.between("createdAt", frmDate, toDte));
		if (!"ALL".equalsIgnoreCase(customerId)) {
			criteria.add(Restrictions.eq("customerId", customerId));
		}
		if (!"ALL".equalsIgnoreCase(status)) {
			criteria.add(Restrictions.eq("status", status));
		}
		if (!"ALL".equalsIgnoreCase(thirdPartyName)) {
			criteria.add(Restrictions.eq("thirdPartyServiceProvider",
					thirdPartyName));
		}
		return (criteria.list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getAllUserDetails(int pageNo) { 
		int firstPage = (pageNo - 1) * 50;
		int maxPage = 50;

		Criteria criteria = (Criteria) getSessionFactory().getCurrentSession()
				.createCriteria(Users.class);
		criteria.setFirstResult(firstPage);
		criteria.setMaxResults(maxPage);
		return criteria.list();
	}

	@Override
	public Number getcountUserList() {
		return (Number) getSessionFactory().getCurrentSession()
				.createCriteria(Users.class)
				.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public List<TransactionTransportBean> generateOpertorWiseReport(
			Timestamp fromDate, Timestamp toDate, String sel, String status,String operator) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(TransactionTransportBean.class);
		if (!"ALL".equalsIgnoreCase(sel)) {
			criteria.add(Restrictions.eq("thirdPartyServiceProvider", sel));
		}
		if (!"ALL".equalsIgnoreCase(status)) {
			criteria.add(Restrictions.eq("status", status));
		}if (!"ALL".equalsIgnoreCase(operator)) {
			criteria.add(Restrictions.eq("operator", operator));
		}
		criteria.addOrder(Order.desc("createdAt")).add(
				Restrictions.between("createdAt", fromDate, toDate));
		return criteria.list();
	}
	@Override
	public List<CustomerTransactionTransportBean> generateCustomerOperatorWiseRechargeReport(
			Timestamp frmDate, Timestamp toDte, String customerId,
			String status, String thirdPartyName,String operator) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CustomerTransactionTransportBean.class)
				.addOrder(Order.desc("createdAt"))
				.add(Restrictions.between("createdAt", frmDate, toDte));
		if (!"ALL".equalsIgnoreCase(customerId)) {
			criteria.add(Restrictions.eq("customerId", customerId));
		}
		if (!"ALL".equalsIgnoreCase(status)) {
			criteria.add(Restrictions.eq("status", status));
		}
		if (!"ALL".equalsIgnoreCase(thirdPartyName)) {
			criteria.add(Restrictions.eq("thirdPartyServiceProvider",
					thirdPartyName));
		}if (!"ALL".equalsIgnoreCase(operator)) {
			criteria.add(Restrictions.eq("operator",
					operator));
		}
		return (criteria.list());
	}
	
	@Override
	public List<CustomerTransactionTransportBean> generateElectricityRechargeReport(
			Timestamp frmDate, Timestamp toDte, String customerId,
			String status, String thirdPartyName,String operator) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CustomerTransactionTransportBean.class)
				.addOrder(Order.desc("createdAt"))
				.add(Restrictions.between("createdAt", frmDate, toDte));
		if (!"ALL".equalsIgnoreCase(customerId)) {
			criteria.add(Restrictions.eq("customerId", customerId));
		}
		if (!"ALL".equalsIgnoreCase(status)) {
			criteria.add(Restrictions.eq("status", status));
		}
		if (!"ALL".equalsIgnoreCase(thirdPartyName)) {
			criteria.add(Restrictions.eq("thirdPartyServiceProvider",
					thirdPartyName));
		}if (!"ALL".equalsIgnoreCase(operator)) {
			criteria.add(Restrictions.eq("operator",
					operator));
		}if (!"ALL".equalsIgnoreCase(CommonConstants.ELECTRICITY)) {
			criteria.add(Restrictions.eq("transactionName",
					CommonConstants.ELECTRICITY));
		}
		return (criteria.list());
	}
	public List<TransactionTransportBean> generateElectricityWiseReport(
			Timestamp fromDate, Timestamp toDate, String sel, String status,String operator) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(TransactionTransportBean.class);
		if (!"ALL".equalsIgnoreCase(sel)) {
			criteria.add(Restrictions.eq("thirdPartyServiceProvider", sel));
		}
		if (!"ALL".equalsIgnoreCase(status)) {
			criteria.add(Restrictions.eq("status", status));
		}if (!"ALL".equalsIgnoreCase(operator)) {
			criteria.add(Restrictions.eq("operator", operator));
		}if (!"ALL".equalsIgnoreCase(CommonConstants.ELECTRICITY)) {
			criteria.add(Restrictions.eq("transactionName",
					CommonConstants.ELECTRICITY));
		}
		criteria.addOrder(Order.desc("createdAt")).add(
				Restrictions.between("createdAt", fromDate, toDate));
		return criteria.list();
	}
}
