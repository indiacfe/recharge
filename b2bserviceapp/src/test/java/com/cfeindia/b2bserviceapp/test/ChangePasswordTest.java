package com.cfeindia.b2bserviceapp.test;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.cfeindia.b2bserviceapp.admin.service.UpdateOperatorCommissionService;
//import com.cfeindia.b2bserviceapp.admin.service.UpdateOperatorCommissionServiceImpl;

public class ChangePasswordTest extends TestCase
{

	ClassPathXmlApplicationContext applicationContext = null;

	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
	}
	
	/*public void testRegistration(){
		
	ChangePasswordServiceImpl changePasswordService=(ChangePasswordServiceImpl) applicationContext.getBean("changePassword");
	//ChangePasswordDist changePasswordDist=new ChangePasswordDist();
	
		
	changePasswordService.changePassword("vikas1", "123456", 102L);
		assertNotNull(changePasswordService);
	}
	public void getLis(){
	//UpdateOperatorCommissionService updateOperatorCommissionImpl=(UpdateOperatorCommissionService)applicationContext.getBean("updateOperatorCommissionService");
	//updateOperatorCommissionImpl.thirdPartyOperatorDetail();
	//assertNotNull(updateOperatorCommissionImpl);
	}*/
}
