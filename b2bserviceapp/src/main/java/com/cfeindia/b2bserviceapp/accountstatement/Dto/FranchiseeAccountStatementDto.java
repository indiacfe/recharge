package com.cfeindia.b2bserviceapp.accountstatement.Dto;

public class FranchiseeAccountStatementDto {

	private Double amount;
	private String createdAt;
	private String mobileNo;
	private String operator;
	private Double creditAmountFranchisee;
	private String particulars;
	private String transactionId;
	private String transactionType;
	private String connectionid;
	// ***********************************FranchiseeBalTransferLog
	// Object*********************************************
	private long distributorId;
	private String transactionFrom;
	private String transactionTo;
	private Double preb2bcurrbal;
	private Double newB2bCurrBal;

	private String status;

	// **********************************Getters and
	// Setters*******************************************

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

	public String getDisplayCreditAmountFranchisee() {
		return getFormattedAmount(creditAmountFranchisee);
	}

	public static void main(String[] args) {
		FranchiseeAccountStatementDto f = new FranchiseeAccountStatementDto();
		f.setCreditAmountFranchisee(.2);
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

	public Double getCreditAmountFranchisee() {
		return creditAmountFranchisee;
	}

	public void setCreditAmountFranchisee(Double creditAmountFranchisee) {
		this.creditAmountFranchisee = creditAmountFranchisee;
	}

	public long getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(long distributorId) {
		this.distributorId = distributorId;
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

	public Double getPreb2bcurrbal() {
		return preb2bcurrbal;
	}

	public void setPreb2bcurrbal(Double preb2bcurrbal) {
		this.preb2bcurrbal = preb2bcurrbal;
	}

	public Double getNewB2bCurrBal() {
		return newB2bCurrBal;
	}

	public void setNewB2bCurrBal(Double newB2bCurrBal) {
		this.newB2bCurrBal = newB2bCurrBal;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
