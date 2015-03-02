package com.cfeindia.b2bserviceapp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "customerbalancetransferlog")
public class CustomerBalanceTransferLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "customer_id")
	private long customerId;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "transfer_type")
	private String transferType;

	@Column(name = "transfer_from")
	private String transferFrom;

	@Column(name = "transfer_to")
	private String transferTo;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "precurrbal")
	private double precurrbal;

	@Column(name = "newcurrbal")
	private double newCurrBal;

	@Column(name = "transfer_amount")
	private double transferAmount;

	@Column
	private String remark;

	@Transient
	private String displayFranchiseeId;

	@Transient
	private String displayTransactionName;

	@Transient
	private String dateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id", referencedColumnName = "USER_ID")
	private UserDetail userDetail;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getPrecurrbal() {
		return precurrbal;
	}

	public void setPrecurrbal(double precurrbal) {
		this.precurrbal = precurrbal;
	}

	public String getDisplayFranchiseeId() {
		return displayFranchiseeId;
	}

	public void setDisplayFranchiseeId(String displayFranchiseeId) {
		this.displayFranchiseeId = displayFranchiseeId;
	}

	public String getDisplayTransactionName() {
		return displayTransactionName;
	}

	public void setDisplayTransactionName(String displayTransactionName) {
		this.displayTransactionName = displayTransactionName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public double getNewCurrBal() {
		return newCurrBal;
	}

	public void setNewCurrBal(double newCurrBal) {
		this.newCurrBal = newCurrBal;
	}

}
