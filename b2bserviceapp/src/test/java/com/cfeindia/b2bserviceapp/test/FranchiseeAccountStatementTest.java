package com.cfeindia.b2bserviceapp.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.accountstatement.Dto.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.franchisee.service.FranAccountStatmentService;

import junit.framework.TestCase;

public class FranchiseeAccountStatementTest extends TestCase {

	ClassPathXmlApplicationContext applicationContext=null;
	protected void setUp() throws Exception {
		super.setUp();
		applicationContext=new ClassPathXmlApplicationContext("spring-config.xml");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testAccountStatementService()
	{
		FranAccountStatmentService franAccountStatmentService=(FranAccountStatmentService) applicationContext.getBean("franAccountStatmentService");
		//List<FranchiseeAccountStatementDto> franchiseeAccountStatementDtolist=franAccountStatmentService.generateAccountStatementReport((long) 100, "05/09/2013", "20/09/2013");
		//assertNotNull(franchiseeAccountStatementDtolist);
		
		List<FranchiseeAccountStatementDto> franchiseeAccountStatementDtolist=franAccountStatmentService.generateRechargeHistoryReport((long) 100, "05/09/2013", "20/09/2013", null);
		assertNotNull(franchiseeAccountStatementDtolist);
	}

}
