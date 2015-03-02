package com.cfeindia.b2bserviceapp.franchisee.service;

import java.util.List;
import java.util.Set;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cfeindia.b2bserviceapp.recharge.mobile.dto.OperatorList;

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
	System.out.println(master.size());
	
	}

}
