package com.cfeindia.b2bserviceapp.controller.franchisee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cfeindia.b2bserviceapp.franchisee.service.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.recharge.mobile.dto.FranchiseeInfo;

@RequestMapping("/franchisee/**")
@Controller
public class FranchiseeController {
	@Autowired
	private FranchiseeInfoService franchiseeInfoService;
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String loginFranchisee(ModelMap model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		String userId=(String)session.getAttribute("userid");
		FranchiseeInfo franchiseeInfo=franchiseeInfoService.getFranchiseeInfo(userId);
		session.setAttribute("franchiseeInfo", franchiseeInfo);
		return "welcomeFran";
	}

}
