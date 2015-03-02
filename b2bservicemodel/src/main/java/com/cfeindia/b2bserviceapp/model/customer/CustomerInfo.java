package com.cfeindia.b2bserviceapp.model.customer;

import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

public class CustomerInfo {

	private String name;
	private double currentBalance;
	private String firmName;
	private String customerId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = ExtractorUtil.generateIdFromString(customerId, "C");
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

}
