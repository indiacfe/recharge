package com.cfeindia.b2bserviceapp.test;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.entity.RetailerCommison;
import com.cfeindia.b2bserviceapp.service.franchisee.RefundRequestFranchiseeService;

public class RetailerCommisonTest extends TestCase{
	

		ClassPathXmlApplicationContext applicationContext = null;
/*
		public void setUp() {
			applicationContext = new ClassPathXmlApplicationContext(
					"spring-config.xml");

		}
		*/	
		public void testRechargeService() {
			//RefundRequestFranchiseeService requestFranchiseeService = (RefundRequestFranchiseeService) applicationContext.getBean("refundRequestService");
			//List<RetailerCommison> list=requestFranchiseeService.getRetailerCommisions();
			//for(RetailerCommison retailerCommison:list){
				//System.out.println(retailerCommison.getOperatorName());
			//}
			System.out.println("done");
		}

	


}
