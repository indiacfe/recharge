package com.cfeindia.b2bserviceapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="distributorcurrbal")
public class DistributorCurrbal {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="curracbalance")
	private Double currAcBalance;
	@Column(name="b2bcurracbalance")
	private Double b2bCurrAcBalance;
	@Column(name="b2bcurracadunitbal")
	private Double b2bCurrAcAdUnitBal;
	@Column(name="distributorid")
	private long distributorId;
	@Column(name ="created_at")
	private Date createdAt;	
	@Column(name ="updated_at")
	private Date updatedAt;
	@Column(name="creator_id")
	private Long creatorId;
	@Column(name="creator_role")
	private String creatorRole;
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Double getCurrAcBalance() {
		return currAcBalance;
	}
	public void setCurrAcBalance(Double currAcBalance) {
		this.currAcBalance = currAcBalance;
	}
	public Double getB2bCurrAcBalance() {
		return b2bCurrAcBalance;
	}
	public void setB2bCurrAcBalance(Double b2bCurrAcBalance) {
		this.b2bCurrAcBalance = b2bCurrAcBalance;
	}
	public Double getB2bCurrAcAdUnitBal() {
		return b2bCurrAcAdUnitBal;
	}
	public void setB2bCurrAcAdUnitBal(Double b2bCurrAcAdUnitBal) {
		this.b2bCurrAcAdUnitBal = b2bCurrAcAdUnitBal;
	}
	public long getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(long distributorId) {
		this.distributorId = distributorId;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorRole() {
		return creatorRole;
	}
	public void setCreatorRole(String creatorRole) {
		this.creatorRole = creatorRole;
	}
	
	
}
	