package com.cfeindia.b2bserviceapp.service.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
import com.cfeindia.b2bserviceapp.dao.admin.AdminReportDao;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.customer.CustomerFundTransferDao;
import com.cfeindia.b2bserviceapp.dao.distributor.FundTransferDao;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.model.admin.UpdateCommissionOperator;
import com.cfeindia.b2bserviceapp.thirdparty.operators.ThirdpartyOperatorList;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service("adminUtilityService")
@Transactional(timeout = 300)
public class AdminUtilityServiceImpl implements AdminUtilityService {
	@Autowired
	CommonDao commonDao;

	@Autowired
	FundTransferDao fundTransferDao;

	@Autowired
	AdminReportDao adminReportDao;

	@Autowired
	CustomerFundTransferDao customerFundTransferDao;

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

	public String setOperatorCommission(
			UpdateCommissionOperator updateCommissionOperator) {

		String result = CommonConstants.FAILURE;
		CompanyOperatorComission companyOperatorComission = commonDao
				.getCompanyOperatorCommissionItem(
						updateCommissionOperator.getRechargeType(),
						updateCommissionOperator.getThirdPartyOperator(),
						updateCommissionOperator.getOperator(),
						updateCommissionOperator.getCommissionType());
		if (companyOperatorComission == null) {
			CompanyOperatorComission comOperatorComission = new CompanyOperatorComission();
			comOperatorComission.setOperatorName(updateCommissionOperator
					.getOperator());
			comOperatorComission.setRechargeType(updateCommissionOperator
					.getRechargeType());
			comOperatorComission
					.setThirdpartyServiceProvider(updateCommissionOperator
							.getThirdPartyOperator());
			comOperatorComission.setRetailercommision(updateCommissionOperator
					.getAmount());
			comOperatorComission.setComissionType(updateCommissionOperator
					.getCommissionType());
			comOperatorComission.setDeductionType(updateCommissionOperator
					.getDeductionType());
			commonDao.saveEntity(comOperatorComission);
			result = CommonConstants.SUCCESS;
		} else {
			companyOperatorComission
					.setRetailercommision(updateCommissionOperator.getAmount());
			companyOperatorComission.setComissionType(updateCommissionOperator
					.getCommissionType());
			companyOperatorComission.setDeductionType(updateCommissionOperator
					.getDeductionType());
			commonDao.updateEntity(companyOperatorComission);
			result = CommonConstants.SUCCESS;
		}
		return result;
	}

