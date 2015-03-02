package com.cfeindia.b2bserviceapp.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="outletdetail")
public class OutletDetail {
	
	@EmbeddedId
	private OutletDetailPK outletDetailPK = new OutletDetailPK();
	
	@Transient
	private String userName;
	
	@Column
	private String outletid;

	public OutletDetailPK getOutletDetailPK() {
		return outletDetailPK;
	}

	public void setOutletDetailPK(OutletDetailPK outletDetailPK) {
		this.outletDetailPK = outletDetailPK;
	}

	public String getOutletid() {
		return outletid;
	}

	public void setOutletid(String outletid) {
		this.outletid = outletid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
