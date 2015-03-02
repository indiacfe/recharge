package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.List;
import java.util.Set;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cfeindia.b2bserviceapp.dto.recharge.mobile.OperatorList;

public class MobileOperatorServiceImpl {
@Autowired
SessionFactory sessionFactory;


public void mobileOperators(){
		
		//Set<OperatorList> mobileOperators=mobileOperatorSet.getMobileOperatorSet();
		
	Session session=sessionFactory.openSession();
	session.beginTransaction();

	Query query=session.createQuery("from masterdata");
	List master=query.list();
	session.getTransaction().commit();
	session.close();
	
	}

}
