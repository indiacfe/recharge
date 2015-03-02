package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cfeindia.b2bserviceapp.admin.service.AdminUtilityService;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;

@RequestMapping("/admin/**")
@Controller
public class UserDetailController {
	@Autowired
	AdminUtilityService adminUtilityService;
	
	@RequestMapping(value="/userdetail")
	public String userDetail(ModelMap model)
	{
		List<UserDetailDto> userDetailDto=adminUtilityService.userDetailList();
		model.addAttribute("userdetail",  userDetailDto);
		return "users";
	}

}
