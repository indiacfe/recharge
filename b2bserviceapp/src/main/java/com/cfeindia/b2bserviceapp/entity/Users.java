package com.cfeindia.b2bserviceapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Entity
@Table(name = "users")
public class Users {
	@Id
	@Column(name = "USER_ID",unique=true,nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	
	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ENABLED")
	private int enabled;

	@Column(name = "displaypassword")
	private String displayPassword;

	@OneToOne(fetch=FetchType.LAZY,mappedBy="userId")
	private UserRole userRole;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userId")
	private List<CompanyBalTransactionLog> companyBalTransactionLogs;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "distributorId")
	private List<CompanyDistributorTransactionLog> companyDistributorTransactionLogs;

	@OneToOne(fetch=FetchType.EAGER,mappedBy="users")
	private UserDetail userDetail;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
/*
	public List<TransactionTransportBean> getTransactionTransportBeans() {
		return transactionTransportBeans;
	}

	public void setTransactionTransportBeans(
			List<TransactionTransportBean> transactionTransportBeans) {
		this.transactionTransportBeans = transactionTransportBeans;
	}
*/
	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getDisplayPassword() {
		return displayPassword;
	}

	public void setDisplayPassword(String displayPassword) {
		this.displayPassword = displayPassword;
	}

	public List<CompanyBalTransactionLog> getCompanyBalTransactionLogs() {
		return companyBalTransactionLogs;
	}

	public void setCompanyBalTransactionLogs(
			List<CompanyBalTransactionLog> companyBalTransactionLogs) {
		this.companyBalTransactionLogs = companyBalTransactionLogs;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public List<CompanyDistributorTransactionLog> getCompanyDistributorTransactionLogs() {
		return companyDistributorTransactionLogs;
	}

	public void setCompanyDistributorTransactionLogs(
			List<CompanyDistributorTransactionLog> companyDistributorTransactionLogs) {
		this.companyDistributorTransactionLogs = companyDistributorTransactionLogs;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	

	

}
