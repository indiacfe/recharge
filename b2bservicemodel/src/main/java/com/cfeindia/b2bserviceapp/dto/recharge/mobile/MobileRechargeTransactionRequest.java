package com.cfeindia.b2bserviceapp.dto.recharge.mobile;

public class MobileRechargeTransactionRequest {
	private long franchiseeId;
	private String mobileno;
	private double amount;
	private String operator;
	private String operatorCode;
	private String circleCode;
	
	public long getFranchiseeId() {
		return franchiseeId;
	}
	public void setFranchiseeId(long franchiseeId) {
		this.franchiseeId = franchiseeId;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCircleCode() {
		return circleCode;
	}
	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}	
	
	
}
