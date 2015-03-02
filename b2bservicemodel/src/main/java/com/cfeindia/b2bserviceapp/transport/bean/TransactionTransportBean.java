package com.cfeindia.b2bserviceapp.transport.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cfeindia.b2bserviceapp.dto.RemittanceUserDto;
import com.cfeindia.b2bserviceapp.entity.ICashRecharge;

@Entity
@Table(name = "recharge_transaction")
public class TransactionTransportBean {

	@Transient
	private String category;

	@Transient
	private String type, RechargeType, rechargeTypeTopup; /* for mobile recharge */

	@Transient
	private String mnp, dthoperator, mobileOperator, circleName, circleCode,
			outletId;
	@Transient
	private String paymentType;

	@Transient
	private String billPayment;
	@Transient
	private String postpaidbill;
	@Transient
	private int errorCode = 0;/* mobile recharge transaction response */
	@Transient
	private String responseMessage;

	@Column(name = "errorMessage")
	private String errorMessage;

	@Transient
	private String agentId; /* base third party input */
	@Transient
	private String loginStatus;
	@Transient
	private String appver;
	@Transient
	private String connectURL;/* Eservice Transaction Request Input */

	@Transient
	private String token;

	@Transient
	private String token1;
	@Transient
	private RemittanceUserDto remittanceUserDto;
	@Transient
	private List<RemittanceUserDto> remittanceUserDtoList;
	
	public List<RemittanceUserDto> getRemittanceUserDtoList() {
		return remittanceUserDtoList;
	}

	public void setRemittanceUserDtoList(
			List<RemittanceUserDto> remittanceUserDtoList) {
		this.remittanceUserDtoList = remittanceUserDtoList;
	}

	@Transient
	private String token2;

	@Transient
	private String token3;
	@Transient
	private Date newDob;

	@Column(name = "franchisee_id")
	private String retailerId;
	@Transient
	private String operatorCode;
	@Transient
	private String product;
	@Transient
	private String custId;
	@Transient
	private String denomination;
	@Transient
	private String recharge;
	@Transient
	private String narration;
	@Transient
	private String agentpwd;

