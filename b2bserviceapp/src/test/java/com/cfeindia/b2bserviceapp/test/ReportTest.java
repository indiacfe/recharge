package com.cfeindia.b2bserviceapp.test;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.service.distributor.DistBalTransStatementService;

public class ReportTest extends TestCase
{

	ClassPathXmlApplicationContext applicationContext = null;

	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
	}
	public void testDistributorFundTransfer(){
		DistBalTransStatementService distBalTransStatementService = (DistBalTransStatementService)applicationContext.getBean("distBalTransStatementService");
		List<DistributorBalanceTransferLog> transferlog = distBalTransStatementService
				.getFranBalTransferStatement(
						"09/01/2013",
						"09/20/2013", "101");
		assertNotNull(transferlog);
	}

}
