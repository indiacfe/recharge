package com.cfeindia.b2bserviceapp.dao.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.NoticeInfo;

@Repository
public class NoticeDaoImpl implements NoticeDao {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createNews(NoticeInfo noticeInfo) {
		sessionFactory.getCurrentSession().save(noticeInfo);
		

	}

	public List<NoticeInfo> noticeList() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NoticeInfo.class);
		return (List<NoticeInfo>)criteria.list();
	}

	public NoticeInfo getEntity(int noticeId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NoticeInfo.class);
		criteria = criteria.add(Restrictions.eq("noticeId", noticeId));
		return (NoticeInfo) criteria.uniqueResult();
	}

	public void updateNotice(NoticeInfo noticeInfo) {
		sessionFactory.getCurrentSession().update(noticeInfo);
		
	}

	@Override
	public void deleteEntity(NoticeInfo noticeInfo) {
		sessionFactory.getCurrentSession().delete(noticeInfo);
	}

}
