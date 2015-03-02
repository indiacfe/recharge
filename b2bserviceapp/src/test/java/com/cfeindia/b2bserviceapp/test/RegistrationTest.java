package com.cfeindia.b2bserviceapp.test;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.distributor.model.Registration;
import com.cfeindia.b2bserviceapp.service.distributor.RegistrationService;

public class RegistrationTest extends TestCase
{

	ClassPathXmlApplicationContext applicationContext = null;

	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
	}
public void testRegistration(){
		
		RegistrationService registrationService=(RegistrationService) applicationContext.getBean("registrationService");
		Registration registration=new Registration();
		registration.setName("newDistributor");
		registration.setMobileNo("2222222222");
		registration.setPassword("rajat2");
		registration.setRegistrationType("ROLE_DISTRIBUTOR");
		registration.setFirmName("firmname");
		registration.setEmailId("email");
		registration.setOfficeAddress("office address");
		registration.setPermanentAddress("parmananet address");
		registrationService.registerationProcessByCompany(registration, 100L,"ROLE_ADMIN");
		assertNotNull(registration);
	}
	
	/*public void testMasterData() {
		DistributorInfoService DistributorInfoService = (DistributorInfoService)applicationContext.getBean("distributorInfoService");
		DistributorInfo distributorInfo=DistributorInfoService.distributorInfo("101");
		assertNotNull(distributorInfo);
	}
	
	public void testMasterData1() {
		FranchiseeInfoService franchiseeInfoService = (FranchiseeInfoService)applicationContext.getBean("franchiseeInfoService");
		FranchiseeInfo franchiseeInfoService1=franchiseeInfoService.getFranchiseeInfo("100");
		assertEquals(56666, franchiseeInfoService1.getB2bCurrentAdUnitBalance());
		assertNotNull(franchiseeInfoService1);
	}
*/
/*	public void testUpdateBalance(){
		CompanyAccount companyAccount=(CompanyAccount)applicationContext.getBean("companyAccount");
		String check=companyAccount.updateCompanyAccount(2000d);
		assertNotNull(check);
	}*/
	
	/*public void testUpdateBalance(){
		AdminUtilityService adminUtilityService=(AdminUtilityService)applicationContext.getBean("SetDistributorCommission");
		String check=adminUtilityService.setOperatorCommission("MOBILE_PREPAID", "payworld","AIRTEL", 40D);
		assertNotNull(check);
	}*/

}
