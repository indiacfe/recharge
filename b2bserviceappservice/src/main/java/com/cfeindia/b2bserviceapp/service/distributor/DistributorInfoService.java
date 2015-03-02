package com.cfeindia.b2bserviceapp.service.distributor;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.recharge.mobile.DistributorInfo;
import com.cfeindia.b2bserviceapp.entity.NoticeInfo;

public interface DistributorInfoService {
	public DistributorInfo distributorInfo(String userId); 
	public List<NoticeInfo> getNoticeInfo();
		

}
