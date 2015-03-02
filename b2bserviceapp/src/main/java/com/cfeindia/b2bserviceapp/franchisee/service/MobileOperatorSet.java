package com.cfeindia.b2bserviceapp.franchisee.service;

import java.util.LinkedHashSet;
import java.util.Set;

import com.cfeindia.b2bserviceapp.recharge.mobile.dto.OperatorList;



public class MobileOperatorSet {
	
	Set<OperatorList> mobileOperatorSet=new LinkedHashSet<OperatorList>();

	public Set<OperatorList> getMobileOperatorSet() {
		return mobileOperatorSet;
	}

	public void setMobileOperatorSet(Set<OperatorList> mobileOperatorSet) {
		this.mobileOperatorSet = mobileOperatorSet;
	}

	

}
