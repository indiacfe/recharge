package com.cfeindia.b2bserviceapp.recharge.mobile.dto;

public class MobileRechargeTransactionResponse {
	
	private int errorCode = 0;
	private String message;
	private String merchantTransId;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMerchantTransId() {
		return merchantTransId;
	}
	public void setMerchantTransId(String merchantTransId) {
		this.merchantTransId = merchantTransId;
	}
	
}
