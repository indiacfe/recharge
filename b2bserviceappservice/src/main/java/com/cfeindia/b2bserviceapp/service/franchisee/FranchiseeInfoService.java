package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.entity.NoticeInfo;

public interface FranchiseeInfoService {
	public FranchiseeInfo getFranchiseeInfo(String franchiseeId);
	public List<NoticeInfo> getNoticeInfo();

}
