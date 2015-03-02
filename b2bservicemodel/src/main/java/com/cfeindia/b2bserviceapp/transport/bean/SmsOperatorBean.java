package com.cfeindia.b2bserviceapp.transport.bean;

public class SmsOperatorBean {
	private String operatorName;
	private String rechargeType;
	private String transactionName;

	public SmsOperatorBean(String operatorName, String rechargeType, String transactionName) {
		this.operatorName = operatorName;
		this.rechargeType = rechargeType;
		this.transactionName = transactionName;
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

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

}
