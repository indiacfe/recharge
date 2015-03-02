package com.cfeindia.b2bserviceapp.common.thirdparty.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.dao.BaseDao;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;

@Repository("thirdpartyDao")
@Transactional
public class ThirdPartyDetailProviderDaoImpl extends BaseDao implements ThirdPartyDetailProviderDao {

	public ThirdPartyDetailObject getThirdPartyDetailObject(String serviceProviderName) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ThirdPartyDetailObject.class).add(Restrictions.eq("thirpartyprovidername", serviceProviderName));
		ThirdPartyDetailObject thirdPartyDetailObject = (ThirdPartyDetailObject) criteria.uniqueResult();
		return thirdPartyDetailObject;
	}
}
