package com.cfeindia.b2bserviceapp.dao.common;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
@Repository("distributorCurrentBalanceDao")
public class DistributorCurrentBalanceDaoImpl extends BaseDao implements
		DistributorCurrentBalanceDao {

	public DistributorCurrbal getDistributorCurrbal(Long distributorId) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(DistributorCurrbal.class);
		criteria = criteria
				.add(Restrictions.eq("distributorId", distributorId));
		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) criteria
				.uniqueResult();
		return distributorCurrbal;
	}

}
