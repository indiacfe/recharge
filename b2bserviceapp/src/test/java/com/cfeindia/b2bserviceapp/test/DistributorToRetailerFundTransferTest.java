package com.cfeindia.b2bserviceapp.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;

import junit.framework.TestCase;

public class DistributorToRetailerFundTransferTest extends TestCase {

	ClassPathXmlApplicationContext applicationContext = null;

	protected void setUp() throws Exception {
	applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
		super.setUp();
	}
	 public void testFundtransfer()
	 {
			FundTransferService fundTransferService=(FundTransferService) applicationContext.getBean("fundTransferService");
			String result=fundTransferService.fundToRetailerCurrService(100L, 101L, 100);
			assertEquals("success", result);
	 }

	protected void tearDown() throws Exception
	{
		applicationContext.destroy();
		super.tearDown();
	}
	

}
