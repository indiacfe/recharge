package com.cfeindia.b2bserviceapp.test;

import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;

public class CompanyBalanceTransToDistributorTest extends TestCase{
	ClassPathXmlApplicationContext applicationContext = null;

	protected void setUp() throws Exception
	{
		applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
		super.setUp();
	}
/*	public void testCompanyToDistributorTransferTest()
	{
		FundTransferService fundTransferService=(FundTransferService) applicationContext.getBean("fundTransferService");
				String result=fundTransferService.companyBalanceTransToDistService(101L, 100, "Cybertel");
		assertEquals("success", result);
	}
	*/
	
/*	
	protected void tearDown() throws Exception {
		applicationContext.destroy();
		super.tearDown();
	}*/

	public void usernameTestk7()
	{
		CommonService commonservice=(CommonService) applicationContext.getBean("commonService");
				Users result=commonservice.searchdistributorId("103");
				
				System.out.println(result.getUsername()+" "+result.getUserId());
		assertNotNull(result);
	}
	

	
	
}
