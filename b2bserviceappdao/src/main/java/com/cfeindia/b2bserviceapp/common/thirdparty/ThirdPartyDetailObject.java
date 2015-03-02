package com.cfeindia.b2bserviceapp.common.thirdparty;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name="thirdpartyapidetail")
public class ThirdPartyDetailObject {
	
	@Id
	private long id;
	@Column(name="retailerid")
	private String retailerId;
	@Column (name="created_at")
	private Date createdAt = new Date();
	@Column (name="connect_url")
	private String connectURL;
	
	@Column(name="token")
	private String token;
	
	@Column(name="token_1")
	private String token1;
	@Column(name="token_2")
	private String token2;
	@Column(name="token_3")
	private String token3;
	
	@Column (name="thirpartyprovidername")
	private String thirpartyprovidername;
	@Column (name="api_version")
	private Double apiVersion;
	@Column
	private String loginstatus;
	@Column
	private String agentid;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getThirpartyprovidername() {
		return thirpartyprovidername;
	}
	public void setThirpartyprovidername(String thirpartyprovidername) {
		this.thirpartyprovidername = thirpartyprovidername;
	}
	public String getLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public Double getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(Double apiVersion) {
		this.apiVersion = apiVersion;
	}
	public String getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getConnectURL() {
		return connectURL;
	}
	public void setConnectURL(String connectURL) {
		this.connectURL = connectURL;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getToken1() {
		return token1;
	}
	public void setToken1(String token1) {
		this.token1 = token1;
	}
	public String getToken2() {
		return token2;
	}
	public void setToken2(String token2) {
		this.token2 = token2;
	}
	public String getToken3() {
		return token3;
	}
	public void setToken3(String token3) {
		this.token3 = token3;
	}
	
}
