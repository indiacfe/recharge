package com.cfeindia.b2bserviceapp.model.franchisee;

public class TelecomRecharge {
	private String keyvalue;
	private String rechargeType;
	private String type;
	private String mnp;
	private String operator;
	private String circle, mobileNo;
	private String rechargeTypeTopup;

	private int amount;

	
	public String getKeyvalue() {
		return keyvalue;
	}

	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMnp() {
		return mnp;
	}

	public void setMnp(String mnp) {
		this.mnp = mnp;
	}

	

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getRechargeTypeTopup() {
		return rechargeTypeTopup;
	}

	public void setRechargeTypeTopup(String rechargeTypeTopup) {
		this.rechargeTypeTopup = rechargeTypeTopup;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
