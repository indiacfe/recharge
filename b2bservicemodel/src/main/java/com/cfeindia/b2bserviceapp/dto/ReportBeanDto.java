package com.cfeindia.b2bserviceapp.dto;

import java.sql.Timestamp;
import java.util.Date;

public class ReportBeanDto {

	private String category;

	private String type, RechargeType, rechargeTypeTopup; /* for mobile recharge */

	private String mnp, dthoperator, mobileOperator, circleName, circleCode,
			outletId;

	private int errorCode = 0;/* mobile recharge transaction response */

	private String responseMessage;

	private String errorMessage;

	private String agentId; /* base third party input */

	private String loginStatus;

	private String appver;

	private String connectURL;/* Eservice Transaction Request Input */

	private String token;

	private String token1;

	private String token2;

	private String token3;

	private Date newDob;

	private String customerId;

	private String operatorCode;

	private String product;

	private String custId;

	private String denomination;

	private String recharge;

	private String narration;

	private String agentpwd;

	private long id;

	private Timestamp createdAt;

	private Timestamp updatedAt;

	private String transactionName;

	private String mobileNo;

	private Double amount;

	private String status;

	private String operator;

	private String message;

	private String errorcode;

	private String thirdpartytransid;

	private String clientTransId;

	private String transid;

	private String connectionid;

	private String thirdPartyTransDateTime;

	private String responsecode;

	private Double retailerPreBal;

	private Double retailerNewBal;

	private String thirdpartyoperatortransid;

	private double creditAmountFranchisee;

	private String thirdPartyServiceProvider;

	private Double refundAmount;
	private String stdCode;
	private String dob;
	private Long accountNumber;
	private Long canumber;
	private String subservice;
	private String customerName;
	private String customerAddress;
	private String pinNumber;
	private String cycleNumber;

	private String franchiseeMobileNum;

	private String packageKey;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCategory() {
		return category;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRechargeType() {
		return RechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.RechargeType = rechargeType;
	}

	public String getRechargeTypeTopup() {
		return rechargeTypeTopup;
	}

	public void setRechargeTypeTopup(String rechargeTypeTopup) {
		this.rechargeTypeTopup = rechargeTypeTopup;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMnp() {
		return mnp;
	}

	public void setMnp(String mnp) {
		this.mnp = mnp;
	}

	public String getDthoperator() {
		return dthoperator;
	}

	public void setDthoperator(String dthoperator) {
		this.dthoperator = dthoperator;
	}

	public String getMobileOperator() {
		return mobileOperator;
	}

	public void setMobileOperator(String mobileOperator) {
		this.mobileOperator = mobileOperator;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public String getCircleCode() {
		return circleCode;
	}

	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getAppver() {
		return appver;
	}

	public void setAppver(String appver) {
		this.appver = appver;
	}

	public String getConnectURL() {
		return connectURL;
	}

	public void setConnectURL(String connectURL) {
		this.connectURL = connectURL;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getClientId() {
		return custId;
	}

	public void setClientId(String custId) {
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getThirdpartytransid() {
		return thirdpartytransid;
	}

	public void setThirdpartytransid(String thirdpartytransid) {
		this.thirdpartytransid = thirdpartytransid;
	}

	public String getClientTransId() {
		return clientTransId;
	}

	public void setClientTransId(String clientTransId) {
		this.clientTransId = clientTransId;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getConnectionid() {
		return connectionid;
	}

	public void setConnectionid(String connectionid) {
		this.connectionid = connectionid;
	}

	public String getThirdPartyTransDateTime() {
		return thirdPartyTransDateTime;
	}

	public void setThirdPartyTransDateTime(String thirdPartyTransDateTime) {
		this.thirdPartyTransDateTime = thirdPartyTransDateTime;
	}

	public Long getCanumber() {
		return canumber;
	}

	public void setCanumber(Long canumber) {
		this.canumber = canumber;
	}

	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public double getCreditAmountFranchisee() {
		return creditAmountFranchisee;
	}

	public void setCreditAmountFranchisee(double creditAmountFranchisee) {
		this.creditAmountFranchisee = creditAmountFranchisee;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getThirdpartyoperatortransid() {
		return thirdpartyoperatortransid;
	}

	public void setThirdpartyoperatortransid(String thirdpartyoperatortransid) {
		this.thirdpartyoperatortransid = thirdpartyoperatortransid;
	}

	public String getThirdPartyServiceProvider() {
		return thirdPartyServiceProvider;
	}

	public void setThirdPartyServiceProvider(String thirdPartyServiceProvider) {
		this.thirdPartyServiceProvider = thirdPartyServiceProvider;
	}

	public Double getRetailerPreBal() {
		return retailerPreBal;
	}

	public void setRetailerPreBal(Double retailerPreBal) {
		this.retailerPreBal = retailerPreBal;
	}

	public Double getRetailerNewBal() {
		return retailerNewBal;
	}

	public void setRetailerNewBal(Double retailerNewBal) {
		this.retailerNewBal = retailerNewBal;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getNewDob() {
		return newDob;
	}

	public void setNewDob(Date newDob) {
		this.newDob = newDob;
	}

	public String getSubservice() {
		return subservice;
	}

	public void setSubservice(String subservice) {
		this.subservice = subservice;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getCycleNumber() {
		return cycleNumber;
	}

	public void setCycleNumber(String cycleNumber) {
		this.cycleNumber = cycleNumber;
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

	public String getFranchiseeMobileNum() {
		return franchiseeMobileNum;
	}

	public void setFranchiseeMobileNum(String franchiseeMobileNum) {
		this.franchiseeMobileNum = franchiseeMobileNum;
	}

	public String getOutletId() {
		return outletId;
	}

	public void setOutletId(String outletId) {
		this.outletId = outletId;
	}

	public String getPackageKey() {
		return packageKey;
	}

	public void setPackageKey(String packageKey) {
		this.packageKey = packageKey;
	}

}
