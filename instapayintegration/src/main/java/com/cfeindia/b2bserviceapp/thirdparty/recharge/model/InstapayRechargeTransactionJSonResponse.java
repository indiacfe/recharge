package com.cfeindia.b2bserviceapp.thirdparty.recharge.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstapayRechargeTransactionJSonResponse {
	@JsonProperty("ipay_id")	
	private String ipayId;
	@JsonProperty("agent_id")
	private String merchantTransId;
	@JsonProperty("opr_id")
	private String operatorTransactionId;
	@JsonProperty("account_no")
	private String accountNo;
	@JsonProperty("sp_key")
	private String spKey;
	@JsonProperty("trans_amt")
	private String transAmt;
	@JsonProperty("charged_amt")
	private String chargedAmt;
	@JsonProperty("opening_bal")
	private String openingBal;
	@JsonProperty("datetime")
	private String datetime;
	@JsonProperty("status")
	private String status;
	@JsonProperty("res_code")
	private String resCode;
	@JsonProperty("res_msg")
	private String resMsg;
	@JsonProperty("ipay_errorcode")
	private String ipayErrorcode;
	@JsonProperty("ipay_errordesc")
	private String ipayErrordesc;
	
	public String getIpayId() {
		return ipayId;
	}
	public void setIpayId(String ipayId) {
		this.ipayId = ipayId;
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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
	public String getChargedAmt() {
		return chargedAmt;
	}
	public void setChargedAmt(String chargedAmt) {
		this.chargedAmt = chargedAmt;
	}
	public String getOpeningBal() {
		return openingBal;
	}
	public void setOpeningBal(String openingBal) {
		this.openingBal = openingBal;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getIpayErrorcode() {
		return ipayErrorcode;
	}
	public void setIpayErrorcode(String ipayErrorcode) {
		this.ipayErrorcode = ipayErrorcode;
	}
	public String getIpayErrordesc() {
		return ipayErrordesc;
	}
	public void setIpayErrordesc(String ipayErrordesc) {
		this.ipayErrordesc = ipayErrordesc;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InstapayRechargeTransactionJSonResponse [ipayId=");
		builder.append(ipayId);
		builder.append(", merchantTransId=");
		builder.append(merchantTransId);
		builder.append(", operatorTransactionId=");
		builder.append(operatorTransactionId);
		builder.append(", accountNo=");
		builder.append(accountNo);
		builder.append(", spKey=");
		builder.append(spKey);
		builder.append(", transAmt=");
		builder.append(transAmt);
		builder.append(", chargedAmt=");
		builder.append(chargedAmt);
		builder.append(", openingBal=");
		builder.append(openingBal);
		builder.append(", datetime=");
		builder.append(datetime);
		builder.append(", status=");
		builder.append(status);
		builder.append(", resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", ipayErrorcode=");
		builder.append(ipayErrorcode);
		builder.append(", ipayErrordesc=");
		builder.append(ipayErrordesc);
		builder.append("]");
		return builder.toString();
	}
	
	
}
