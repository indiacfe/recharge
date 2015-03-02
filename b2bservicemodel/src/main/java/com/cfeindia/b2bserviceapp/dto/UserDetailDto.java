package com.cfeindia.b2bserviceapp.dto;

import java.sql.Timestamp;

public class UserDetailDto {

	private String userId;
	private String userName;
	private String password;
	private String userRoleId;
	private String authority;
	private String firmName;
	private String emailId;
	private String mobileNo;
	private int enabled;
	private String bankAccount;
	private String eMailId;
	private String officeAddress;
	private String permanentAddress;
	private String state;
	private String currBal;
	private String b2bCurrBal;
	private String b2bCurrAcBal;
	private String b2bCurrAdUnitBal;
	private String creatorId;
	private int flag;
	private Timestamp createdAt;
	private String createdBy;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String geteMailId() {
		return eMailId;
	}

	public void seteMailId(String eMailId) {
		this.eMailId = eMailId;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCurrBal() {
		return currBal;
	}

	public void setCurrBal(String currBal) {
		this.currBal = currBal;
	}

	public String getB2bCurrBal() {
		return b2bCurrBal;
	}

	public void setB2bCurrBal(String b2bCurrBal) {
		this.b2bCurrBal = b2bCurrBal;
	}

	public String getB2bCurrAcBal() {
		return b2bCurrAcBal;
	}

	public void setB2bCurrAcBal(String b2bCurrAcBal) {
		this.b2bCurrAcBal = b2bCurrAcBal;
	}

	public String getB2bCurrAdUnitBal() {
		return b2bCurrAdUnitBal;
	}

	public void setB2bCurrAdUnitBal(String b2bCurrAdUnitBal) {
		this.b2bCurrAdUnitBal = b2bCurrAdUnitBal;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
