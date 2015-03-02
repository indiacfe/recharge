package com.cfeindia.b2bserviceapp.service.changepassword;

public interface ChangePasswordService {
	public boolean  changePassword(String oldPassword,String newPassword,Long userId);

}
