package com.cfeindia.b2bserviceapp.franchisee.model;

public class CashDepositRequestFran {
	
	private String cashDepositRequestType,
					paymentMode,
					amount,
					counterBank,
					bankName,
					reciptNo,
					depositBranch,
					reciptDate,
					remark,
					uploadRecipt;

	public String getCashDepositRequestType() {
		return cashDepositRequestType;
	}

	public void setCashDepositRequestType(String cashDepositRequestType) {
		this.cashDepositRequestType = cashDepositRequestType;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCounterBank() {
		return counterBank;
	}

	public void setCounterBank(String counterBank) {
		this.counterBank = counterBank;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getReciptNo() {
		return reciptNo;
	}

	public void setReciptNo(String reciptNo) {
		this.reciptNo = reciptNo;
	}

	public String getDepositBranch() {
		return depositBranch;
	}

	public void setDepositBranch(String depositBranch) {
		this.depositBranch = depositBranch;
	}

	public String getReciptDate() {
		return reciptDate;
	}

	public void setReciptDate(String reciptDate) {
		this.reciptDate = reciptDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUploadRecipt() {
		return uploadRecipt;
	}

	public void setUploadRecipt(String uploadRecipt) {
		this.uploadRecipt = uploadRecipt;
	}
	
	
}
