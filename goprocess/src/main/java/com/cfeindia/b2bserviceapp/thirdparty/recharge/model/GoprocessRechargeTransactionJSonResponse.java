package com.cfeindia.b2bserviceapp.thirdparty.recharge.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoprocessRechargeTransactionJSonResponse {

	/*
	 * {"trans_id":"100101100000001","client_trans_id":"SA12","msisdn":"9717131580",
	 * "operator_code":1,"circle_code":0,"trans_amount":10,"datetime":"2013-09-27 06:53:07",
	 * "status":"Success","charged_amount":10,"response_code":"0","opr_transid":"0"}
	 */
	@JsonProperty("trans_id")	
	private String goProcessId;
	@JsonProperty("client_trans_id")
	private String merchantTransId;
	@JsonProperty("opr_transid")
	private String operatorTransactionId;
	@JsonProperty("msisdn")
	private String msisdn;
	@JsonProperty("sp_key")
	private String spKey;
	@JsonProperty("trans_amt")
	private String transAmt;
	@JsonProperty("datetime")
	private String datetime;
	@JsonProperty("response_code")
	private String responseCode;
	@JsonProperty("status")
	private String status;
	@JsonProperty("operator_code")
	private String operatorCode;
	@JsonProperty("circle_code")
	private String circleCode;
	@JsonProperty("charged_amount")
	private String chargedAmount;
	@JsonProperty("ErrorCode")
	private String errorCode;
	@JsonProperty("Message")
	private String errorMessage;
	public String getGoProcessId() {
		return goProcessId;
	}
	public void setGoProcessId(String goProcessId) {
		this.goProcessId = goProcessId;
	}
	public String getMerchantTransId() {
		return merchantTransId;
	}
	public void setMerchantTransId(String merchantTransId) {
		this.merchantTransId = merchantTransId;
	}
	public String getOperatorTransactionId() {
		return operatorTransactionId;
	}
	public void setOperatorTransactionId(String operatorTransactionId) {
		this.operatorTransactionId = operatorTransactionId;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getSpKey() {
		return spKey;
	}
	public void setSpKey(String spKey) {
		this.spKey = spKey;
	}
	public String getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getCircleCode() {
		return circleCode;
	}
	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
	}
	public String getChargedAmount() {
		return chargedAmount;
	}
	public void setChargedAmount(String chargedAmount) {
		this.chargedAmount = chargedAmount;
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
	
}
