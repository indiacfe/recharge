package com.cfeindia.b2bserviceapp.recharge.mobile.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="masterdata")
public class OperatorList {

	@Column(name="KEY_VAL")
	private String keyValue;
	@Column(name="DISPLAY_NAME")
	private String displayName;
	@Column(name="VALUE")
	private String value;
	
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
