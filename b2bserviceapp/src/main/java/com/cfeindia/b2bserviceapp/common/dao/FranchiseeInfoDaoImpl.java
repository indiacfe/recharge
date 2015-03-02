package com.cfeindia.b2bserviceapp.common.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Repository("franchiseeInfoDao")
public class FranchiseeInfoDaoImpl extends BaseDao implements FranchiseeInfoDao {

	public FranchiseeCurrBal getFranchiseeAccountBalance(Long franchiseeId) {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(FranchiseeCurrBal.class).add(Restrictions.eq("franchiseeid", franchiseeId));
		FranchiseeCurrBal franchiseeCurrBal=(FranchiseeCurrBal)criteria.uniqueResult();
		getSessionFactory().getCurrentSession().buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		getSessionFactory().getCurrentSession().refresh(franchiseeCurrBal);
		return franchiseeCurrBal;
	}

	public List<TransactionTransportBean> getRefundDetail(String id,
			String rechargeType, Timestamp fromDate, Timestamp toDate) {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(TransactionTransportBean.class)
		.add(Restrictions.eq("retailerId",id))
		.add(Restrictions.eq("status","REFUND"))
		.addOrder(Order.desc("createdAt"))
		.add(Restrictions.between("createdAt", fromDate, toDate));
		 if(!"-1".equalsIgnoreCase(rechargeType))
		 {
			criteria=criteria.add(Restrictions.eq("transactionName", rechargeType));
		 } 
		 List<TransactionTransportBean> transportBean=criteria.list();
		return transportBean;
	}

}