	@Transient
	String clientTransId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	@Column(name = "transaction_name")
	private String transactionName;
	@Column(name = "mobile_no")
	private String mobileNo;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "STATUS")
	private String status = "FAILED";
	@Column(name = "operator")
	private String operator;
	@Column(name = "message")
	private String message;
	@Column(name = "errorcode")
	private String errorcode;
	@Column(name = "thirdpartytransid")
	String thirdpartytransid;
	@Column(name = "transid")
	String transid;
	@Column(name = "connection_no")
	private String connectionid;
	@Column
	String thirdPartyTransDateTime;
	@Column(name = "responsecode")
	private String responsecode;

	@Column(name = "retailer_pre_bal")
	private Double retailerPreBal;

	@Column(name = "retailer_new_bal")
	private Double retailerNewBal;

	@Column(name = "thirdpartyoperatortransid")
	private String thirdpartyoperatortransid;

	@Column(name = "credit_amount_franchisee")
	private double creditAmountFranchisee;

	@Column(name = "thirdpartyserviceprovider")
	private String thirdPartyServiceProvider;

	@Column(name = "refund_amount")
	private Double refundAmount;
	@Column(name = "commission_type")
	private String commissionType;
	@Column(name = "commission_deduct_type")
	private String commissionDeductType;
	private String stdCode;
	private String dob;
	private Long accountNumber;
	private Long canumber;
	private String subservice;
	private String customerName;
	private String customerAddress;
	private String pinNumber;
	private String cycleNumber;
	@Transient
	private String franchiseeMobileNum;
	@Transient
	private String packageKey;
	@Transient
	private Double totalAmount;
	@Transient
	private ICashRecharge iCashRecharge;
	@Transient
	private String airtelUserName;
	@Transient
	private String customerUserName;
	@Transient
	private TransactionState transactionState = TransactionState.PREAUTHORIZATION;

	public TransactionState getTransactionState() {
		return transactionState;
	}

	public void setTransactionState(TransactionState transactionState) {
		this.transactionState = this.transactionState
				.validateNextState(transactionState);
	}

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

	public RemittanceUserDto getRemittanceUserDto() {
		return remittanceUserDto;
	}

	public void setRemittanceUserDto(RemittanceUserDto remittanceUserDto) {
		this.remittanceUserDto = remittanceUserDto;
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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
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

	public String getClientTransId() {
		return clientTransId;
	}

	public void setClientTransId(String clientTransId) {
		this.clientTransId = clientTransId;
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

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ICashRecharge getiCashRecharge() {
		return iCashRecharge;
	}

	public void setiCashRecharge(ICashRecharge iCashRecharge) {
		this.iCashRecharge = iCashRecharge;
	}

	public String getAirtelUserName() {
		return airtelUserName;
	}

	public void setAirtelUserName(String airtelUserName) {
		this.airtelUserName = airtelUserName;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getBillPayment() {
		return billPayment;
	}

	public void setBillPayment(String billPayment) {
		this.billPayment = billPayment;
	}

	public String getCustomerUserName() {
		return customerUserName;
	}

	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}

	public String getPostpaidbill() {
		return postpaidbill;
	}

	public void setPostpaidbill(String postpaidbill) {
		this.postpaidbill = postpaidbill;
	}

	public String getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}
	

	public String getCommissionDeductType() {
		return commissionDeductType;
	}

	public void setCommissionDeductType(String commissionDeductType) {
		this.commissionDeductType = commissionDeductType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransactionTransportBean [category=");
		builder.append(category);
		builder.append(", type=");
		builder.append(type);
		builder.append(", RechargeType=");
		builder.append(RechargeType);
		builder.append(", rechargeTypeTopup=");
		builder.append(rechargeTypeTopup);
		builder.append(", mnp=");
		builder.append(mnp);
		builder.append(", dthoperator=");
		builder.append(dthoperator);
		builder.append(", mobileOperator=");
		builder.append(mobileOperator);
		builder.append(", circleName=");
		builder.append(circleName);
		builder.append(", circleCode=");
		builder.append(circleCode);
		builder.append(", outletId=");
		builder.append(outletId);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", commissionType=");
		builder.append(commissionType);
		builder.append(", billPayment=");
		builder.append(billPayment);
		builder.append(", postpaidbill=");
		builder.append(postpaidbill);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", responseMessage=");
		builder.append(responseMessage);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", agentId=");
		builder.append(agentId);
		builder.append(", loginStatus=");
		builder.append(loginStatus);
		builder.append(", appver=");
		builder.append(appver);
		builder.append(", connectURL=");
		builder.append(connectURL);
		builder.append(", token=");
		builder.append(token);
		builder.append(", token1=");
		builder.append(token1);
		builder.append(", token2=");
		builder.append(token2);
		builder.append(", token3=");
		builder.append(token3);
		builder.append(", newDob=");
		builder.append(newDob);
		builder.append(", retailerId=");
		builder.append(retailerId);
		builder.append(", operatorCode=");
		builder.append(operatorCode);
		builder.append(", product=");
		builder.append(product);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", denomination=");
		builder.append(denomination);
		builder.append(", recharge=");
		builder.append(recharge);
		builder.append(", narration=");
		builder.append(narration);
		builder.append(", agentpwd=");
		builder.append(agentpwd);
		builder.append(", clientTransId=");
		builder.append(clientTransId);
		builder.append(", id=");
		builder.append(id);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append(", transactionName=");
		builder.append(transactionName);
		builder.append(", mobileNo=");
		builder.append(mobileNo);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", status=");
		builder.append(status);
		builder.append(", operator=");
		builder.append(operator);
		builder.append(", message=");
		builder.append(message);
		builder.append(", errorcode=");
		builder.append(errorcode);
		builder.append(", thirdpartytransid=");
		builder.append(thirdpartytransid);
		builder.append(", transid=");
		builder.append(transid);
		builder.append(", connectionid=");
		builder.append(connectionid);
		builder.append(", thirdPartyTransDateTime=");
		builder.append(thirdPartyTransDateTime);
		builder.append(", responsecode=");
		builder.append(responsecode);
		builder.append(", retailerPreBal=");
		builder.append(retailerPreBal);
		builder.append(", retailerNewBal=");
		builder.append(retailerNewBal);
		builder.append(", thirdpartyoperatortransid=");
		builder.append(thirdpartyoperatortransid);
		builder.append(", creditAmountFranchisee=");
		builder.append(creditAmountFranchisee);
		builder.append(", thirdPartyServiceProvider=");
		builder.append(thirdPartyServiceProvider);
		builder.append(", refundAmount=");
		builder.append(refundAmount);
		builder.append(", stdCode=");
		builder.append(stdCode);
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", canumber=");
		builder.append(canumber);
		builder.append(", subservice=");
		builder.append(subservice);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", customerAddress=");
		builder.append(customerAddress);
		builder.append(", pinNumber=");
		builder.append(pinNumber);
		builder.append(", cycleNumber=");
		builder.append(cycleNumber);
		builder.append(", franchiseeMobileNum=");
		builder.append(franchiseeMobileNum);
		builder.append(", packageKey=");
		builder.append(packageKey);
		builder.append(", totalAmount=");
		builder.append(totalAmount);
		builder.append(", iCashRecharge=");
		builder.append(iCashRecharge);
		builder.append(", airtelUserName=");
		builder.append(airtelUserName);
		builder.append(", customerUserName=");
		builder.append(customerUserName);
		builder.append(", transactionState=");
		builder.append(transactionState);
		builder.append("]");
		return builder.toString();
	}

}
