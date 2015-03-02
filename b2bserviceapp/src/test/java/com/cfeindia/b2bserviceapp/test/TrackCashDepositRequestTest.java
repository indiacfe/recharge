package com.cfeindia.b2bserviceapp.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;

import junit.framework.TestCase;

public class TrackCashDepositRequestTest extends TestCase {

	ClassPathXmlApplicationContext applicationContext = null;

	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testTrackCashDepositRequests()
	{
		CommonService commonService=(CommonService)applicationContext.getBean("commonService");
		List<FranchiseeCashDepositRequest> cashDepositRequestList=commonService.trackCashDepositRequests();
		System.out.println(cashDepositRequestList.get(0).getRequestType());
		assertNotNull(commonService.trackCashDepositRequests());
	}
}
