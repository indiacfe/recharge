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

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;

@Entity
@Table(name = "distributorbaltransferlog")
public class DistributorBalanceTransferLog {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "distributorid")
	private long distId;

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

	@Column(name = "preb2bcurrbal")
	private double preb2bcurrbal;

	@Column(name = "preb2adunitbal")
	private double preB2bCurrAdUnitBal;

	@Column(name = "newb2bcurrbal")
	private double newB2bCurrBal;

	@Column(name = "newb2badunitbal")
	private double newB2bAdUnitBal;

	@Column(name = "prebusinessbal")
	private double preBusinessBal;

	@Column(name = "newbusinessbal")
	private double newBusinessBal;

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
	@Transient
	private long franchiseeid;
	
	public long getFranchiseeid() {
		return franchiseeid;
	}

	public void setFranchiseeid(long franchiseeid) {
		this.franchiseeid = franchiseeid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "franchiseeid", referencedColumnName = "USER_ID")
	private UserDetail retailerDetail;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDistId() {
		return distId;
	}

	public void setDistId(long distId) {
		this.distId = distId;
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

	public double getPreb2bcurrbal() {
		return preb2bcurrbal;
	}

	public void setPreb2bcurrbal(double preb2bcurrbal) {
		this.preb2bcurrbal = preb2bcurrbal;
	}

	public double getPreB2bCurrAdUnitBal() {
		return preB2bCurrAdUnitBal;
	}

	public void setPreB2bCurrAdUnitBal(double preB2bCurrAdUnitBal) {
		this.preB2bCurrAdUnitBal = preB2bCurrAdUnitBal;
	}

	public double getNewB2bCurrBal() {
		return newB2bCurrBal;
	}

	public void setNewB2bCurrBal(double newB2bCurrBal) {
		this.newB2bCurrBal = newB2bCurrBal;
	}

	public double getNewB2bAdUnitBal() {
		return newB2bAdUnitBal;
	}

	public void setNewB2bAdUnitBal(double newB2bAdUnitBal) {
		this.newB2bAdUnitBal = newB2bAdUnitBal;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getDisplayFranchiseeId() {
		return displayFranchiseeId;
	}

	public void setDisplayFranchiseeId(String displayFranchiseeId) {
		this.displayFranchiseeId = displayFranchiseeId;
	}

	public UserDetail getRetailerDetail() {
		return retailerDetail;
	}

	public void setRetailerDetail(UserDetail retailerDetail) {
		this.retailerDetail = retailerDetail;
	}

	/*
	 * public String displayCredit() {
	 * if(CommonConstants.TRANSFER_TYPE_CREDIT.equalsIgnoreCase(transferType)) {
	 * return CommonConstants.TRANSFER_TYPE_CREDIT; } return ""; } public String
	 * displayDebit() {
	 * if(CommonConstants.TRANSFER_TYPE_DEBIT.equalsIgnoreCase(transferType)) {
	 * return CommonConstants.TRANSFER_TYPE_DEBIT; } return ""; }
	 */

	public String getDisplayTransactionName() {
		return displayTransactionName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public void setDisplayTransactionName(String displayTransactionName) {
		this.displayTransactionName = displayTransactionName;
	}

	public double getPreBusinessBal() {
		return preBusinessBal;
	}

	public void setPreBusinessBal(double preBusinessBal) {
		this.preBusinessBal = preBusinessBal;
	}

	public double getNewBusinessBal() {
		return newBusinessBal;
	}

	public void setNewBusinessBal(double newBusinessBal) {
		this.newBusinessBal = newBusinessBal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
