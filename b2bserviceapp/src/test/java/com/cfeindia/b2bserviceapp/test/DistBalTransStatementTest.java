package com.cfeindia.b2bserviceapp.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.service.distributor.DistBalTransStatementService;

import junit.framework.TestCase;

public class DistBalTransStatementTest extends TestCase {

	ClassPathXmlApplicationContext applicationContext = null;

	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
	}
	
	public void testBalTransferStatement()
	{
		DistBalTransStatementService distBalTransStatementService=(DistBalTransStatementService) applicationContext.getBean("distBalTransStatementService");
		List<DistributorBalanceTransferLog> transferlog=distBalTransStatementService.getFranBalTransferStatement("01/09/2013", "13/09/2013","102");
		assertNotNull(transferlog);
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
