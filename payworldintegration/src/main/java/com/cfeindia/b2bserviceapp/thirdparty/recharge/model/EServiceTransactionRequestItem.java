package com.cfeindia.b2bserviceapp.thirdparty.recharge.model;

public class EServiceTransactionRequestItem {
	private String merchantTransId;
	private String transId;
	private String transDateTime;
	private String responseCode;
	private String responseMessage;
	public String getMerchantTransId() {
		return merchantTransId;
	}
	public void setMerchantTransId(String merchantTransId) {
		this.merchantTransId = merchantTransId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getTransDateTime() {
		return transDateTime;
	}
	public void setTransDateTime(String transDateTime) {
		this.transDateTime = transDateTime;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	

}
