package com.cfeindia.b2bserviceapp.service.distributor;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.dao.CommonDao;
import com.cfeindia.b2bserviceapp.common.dao.RegistrationDao;
import com.cfeindia.b2bserviceapp.distributor.model.Registration;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.NewIdCreationBalanceLog;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;

@Service("registrationService")
@Transactional
public class RegistrationServiceImpl implements RegistrationService {



	@Autowired
	RegistrationDao registrationDao;

	@Autowired
	CommonDao commonDao;
	
	public Long registerationProcessByCompany(Registration registration,
			Long curComtId,String category) {
		Long newUserId = saveUser(registration);
		
		saveUserDetail(registration, newUserId);

		saveNewUserRole(registration, newUserId);
		if (("ROLE_DISTRIBUTOR").equalsIgnoreCase(registration
				.getRegistrationType())) {

			createDistZeroCurrBalance(curComtId, newUserId, category);

			createDefaultDistributorCommision(newUserId);

			updateCompanyBalance(curComtId,category, CommonConstants.DISTRIBUTOR_CREATION_CHARGE, true, newUserId);


		} else if (("ROLE_FRANCHISEE").equalsIgnoreCase(registration
				.getRegistrationType())) {

			saveFranchiseeZeroCurrBal(curComtId, newUserId, category);
			
			updateCompanyBalance(curComtId,category, CommonConstants.RETAILER_CREATION_CHARGE, false, newUserId);
			
		}
		return newUserId;
	}

	public Long registerationProcess(Registration registration, Long currDistid) {

		Long newUserId = saveUser(registration);

		saveUserDetail(registration, newUserId);

		saveNewUserRole(registration, newUserId);

		if (("ROLE_DISTRIBUTOR").equalsIgnoreCase(registration
				.getRegistrationType())) {

			createDistZeroCurrBalance(currDistid, newUserId, "ROLE_DISTRIBUTOR");

			createDefaultDistributorCommision(newUserId);

			updateCurrentDistCurrBal(currDistid, CommonConstants.DISTRIBUTOR_CREATION_CHARGE, true, newUserId);


		} else if (("ROLE_FRANCHISEE").equalsIgnoreCase(registration
				.getRegistrationType())) {

			saveFranchiseeZeroCurrBal(currDistid, newUserId, "ROLE_DISTRIBUTOR");
			
			updateCurrentDistCurrBal(currDistid, CommonConstants.RETAILER_CREATION_CHARGE, false, newUserId);
			
		}
		return newUserId;
	}

	private void saveFranchiseeZeroCurrBal(Long currDistid, Long newUserId, String creatorRole) {
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
		Users user=new Users();
		user.setUserId(newUserId);
		role.setUserId(user);
		role.setAuthority(registration.getRegistrationType());
		commonDao.saveEntity(role);
	}

	private void updateCurrentDistCurrBal(Long currDistid, double creationCharge, boolean isDistributor, long newUserId) {
		DistributorCurrbal existingDistributorCurrbal = registrationDao
				.loadDistributorCurrbal(currDistid);
		double precurrbal = existingDistributorCurrbal.getCurrAcBalance();
		existingDistributorCurrbal.setCurrAcBalance(precurrbal - creationCharge);
		commonDao.updateEntity(existingDistributorCurrbal);
		
		
		NewIdCreationBalanceLog newIdCreationBalanceLog = new NewIdCreationBalanceLog();			
		newIdCreationBalanceLog.setCreatorid(currDistid);
		newIdCreationBalanceLog.setTransactionId(CyberTelUtil.generateTransId("NEWID", null));
		newIdCreationBalanceLog.setIdCreatorRole(CommonConstants.ROLE_DISTRIBUTOR);
		newIdCreationBalanceLog.setTransferType(CommonConstants.NEW_ID_CREATION);
		if(isDistributor)
			newIdCreationBalanceLog.setNewDistributorId(newUserId);
		else 
			newIdCreationBalanceLog.setNewFranchiseeId(newUserId);
		newIdCreationBalanceLog.setPrecurrbal(precurrbal);
		newIdCreationBalanceLog.setNewcurrbal(existingDistributorCurrbal.getCurrAcBalance());
		newIdCreationBalanceLog.setCreationCharge(creationCharge);
		commonDao.saveEntity(newIdCreationBalanceLog);
		
	}
	private void updateCompanyBalance(Long currDistid,String categary, double creationCharge, boolean isDistributor, long newUserId) {
		CompanyBalance companyBalance = registrationDao
				.loadCompanyAccountBalance();
		double precurrbal = companyBalance.getCurrBalance();
		companyBalance.setCurrBalance(precurrbal - creationCharge);
		commonDao.updateEntity(companyBalance);
		
		
		NewIdCreationBalanceLog newIdCreationBalanceLog = new NewIdCreationBalanceLog();			
		newIdCreationBalanceLog.setCreatorid(currDistid);
		newIdCreationBalanceLog.setTransactionId(CyberTelUtil.generateTransId("NEWID", null));
		newIdCreationBalanceLog.setIdCreatorRole(categary);
		newIdCreationBalanceLog.setTransferType(CommonConstants.NEW_ID_CREATION);
		if(isDistributor)
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
		distributorCommision.setCommision(CommonConstants.DEFAULT_DISTRIBUTOR_COMMISSION);
		distributorCommision.setDistributorId(newUserId);
		commonDao.saveEntity(distributorCommision);
	}

	private void createDistZeroCurrBalance(Long creatorId, Long newUserId, String creatorRole) {
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
		Users user=new Users();
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

	

}
