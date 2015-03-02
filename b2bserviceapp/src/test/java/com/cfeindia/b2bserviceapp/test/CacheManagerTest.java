package com.cfeindia.b2bserviceapp.test;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.common.CacheManager;

public class CacheManagerTest  extends TestCase
{

	ClassPathXmlApplicationContext applicationContext = null;

	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
	}
	public void testGetOperatorCircles() {
		String listOfCircles = CacheManager.getOperatorCircles("MTNL_TALKTIME", "MOBILE_PREPAID");
		assertNotNull(listOfCircles);
	}

}
