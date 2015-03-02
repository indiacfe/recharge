package com.cfeindia.b2bserviceapp.franchisee.model;

public class AccountStament {
	
	private String fromDate,
					toDate;
	private String franchiseeid;
	
	private String number;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFranchiseeid() {
		return franchiseeid;
	}

	public void setFranchiseeid(String franchiseeid) {
		this.franchiseeid = franchiseeid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}
