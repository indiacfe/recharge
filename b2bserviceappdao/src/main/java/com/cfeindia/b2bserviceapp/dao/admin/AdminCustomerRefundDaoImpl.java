package com.cfeindia.b2bserviceapp.dao.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.BaseDao;
import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.CustomerRefundRequest;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Repository
public class AdminCustomerRefundDaoImpl extends BaseDao implements
		AdminCustomerRefundDao {

	public CustomerTransactionTransportBean getEntityByKey(String tid) {

		CustomerTransactionTransportBean transactionTransportBean = (CustomerTransactionTransportBean) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CustomerTransactionTransportBean.class)
				.add(Restrictions.eq("clientTransId", tid)).uniqueResult();
		return transactionTransportBean;
	}

	public CustomerRefundRequest getCustomerRefundRequestBean(String tId) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CustomerRefundRequest.class)
				.add(Restrictions.eq("clientTransId", tId))
				.add(Restrictions.eq("status", CommonConstants.IN_PROCESS));
		return (CustomerRefundRequest) criteria.uniqueResult();
	}

	@Override
	public List<CustomerRefundRequest> getAllRefundRequest() {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CustomerRefundRequest.class)
				.add(Restrictions.eq("status", CommonConstants.IN_PROCESS));
		;
		return criteria.list();
	}

	@Override
	public void setCustomerBalTransferLog(
			CustomerBalanceTransferLog customerBalanceTransferLog) {
		getSessionFactory().getCurrentSession()
				.save(customerBalanceTransferLog);

	}

	@Override
	public double updateCustomerCurrBal(long customerId, Double refundedAmount) {
		CustomerCurrentBalance customerCurrentBalance = (CustomerCurrentBalance) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CustomerCurrentBalance.class)
				.add(Restrictions.eq("customerId", customerId)).uniqueResult();
		getSessionFactory().getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE)
				.lock(customerCurrentBalance);
		getSessionFactory().getCurrentSession().refresh(customerCurrentBalance);
		double updatedCurrbal = customerCurrentBalance.getCurrAcBalance()
				+ refundedAmount;
		customerCurrentBalance.setCurrAcBalance(updatedCurrbal);
		customerCurrentBalance.setUpdatedAt(TimeStampUtil.getTimestamp());
		getSessionFactory().getCurrentSession().update(customerCurrentBalance);
		return updatedCurrbal;
	}

}
