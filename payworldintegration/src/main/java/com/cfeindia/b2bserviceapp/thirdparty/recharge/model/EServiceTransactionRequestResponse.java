package com.cfeindia.b2bserviceapp.thirdparty.recharge.model;

public class EServiceTransactionRequestResponse {
	private String merchantTransId;
	private String transId;
	private String transDateTime;
	private String responseCode;
	private String responseMessage;

	private String errorCode;
	private String errorMessage;

	public String getMerchantTransId() {
		return merchantTransId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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