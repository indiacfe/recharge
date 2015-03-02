package com.cfeindia.b2bserviceapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_operatorcomission")
public class CustomerCommision {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "operator_name")
	private String operatorName;
	@Column(name = "customer_comision")
	private double customerCommission;
	@Column(name = "recharge_type")
	private String rechargeType;
	@Column(name = "thirdparty_service_provider")
	private String thirdpartyServiceProvider;

	@Column(name = "comission_type")
	private String comissionType;
	@Column(name = "user_id")
	private long userId;

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

	public double getCustomerCommission() {
		return customerCommission;
	}

	public void setCustomerCommission(double customerCommission) {
		this.customerCommission = customerCommission;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

}
