package com.cfeindia.b2bserviceapp.dao.common.thirdparty;

import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;


public interface ThirdPartyDetailProviderDao {
	public ThirdPartyDetailObject getThirdPartyDetailObject(String serviceProviderName);
}
