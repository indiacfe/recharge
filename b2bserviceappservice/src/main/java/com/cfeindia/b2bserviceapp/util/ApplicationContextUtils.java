package com.cfeindia.b2bserviceapp.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.MoneyTransERegistrationRequestService;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

	private static ApplicationContext ctx;

	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		ctx = appContext;

	}

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}
	
	public ETransactionRequestService getServiceProvierBean(String beanName){
		return (ETransactionRequestService)ctx.getBean(beanName);
	}
	
	public MoneyTransERegistrationRequestService getServiceRegistrationProvierBean(String beanName){
		return (MoneyTransERegistrationRequestService)ctx.getBean(beanName);
	}
}
