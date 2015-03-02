package com.cfeindia.b2bserviceapp.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.common.RegistrationDao;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.model.distributor.Registration;
import com.cfeindia.b2bserviceapp.service.distributor.RegistrationServiceImpl;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;

@Service
@Transactional
public class ApiRegisterServiceImpl implements ApiRegisterService {
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private RegistrationDao registrationDao;

	@Override
	public Long registerationProcessByCompany(Registration registration,
			Long curComtId, String category) {

		registration
				.setPassword(CyberTelUtil.getGeneratedPassword().toString());
		Long newUserId = saveUser(registration);

		saveUserDetail(registration, newUserId);

		saveNewUserRole(registration, newUserId);

		createCustZeroCurrBalance(curComtId, newUserId, category);

		return newUserId;
	}

	private Long saveUser(Registration registration) {
		Users user = new Users();
		user.setUsername(registration.getMobileNo());
		String encryptedPassword = RegistrationServiceImpl
				.encodePasswordWithBCrypt(registration.getPassword());
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
		Users user = new Users();
		user.setUserId(newUserId);
		detail.setUsers(user);
		commonDao.saveEntity(detail);
	}

	private void createCustZeroCurrBalance(Long creatorId, Long newUserId,
			String creatorRole) {
		CustomerCurrentBalance customerCurrbal = new CustomerCurrentBalance();
		customerCurrbal.setCurrAcBalance(CommonConstants.ZERO);
		customerCurrbal.setCustomerId(newUserId);
		customerCurrbal.setCreatorId(creatorId);
		customerCurrbal.setCreatorRole(creatorRole);
		commonDao.saveEntity(customerCurrbal);
	}
}
