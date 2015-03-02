package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.admin.service.AdminUtilityService;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;

@RequestMapping("/admin/**")
@Controller
public class AllDistributorsAndRetailers {
	@Autowired
	AdminUtilityService adminUtilityService;

	@Autowired
	CommonService commonService;

	@RequestMapping(value = "/alldistributorsandretailers", method = RequestMethod.GET)
	public String showSearchPage() {
		return "alldistributorsandretailers";
	}

	@RequestMapping(value = "/distributorsandretailerslist", method = RequestMethod.GET)
	public String showDistributorsAndRetailers(@RequestParam String sel,
			ModelMap model) {

		if (sel.equals("Distributors")) {

		} else if (sel.equals("Retailers")) {

		} else {
			List<UserDetailDto> userDetailDtoList = adminUtilityService
					.userDetailList();
			model.addAttribute("userdetail", userDetailDtoList);
			return "alldistributorsandretailers";
		}
		return "alldistributorsandretailers";
	}

	@RequestMapping(value = "/showuserdetails", method =requ)
	public String showUserDetails(@RequestParam String userId, ModelMap model) {
		
		return "edituser";
	}

}
