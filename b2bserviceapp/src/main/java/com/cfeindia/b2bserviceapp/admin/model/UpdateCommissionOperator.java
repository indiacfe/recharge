package com.cfeindia.b2bserviceapp.admin.model;

public class UpdateCommissionOperator {
	
	private String operator;
	private String rechargeType;
	private Double amount;
	private String thirdPartyOperator;
	
	public String getThirdPartyOperator() {
		return thirdPartyOperator;
	}
	public void setThirdPartyOperator(String thirdPartyOperator) {
		this.thirdPartyOperator = thirdPartyOperator;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	

}
