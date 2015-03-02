package com.cfeindia.b2bserviceapp.dto.recharge.mobile;

import com.cfeindia.b2bserviceapp.util.ExtractorUtil;


public class DistributorInfo {
	private String name;
	private double currAcBalance;
	private double b2bCurrAcBalance;
	private double b2bCurrAcAdUnitBal;
	private Integer numberofFranchisee;
	private String firmName;
	private String distId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCurrAcBalance() {
		return currAcBalance;
	}

	public void setCurrAcBalance(double currAcBalance) {
		this.currAcBalance = currAcBalance;
	}

	public double getB2bCurrAcBalance() {
		return b2bCurrAcBalance;
	}

	public void setB2bCurrAcBalance(double b2bCurrAcBalance) {
		this.b2bCurrAcBalance = b2bCurrAcBalance;
	}

	public double getB2bCurrAcAdUnitBal() {
		return b2bCurrAcAdUnitBal;
	}

	public void setB2bCurrAcAdUnitBal(double b2bCurrAcAdUnitBal) {
		this.b2bCurrAcAdUnitBal = b2bCurrAcAdUnitBal;
	}

	public Integer getNumberofFranchisee() {
		return numberofFranchisee;
	}

	public void setNumberofFranchisee(Integer numberofFranchisee) {
		this.numberofFranchisee = numberofFranchisee;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getDistId() {
		return distId;
	}

	public void setDistId(String distId) {
		this.distId = ExtractorUtil.generateIdFromString(distId, "D");
	}
}
