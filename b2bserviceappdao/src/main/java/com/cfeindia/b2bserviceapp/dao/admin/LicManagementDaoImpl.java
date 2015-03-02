package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.LicPremiumBean;

@Repository
public class LicManagementDaoImpl implements LicManagementDao {
	@Autowired
	private SessionFactory sessionFactory;

	public List<LicPremiumBean> getDetailsdao(Timestamp fromDate,
			Timestamp toDate, String status) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(LicPremiumBean.class)
				.add(Restrictions.between("createdAt", fromDate, toDate))
				.add(Restrictions.eq("status", status));

		return (List<LicPremiumBean>) criteria.list();

	}

	public List<LicPremiumBean> getDetailStatusSuccess() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				LicPremiumBean.class);
		return (List<LicPremiumBean>) criteria.list();
	}

	

}
