package com.cfeindia.b2bserviceapp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginSuccessDto 
{
	@JsonProperty("baseId")
	private String baseId;
	@JsonProperty("userName")
	private String userMobileNo;
	@JsonProperty("currentBalance")
	private String currentBalance;
	@JsonProperty("firmName")
	private String firmName;
	@JsonProperty("franId")
	private String franId;
	@JsonProperty("name")
	private String name;
	
	public String getUserId() {
		return baseId;
	}
	public void setUserId(String userId) {
		this.baseId = userId;
	}
	public String getUserName() {
		return userMobileNo;
	}
	public void setUserName(String userName) {
		this.userMobileNo = userName;
	}
	public String getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getFranId() {
		return franId;
	}
	public void setFranId(String franId) {
		this.franId = franId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBaseId() {
		return baseId;
	}
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
	public String getUserMobileNo() {
		return userMobileNo;
	}
	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}
	
	
}
