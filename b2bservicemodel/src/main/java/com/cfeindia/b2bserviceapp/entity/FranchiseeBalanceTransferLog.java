package com.cfeindia.b2bserviceapp.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "franchiseebaltransferlog")
public class FranchiseeBalanceTransferLog {
	@Id
	private long id;
	
	@Column(name = "franchiseeid")
	private Long franchiseeId;
	
	@Column(name = "sender_id")
	private Long senderId;

	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "transaction_from")
	private String transactionFrom;
	
	@Column(name = "transaction_To")
	private String transactionTo;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name="preb2bcurrbal")
	private double preb2bcurrbal;	
	
	@Column(name="preb2badunitbal")
	private double preB2bCurrAdUnitBal;
	
	@Column(name="newb2bcurrbal")
	private double newB2bCurrBal;
	
	@Column(name="newb2badunitbal")
	private double newB2bAdUnitBal;
	
	@Column(name="transfer_amount")
	private double transferAmount;
	
	@Column
	private String remark;


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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionFrom() {
		return transactionFrom;
	}

	public void setTransactionFrom(String transactionFrom) {
		this.transactionFrom = transactionFrom;
	}

	public String getTransactionTo() {
		return transactionTo;
	}

	public void settransactionTo(String transactionTo) {
		this.transactionTo = transactionTo;
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

	public Long getFranchiseeId() {
		return franchiseeId;
	}

	public void setFranchiseeId(Long franchiseeId) {
		this.franchiseeId = franchiseeId;
	}

	public void setTransactionTo(String transactionTo) {
		this.transactionTo = transactionTo;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
