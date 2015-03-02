package com.cfeindia.b2bserviceapp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElectricityBillDto {
	@JsonProperty("operator")
	private String operator;
	@JsonProperty("connetionNumber")
	private String connectionNumber;
	@JsonProperty("amount")
	private String amount;
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("mobileNumber")
	private String mobilenumber;
	@JsonProperty("cycleNumber")
	private String cyclenumber;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getConnectionNumber() {
		return connectionNumber;
	}
	public void setConnectionNumber(String connectionNumber) {
		this.connectionNumber = connectionNumber;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getCyclenumber() {
		return cyclenumber;
	}
	public void setCyclenumber(String cyclenumber) {
		this.cyclenumber = cyclenumber;
	}
	
	

}
