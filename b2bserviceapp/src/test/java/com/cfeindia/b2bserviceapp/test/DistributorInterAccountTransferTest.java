package com.cfeindia.b2bserviceapp.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import junit.framework.TestCase;

public class DistributorInterAccountTransferTest extends TestCase {

	ClassPathXmlApplicationContext applicationContext = null;

	protected void setUp() throws Exception
	{
		applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
		super.setUp();
	}
	public void testDistributorInterAccountTransfer()
	{
			
			FundTransferService fundTransferService=(FundTransferService) applicationContext.getBean("fundTransferService");
			String result=fundTransferService.distAdUnitBalTransTodistCurrBal(101L,100.00);
			assertEquals("success",result);
	}


	protected void tearDown() throws Exception {
		super.tearDown();
		applicationContext.destroy();
	}

}
