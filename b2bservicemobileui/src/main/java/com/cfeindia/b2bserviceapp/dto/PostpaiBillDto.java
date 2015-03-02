package com.cfeindia.b2bserviceapp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostpaiBillDto {
	@JsonProperty("cname")
	private String operator;
	@JsonProperty("mobileNo")
	private String connectioNumber;
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
	public String getConnectioNumber() {
		return connectioNumber;
	}
	public void setConnectioNumber(String connectioNumber) {
		this.connectioNumber = connectioNumber;
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
