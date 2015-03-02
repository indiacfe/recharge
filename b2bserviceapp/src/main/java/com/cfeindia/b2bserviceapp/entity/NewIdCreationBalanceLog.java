package com.cfeindia.b2bserviceapp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="newidcreationbalancelog")
public class NewIdCreationBalanceLog {
	@Id
	@Column(name="id")
	private long id;
	
	@Column
	private long creatorid;
	
	@Column(name="idcreator_role")
	private String  idCreatorRole;
	
	@Column(name="transfer_type")
	private String transferType;
	
	@Column
	private double precurrbal;
	
	@Column
	private double newcurrbal;
	
	@Column(name="creation_charge")
	private double creationCharge;
	
	@Column(name="transaction_id")
	private String transactionId;
	
	@Column(name="newfranchiseeid")
	private long newFranchiseeId;
	
	@Column(name="newdistributorid")
	private long newDistributorId;
	
	@Column(name="created_at")
	private Timestamp createdAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(long creatorid) {
		this.creatorid = creatorid;
	}

	public String getIdCreatorRole() {
		return idCreatorRole;
	}

	public void setIdCreatorRole(String idCreatorRole) {
		this.idCreatorRole = idCreatorRole;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public double getPrecurrbal() {
		return precurrbal;
	}

	public void setPrecurrbal(double precurrbal) {
		this.precurrbal = precurrbal;
	}

	public double getNewcurrbal() {
		return newcurrbal;
	}

	public void setNewcurrbal(double newcurrbal) {
		this.newcurrbal = newcurrbal;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public long getNewFranchiseeId() {
		return newFranchiseeId;
	}

	public void setNewFranchiseeId(long newFranchiseeId) {
		this.newFranchiseeId = newFranchiseeId;
	}

	public long getNewDistributorId() {
		return newDistributorId;
	}

	public void setNewDistributorId(long newDistributorId) {
		this.newDistributorId = newDistributorId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public double getCreationCharge() {
		return creationCharge;
	}

	public void setCreationCharge(double creationCharge) {
		this.creationCharge = creationCharge;
	}
	
	

}
