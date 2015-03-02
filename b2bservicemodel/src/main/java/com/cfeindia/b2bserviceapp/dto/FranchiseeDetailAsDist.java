package com.cfeindia.b2bserviceapp.dto;

import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

public class FranchiseeDetailAsDist {
	private String firmName;
	private String franId;
	private String mobileNo;
	private Double balance;
	private Double adUnitBalance;

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getFranId() {
		return franId;
	}

	public void setFranId(String franId) {
		this.franId = ExtractorUtil.generateIdFromString(franId, "R");
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getAdUnitBalance() {
		return adUnitBalance;
	}

	public void setAdUnitBalance(Double adUnitBalance) {
		this.adUnitBalance = adUnitBalance;
	}

}
