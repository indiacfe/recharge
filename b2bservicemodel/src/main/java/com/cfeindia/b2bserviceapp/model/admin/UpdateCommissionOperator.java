package com.cfeindia.b2bserviceapp.model.admin;

public class UpdateCommissionOperator {

	private String operator;
	private String rechargeType;
	private Double amount;
	private String thirdPartyOperator;
	private String commissionType;
	private String deductionType;
	
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

	public String getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}

	public String getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}
	
}
