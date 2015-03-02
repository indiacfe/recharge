package com.cfeindia.b2bserviceapp.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.admin.service.RefundAmountService;
import com.cfeindia.b2bserviceapp.dto.RefundAmountDto;

public class AdminUtilityTest extends TestCase {

	ClassPathXmlApplicationContext applicationContext = null;

	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring-config.xml");
	}



	public void serchByTi() {

		RefundAmountService refundAmountService = (RefundAmountService) applicationContext
				.getBean("refundAmountService");
		//refundAmountService.refundAmount(4L,1000D,"103");
		
		}
		
	}


