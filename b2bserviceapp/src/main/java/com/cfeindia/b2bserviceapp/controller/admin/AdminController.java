package com.cfeindia.b2bserviceapp.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.admin.service.CompanyAccount;
import com.cfeindia.b2bserviceapp.common.CacheManager;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorSearchFranchiseeService;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class AdminController {
	@Autowired
	CompanyAccount company;

	@Autowired
	CommonService commonService;

	@Autowired
	FundTransferService fundTransferService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String redirect(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String[] companyInfo = company.companyInfo();
		session.setAttribute("amount", Double.parseDouble(companyInfo[0]));
		session.setAttribute("distributors", Integer.parseInt(companyInfo[1]));
		session.setAttribute("franchisees", Integer.parseInt(companyInfo[2]));
		return "adminHome";
	}

	@RequestMapping(value = "/refreshthirdpartyapi", method = RequestMethod.GET)
	public String refreshThirdPartyApis() {
		CacheManager.refreshThirdpartyAPIDetails();
		return "adminHome";
	}

	@RequestMapping(value = "/refreshthirdpartyoplist", method = RequestMethod.GET)
	public String refreshThirdpartyOperatorList() {
		CacheManager.refreshThirdpartyOperatorList();
		return "adminHome";
	}

}
