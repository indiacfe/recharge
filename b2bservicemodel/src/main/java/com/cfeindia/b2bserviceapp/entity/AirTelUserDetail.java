package com.cfeindia.b2bserviceapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "airtel_store_data")
public class AirTelUserDetail {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Column(name = "USER_NAME")
	private String airtelUserName;
	@Column(name = "STORE_CODE")
	private String storeCode;
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "CIRCLE_NAME")
	private String circleName;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "CITY_NAME")
	private String cityName;
	@Column(name = "PINCODE")
	private String pinCode;
	@Column(name = "WALLET_NAME")
	private String walletName;
	@Column(name = "STATUS")
	private String status;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getAirtelUserName() {
		return airtelUserName;
	}

	public void setAirtelUserName(String airtelUserName) {
		this.airtelUserName = airtelUserName;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
