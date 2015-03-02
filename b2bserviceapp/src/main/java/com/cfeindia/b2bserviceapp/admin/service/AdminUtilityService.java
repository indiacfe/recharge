package com.cfeindia.b2bserviceapp.admin.service;

import java.util.List;

import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.thirdparty.operators.ThirdpartyOperatorList;

public interface AdminUtilityService {
	
	public String setDistributorCommission(Long userId,Double commission);
	public String updateCompanyAccount(Double amount);
	public List<ThirdpartyOperatorList> thirdPartyOperatorList();
	public List<CompanyOperatorComission> companyOperatorComission();
	public List<ThirdPartyServiceProvider> thirdPartyServiceProvider();
	public String setOperatorCommission(String rechargeType,String thirdPartyName,String operatorName,Double amount);
	public void updateThirdPartyAPISelection(String serviceType,
			String operatorName, String thirdPartyServiceProviderName);
	public List<UserDetailDto> userDetailList();
	public UserDetailDto getUserDetail(long userId);
	
}
