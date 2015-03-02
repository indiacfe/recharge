package com.cfeindia.b2bserviceapp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "franchiseecurrbal")
public class FranchiseeCurrBal {
	@Id
	private long id;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	@Column(name = "franchiseeid")
	private long franchiseeid;
	@Column(name = "b2bcurrbal")
	private Double b2bcurrbal;
	@Column(name = "b2bcurrbaladunit")
	private Double b2bcurrbaladunit;
	@Column(name = "creator_id",nullable=true)
	private Long creatorId;
	@Column(name = "creator_role")
	private String creatorRole;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="franchiseeid", referencedColumnName="USER_ID", insertable = false, updatable = false)
	private Users users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getFranchiseeid() {
		return franchiseeid;
	}

	public void setFranchiseeid(long franchiseeid) {
		this.franchiseeid = franchiseeid;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Double getB2bcurrbal() {
		return b2bcurrbal;
	}

	public void setB2bcurrbal(Double b2bcurrbal) {
		this.b2bcurrbal = b2bcurrbal;
	}

	public Double getB2bcurrbaladunit() {
		return b2bcurrbaladunit;
	}

	public void setB2bcurrbaladunit(Double b2bcurrbaladunit) {
		this.b2bcurrbaladunit = b2bcurrbaladunit;
	}

	public String getCreatorRole() {
		return creatorRole;
	}

	public void setCreatorRole(String creatorRole) {
		this.creatorRole = creatorRole;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
}
