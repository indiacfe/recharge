package com.cfeindia.b2bserviceapp.thirdparty.recharge.model;

public class EServiceDataItem {
	private String operatorCode;
	private String operatorName;
	private String cirCode;
	private String cirName;
	private String product;
	private String productDescription;
	private String domination;
	private String custIdLabel;
	private String custIdLength;
	private String talkTime;
	private String adminFee;
	private String serviceTax;
	private String validity;
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getCirCode() {
		return cirCode;
	}
	public void setCirCode(String cirCode) {
		this.cirCode = cirCode;
	}
	public String getCirName() {
		return cirName;
	}
	public void setCirName(String cirName) {
		this.cirName = cirName;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getDomination() {
		return domination;
	}
	public void setDomination(String domination) {
		this.domination = domination;
	}
	public String getCustIdLabel() {
		return custIdLabel;
	}
	public void setCustIdLabel(String custIdLabel) {
		this.custIdLabel = custIdLabel;
	}
	public String getCustIdLength() {
		return custIdLength;
	}
	public void setCustIdLength(String custIdLength) {
		this.custIdLength = custIdLength;
	}
	public String getTalkTime() {
		return talkTime;
	}
	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime;
	}
	public String getAdminFee() {
		return adminFee;
	}
	public void setAdminFee(String adminFee) {
		this.adminFee = adminFee;
	}
	public String getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(String serviceTax) {
		this.serviceTax = serviceTax;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EServiceDataItem [operatorCode=");
		builder.append(operatorCode);
		builder.append(", operatorName=");
		builder.append(operatorName);
		builder.append(", cirCode=");
		builder.append(cirCode);
		builder.append(", cirName=");
		builder.append(cirName);
		builder.append(", product=");
		builder.append(product);
		builder.append(", productDescription=");
		builder.append(productDescription);
		builder.append(", domination=");
		builder.append(domination);
		builder.append(", custIdLabel=");
		builder.append(custIdLabel);
		builder.append(", custIdLength=");
		builder.append(custIdLength);
		builder.append(", talkTime=");
		builder.append(talkTime);
		builder.append(", adminFee=");
		builder.append(adminFee);
		builder.append(", serviceTax=");
		builder.append(serviceTax);
		builder.append(", validity=");
		builder.append(validity);
		builder.append("]");
		
		return builder.toString();
	}
	
	

}
