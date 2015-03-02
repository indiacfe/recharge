package com.cfeindia.b2bserviceapp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cfeindia.b2bserviceapp.util.CyberTelUtil;

@Entity
@Table(name = "licpremiumdetails")
public class LicPremiumBean {

	@Id
	@Column(name = "id")
	private long id;
	@Column(name = "policy_number")
	private String policyNumber;
	@Column(name = "dob")
	private String dob;
	@Column(name = "amount")
	private double amount;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "status")
	private String status;

	@Column(name = "user_id")
	private Long userId;
	@Column(name = "transactionId")
	private String transactionId;
	@Column(name = "customer_name")
	private String customerName;
	@Column(name = "mobile_number")
	private String mobileNo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public double getAmount() {

		return amount;
	}

	public String getDisplayAmount() {

		return CyberTelUtil.twoDecimalPlaceNumber(amount);
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	
	
}
