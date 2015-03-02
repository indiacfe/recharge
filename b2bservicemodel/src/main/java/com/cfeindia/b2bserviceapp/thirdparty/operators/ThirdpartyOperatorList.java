package com.cfeindia.b2bserviceapp.thirdparty.operators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thirdparty_operator_list")
public class ThirdpartyOperatorList {
	
	@Id
	public int id;
	
	@Column(name ="thirdparty_service_provider")
	public String thirdpartyServiceProvider;
	
	@Column(name ="operator_name")
	public String operatorName;
	
	@Column(name ="recharge_type")
	public String rechargeType;
	
	@Column(name ="operator_code")
	public String operatorCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getThirdpartyServiceProvider() {
		return thirdpartyServiceProvider;
	}

	public void setThirdpartyServiceProvider(String thirdpartyServiceProvider) {
		this.thirdpartyServiceProvider = thirdpartyServiceProvider;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

}
