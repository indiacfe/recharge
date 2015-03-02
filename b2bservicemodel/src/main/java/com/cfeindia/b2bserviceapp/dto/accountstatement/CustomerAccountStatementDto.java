package com.cfeindia.b2bserviceapp.dto.accountstatement;

public class CustomerAccountStatementDto {
	private Double amount;
	private String createdAt;
	private String mobileNo;
	private String operator;
	private Double creditAmountCustomer;
	private String particulars;
	private String transactionId;
	private String transactionType;
	private String connectionid;
	private Long senderId;
	private String transactionFrom;
	private String transactionTo;
	private Double precurrbal;
	private Double newCurrBal;
	private String status;
	private String clientTransId;
	private boolean refunded = false; 

	public String getDisplayAmount() {
		return getFormattedAmount(amount);
	}

	private String getFormattedAmount(Double amount) {
		String d = "" + amount;
		if (amount != null && amount != 0) {

			String a[] = d.split("\\.");
			if (a.length > 1) {
				if (a[1].length() == 1) {
					d = d + "0";
				}
			} else {
				d = d + ".00";
			}
		} else {
			d = "0.00";
		}
		return d;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Double getCreditAmountCustomer() {
		return creditAmountCustomer;
	}

	public void setCreditAmountCustomer(Double creditAmountCustomer) {
		this.creditAmountCustomer = creditAmountCustomer;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getConnectionid() {
		return connectionid;
	}

	public void setConnectionid(String connectionid) {
		this.connectionid = connectionid;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getTransactionFrom() {
		return transactionFrom;
	}

	public void setTransactionFrom(String transactionFrom) {
		this.transactionFrom = transactionFrom;
	}

	public String getTransactionTo() {
		return transactionTo;
	}

	public void setTransactionTo(String transactionTo) {
		this.transactionTo = transactionTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPrecurrbal() {
		return precurrbal;
	}

	public void setPrecurrbal(Double precurrbal) {
		this.precurrbal = precurrbal;
	}

	public Double getNewCurrBal() {
		return newCurrBal;
	}

	public void setNewCurrBal(Double newCurrBal) {
		this.newCurrBal = newCurrBal;
	}

	public String getClientTransId() {
		return clientTransId;
	}

	public void setClientTransId(String clientTransId) {
		this.clientTransId = clientTransId;
	}

	public boolean isRefunded() {
		return refunded;
	}

	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}

}
