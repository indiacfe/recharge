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
    private Double preBalance;
	
	@Column(name="new_balance")
    private Double newBalance;
	@Column(name="transfer_from")
	private String transferFrom;
	
	@Column(name="transfer_to")
	private String transferTo;
	@Column(name="remark")
	private String remark;
	
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

	public Double getPreBalance() {
		return preBalance;
	}

	public void setPreBalance(Double preBalance) {
		this.preBalance = preBalance;
	}

	public Double getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(Double newBalance) {
		this.newBalance = newBalance;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
