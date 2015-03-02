package com.cfeindia.b2bserviceapp.entity;

import javax.persistence.Entity;


public class RetailerCommison {
	private double retailercommison;
	private String operatorName;
	private String rechargeType;
	private String serviceProvider;
	
	public double getRetailercommison() {
		return retailercommison;
	}
	public void setRetailercommison(double retailercommison) {
		this.retailercommison = retailercommison;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	
	

}
