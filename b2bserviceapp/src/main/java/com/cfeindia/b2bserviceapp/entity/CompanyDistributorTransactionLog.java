package com.cfeindia.b2bserviceapp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="company_distributor_transaction_log")
public class CompanyDistributorTransactionLog {

	@Id
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "distributor_id")
	private Users distributorId;
	
	@Column(name="transfer_amount")
    private double transferAmount;
	
	@Column(name="transfer_type")
	private String transferType;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="transaction_id")
	private String transId;

	@Column(name="pre_balance")
    private double preBalance;
	
	@Column(name="new_balance")
    private double newBalance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Users getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Users distributorId) {
		this.distributorId = distributorId;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
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

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public double getPreBalance() {
		return preBalance;
	}

	public void setPreBalance(double preBalance) {
		this.preBalance = preBalance;
	}

	public double getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}

	
}
