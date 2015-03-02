package com.cfeindia.b2bserviceapp.distributor.model;

public class FundToRetailerDto {
	private String retailerId;
	private String retailerName;
	private double b2bCurrentBal;
	private double adUnitBal;
	private double b2bFundAmount;
	private double adUnitFundAmount;
	private String distributorId;
	public String getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public double getB2bCurrentBal() {
		return b2bCurrentBal;
	}
	public void setB2bCurrentBal(double b2bCurrentBal) {
		this.b2bCurrentBal = b2bCurrentBal;
	}
	public double getAdUnitBal() {
		return adUnitBal;
	}
	public void setAdUnitBal(double adUnitBal) {
		this.adUnitBal = adUnitBal;
	}
	public double getB2bFundAmount() {
		return b2bFundAmount;
	}
	public void setB2bFundAmount(double b2bFundAmount) {
		this.b2bFundAmount = b2bFundAmount;
	}
	public double getAdUnitFundAmount() {
		return adUnitFundAmount;
	}
	public void setAdUnitFundAmount(double adUnitFundAmount) {
		this.adUnitFundAmount = adUnitFundAmount;
	}
	public String getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}
	

}
