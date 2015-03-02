package com.cfeindia.b2bserviceapp.controller.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.admin.CompanyAccount;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorSearchFranchiseeService;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/employee/**")
@Controller
public class EmployeeController {
	
	@Autowired
	FundTransferService fundTransferService;
	@Autowired
	CommonService commonService;
	@Autowired
	CompanyAccount company;
	@Autowired
	DistributorSearchFranchiseeService distributorSearchFranchiseeService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String loginEmployee(ModelMap model,HttpServletRequest request) {
		String[] companyInfo=company.companyInfo();
		Double companyAc=company.companyAccount();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("userid");
		session.setAttribute("empid", ExtractorUtil.generateIdFromString(id, "E"));
		session.setAttribute("companyName", CommonConstants.companyName);
		session.setAttribute("amount", Double.parseDouble(companyInfo[0]));
		session.setAttribute("distributors", Integer.parseInt(companyInfo[1]));
		session.setAttribute("franchisees",  Integer.parseInt(companyInfo[2]));
		return "employeeHome";

	}

	@RequestMapping(value = "/searchdistributorpage", method = RequestMethod.GET)
	public String searchDistributorPage(ModelMap model) {
		
		return "searchDistributorPage";

	}
	
	@RequestMapping(value = "/searchdistributor", method = RequestMethod.GET)
	public String searchDistributor(@RequestParam String numberOrDistId, ModelMap model) 
	{
		
		Users users =distributorSearchFranchiseeService.searchUserBasedOnIdOrMobNum(numberOrDistId);
		if(users != null) {
			model.addAttribute("mobileNumber", users.getUsername());
			model.addAttribute("distId", users.getUserId());
		} else {
			model.addAttribute("error", "Not Exists " + numberOrDistId);
			return "searchDistributorPage";
		}
		
		return "distributorResultHome";

	}
	
	@RequestMapping(value = "/transfertodistributor", method = RequestMethod.POST)
	public String transferToDistributor(@RequestParam Long distId, @RequestParam String mobileNumber, @RequestParam double amount,  ModelMap model) 
	{
		model.addAttribute("mobileNumber", mobileNumber);
		model.addAttribute("distId", distId);
		model.addAttribute("amount", amount);
		fundTransferService.companyBalanceTransToDistService(distId, amount, CommonConstants.companyName);
		return "distributorTransferSuccess";

	}

}