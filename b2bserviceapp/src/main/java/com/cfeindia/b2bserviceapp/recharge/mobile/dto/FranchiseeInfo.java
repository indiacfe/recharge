package com.cfeindia.b2bserviceapp.recharge.mobile.dto;

public class FranchiseeInfo {
	private String name;
	private double b2bCurrentBalance;
	private double b2bCurrentAdUnitBalance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getB2bCurrentBalance() {
		return b2bCurrentBalance;
	}

	public void setB2bCurrentBalance(double b2bCurrentBalance) {
		this.b2bCurrentBalance = b2bCurrentBalance;
	}

	public double getB2bCurrentAdUnitBalance() {
		return b2bCurrentAdUnitBalance;
	}

	public void setB2bCurrentAdUnitBalance(double b2bCurrentAdUnitBalance) {
		this.b2bCurrentAdUnitBalance = b2bCurrentAdUnitBalance;
	}
}
