package com.cfeindia.b2bserviceapp.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

import junit.framework.TestCase;

public class RoundedDoubleTest extends TestCase {

	ClassPathXmlApplicationContext applicationContext = null;

	protected void setUp() throws Exception
	{
		applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
		super.setUp();
	}
	
	public void testRoundedDouble()
	{
		double roundedAmount=ExtractorUtil.getRoundedDouble((double) 123);
		System.out.println(roundedAmount);
		assertEquals(123.00, roundedAmount);
	}	

	protected void tearDown() throws Exception {
		super.tearDown();
		applicationContext.destroy();
	}


}
