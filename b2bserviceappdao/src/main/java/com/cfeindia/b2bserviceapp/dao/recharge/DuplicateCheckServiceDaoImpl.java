package com.cfeindia.b2bserviceapp.dao.recharge;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Repository
public class DuplicateCheckServiceDaoImpl implements DuplicateCheckServiceDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean checkDuplicate(
			TransactionTransportBean transactionTransportBean) {
		String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				TransactionTransportBean.class);
		Criterion lhs = Restrictions.eq("mobileNo",
				transactionTransportBean.getMobileNo());
		Criterion rhs = Restrictions.eq("connectionid",
				transactionTransportBean.getConnectionid());
		criteria.add(Restrictions.or(lhs, rhs));
		criteria.add(Restrictions.ne("status", CommonConstants.FAILED));
		criteria.add(Restrictions.between("createdAt",
				TimeStampUtil.getTimeStampFromStringFromdate(date),
				TimeStampUtil.getTimeStampFromStringTodate(date)));
		criteria.addOrder(Order.desc("createdAt"));
		List<TransactionTransportBean> list = criteria.list();
		if(list.size()>0)
		{		TransactionTransportBean bean = list.get(0);
		long diff = TimeStampUtil.getTimestamp().getTime()
				- bean.getCreatedAt().getTime();
		long diffInMinute = diff / (60 * 1000) % 60;
		if (diffInMinute < 5) {
			return true;
		} else {
			return false;
		}
		}else {
			return false;
		}

	}
}
