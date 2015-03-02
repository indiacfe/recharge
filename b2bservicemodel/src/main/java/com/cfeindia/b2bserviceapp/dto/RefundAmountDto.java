package com.cfeindia.b2bserviceapp.dto;

import java.sql.Timestamp;

import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

public class RefundAmountDto {
	
	private long id;
	private String transid;
	private String retailerId;
	private String retailerMobNo;
	private String retailerName;
	private String mobileNo;
	private String connectionid;
	private String transactionName;
	private String operator;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Double amount;
	private double creditAmountFranchisee;
	private String status;
	private Double refundAmount;
	private String thirdPartyServiceProviderName; 
	private String errorMessage;
	private String remark;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getRetailerMobNo() {
		return retailerMobNo;
	}

	public void setRetailerMobNo(String retailerMobNo) {
		this.retailerMobNo = retailerMobNo;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getConnectionid() {
		return connectionid;
	}

	public void setConnectionid(String connectionid) {
		this.connectionid = connectionid;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public double getCreditAmountFranchisee() {
		return creditAmountFranchisee;
	}

	public void setCreditAmountFranchisee(double creditAmountFranchisee) {
		this.creditAmountFranchisee = creditAmountFranchisee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getGeneratedId() {
		return ExtractorUtil.generateIdFromString(retailerId,"R");
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getThirdPartyServiceProviderName() {
		return thirdPartyServiceProviderName;
	}

	public void setThirdPartyServiceProviderName(
			String thirdPartyServiceProviderName) {
		this.thirdPartyServiceProviderName = thirdPartyServiceProviderName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

}
