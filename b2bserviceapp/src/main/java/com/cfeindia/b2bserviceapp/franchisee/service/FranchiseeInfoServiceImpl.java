package com.cfeindia.b2bserviceapp.franchisee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.dao.CommonDao;
import com.cfeindia.b2bserviceapp.common.dao.FranchiseeInfoDao;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.recharge.mobile.dto.FranchiseeInfo;

@Service("franchiseeInfoService")
@Transactional
public class FranchiseeInfoServiceImpl implements FranchiseeInfoService {
	@Autowired
	private FranchiseeInfoDao franchiseeInfoDao;
	@Autowired
	private CommonDao commonDao;
	
	public FranchiseeInfo getFranchiseeInfo(String franchiseeId) {
		FranchiseeInfo franchiseeInfo=new FranchiseeInfo();
		Long franchiseeIdLong=Long.parseLong(franchiseeId);
		System.out.println(franchiseeIdLong);
		FranchiseeCurrBal franchiseeCurrBal=franchiseeInfoDao.getFranchiseeAccountBalance(franchiseeIdLong);
		UserDetail userDetail=commonDao.getUserDetail(franchiseeIdLong);
		franchiseeInfo.setName(userDetail.getUserName());
		franchiseeInfo.setB2bCurrentAdUnitBalance(franchiseeCurrBal.getB2bcurrbaladunit());
		franchiseeInfo.setB2bCurrentBalance(franchiseeCurrBal.getB2bcurrbal());
		return franchiseeInfo;
	}

}
