package com.cfeindia.b2bserviceapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OutletDetailPK implements Serializable {
	@Column(name="franchiseeid")
	private Long franchiseeId;
	
	@Column
	private String operator;
	
	@Column
	private String thirdpartyname;

	public Long getFranchiseeId() {
		return franchiseeId;
	}

	public void setFranchiseeId(Long franchiseeId) {
		this.franchiseeId = franchiseeId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getThirdpartyname() {
		return thirdpartyname;
	}

	public void setThirdpartyname(String thirdpartyname) {
		this.thirdpartyname = thirdpartyname;
	}
	
}
