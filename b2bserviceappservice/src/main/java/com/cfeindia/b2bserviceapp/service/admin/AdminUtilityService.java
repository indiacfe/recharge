package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.model.admin.UpdateCommissionOperator;
import com.cfeindia.b2bserviceapp.thirdparty.operators.ThirdpartyOperatorList;

public interface AdminUtilityService {

	public String setDistributorCommission(Long userId, Double commission);

	public String updateCompanyAccount(Double amount);

	public List<ThirdpartyOperatorList> thirdPartyOperatorList();

	public List<CompanyOperatorComission> companyOperatorComission();

	public List<ThirdPartyServiceProvider> thirdPartyServiceProvider();

	public String setOperatorCommission(
			UpdateCommissionOperator updateCommissionOperator);

	public void updateThirdPartyAPISelection(String serviceType,
			String operatorName, String thirdPartyServiceProviderName);

	public List<UserDetailDto> userDetailList();
	
	public List<UserDetailDto> userDetailList(int pageNo );

	public List<UserDetailDto> customerDetailList();

	public UserDetailDto getUserDetail(long userId);
	
	public String deductDistcurrBal(Long distId, Double amount, String remark);

	public String deductDistb2bBal(Long distId, Double amount, String remark);

	public String deductDistAdUnitBal(Long distId, Double Amount, String remark);

	public String deductFranCurrBal(Long senderId, Long franId, Double Amount,
			String remark,String paymentType);

	public List<UserDetailDto> userDetailListDistributor(String selected);

	public List<UserDetailDto> retailersLists(Long dUserId);

	public Number countUserList();


}
