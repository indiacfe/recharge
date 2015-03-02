package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.entity.RetailerCommison;
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
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.add(Restrictions.eq("transid", tId))
				.add(Restrictions.or(
						Restrictions.eq("status", CommonConstants.SUCCESS),

						Restrictions.eq("status", CommonConstants.ERROR)));

		/*
		 * Query query=sessionFactory.getCurrentSession().createQuery(
		 * "from  TransactionTransportBean where transid=:tid and (status=:status1 or status=:status2 or status=:status3) "
		 * ); query.setString("tid", "tId");
		 * query.setString("status1",CommonConstants.SUCCESS);
		 * query.setString("status2", "REFUND"); query.setString("status3",
		 * "REJECTED");
		 */

		return (TransactionTransportBean) criteria.uniqueResult();
	}

	public TransactionTransportBean getransactionDetails(String tId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.add(Restrictions.eq("transid", tId));

		/*
		 * Query query=sessionFactory.getCurrentSession().createQuery(
		 * "from  TransactionTransportBean where transid=:tid and (status=:status1 or status=:status2 or status=:status3) "
		 * ); query.setString("tid", "tId");
		 * query.setString("status1",CommonConstants.SUCCESS);
		 * query.setString("status2", "REFUND"); query.setString("status3",
		 * "REJECTED");
		 */

		return (TransactionTransportBean) criteria.uniqueResult();
	}

	public List<FranchiseeRefundRequests> getAllList(Long userId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(FranchiseeRefundRequests.class)
				.add(Restrictions.eq("userId", userId));
		return (List<FranchiseeRefundRequests>) criteria.list();
	}

	public List<RetailerCommison> getRetailerComm() {
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT c1.retailercommision, c1.operator_name, c1.recharge_type, c1.thirdparty_service_provider FROM companyoperatorcomission c1, current_thirdparty_service_provider c2 WHERE c1.operator_name = c2.operator_name AND c1.recharge_type = c2.service_type AND c1.thirdparty_service_provider = c2.third_party_service_provider ORDER BY c1.recharge_type DESC, c1.operator_name;");
		List list = query.list();
		List<RetailerCommison> listRetailerCommisons = new ArrayList<RetailerCommison>();
		for (Object obj : list) {
			RetailerCommison retailerCommission = new RetailerCommison();
			Object[] valSet = (Object[]) obj;
			retailerCommission.setOperatorName((String) valSet[1]);
			retailerCommission.setRechargeType((String) valSet[2]);
			retailerCommission.setRetailercommison(Double.parseDouble(valSet[0]
					.toString()));
			retailerCommission.setServiceProvider((String) valSet[3]);
			listRetailerCommisons.add(retailerCommission);
		}
		return listRetailerCommisons;

	}

	@Override
	public FranchiseeRefundRequests getRefundRequestByTransactionId(String tId,long franchiseeId) {
		FranchiseeRefundRequests refundRequests = (FranchiseeRefundRequests) sessionFactory
				.getCurrentSession()
				.createCriteria(FranchiseeRefundRequests.class)
				.add(Restrictions.eq("transId", tId)).uniqueResult();
		return refundRequests;
	}

}
