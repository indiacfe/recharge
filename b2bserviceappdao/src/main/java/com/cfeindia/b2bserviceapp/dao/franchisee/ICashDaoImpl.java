package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.dao.common.BaseDao;
import com.cfeindia.b2bserviceapp.entity.ICashRecharge;

@Repository("iCashDao")
public class ICashDaoImpl extends BaseDao implements ICashDao {
	@Override
	public ICashRecharge checkICashRegistration(String mobileNo) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(ICashRecharge.class)
				.add(Restrictions.eq("mobileNumber", mobileNo));
		return (ICashRecharge) criteria.uniqueResult();
	}
}
