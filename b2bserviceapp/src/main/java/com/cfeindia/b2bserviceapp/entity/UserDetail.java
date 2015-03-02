package com.cfeindia.b2bserviceapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Entity
@Table(name = "user_detail")
public class UserDetail implements Serializable {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private Users users;
	@Id
	@Column(name = "USER_DETAIL_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userDetailId;
	@Column(name = "FIRM_NAME")
	private String firmName;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "PERMANENT_ADDRESS")
	private String permanentAddress;
	@Column(name = "OFFICE_ADDRESS")
	private String officeAddress;
	@Column(name = "STATE")
	private String state;
	@Column(name = "DISTRICT")
	private String district;
	@Column(name = "PIN_CODE")
	private String pinCode;
	@Column(name = "LAND_LINE")
	private String landLine;
	@Column(name = "EMAIL_ID")
	private String emailId;
	@Column(name = "BANK_ACCOUNT")
	private String bankAccount;
	@Column(name = "BANK_NAME")
	private String bankName;
	@Column(name = "BANK_BRANCH")
	private String bankBranch;
	@Column(name = "BANK_BRANCH_CITY")
	private String bankBranchCity;
	@Column(name = "IFS_CODE")
	private String ifsCode;
	@Column(name = "PAN_CARD")
	private String panCard;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "retailerDetail")
	private List<DistributorBalanceTransferLog> distributorBalanceTransferLogList;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public long getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(long userDetailId) {
		this.userDetailId = userDetailId;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankBranchCity() {
		return bankBranchCity;
	}

	public void setBankBranchCity(String bankBranchCity) {
		this.bankBranchCity = bankBranchCity;
	}

	public String getIfsCode() {
		return ifsCode;
	}

	public void setIfsCode(String ifsCode) {
		this.ifsCode = ifsCode;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public List<DistributorBalanceTransferLog> getDistributorBalanceTransferLogList() {
		return distributorBalanceTransferLogList;
	}

	public void setDistributorBalanceTransferLogList(
			List<DistributorBalanceTransferLog> distributorBalanceTransferLogList) {
		this.distributorBalanceTransferLogList = distributorBalanceTransferLogList;
	}

}
