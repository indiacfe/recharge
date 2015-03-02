package com.cfeindia.b2bserviceapp.entity;

import java.util.Date;

import javax.persistence.Id;

public class BaseEntity {
	@Id
	private long id;
	private Date createdAt = new Date();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", createdAt=" + createdAt + "]";
	}
	

}
