package com.cfeindia.b2bserviceapp.dao.common.thirdparty;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.dao.common.BaseDao;

@Repository("thirdpartyDao")
@Transactional
public class ThirdPartyDetailProviderDaoImpl extends BaseDao implements ThirdPartyDetailProviderDao {

	public ThirdPartyDetailObject getThirdPartyDetailObject(String serviceProviderName) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ThirdPartyDetailObject.class).add(Restrictions.eq("thirpartyprovidername", serviceProviderName));
		ThirdPartyDetailObject thirdPartyDetailObject = (ThirdPartyDetailObject) criteria.uniqueResult();
		return thirdPartyDetailObject;
	}
}
