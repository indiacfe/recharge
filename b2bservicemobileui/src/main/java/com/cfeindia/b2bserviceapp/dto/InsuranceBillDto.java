package com.cfeindia.b2bserviceapp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InsuranceBillDto {
	@JsonProperty("operator")
	private String operator;
	@JsonProperty("connetionNumber")
	private String connectionNumber;
	@JsonProperty("dob")
	private String dob;
	@JsonProperty("amount")
	private String amount;
	@JsonProperty("userId")
	private String userId;
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
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

	
	
}
