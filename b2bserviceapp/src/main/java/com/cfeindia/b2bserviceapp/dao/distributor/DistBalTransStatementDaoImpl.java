package com.cfeindia.b2bserviceapp.dao.distributor;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;

@Repository
public class DistBalTransStatementDaoImpl implements DistBalTransStatementDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<DistributorBalanceTransferLog> getBalanceTransferStatement(Timestamp fromDate, Timestamp toDate,String distId)
	{
		long distributorId=CyberTelUtil.getStrInLong(distId);
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(DistributorBalanceTransferLog.class)
		.add(Restrictions.eq("distId", distributorId))
		.addOrder(Order.desc("createdAt"))
		.add(Restrictions.between("createdAt", fromDate, toDate));
		List<DistributorBalanceTransferLog> transferLog=criteria.list();	
		return transferLog;	
	}
	
	@SuppressWarnings("unchecked")
	public List<UserDetail> getMultipleUserDetail(long userId) {
		String q = "select distinct userDetail_ from DistributorBalanceTransferLog as DistributorBalanceTransferLog_ inner join DistributorBalanceTransferLog_.userDetail as userDetail_ where DistributorBalanceTransferLog_.distId=:distId";
		Query query = sessionFactory.getCurrentSession().createQuery(q);
		query.setParameter("distId", userId);
		List<UserDetail> li = query.list();
		return li;

	}

}
