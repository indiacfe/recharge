package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;

@RequestMapping("/admin/**")
@Controller
public class UserDetailController {
	@Autowired
	private AdminUtilityService adminUtilityService;

	@RequestMapping(value = "/userdetail", method = RequestMethod.GET)
	public String userDetail(
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			ModelMap model) {

		Number number = adminUtilityService.countUserList();
		int totalPage;
		int remainder = number.intValue() % 50;
		int divide = number.intValue() / 50;
		if (remainder <= 0) {
			totalPage = divide;
		} else {
			totalPage = divide + 1;
		}
		List<UserDetailDto> userDetailDto = adminUtilityService
				.userDetailList(pageNo);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("srNo", (pageNo - 1) * 50);
		model.addAttribute("userdetail", userDetailDto);
		model.addAttribute("noOfPage", 10);
		model.addAttribute("currentPage", pageNo);
		return "users";
	}

	@RequestMapping(value = "/customerdetail")
	public String customerDetail(ModelMap model) {
		List<UserDetailDto> userDetailDto = adminUtilityService
				.customerDetailList();
		model.addAttribute("userDetailDto", new UserDetailDto());
		model.addAttribute("userdetail", userDetailDto);
		return "customers";
	}

}
