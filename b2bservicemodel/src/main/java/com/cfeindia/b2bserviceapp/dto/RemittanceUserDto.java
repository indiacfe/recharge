package com.cfeindia.b2bserviceapp.dto;

import java.io.Serializable;

public class RemittanceUserDto implements Serializable {

	private long id;
	private long remittanceUserId;
	private long remittanceUserBankId;
	private String mobileNO;
	private String firstName;
	private String lastName;
	private String middleName;
	private String dateOfBirth;
	private String gender;
	private String pinCode;
	private String emailId;
	private String nickName;
	private String accountName;
	private String bankAccountNumber;
	private String accountType;
	private String IFSCCode;
	private String IFSCStatus;
	private String impsmmidenable;
	private String impsifscenable;
	private String impsneftenable;
	private String deleteStatus;
	private String OTP;
	private String operatorName;
	private String address;
	private String city;
	private String state;
	private String transactionId;private String transactionLimit;private String transactionstatus,transactionamount;
	private String OTPStatus;
	private String type;
	private String step;
	private String cardno;private String cardStatus;
	private String benename;
	private String beneId;
	private String benemobile;
	private String benestatus;
	private String bankname;
	private String branchname;
	private String accountno,toaccountno;
	private String amount;
	private String agentId;
	private String fromDate;
	private String toDate;
	public String urlStatus;
	public String motherMaidenName;
	public String idProofType;
	public String idProof;
	public String idProofURL;
	public String addressProofType;
	public String addressProof;
	public String addressProofURL;
	public String kycStatus;public String kycRemarks;
	public String mmId;
	public String mmIdStatus;
	public String status;
	public String statustype;
	public String statusCode;
	public String remarks;
	public String transType;
	public String item;
	public String pin;
	public String fromaccountno,ipayId,dateTime,senderName,senderMobile,serviceCharge,impsreferenceNO;
 

	public String getImpsreferenceNO() {
		return impsreferenceNO;
	}

	public void setImpsreferenceNO(String impsreferenceNO) {
		this.impsreferenceNO = impsreferenceNO;
	}

	 

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderMobile() {
		return senderMobile;
	}

	public void setSenderMobile(String senderMobile) {
		this.senderMobile = senderMobile;
	}

	public String getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getIpayId() {
		return ipayId;
	}

	public void setIpayId(String ipayId) {
		this.ipayId = ipayId;
	}

	public String getChargedAmount() {
		return chargedAmount;
	}

	public void setChargedAmount(String chargedAmount) {
		this.chargedAmount = chargedAmount;
	}

	public String merchanttransid,chargedAmount;
	
	public String getTransactionamount() {
		return transactionamount;
	}

	public void setTransactionamount(String transactionamount) {
		this.transactionamount = transactionamount;
	}

	public String getTransactionstatus() {
		return transactionstatus;
	}

	public void setTransactionstatus(String transactionstatus) {
		this.transactionstatus = transactionstatus;
	}

	public String getMerchanttransid() {
		return merchanttransid;
	}

	public void setMerchanttransid(String merchanttransid) {
		this.merchanttransid = merchanttransid;
	}

	public String getStatustype() {
		return statustype;
	}

	public void setStatustype(String statustype) {
		this.statustype = statustype;
	}

	public String getFromaccountno() {
		return fromaccountno;
	}

	public void setFromaccountno(String fromaccountno) {
		this.fromaccountno = fromaccountno;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOTPStatus() {
		return OTPStatus;
	}

	public void setOTPStatus(String oTPStatus) {
		OTPStatus = oTPStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRemittanceUserId() {
		return remittanceUserId;
	}

	public void setRemittanceUserId(long remittanceUserId) {
		this.remittanceUserId = remittanceUserId;
	}

	public long getRemittanceUserBankId() {
		return remittanceUserBankId;
	}

	public void setRemittanceUserBankId(long remittanceUserBankId) {
		this.remittanceUserBankId = remittanceUserBankId;
	}

	public String getMobileNO() {
		return mobileNO;
	}

	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getIFSCCode() {
		return IFSCCode;
	}

	public void setIFSCCode(String iFSCCode) {
		IFSCCode = iFSCCode;
	}

	public String getIFSCStatus() {
		return IFSCStatus;
	}

	public void setIFSCStatus(String iFSCStatus) {
		IFSCStatus = iFSCStatus;
	}

	public String getImpsmmidenable() {
		return impsmmidenable;
	}

	public void setImpsmmidenable(String impsmmidenable) {
		this.impsmmidenable = impsmmidenable;
	}

	public String getImpsifscenable() {
		return impsifscenable;
	}

	public void setImpsifscenable(String impsifscenable) {
		this.impsifscenable = impsifscenable;
	}

	public String getImpsneftenable() {
		return impsneftenable;
	}

	public void setImpsneftenable(String impsneftenable) {
		this.impsneftenable = impsneftenable;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getBenename() {
		return benename;
	}

	public void setBenename(String benename) {
		this.benename = benename;
	}

	

	public String getBeneId() {
		return beneId;
	}

	public void setBeneId(String beneId) {
		this.beneId = beneId;
	}

	public String getBenemobile() {
		return benemobile;
	}

	public void setBenemobile(String benemobile) {
		this.benemobile = benemobile;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getUrlStatus() {
		return urlStatus;
	}

	public void setUrlStatus(String urlStatus) {
		this.urlStatus = urlStatus;
	}

	public String getMotherMaidenName() {
		return motherMaidenName;
	}

	public void setMotherMaidenName(String motherMaidenName) {
		this.motherMaidenName = motherMaidenName;
	}

	public String getIdProofType() {
		return idProofType;
	}

	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}

	public String getIdProof() {
		return idProof;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public String getIdProofURL() {
		return idProofURL;
	}

	public void setIdProofURL(String idProofURL) {
		this.idProofURL = idProofURL;
	}

	public String getAddressProofType() {
		return addressProofType;
	}

	public void setAddressProofType(String addressProofType) {
		this.addressProofType = addressProofType;
	}

	public String getAddressProof() {
		return addressProof;
	}

	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
	}

	public String getAddressProofURL() {
		return addressProofURL;
	}

	public void setAddressProofURL(String addressProofURL) {
		this.addressProofURL = addressProofURL;
	}
public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(String kycStatus) {
		this.kycStatus = kycStatus;
	}

	public String getKycRemarks() {
		return kycRemarks;
	}

	public void setKycRemarks(String kycRemarks) {
		this.kycRemarks = kycRemarks;
	}

	public String getMmId() {
		return mmId;
	}

	public void setMmId(String mmId) {
		this.mmId = mmId;
	}

	public String getMmIdStatus() {
		return mmIdStatus;
	}

	public void setMmIdStatus(String mmIdStatus) {
		this.mmIdStatus = mmIdStatus;
	}

	public String getBenestatus() {
		return benestatus;
	}

	public void setBenestatus(String benestatus) {
		this.benestatus = benestatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(String transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getToaccountno() {
		return toaccountno;
	}

	public void setToaccountno(String toaccountno) {
		this.toaccountno = toaccountno;
	}

	 
    
}
