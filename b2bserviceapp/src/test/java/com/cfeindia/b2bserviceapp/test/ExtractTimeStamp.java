package com.cfeindia.b2bserviceapp.test;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;

import junit.framework.TestCase;

public class ExtractTimeStamp extends TestCase {

	ClassPathXmlApplicationContext applicationContext = null;

	protected void setUp() throws Exception
	{
		applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
		super.setUp();
	}
	public void testExtractTimeStamp()
	{
			CommonService commonService=(CommonService) applicationContext.getBean("commonService");
			List<DistributorBalanceTransferLog> result=commonService.getBalanceTransferLogs();
			assertNotNull(result);
	}


	protected void tearDown() throws Exception {
		super.tearDown();
		applicationContext.destroy();
	}

}
