package com.cfeindia.b2bserviceapp.thirdparty.recharge.model;

public class EServiceTransactionRequestInput extends BaseThirdPartyInput{
	
	private String connectURL;
	private String transId;
	private String retailerId;
	private String operatorCode;
	private String cirCode;
	private String product;
	private String mobileNo;
	private String custId;
	private String denomination;
	private String recharge;
	private String narration;
	private String agentpwd;
	private long connectionid;
	public long getConnectionid() {
		return connectionid;
	}
	public void setConnectionid(long connectionid) {
		this.connectionid = connectionid;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getCirCode() {
		return cirCode;
	}
	public void setCirCode(String cirCode) {
		this.cirCode = cirCode;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public String getRecharge() {
		return recharge;
	}
	public void setRecharge(String recharge) {
		this.recharge = recharge;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getAgentpwd() {
		return agentpwd;
	}
	public void setAgentpwd(String agentpwd) {
		this.agentpwd = agentpwd;
	}
	public String getConnectURL() {
		return connectURL;
	}
	public void setConnectURL(String connectURL) {
		this.connectURL = connectURL;
	}
	
	

}
