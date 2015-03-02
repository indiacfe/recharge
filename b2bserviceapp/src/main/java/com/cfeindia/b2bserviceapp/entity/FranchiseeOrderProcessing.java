package com.cfeindia.b2bserviceapp.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="order_processing")
public class FranchiseeOrderProcessing {

	@Id
	private long id;
	
	@Column(name="request_type")
	private String requestType;
	
	@Column(name="payment_mode")
    private String paymentMode;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="counter")
	private String counter;
	
	@Column(name="bank_name")
	private String bankName;

	@Column(name="reciept_cheque_dd_no")
    private String recieptChequeDdNo;
	
	@Column(name="deposit_branch_office")
    private String depositBranchOffice;

	@Column(name="cheque_dd_date")
    private Date chequeDdDate;

	@Column(name="remark")
    private String remark;
	
	@Lob
	@Column(name="reciept")
    private String receipt;
	
	@Column(name="created_at")
    private Timestamp createdat;

	@Column(name="updated_at")
    private Timestamp updatedAt;
	
	@Column(name="order_processing")
	private String status;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getRecieptChequeDdNo() {
		return recieptChequeDdNo;
	}

	public void setRecieptChequeDdNo(String recieptChequeDdNo) {
		this.recieptChequeDdNo = recieptChequeDdNo;
	}

	public String getDepositBranchOffice() {
		return depositBranchOffice;
	}

	public void setDepositBranchOffice(String depositBranchOffice) {
		this.depositBranchOffice = depositBranchOffice;
	}

	public Date getChequeDdDate() {
		return chequeDdDate;
	}

	public void setChequeDdDate(Date chequeDdDate) {
		this.chequeDdDate = chequeDdDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Timestamp getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Timestamp createdat) {
		this.createdat = createdat;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
	