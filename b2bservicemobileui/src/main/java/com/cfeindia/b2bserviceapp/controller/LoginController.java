package com.cfeindia.b2bserviceapp.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.LoginSuccessDto;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;

@Controller
public class LoginController {
	@Autowired
	CommonService commonservice;
	@Autowired
	FundTransferService fundTransferService;
	@Autowired
	private FranchiseeInfoService franchiseeInfoService;
	private static String XML_VIEW_NAME= "loginController";
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	@Secured ({"ROLE_FRANCHISEE"})
	public ModelAndView login(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Collection<GrantedAuthority> authorities = auth.getAuthorities();
		boolean success = false;
		LoginSuccessDto loginSuccessDto=new LoginSuccessDto();		
		for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equalsIgnoreCase(
						CommonConstants.ROLE_FRANCHISEE)) {
					success = true;
				}
		}
		StringBuilder returnMessage = new StringBuilder("");
		if(success) {		
			returnMessage.append(auth.getName()).append(":");
			int id=commonservice.getUserId(auth.getName());
			returnMessage.append(id);
			loginSuccessDto.setUserId(Long.valueOf(id).toString());
			loginSuccessDto.setUserName(auth.getName());
			FranchiseeCurrBal franchiseeCurrBal=fundTransferService.getCurrentDetail((long) id);
			loginSuccessDto.setCurrentBalance(franchiseeCurrBal.getB2bcurrbal().toString());
			FranchiseeInfo franchiseeInfo=franchiseeInfoService.getFranchiseeInfo(Long.valueOf(id).toString());
			loginSuccessDto.setFirmName(franchiseeInfo.getFirmName());
			loginSuccessDto.setFranId(franchiseeInfo.getFranId());
			loginSuccessDto.setName(franchiseeInfo.getName());
			return new ModelAndView(XML_VIEW_NAME, "userDetails",loginSuccessDto);
		}	
		else
		{
			loginSuccessDto.setUserId("");
			loginSuccessDto.setUserName("");
			return new ModelAndView(XML_VIEW_NAME, "userDetails",loginSuccessDto);
		}	
	}
	
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "welcome";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "welcome";

	}
	@RequestMapping(value ="/admin", method = RequestMethod.GET)
	public String adminLogin(ModelMap model) {
		
		model.addAttribute("login", "true");

		return "admin";

	}
}