	public void updateThirdPartyAPISelection(String serviceType,
			String operatorName, String thirdPartyServiceProviderName) {
		ThirdPartyServiceProvider thirdpartyServiceProvider = commonDao
				.getThirdPartyServiceProvider(serviceType, operatorName,
						thirdPartyServiceProviderName);
		if (thirdpartyServiceProvider != null) {
			thirdpartyServiceProvider
					.setServiceProvider(thirdPartyServiceProviderName);
			commonDao.updateEntity(thirdpartyServiceProvider);
		} else {
			thirdpartyServiceProvider = new ThirdPartyServiceProvider();
			thirdpartyServiceProvider.setOperatorName(operatorName);
			thirdpartyServiceProvider
					.setServiceProvider(thirdPartyServiceProviderName);
			thirdpartyServiceProvider.setServiceType(serviceType);
			thirdpartyServiceProvider
					.setCreatedAt(TimeStampUtil.getTimestamp());
			commonDao.saveEntity(thirdpartyServiceProvider);

		}
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
		FranchiseeCurrBal franchiseeCurrBal = null;
		for (Users user : userList) {
			userDetailDto = new UserDetailDto();
			userDetailDto.setPassword(user.getDisplayPassword());
			userDetailDto.setUserName(user.getUsername());
			UserRole userRole = user.getUserRole();
			if (userRole != null) {
				Long userId = user.getUserId();
				if ("ROLE_FRANCHISEE".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "R"));
					franchiseeCurrBal = (FranchiseeCurrBal) fundTransferDao
							.getCurrentDetailWithoutLockingForDisplay(user
									.getUserId());
					if (franchiseeCurrBal != null) {
						userDetailDto.setB2bCurrBal(franchiseeCurrBal
								.getB2bcurrbal().toString());
						userDetailDto.setB2bCurrAdUnitBal(franchiseeCurrBal
								.getB2bcurrbaladunit().toString());

					}

				} else if ("ROLE_DISTRIBUTOR".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "D"));
					DistributorCurrbal distributorCurrbal = fundTransferDao
							.getDistCurrBalWithoutLockingForDisplay(user
									.getUserId());
					if (distributorCurrbal != null) {
						userDetailDto.setB2bCurrAcBal(distributorCurrbal
								.getB2bCurrAcBalance().toString());
						userDetailDto.setB2bCurrAdUnitBal(distributorCurrbal
								.getB2bCurrAcAdUnitBal().toString());
						userDetailDto.setCurrBal(distributorCurrbal
								.getCurrAcBalance().toString());
					}
				}

				else if ("ROLE_EMPLOYEE".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "E"));
				}

				else if ("ROLE_ADMIN".equals(userRole.getAuthority())) {
					CompanyBalance companyBalance = commonDao
							.getCompanyBalance();
					userDetailDto.setCurrBal(companyBalance.getCurrBalance()
							.toString());
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "A"));
				}

			}
			Long creatorId = null;
			Users createdByUser = null;
			String creatorName = null;
			if (franchiseeCurrBal !=null && franchiseeCurrBal.getCreatorId() != null
					&& franchiseeCurrBal.getCreatorId() != 0L) {
				creatorId = franchiseeCurrBal.getCreatorId();
				createdByUser = (Users) commonDao.getEntityByPrimaryKey(
						Users.class, creatorId);
				creatorName = createdByUser.getUserDetail().getUserName();
			}

			UserDetail userDetail = user.getUserDetail();
			if (userDetail != null) {
				userDetailDto.setEmailId(userDetail.getEmailId());
				userDetailDto.setFirmName(userDetail.getFirmName());
				userDetailDto.setCreatedAt(userDetail.getCreateAt());
				userDetailDto.setMobileNo(user.getUsername());
				userDetailDto.setEnabled(user.getEnabled());
				userDetailDto.setCreatedBy(creatorName);
			}
			userDetailsDto.add(userDetailDto);

		}

		return userDetailsDto;
	}

	public List<UserDetailDto> userDetailList(int pageNo) {
		List<Users> userList = adminReportDao.getAllUserDetails(pageNo);
		UserDetailDto userDetailDto = null;
		List<UserDetailDto> userDetailsDto = new ArrayList<UserDetailDto>();
		FranchiseeCurrBal franchiseeCurrBal = null;
		for (Users user : userList) {
			userDetailDto = new UserDetailDto();
			userDetailDto.setPassword(user.getDisplayPassword());
			userDetailDto.setUserName(user.getUsername());
			UserRole userRole = user.getUserRole();
			if (userRole != null) {
				Long userId = user.getUserId();
				if ("ROLE_FRANCHISEE".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "R"));
					franchiseeCurrBal = (FranchiseeCurrBal) fundTransferDao
							.getCurrentDetailWithoutLockingForDisplay(user
									.getUserId());
					if (franchiseeCurrBal != null) {
						userDetailDto.setB2bCurrBal(franchiseeCurrBal
								.getB2bcurrbal().toString());
						userDetailDto.setB2bCurrAdUnitBal(franchiseeCurrBal
								.getB2bcurrbaladunit().toString());

					}

				} else if ("ROLE_DISTRIBUTOR".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "D"));
					DistributorCurrbal distributorCurrbal = fundTransferDao
							.getDistCurrBalWithoutLockingForDisplay(user
									.getUserId());
					if (distributorCurrbal != null) {
						userDetailDto.setB2bCurrAcBal(distributorCurrbal
								.getB2bCurrAcBalance().toString());
						userDetailDto.setB2bCurrAdUnitBal(distributorCurrbal
								.getB2bCurrAcAdUnitBal().toString());
						userDetailDto.setCurrBal(distributorCurrbal
								.getCurrAcBalance().toString());
					}
				}

				else if ("ROLE_EMPLOYEE".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "E"));
				}

				else if ("ROLE_ADMIN".equals(userRole.getAuthority())) {
					CompanyBalance companyBalance = commonDao
							.getCompanyBalance();
					userDetailDto.setCurrBal(companyBalance.getCurrBalance()
							.toString());
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "A"));
				}

			}
			Long creatorId = null;
			Users createdByUser = null;
			String creatorName = null;
			if (franchiseeCurrBal != null
					&& franchiseeCurrBal.getCreatorId() != null
					&& franchiseeCurrBal.getCreatorId() != 0L) {
				creatorId = franchiseeCurrBal.getCreatorId();
				createdByUser = (Users) commonDao.getEntityByPrimaryKey(
						Users.class, creatorId);
				creatorName = createdByUser.getUserDetail().getUserName();
			}

			UserDetail userDetail = user.getUserDetail();
			if (userDetail != null) {
				userDetailDto.setEmailId(userDetail.getEmailId());
				userDetailDto.setFirmName(userDetail.getFirmName());
				userDetailDto.setCreatedAt(userDetail.getCreateAt());
				userDetailDto.setMobileNo(user.getUsername());
				userDetailDto.setEnabled(user.getEnabled());
				userDetailDto.setCreatedBy(creatorName);
			}
			userDetailsDto.add(userDetailDto);

		}
		return userDetailsDto;
	}

	@Override
	public List<UserDetailDto> customerDetailList() {
		List<Users> customerList = commonDao.getCustomerList();
		List<UserDetailDto> userDetailDtoList = new ArrayList<UserDetailDto>();
		for (Users users : customerList) {
			Long userId = users.getUserId();
			UserDetailDto userDetailDto = new UserDetailDto();
			userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
					userId.toString(), "C"));
			userDetailDto.setPassword(users.getDisplayPassword());
			userDetailDto.setUserName(users.getUsername());
			userDetailDto.setEnabled(users.getEnabled());
			UserDetail userDetail = users.getUserDetail();
			if (userDetail != null) {
				userDetailDto.setEmailId(userDetail.getEmailId());
				userDetailDto.setFirmName(userDetail.getFirmName());
				userDetailDto.setCreatedAt(userDetail.getCreateAt());
				userDetailDto.setCreatedBy(userDetail.getUsers().getUsername());
			}
			CustomerCurrentBalance customerCurrentBalance = customerFundTransferDao
					.getCurrentDetailWithoutLockingForDisplay(userId);
			if (customerCurrentBalance != null) {
				userDetailDto.setCurrBal(customerCurrentBalance
						.getCurrAcBalance().toString());
			}
			userDetailDtoList.add(userDetailDto);
		}
		return userDetailDtoList;
	}

	// added by Ashish

	public List<UserDetailDto> userDetailListDistributor(String select) {

		List<Users> userList = commonDao.Distributors(select);
		UserDetailDto userDetailDto = null;
		List<UserDetailDto> userDetailsDto = new ArrayList<UserDetailDto>();

		for (Users user : userList) {
			userDetailDto = new UserDetailDto();
			userDetailDto.setPassword(user.getDisplayPassword());
			userDetailDto.setUserName(user.getUsername());
			UserRole userRole = user.getUserRole();
			userDetailDto.setCreatedBy(user.getUserDetail().getUserName());
			if (userRole != null) {
				Long userId = user.getUserId();
				if ("ROLE_DISTRIBUTOR".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "D"));
					UserDetail userDetail = user.getUserDetail();
					DistributorCurrbal distributorCurrbal = fundTransferDao
							.getDistCurrBalWithoutLockingForDisplay(user
									.getUserId());
					userDetailDto.setCurrBal(distributorCurrbal
							.getCurrAcBalance().toString());
					userDetailDto.setB2bCurrAcBal(distributorCurrbal
							.getB2bCurrAcBalance().toString());
					userDetailDto.setB2bCurrAdUnitBal(distributorCurrbal
							.getB2bCurrAcAdUnitBal().toString());
					userDetailDto.setCreatedAt(userDetail.getCreateAt());
					if (userDetail != null) {
						userDetailDto.setEmailId(userDetail.getEmailId());
						userDetailDto.setFirmName(userDetail.getFirmName());
						userDetailDto.setMobileNo(user.getUsername());
						userDetailDto.setEnabled(user.getEnabled());

					}
					userDetailsDto.add(userDetailDto);

				}

			}

		}

		return userDetailsDto;
	}

	public UserDetailDto getUserDetail(long userId) {
		Users users = commonDao.getUsers(userId);
		UserDetailDto userDetailDto = new UserDetailDto();
		userDetailDto.setUserName(users.getUsername());
		return userDetailDto;
	}

	public List<UserDetailDto> retailersLists(Long dUserId) {
		List<FranchiseeCurrBal> franCurrBal = commonDao
				.userDetailListRetailers(dUserId);

		UserDetailDto userDetailDto = null;
		List<UserDetailDto> userDetailsDto = new ArrayList<UserDetailDto>();

		for (FranchiseeCurrBal fruser : franCurrBal) {

			userDetailDto = new UserDetailDto();
			if (fruser.getUsers() != null) {
				Users users = new Users();

				users = fruser.getUsers();
				UserRole urole = new UserRole();
				urole = users.getUserRole();
				if (urole != null) {

					Long userId = users.getUserId();
					if ("ROLE_FRANCHISEE".equals(urole.getAuthority())) {
						userDetailDto.setUserId(ExtractorUtil
								.generateIdFromString(userId.toString(), "R"));
						UserDetail userDetail = users.getUserDetail();
						if (userDetail != null) {
							userDetailDto.setEmailId(userDetail.getEmailId());
							userDetailDto.setFirmName(userDetail.getFirmName());
							userDetailDto.setMobileNo(users.getUsername());
							userDetailDto.setEnabled(users.getEnabled());

							userDetailDto.setCreatorId(ExtractorUtil
									.generateIdFromString(fruser.getCreatorId()
											.toString(), "D"));
							if (fruser.getCreatorId() == 0L) {
								userDetailDto.setFlag(0);
							} else {
								userDetailDto.setFlag(1);
							}

						}
						userDetailsDto.add(userDetailDto);

					}
				}
			}
		}

		return userDetailsDto;
	}

	public String deductDistb2bBal(Long userId, Double amount, String remark) {
		CompanyDistributorTransactionLog companyDistributorTransactionLog = new CompanyDistributorTransactionLog();
		/*
		 * DistributorBalanceTransferLog distributorBalanceTransferLog = new
		 * DistributorBalanceTransferLog();
		 * distributorBalanceTransferLog.setDistId(userId);
		 * distributorBalanceTransferLog
		 * .setCreatedAt(TimeStampUtil.getTimestamp()); DistributorCurrbal
		 * distributorCurrbal = fundTransferDao .getDistCurrBal(userId); double
		 * dCurrBal = distributorCurrbal.getB2bCurrAcBalance();
		 * distributorBalanceTransferLog.setPreb2bcurrbal(dCurrBal);
		 * distributorBalanceTransferLog.setNewB2bCurrBal(dCurrBal - amount);
		 * distributorBalanceTransferLog.setTransactionId(CyberTelUtil
		 * .generateTransId( CommonConstants.DIST_TO_COMPANY_TRANSID_PREFIX,
		 * null)); distributorBalanceTransferLog.setTransferAmount(amount);
		 * distributorBalanceTransferLog
		 * .setTransferFrom(CommonConstants.TRANSFER_DIST_CURR_BAL);
		 * distributorBalanceTransferLog
		 * .setTransferTo(CommonConstants.COMPANY_TRANSFER_FROM);
		 * distributorBalanceTransferLog
		 * .setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
		 * distributorBalanceTransferLog.setRemark(remark);
		 */
		CompanyBalance companyBalance = fundTransferDao
				.getCompanyBalance(CommonConstants.companyName);
		double compCurrBal = companyBalance.getCurrBalance();
		companyDistributorTransactionLog.setPreBalance(compCurrBal);
		companyDistributorTransactionLog.setNewBalance(compCurrBal + amount);
		Users users = new Users();
		users.setUserId(userId);
		companyDistributorTransactionLog.setDistributorId(users);
		companyDistributorTransactionLog.setTransferAmount(amount);
		companyDistributorTransactionLog
				.setTransferType(CommonConstants.TRANSFER_TYPE_CREDIT);
		companyDistributorTransactionLog.setTransId(CyberTelUtil
				.generateTransId(
						CommonConstants.DIST_TO_COMPANY_TRANSID_PREFIX, null));
		companyDistributorTransactionLog.setRemark(remark);
		/*
		 * fundTransferDao
		 * .setDistributorBalTransferLog(distributorBalanceTransferLog);
		 */
		fundTransferDao
				.companyDistributorTransactionLog(companyDistributorTransactionLog);
		fundTransferDao.reduceDistributorB2bBal(userId, amount);
		updateCompanyAccount(amount);
		return CommonConstants.SUCCESS;

	}

	public String deductDistAdUnitBal(Long userId, Double amount, String remark) {
		CompanyDistributorTransactionLog companyDistributorTransactionLog = new CompanyDistributorTransactionLog();
		/*
		 * DistributorBalanceTransferLog distributorBalanceTransferLog = new
		 * DistributorBalanceTransferLog();
		 * distributorBalanceTransferLog.setDistId(userId);
		 * distributorBalanceTransferLog
		 * .setCreatedAt(TimeStampUtil.getTimestamp()); DistributorCurrbal
		 * distributorCurrbal = fundTransferDao .getDistCurrBal(userId); double
		 * dCurrBal = distributorCurrbal.getB2bCurrAcAdUnitBal();
		 * distributorBalanceTransferLog.setPreB2bCurrAdUnitBal(dCurrBal);
		 * distributorBalanceTransferLog.setNewB2bAdUnitBal(dCurrBal - amount);
		 * distributorBalanceTransferLog.setTransactionId(CyberTelUtil
		 * .generateTransId( CommonConstants.DIST_TO_COMPANY_TRANSID_PREFIX,
		 * null)); distributorBalanceTransferLog.setTransferAmount(amount);
		 * distributorBalanceTransferLog
		 * .setTransferFrom(CommonConstants.COMPANY_TRANSFER_TO);
		 * distributorBalanceTransferLog
		 * .setTransferTo(CommonConstants.COMPANY_TRANSFER_FROM);
		 * distributorBalanceTransferLog
		 * .setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
		 * distributorBalanceTransferLog.setRemark(remark);
		 */
		CompanyBalance companyBalance = fundTransferDao
				.getCompanyBalance(CommonConstants.companyName);
		double compCurrBal = companyBalance.getCurrBalance();
		companyDistributorTransactionLog.setPreBalance(compCurrBal);
		companyDistributorTransactionLog.setNewBalance(compCurrBal + amount);
		Users users = new Users();
		users.setUserId(userId);
		companyDistributorTransactionLog.setDistributorId(users);
		companyDistributorTransactionLog.setTransferAmount(amount);
		companyDistributorTransactionLog
				.setTransferType(CommonConstants.TRANSFER_TYPE_CREDIT);
		companyDistributorTransactionLog.setTransId(CyberTelUtil
				.generateTransId(
						CommonConstants.DIST_TO_COMPANY_TRANSID_PREFIX, null));
		companyDistributorTransactionLog.setRemark(remark);

		/*
		 * fundTransferDao
		 * .setDistributorBalTransferLog(distributorBalanceTransferLog);
		 */
		fundTransferDao
				.companyDistributorTransactionLog(companyDistributorTransactionLog);
		fundTransferDao.reduceDistributorAdUnitBal(userId, amount);
		updateCompanyAccount(amount);
		return CommonConstants.SUCCESS;
	}

	public String deductFranCurrBal(Long senderId, Long franId, Double amount,
			String remark, String paymentType) {
		CompanyBalTransactionLog cLog = new CompanyBalTransactionLog();
		/*
		 * FranchiseeBalanceTransferLog franchiseeBalanceTransferLog = new
		 * FranchiseeBalanceTransferLog(); FranchiseeCurrBal franchiseCurrBal =
		 * fundTransferDao .getCurrentDetail(franId); double fCurrBal =
		 * franchiseCurrBal.getB2bcurrbal();
		 * franchiseeBalanceTransferLog.setPreb2bcurrbal(fCurrBal);
		 * franchiseeBalanceTransferLog.setNewB2bCurrBal(fCurrBal - amount);
		 * franchiseeBalanceTransferLog
		 * .setCreatedAt(TimeStampUtil.getTimestamp());
		 * franchiseeBalanceTransferLog.setFranchiseeId(franId);
		 * franchiseeBalanceTransferLog
		 * .setTransactionFrom(CommonConstants.DIST_TRANSFER_TO);
		 * franchiseeBalanceTransferLog
		 * .settransactionTo(CommonConstants.COMPANY_TRANSFER_FROM);
		 * franchiseeBalanceTransferLog.setTransferAmount(amount);
		 * franchiseeBalanceTransferLog
		 * .setTransactionType(CommonConstants.TRANSFER_TYPE_DEBIT);
		 * franchiseeBalanceTransferLog.setSenderId(senderId);
		 * franchiseeBalanceTransferLog.setRemark(remark);
		 * franchiseeBalanceTransferLog.setTransactionId(CyberTelUtil
		 * .generateTransId(CommonConstants.FRAN_TO_COMP, null));
		 */
		CompanyBalance companyBalance = fundTransferDao
				.getCompanyBalance(CommonConstants.companyName);
		double compCurrBal = companyBalance.getCurrBalance();
		cLog.setUserType(CommonConstants.ROLE_FRANCHISEE);
		Users users = new Users();
		users.setUserId(franId);
		cLog.setUserId(users);
		cLog.setTransferAmount(amount);
		cLog.setTransferType(CommonConstants.TRANSFER_TYPE_CREDIT);
		cLog.setCreatedAt(TimeStampUtil.getTimestamp());
		cLog.setTransactionId(CyberTelUtil.generateTransId(
				CommonConstants.FRAN_TO_COMP, null));
		cLog.setPreBalance(compCurrBal);
		cLog.setNewBalance(compCurrBal + amount);
		cLog.setRemark(remark);
		commonDao.saveEntity(cLog);
		// commonDao.saveEntity(franchiseeBalanceTransferLog);
		fundTransferDao.reduceFranchiseCurrBal(franId, amount, paymentType);
		updateCompanyAccount(amount);
		return CommonConstants.SUCCESS;
	}

	@Override
	public Number countUserList() {
		Number number = adminReportDao.getcountUserList();
		return number;
	}

	@Override
	public String deductDistcurrBal(Long userId, Double amount, String remark) {
		CompanyDistributorTransactionLog companyDistributorTransactionLog = new CompanyDistributorTransactionLog();
		CompanyBalance companyBalance = fundTransferDao
				.getCompanyBalance(CommonConstants.companyName);
		double compCurrBal = companyBalance.getCurrBalance();
		companyDistributorTransactionLog.setPreBalance(compCurrBal);
		companyDistributorTransactionLog.setNewBalance(compCurrBal + amount);
		Users users = new Users();
		users.setUserId(userId);
		companyDistributorTransactionLog.setDistributorId(users);
		companyDistributorTransactionLog.setTransferAmount(amount);
		companyDistributorTransactionLog
				.setTransferType(CommonConstants.TRANSFER_TYPE_CREDIT);
		companyDistributorTransactionLog.setTransId(CyberTelUtil
				.generateTransId(
						CommonConstants.DIST_TO_COMPANY_TRANSID_PREFIX, null));
		companyDistributorTransactionLog.setRemark(remark);
		/*
		 * fundTransferDao
		 * .setDistributorBalTransferLog(distributorBalanceTransferLog);
		 */
		fundTransferDao
				.companyDistributorTransactionLog(companyDistributorTransactionLog);
		fundTransferDao.reduceDistributorCurrBal(userId, amount);
		updateCompanyAccount(amount);
		return CommonConstants.SUCCESS;

	}

}
