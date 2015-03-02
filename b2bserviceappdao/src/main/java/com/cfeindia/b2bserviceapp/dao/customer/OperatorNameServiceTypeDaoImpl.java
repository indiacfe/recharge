package com.cfeindia.b2bserviceapp.dao.customer;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.B2bOperatorsCodeBean;

@Repository
public class OperatorNameServiceTypeDaoImpl implements
		OperatorNameServiceTypeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public B2bOperatorsCodeBean getOperatorAndServiceType(String opcode) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(B2bOperatorsCodeBean.class)
				.add(Restrictions.eq("opCode", opcode));
		return (B2bOperatorsCodeBean) criteria.uniqueResult();
	}

}
