package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.common.FranchiseeInfoDao;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.NoticeInfo;
import com.cfeindia.b2bserviceapp.entity.UserDetail;

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
		FranchiseeCurrBal franchiseeCurrBal=franchiseeInfoDao.getFranchiseeAccountBalance(franchiseeIdLong);
		UserDetail userDetail=commonDao.getUserDetail(franchiseeIdLong);
		Long franId=userDetail.getUsers().getUserId();
		franchiseeInfo.setFranId(franId.toString());
		franchiseeInfo.setFirmName(userDetail.getFirmName());
		franchiseeInfo.setName(userDetail.getUserName());
		franchiseeInfo.setB2bCurrentAdUnitBalance(franchiseeCurrBal.getB2bcurrbaladunit());
		franchiseeInfo.setB2bCurrentBalance(franchiseeCurrBal.getB2bcurrbal());
		return franchiseeInfo;
	}

	public List<NoticeInfo> getNoticeInfo() {
		List<NoticeInfo> list=commonDao.getNoticeRetailer();
		return list;
	}

}
