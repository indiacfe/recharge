package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.LicPremiumBean;

@Repository
public class LicPremiumDaoImpl implements LicPremiumDao {
	@Autowired
	private SessionFactory sessionFactory;

	public String saveLicPremiumDao(LicPremiumBean licPremiumBean) {
		sessionFactory.getCurrentSession().save(licPremiumBean);
		return CommonConstants.SUCCESS;
	}

	public List<LicPremiumBean> getLicPremiumDetails(Long userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LicPremiumBean.class).add(Restrictions.eq("userId", userId));
		
		return (List<LicPremiumBean>) criteria.list();
	}

}
