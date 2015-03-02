package com.cfeindia.b2bserviceapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "b2boperatorcodes")
public class B2bOperatorsCodeBean {
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "operator_name")
	private String operatorName;
	@Column(name = "recharge_type")
	private String rechargeType;
	@Column(name = "b2bcode")
	private String opCode;
	@Column(name = "transaction_name")
	private String transactionName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

}
