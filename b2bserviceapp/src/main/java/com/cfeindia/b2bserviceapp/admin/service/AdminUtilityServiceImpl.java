package com.cfeindia.b2bserviceapp.admin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.dao.CommonDao;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
import com.cfeindia.b2bserviceapp.dao.distributor.FundTransferDao;
import com.cfeindia.b2bserviceapp.dao.distributor.FundTransferDaoImpl;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.thirdparty.operators.ThirdpartyOperatorList;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Service("adminUtilityService")
@Transactional
public class AdminUtilityServiceImpl implements AdminUtilityService {
	@Autowired
	CommonDao commonDao;

	@Autowired
	FundTransferDao fundTransferDao;
	
	public String setDistributorCommission(Long userId, Double commission) {

		Users users = commonDao.getUsers(userId);
		Long distributorId = users.getUserId();
		String mobNo = users.getUsername();
		DistributorCommision distributorCommision = commonDao
				.getDistributorCommision(distributorId);
		distributorCommision.setCommision(commission);
		distributorCommision.setMobileNo(mobNo);
		commonDao.setDistributorCommission(distributorCommision);
		return "success";
	}

	public String updateCompanyAccount(Double amount) {

		CompanyBalance combalance = commonDao.getCompanyBalance();
		combalance.setCurrBalance(combalance.getCurrBalance() + amount);
		commonDao.updateCompanybalance(combalance);
		return "success";

	}

	public List<ThirdpartyOperatorList> thirdPartyOperatorList() {
		List<ThirdpartyOperatorList> thirdpartyOperatorList = (List<ThirdpartyOperatorList>) commonDao
				.findAll(ThirdpartyOperatorList.class);
		return thirdpartyOperatorList;
	}

	public List<CompanyOperatorComission> companyOperatorComission() {
		List<CompanyOperatorComission> operatorComission = commonDao
				.findAll(CompanyOperatorComission.class);
		Collections.sort(operatorComission,
				new CompanyOperatorCommisionComparator());
		return operatorComission;
	}

	public List<ThirdPartyServiceProvider> thirdPartyServiceProvider() {
		List<ThirdPartyServiceProvider> thirdPartySerProvider = commonDao
				.findAll(ThirdPartyServiceProvider.class);
		Collections.sort(thirdPartySerProvider,
				new ThirdPartyServiceProviderComparator());
		return thirdPartySerProvider;
	}

	public String setOperatorCommission(String rechargeType,
			String thirdPartyName, String operatorName, Double amount) {

		String result = CommonConstants.FAILURE;
		CompanyOperatorComission companyOperatorComission = commonDao
				.getCompanyOperatorCommissionItem(rechargeType, thirdPartyName,
						operatorName);
		if (companyOperatorComission == null) {
			CompanyOperatorComission comOperatorComission = new CompanyOperatorComission();
			comOperatorComission.setOperatorName(operatorName);
			comOperatorComission.setRechargeType(rechargeType);
			comOperatorComission.setThirdpartyServiceProvider(thirdPartyName);
			comOperatorComission.setRetailercommision(amount);
			commonDao.saveEntity(comOperatorComission);
			result = CommonConstants.SUCCESS;
		} else {
			companyOperatorComission.setRetailercommision(amount);
			commonDao.updateEntity(companyOperatorComission);
			result = CommonConstants.SUCCESS;
		}
		return result;
	}

	public void updateThirdPartyAPISelection(String serviceType,
			String operatorName, String thirdPartyServiceProviderName) {
		commonDao.updateThirdPartyAPISelection(serviceType, operatorName,
				thirdPartyServiceProviderName);
	}

	class CompanyOperatorCommisionComparator implements
			Comparator<CompanyOperatorComission> {

		public int compare(CompanyOperatorComission o1,
				CompanyOperatorComission o2) {
			return o1.getOperatorName().compareTo(o2.getOperatorName());
		}

	}

	class ThirdPartyServiceProviderComparator implements
			Comparator<ThirdPartyServiceProvider> {

		public int compare(ThirdPartyServiceProvider o1,
				ThirdPartyServiceProvider o2) {
			return o1.getOperatorName().compareTo(o2.getOperatorName());
		}

	}

	public List<UserDetailDto> userDetailList() {
		List<Users> userList = commonDao.findAll(Users.class);
		UserDetailDto userDetailDto = null;
		List<UserDetailDto> userDetailsDto = new ArrayList<UserDetailDto>();

		for (Users user : userList) {
			userDetailDto = new UserDetailDto();
			userDetailDto.setPassword(user.getDisplayPassword());
			userDetailDto.setUserName(user.getUsername());
			UserRole userRole = user.getUserRole(
					);
			if (userRole != null) {
				Long userId = user.getUserId();
				if ("ROLE_FRANCHISEE".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "R"));
					System.out.println(user.getUserId());
					FranchiseeCurrBal franchiseeCurrBal = (FranchiseeCurrBal) fundTransferDao
							.getCurrentDetail(user.getUserId());
					if(franchiseeCurrBal!=null) {
						userDetailDto.setB2bCurrBal(franchiseeCurrBal
								.getB2bcurrbal().toString());
						userDetailDto.setB2bCurrAdUnitBal(franchiseeCurrBal
								.getB2bcurrbaladunit().toString()); 
					}

				} else if ("ROLE_DISTRIBUTOR".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "D"));
					DistributorCurrbal distributorCurrbal = fundTransferDao.getDistCurrBal(user.getUserId());
					if(distributorCurrbal!=null) {
						userDetailDto.setB2bCurrAcBal(distributorCurrbal.getB2bCurrAcBalance().toString());
						userDetailDto.setB2bCurrAdUnitBal(distributorCurrbal.getB2bCurrAcAdUnitBal().toString());
						userDetailDto.setCurrBal(distributorCurrbal.getCurrAcBalance().toString());
					}
				}

				else if ("ROLE_EMPLOYEE".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "E"));
				}

				else if ("ROLE_ADMIN".equals(userRole.getAuthority()))
				{
					CompanyBalance companyBalance=commonDao.getCompanyBalance();
					userDetailDto.setCurrBal(companyBalance.getCurrBalance().toString());
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "A"));
				}

			}
			UserDetail userDetail = user.getUserDetail();
			if (userDetail != null) {
				userDetailDto.setEmailId(userDetail.getEmailId());
				userDetailDto.setFirmName(userDetail.getFirmName());
				userDetailDto.setMobileNo(user.getUsername());
			}
			userDetailsDto.add(userDetailDto);

		}

		return userDetailsDto;
	}

	public UserDetailDto getUserDetail(long userId) {
		Users users = commonDao.getUsers(userId);
		UserDetailDto userDetailDto = new UserDetailDto();
		userDetailDto.setUserName(users.getUsername());
		return null;
	}
}
