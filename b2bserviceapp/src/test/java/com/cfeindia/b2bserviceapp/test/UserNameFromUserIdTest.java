package com.cfeindia.b2bserviceapp.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.common.CommonService;

import junit.framework.TestCase;

public class UserNameFromUserIdTest extends TestCase {

	ClassPathXmlApplicationContext applicationContext = null;

	protected void setUp() throws Exception {
	applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
		super.setUp();
	}

	public void testUserNameFromUserId()
	{
		CommonService commonService=(CommonService) applicationContext.getBean("commonService");
		String userName=commonService.getUsername("100");
		System.out.println("username:"+userName);
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
