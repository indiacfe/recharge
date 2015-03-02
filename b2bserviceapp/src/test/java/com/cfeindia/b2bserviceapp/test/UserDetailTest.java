package com.cfeindia.b2bserviceapp.test;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.admin.service.AdminUtilityService;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;

public class UserDetailTest extends TestCase {

	ClassPathXmlApplicationContext applicationContext = null;

	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring-config.xml");

	}
/*	public void userDetailTes()
	{
		AdminUtilityService adminUtilityService=(AdminUtilityService) applicationContext.getBean("adminUtilityService");
		List<UserDetailDto> userDetailDto=adminUtilityService.userDetail();
		for(UserDetailDto list:userDetailDto){
			System.out.println(list.getUserId()+""+list.getUserName());
		}
		
	}
*/}
