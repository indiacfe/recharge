package com.cfeindia.b2bserviceapp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "icash_recharge")
public class ICashRecharge {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "icash_id")
	private Long id;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "date_of_birth")
	private Timestamp dateOfBirth;
	@Column(name = "address")
	private String address;
	@Column(name = "state")
	private String state;
	@Column(name = "mobile_number", unique = true)
	private String mobileNumber;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "document_detail")
	private String documentDetail;
	@Column(name = "document_type")
	private String documentType;
	@Column(name = "mother_name")
	private String motherName;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "amount_all")
	private Double amountAll;
	@Column(name = "comment")
	private String comment;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "registration_status")
	private String registrationStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDocumentDetail() {
		return documentDetail;
	}

	public void setDocumentDetail(String documentDetail) {
		this.documentDetail = documentDetail;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmountAll() {
		return amountAll;
	}

	public void setAmountAll(Double amountAll) {
		this.amountAll = amountAll;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

}
