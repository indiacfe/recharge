package com.cfeindia.b2bserviceapp.model.admin;

import java.sql.Timestamp;

public class AdminFundTrasferReportDTO {

	private int userId;
	private String mobileNumber;
	private String firmName;
	private String userType;
	private Double transferAmount;
	private String transferType;
	private Timestamp createdAt;
	private String transactionId;
	private Double preBalance;
	private Double newBalance;
	private String transferFrom;
	private String transferTo;
	private String connectionid;
	private String thirdPartyServiceProviderName;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(Double transferAmount) {
		this.transferAmount = transferAmount;
	}
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Double getPreBalance() {
		return preBalance;
	}
	public void setPreBalance(Double preBalance) {
		this.preBalance = preBalance;
	}
	public String getTransferFrom() {
		return transferFrom;
	}
	public void setTransferFrom(String transferFrom) {
		this.transferFrom = transferFrom;
	}
	public String getTransferTo() {
		return transferTo;
	}
	public void setTransferTo(String transferTo) {
		this.transferTo = transferTo;
	}
	public Double getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(Double newBalance) {
		this.newBalance = newBalance;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getConnectionid() {
		return connectionid;
	}
	public void setConnectionid(String connectionid) {
		this.connectionid = connectionid;
	}
	public String getThirdPartyServiceProviderName() {
		return thirdPartyServiceProviderName;
	}
	public void setThirdPartyServiceProviderName(
			String thirdPartyServiceProviderName) {
		this.thirdPartyServiceProviderName = thirdPartyServiceProviderName;
	}
	
	
}
