package com.cfeindia.b2bserviceapp.recharge.mobile.dto;

public class ThirdPartyOperatorDetailDto {
	
	private String thirdPartyServiceProvider;
	private String operatorName;
	private String operatorCode;
	private String rechargeType;
	
	public String getThirdPartyServiceProvider() {
		return thirdPartyServiceProvider;
	}
	public void setThirdPartyServiceProvider(String thirdPartyServiceProvider) {
		this.thirdPartyServiceProvider = thirdPartyServiceProvider;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}
	

}
