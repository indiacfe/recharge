package com.cfeindia.b2bserviceapp.service.changepassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;

@Service("changePassword")
@Transactional
public class ChangePasswordServiceImpl implements ChangePasswordService {
	@Autowired
	CommonService commonService;
	@Autowired
	private CommonDao commonDao;

	public boolean changePassword(String oldPassword, String newPassword,
			Long userId) {
		boolean changed = false;
		Users users = (Users) commonService.getEntityByPrimaryKey(Users.class,
				userId);
		String password = users.getPassword();
		String oldPass = encodePasswordWithBCrypt(oldPassword);
		if (password.equalsIgnoreCase(oldPass)) {
			users.setDisplayPassword(newPassword);
			users.setPassword(encodePasswordWithBCrypt(newPassword));
			if (users.getResetPasswordBit() == 1) {
				users.setResetPasswordBit(0);
			}
			commonService.saveEntity(users);
			changed = true;
		}

		return changed;

	}

	public static String encodePasswordWithBCrypt(String plainPassword) {
		return new MessageDigestPasswordEncoder("MD5").encodePassword(
				plainPassword, null);
	}

	public static void main(String args[]) {
		System.out.println(encodePasswordWithBCrypt("admin123"));
	}

	public String[] ResetPassword(String userId) {
		String[] list = new String[3];

		Users user = commonDao.searchUserBasedOnIdOrMobNum(userId);
		list[1] = CyberTelUtil.getGeneratedPassword().toString();

		if (user != null) {
			user.setResetPasswordBit(1);
			user.setPassword(encodePasswordWithBCrypt(list[1]));
			user.setDisplayPassword(list[1]);
			list[0] = user.getUserDetail().getEmailId();
			commonDao.userUpdate(user);
			list[2] = user.getUsername();

		}

		return list;
	}

}
