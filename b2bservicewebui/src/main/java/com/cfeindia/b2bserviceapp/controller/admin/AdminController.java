package com.cfeindia.b2bserviceapp.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cfeindia.b2bserviceapp.common.CacheManager;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.service.admin.CompanyAccount;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class AdminController {
	@Autowired
	private CompanyAccount company;

	@Autowired
	CommonService commonService;
	@Autowired
	FundTransferService fundTransferService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String redirect(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String[] companyInfo = company.companyInfo();
		String id = (String) session.getAttribute("userid");
		session.setAttribute("adminId",
				ExtractorUtil.generateIdFromString(id, "A"));
		session.setAttribute("companyName", CommonConstants.companyName);
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

	@RequestMapping(value = "/refreshcustomerslist", method = RequestMethod.GET)
	public String refreshCustmerUserNameList() {
		CacheManager.refreshCustmerUserName();
		return "adminHome";
	}

}
