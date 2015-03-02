package com.cfeindia.b2bserviceapp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LandLineBillDto {
	@JsonProperty("operator")
	private String operator;
	@JsonProperty("connetionNumber")
	private String connectionNumber;
	@JsonProperty("stdCode")
	private String stdCode;
	@JsonProperty("caNumber")
	private Long caNumber;
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
	public String getStdCode() {
		return stdCode;
	}
	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}
	public Long getCaNumber() {
		return caNumber;
	}
	public void setCaNumber(Long caNumber) {
		this.caNumber = caNumber;
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
