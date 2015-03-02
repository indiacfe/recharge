package com.cfeindia.b2bserviceapp.dao.admin;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

public interface FranchiseeAddRemovedao {
	
	public FranchiseeCurrBal removeFranchiseeCreator(Long franId);
	public void updateDetails(FranchiseeCurrBal franchiseeCurrBal);

}
