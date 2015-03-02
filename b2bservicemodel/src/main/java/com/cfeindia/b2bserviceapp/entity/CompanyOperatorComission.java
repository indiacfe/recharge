package com.cfeindia.b2bserviceapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="companyoperatorcomission")
public class CompanyOperatorComission {

	@Id
	private long id;
	@Column(name="operator_name")
	private String operatorName; 
	@Column(name="retailercommision")
	private double retailercommision;
	@Column(name="recharge_type")
	private String rechargeType;
	@Column(name="thirdparty_service_provider")
	private String thirdpartyServiceProvider;
	
	@Column(name="comission_type")
	private String comissionType;
	
	@Column(name="deduction_type")
	private String deductionType;
	
	public String getComissionType() {
		return comissionType;
	}
	public void setComissionType(String comissionType) {
		this.comissionType = comissionType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public double getRetailercommision() {
		return retailercommision;
	}
	public void setRetailercommision(double retailercommision) {
		this.retailercommision = retailercommision;
	}
	public String getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}
	public String getThirdpartyServiceProvider() {
		return thirdpartyServiceProvider;
	}
	public void setThirdpartyServiceProvider(String thirdpartyServiceProvider) {
		this.thirdpartyServiceProvider = thirdpartyServiceProvider;
	}
	public String getDeductionType() {
		return deductionType;
	}
	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}
	
	
	
}

