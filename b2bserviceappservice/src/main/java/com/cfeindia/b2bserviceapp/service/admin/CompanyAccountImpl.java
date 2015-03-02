package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.entity.UserRole;

@Service("companyAccount")
@Transactional
public class CompanyAccountImpl implements CompanyAccount {

	@Autowired
	CommonDao commonDao;
	double dAccount=0.0d;
	public double companyAccount() {
		dAccount = commonDao.getCompanyAccountBalance();
		return dAccount;
	}

	public String[] companyInfo() {
		Double dAccount = commonDao.getCompanyAccountBalance();
		String returnvalue[]=new String[3];
		Integer numberOfDist=0;
		Integer numberOfFran=0;
		List<UserRole> distributorList = commonDao.getDistributorsList();
		List<UserRole> franchiseeList = commonDao.getFranchiseeList();
		
		for (UserRole userRole : distributorList) {
			
			numberOfDist++;
		}
		for (UserRole userRole : franchiseeList) {
			numberOfFran++;
		}
		returnvalue[0]=dAccount.toString();
		returnvalue[1]=numberOfDist.toString();
		returnvalue[2]=numberOfFran.toString();
		return returnvalue;
	}

	
}
