package com.cfeindia.b2bserviceapp.service.distributor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.converter.AirtelEntityToRegistrationDtoConverter;
import com.cfeindia.b2bserviceapp.converter.RegistrationDtoToAirtelEntityConverter;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.common.RegistrationDao;
import com.cfeindia.b2bserviceapp.entity.AirTelUserDetail;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.NewIdCreationBalanceLog;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.model.distributor.Registration;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Service("registrationService")
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationDao registrationDao;

	@Autowired
	CommonDao commonDao;

	public Long registerationProcessByCompany(Registration registration,
			Long curComtId, String category) {

		registration
				.setPassword(CyberTelUtil.getGeneratedPassword().toString());
		Long newUserId = saveUser(registration);

		saveUserDetail(registration, newUserId);

		saveNewUserRole(registration, newUserId);
		if (("ROLE_DISTRIBUTOR").equalsIgnoreCase(registration
				.getRegistrationType())) {

			createDistZeroCurrBalance(curComtId, newUserId, category);

			createDefaultDistributorCommision(newUserId);

			updateCompanyBalance(curComtId, category,
					CommonConstants.DISTRIBUTOR_CREATION_CHARGE, true,
					newUserId);

		} else if (("ROLE_FRANCHISEE").equalsIgnoreCase(registration
				.getRegistrationType())) {

			saveFranchiseeZeroCurrBal(curComtId, newUserId, category);

			updateCompanyBalance(curComtId, category,
					CommonConstants.RETAILER_CREATION_CHARGE, false, newUserId);

		}
		return newUserId;
	}

	public Long registerationProcess(Registration registration, Long currDistid) {
		registration
				.setPassword(CyberTelUtil.getGeneratedPassword().toString());
		Long newUserId = saveUser(registration);

		saveUserDetail(registration, newUserId);

		saveNewUserRole(registration, newUserId);

		if (("ROLE_DISTRIBUTOR").equalsIgnoreCase(registration
				.getRegistrationType())) {

			createDistZeroCurrBalance(currDistid, newUserId, "ROLE_DISTRIBUTOR");

			createDefaultDistributorCommision(newUserId);

			updateCurrentDistCurrBal(currDistid,
					CommonConstants.DISTRIBUTOR_CREATION_CHARGE, true,
					newUserId);

		} else if (("ROLE_FRANCHISEE").equalsIgnoreCase(registration
				.getRegistrationType())) {

			saveFranchiseeZeroCurrBal(currDistid, newUserId, "ROLE_DISTRIBUTOR");

			updateCurrentDistCurrBal(currDistid,
					CommonConstants.RETAILER_CREATION_CHARGE, false, newUserId);

		}
		return newUserId;
	}

	private void saveFranchiseeZeroCurrBal(Long currDistid, Long newUserId,
			String creatorRole) {
		FranchiseeCurrBal franchiseeCurrBal = new FranchiseeCurrBal();
		franchiseeCurrBal.setB2bcurrbal(CommonConstants.ZERO);
		franchiseeCurrBal.setB2bcurrbaladunit(CommonConstants.ZERO);
		franchiseeCurrBal.setFranchiseeid(newUserId);
		franchiseeCurrBal.setCreatorId(currDistid);
		franchiseeCurrBal.setCreatorRole(creatorRole);
		commonDao.saveEntity(franchiseeCurrBal);
	}

	private Long saveUser(Registration registration) {
		Users user = new Users();
		user.setUsername(registration.getMobileNo());
		String encryptedPassword = encodePasswordWithBCrypt(registration
				.getPassword());
		user.setPassword(encryptedPassword);
		user.setEnabled(1);
		user.setDisplayPassword(registration.getPassword());
		Long newUserId = registrationDao.usersDataSave(user);
		return newUserId;
	}

	private void saveNewUserRole(Registration registration, Long newUserId) {
		UserRole role = new UserRole();
		Users user = new Users();
		user.setUserId(newUserId);
		role.setUserId(user);
		role.setAuthority(registration.getRegistrationType());
		commonDao.saveEntity(role);
	}

	private void updateCurrentDistCurrBal(Long currDistid,
			double creationCharge, boolean isDistributor, long newUserId) {
		DistributorCurrbal existingDistributorCurrbal = registrationDao
				.loadDistributorCurrbal(currDistid);
		double precurrbal = existingDistributorCurrbal.getCurrAcBalance();
		existingDistributorCurrbal
				.setCurrAcBalance(precurrbal - creationCharge);
		commonDao.updateEntity(existingDistributorCurrbal);

		NewIdCreationBalanceLog newIdCreationBalanceLog = new NewIdCreationBalanceLog();
		newIdCreationBalanceLog.setCreatorid(currDistid);
		newIdCreationBalanceLog.setTransactionId(CyberTelUtil.generateTransId(
				"NEWID", null));
		newIdCreationBalanceLog
				.setIdCreatorRole(CommonConstants.ROLE_DISTRIBUTOR);
		newIdCreationBalanceLog
				.setTransferType(CommonConstants.NEW_ID_CREATION);
		if (isDistributor)
			newIdCreationBalanceLog.setNewDistributorId(newUserId);
		else
			newIdCreationBalanceLog.setNewFranchiseeId(newUserId);
		newIdCreationBalanceLog.setPrecurrbal(precurrbal);
		newIdCreationBalanceLog.setNewcurrbal(existingDistributorCurrbal
				.getCurrAcBalance());
		newIdCreationBalanceLog.setCreationCharge(creationCharge);
		commonDao.saveEntity(newIdCreationBalanceLog);

	}

	private void updateCompanyBalance(Long currDistid, String categary,
			double creationCharge, boolean isDistributor, long newUserId) {
		CompanyBalance companyBalance = registrationDao
				.loadCompanyAccountBalance();
		double precurrbal = companyBalance.getCurrBalance();
		companyBalance.setCurrBalance(precurrbal - creationCharge);
		commonDao.updateEntity(companyBalance);

		NewIdCreationBalanceLog newIdCreationBalanceLog = new NewIdCreationBalanceLog();
		newIdCreationBalanceLog.setCreatorid(currDistid);
		newIdCreationBalanceLog.setTransactionId(CyberTelUtil.generateTransId(
				"NEWID", null));
		newIdCreationBalanceLog.setIdCreatorRole(categary);
		newIdCreationBalanceLog
				.setTransferType(CommonConstants.NEW_ID_CREATION);
		if (isDistributor)
			newIdCreationBalanceLog.setNewDistributorId(newUserId);
		else
			newIdCreationBalanceLog.setNewFranchiseeId(newUserId);
		newIdCreationBalanceLog.setPrecurrbal(precurrbal);
		newIdCreationBalanceLog.setNewcurrbal(companyBalance.getCurrBalance());
		newIdCreationBalanceLog.setCreationCharge(creationCharge);
		commonDao.saveEntity(newIdCreationBalanceLog);

	}

	private void createDefaultDistributorCommision(Long newUserId) {
		DistributorCommision distributorCommision = new DistributorCommision();
		distributorCommision
				.setCommision(CommonConstants.DEFAULT_DISTRIBUTOR_COMMISSION);
		distributorCommision.setDistributorId(newUserId);
		commonDao.saveEntity(distributorCommision);
	}

	private void createDistZeroCurrBalance(Long creatorId, Long newUserId,
			String creatorRole) {
		DistributorCurrbal distributorCurrbal = new DistributorCurrbal();
		distributorCurrbal.setCurrAcBalance(CommonConstants.ZERO);
		distributorCurrbal.setB2bCurrAcBalance(CommonConstants.ZERO);
		distributorCurrbal.setB2bCurrAcAdUnitBal(CommonConstants.ZERO);
		distributorCurrbal.setDistributorId(newUserId);
		distributorCurrbal.setCreatorId(creatorId);
		distributorCurrbal.setCreatorRole(creatorRole);
		commonDao.saveEntity(distributorCurrbal);
	}

	private void saveUserDetail(Registration registration, Long newUserId) {
		UserDetail detail = new UserDetail();
		detail.setBankAccount(registration.getBankAccount());
		detail.setBankBranch(registration.getBankBranch());
		detail.setBankBranchCity(registration.getBankBranchCity());
		detail.setBankName(registration.getBankName());
		detail.setDistrict(registration.getDistrict());
		detail.setEmailId(registration.getEmailId());
		detail.setFirmName(registration.getFirmName());
		detail.setIfsCode(registration.getIfsCode());
		detail.setLandLine(registration.getLandLine());
		detail.setOfficeAddress(registration.getOfficeAddress());
		detail.setPanCard(registration.getPancardNo());
		detail.setPermanentAddress(registration.getPermanentAddress());
		detail.setPinCode(registration.getPincode());
		detail.setState(registration.getState());
		detail.setUserName(registration.getName());
		detail.setPermanentAddress(registration.getPermanentAddress());
		/*
		 * java.util.Date date= new java.util.Date(); detail.setCreateAt(new
		 * Timestamp(date.getTime()));
		 */
		Users user = new Users();
		user.setUserId(newUserId);
		detail.setUsers(user);
		commonDao.saveEntity(detail);
	}

	public static String encodePasswordWithBCrypt(String plainPassword) {
		return new MessageDigestPasswordEncoder("MD5").encodePassword(
				plainPassword, null);
	}

	public RegistrationDao getRegistrationDao() {
		return registrationDao;
	}

	public void setRegistrationDao(RegistrationDao registrationDao) {
		this.registrationDao = registrationDao;
	}

	public Registration getUserDetail(Long userId) {
		Registration register = new Registration();
		Users user = (Users) commonDao.getEntityByPrimaryKey(Users.class,
				userId);
		register.setUserName(user.getUsername());
		register.setRegistrationType(user.getUserRole().getAuthority());
		register.setFirmName(user.getUserDetail().getFirmName());
		register.setEmailId(user.getUserDetail().getEmailId());
		register.setName(user.getUserDetail().getUserName());
		register.setDistrict(user.getUserDetail().getDistrict());
		register.setIfsCode(user.getUserDetail().getIfsCode());
		register.setBankAccount(user.getUserDetail().getBankAccount());
		register.setBankBranch(user.getUserDetail().getBankBranch());
		register.setBankName(user.getUserDetail().getBankName());
		register.setBankBranchCity(user.getUserDetail().getBankBranchCity());
		register.setMobileNo(user.getUsername());
		register.setPancardNo(user.getUserDetail().getPanCard());
		register.setLandLine(user.getUserDetail().getLandLine());
		register.setOfficeAddress(user.getUserDetail().getOfficeAddress());
		register.setPassword(user.getPassword());
		register.setPermanentAddress(user.getUserDetail().getPermanentAddress());
		register.setPincode(user.getUserDetail().getPinCode());
		register.setState(user.getUserDetail().getState());
		return register;
	}

	public String updateUser(Registration registration) {
		String RESULT = null;
		try {
			long id = Long.parseLong(ExtractorUtil
					.extractIdFromString(registration.getUserId()));

			Users user = (Users) commonDao.getEntityByPrimaryKey(Users.class,
					id);
			UserDetail detail = user.getUserDetail();
			detail.setBankAccount(registration.getBankAccount());
			detail.setBankBranch(registration.getBankBranch());
			detail.setBankBranchCity(registration.getBankBranchCity());
			detail.setBankName(registration.getBankName());
			detail.setDistrict(registration.getDistrict());
			detail.setEmailId(registration.getEmailId());
			detail.setFirmName(registration.getFirmName());
			detail.setIfsCode(registration.getIfsCode());
			detail.setLandLine(registration.getLandLine());
			detail.setOfficeAddress(registration.getOfficeAddress());
			detail.setPanCard(registration.getPancardNo());
			detail.setPermanentAddress(registration.getPermanentAddress());
			detail.setPinCode(registration.getPincode());
			detail.setState(registration.getState());
			detail.setUserName(registration.getName());
			detail.setPermanentAddress(registration.getPermanentAddress());
			UserRole urole = user.getUserRole();
			urole.setAuthority(registration.getRegistrationType());
			user.setUserDetail(detail);
			user.setUserRole(urole);
			user.setUsername(registration.getMobileNo());
			commonDao.userUpdate(user);
			RESULT = "success";
		} catch (Exception e) {
			e.printStackTrace();
			RESULT = "fail";
		}

		return RESULT;
	}

	public String userDeactivate(Long userId, int enabled) {

		Users user = (Users) commonDao.getEntityByPrimaryKey(Users.class,
				userId);
		if (enabled == 1) {
			user.setEnabled(0);
		} else {
			user.setEnabled(1);
		}
		boolean result = commonDao.userUpdate(user);
		return user.getUserRole().getAuthority();
	}

	@Override
	public String addNewAirtelUser(Registration registration) {
		RegistrationDtoToAirtelEntityConverter converter = new RegistrationDtoToAirtelEntityConverter();
		AirTelUserDetail airTelUserDetail = converter.convert(registration);
		commonDao.saveEntity(airTelUserDetail);
		return CommonConstants.SUCCESS;
	}

	@Override
	public boolean checkAirtelUser(String mobileNumber) {
		AirTelUserDetail airTelUserDetail = commonDao
				.getAirtelUserName(mobileNumber);
		if (airTelUserDetail == null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Registration getAirTelUser(String mobileNumberOrUserName) {
		AirTelUserDetail airTelUserDetail = commonDao
				.getAirtelUserName(mobileNumberOrUserName);
		Registration registration = null;
		if (airTelUserDetail != null) {
			AirtelEntityToRegistrationDtoConverter converter = new AirtelEntityToRegistrationDtoConverter();
			registration = converter.convert(airTelUserDetail);
			return registration;
		} else {
			return registration;
		}
	}

	@Override
	public String updateAirTelUser(Registration registration) {
		AirTelUserDetail airTelUserDetail = commonDao
				.getAirtelUserName(registration.getMobileNo());
		airTelUserDetail.setAirtelUserName(registration.getUserName());
		airTelUserDetail.setStoreCode(registration.getDocumentDetail());
		airTelUserDetail.setMobileNumber(registration.getMobileNo());
		airTelUserDetail.setAddress(registration.getPermanentAddress());
		airTelUserDetail.setCircleName(registration.getDocumentType());
		airTelUserDetail.setStateName(registration.getState());
		airTelUserDetail.setCityName(registration.getDistrict());
		airTelUserDetail.setPinCode(registration.getPincode());
		airTelUserDetail.setWalletName(CommonConstants.AIRTEL_WALLET_NAME);
		airTelUserDetail.setStatus(CommonConstants.AIRTEL_USER_CONFIGURED);
		commonDao.updateEntity(airTelUserDetail);
		return CommonConstants.SUCCESS;
	}

}
