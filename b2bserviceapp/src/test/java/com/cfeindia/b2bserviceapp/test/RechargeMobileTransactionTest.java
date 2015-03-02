package com.cfeindia.b2bserviceapp.test;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public class RechargeMobileTransactionTest extends TestCase {

	ClassPathXmlApplicationContext applicationContext = null;

	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring-config.xml");

	}
	
	public void testRechargeService() {
		RechargeTransactionService rechargeTransactionService = (RechargeTransactionService) applicationContext.getBean("RechargeTransactionService");
		TransactionTransportBean transactionTransport=new TransactionTransportBean();
		transactionTransport.setAmount(10.0);
		transactionTransport.setRetailerId("100");
		//transactionTransport.setCircleCode("5");
		transactionTransport.setMobileNo("8459505105");
		//transactionTransport.setConnectionid(1000019);
		transactionTransport.setOperator("MTS");
		transactionTransport.setTransactionName("MOBILE_RECHARGE");
		transactionTransport.setRechargeType("MOBILE_PREPAID");
		rechargeTransactionService.doRechargeService(transactionTransport);
	}

	/*public void testFundTransfer() {
	    FundToRetailerService fundToRetailerService =(FundToRetailerService)applicationContext.getBean("fundToRetailerService");
	    FundToRetailerDto fundToRetailer = new FundToRetailerDto();
	    fundToRetailer.setB2bCurrentBal(2000);
		fundToRetailer.setB2bFundAmount(3000);
		fundToRetailer.setRetailerId("101");
		fundToRetailer.setDistributorId("34");
		fundToRetailerService.fundToRetailerCurrService(fundToRetailer );
	
	}*/
	
	/*public void testCommisionTransfer()          
	{
		FundTransferService fundToRetailerService =(FundTransferService)applicationContext.getBean("fundTransferService");
		fundToRetailerService.distAdUnitBalTransTodistCurrBal("102", 100);
	}*/
}
	/*public void testCommisionTransfer()          
	{
		FundTransferService fundTransferService =(FundTransferService)applicationContext.getBean("fundTransferService");
		String str=fundTransferService.companyBalanceTransToDistService("105", 2000, "cfe", "credit");
		String str2="";
		if(str=="data saved successfully")
		{
			str2=str;
		}	
		else
		{
			str2="data not saved";
		}	
		assertNotSame(str, str2);
	}	
	
	public void testSearch()          
	{
		CommonService commonService = (CommonService)applicationContext.getBean("commonService");
		Users user =(Users)commonService.searchUserBasedOnIdOrMobNum("vikas1");
		assertNotNull(user);
	}
}*/
