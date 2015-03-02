package com.cfeindia.b2bserviceapp.dao.common;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveEntity(Object obj) {
		sessionFactory.getCurrentSession().save(obj);
	}

	public void updateEntity(Object obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	public Object getEntityByPrimaryKey(Class classObj, long id) {
		return sessionFactory.getCurrentSession().load(classObj, id);
	}
	public Long save(Object obj) {
		return (Long)sessionFactory.getCurrentSession().save(obj);
	}
	
}
