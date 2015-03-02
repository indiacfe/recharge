package com.cfeindia.b2bserviceapp.test;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.common.thirdparty.dao.ThirdPartyDetailProviderDao;

public class ThirdPartyApiDetailLoaderTest 
	 extends TestCase {

			ClassPathXmlApplicationContext applicationContext = null;

			public void setUp() {
				applicationContext = new ClassPathXmlApplicationContext(
						"spring-config.xml");

			}

			public void testGetThirdPartyDetailsProviderDao() {
				ThirdPartyDetailProviderDao thirdPartyDetailProviderDaoImpl = (ThirdPartyDetailProviderDao)applicationContext.getBean("thirdpartyDao");
				ThirdPartyDetailObject thirdPartyDetailObject = thirdPartyDetailProviderDaoImpl.getThirdPartyDetailObject("payworld");
				assertNotNull(thirdPartyDetailObject);
			}
}
