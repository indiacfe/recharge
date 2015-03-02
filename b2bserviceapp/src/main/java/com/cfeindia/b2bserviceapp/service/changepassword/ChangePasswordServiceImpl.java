package com.cfeindia.b2bserviceapp.service.changepassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.entity.Users;

@Service("changePassword")
@Transactional
public class ChangePasswordServiceImpl implements ChangePasswordService {
	@Autowired
	CommonService commonService;

	public boolean changePassword(String oldPassword,String newPassword,Long userId){
		boolean changed=false;
		Users users=(Users)commonService.getEntityByPrimaryKey(Users.class, userId);
		String password=users.getPassword();
		String oldPass=encodePasswordWithBCrypt(oldPassword);
		if(password.equalsIgnoreCase(oldPass)){
			users.setDisplayPassword(newPassword);
			users.setPassword(encodePasswordWithBCrypt(newPassword));
			commonService.saveEntity(users);
			changed=true;
		}
	
	
		return changed;
		
	}
	
	public static String encodePasswordWithBCrypt(String plainPassword) {
		return new MessageDigestPasswordEncoder("MD5").encodePassword(
				plainPassword, null);
	}
}
