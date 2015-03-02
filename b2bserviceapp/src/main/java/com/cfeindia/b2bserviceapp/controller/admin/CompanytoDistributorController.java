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
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorInfoService;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorSearchFranchiseeService;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class CompanytoDistributorController {

	@Autowired
	private CompanyAccount company;

	@Autowired
	DistributorSearchFranchiseeService distributorSearchFranchiseeService;

	@Autowired
	FundTransferService fundTransferService;

	@Autowired
	private DistributorInfoService distributorInfoService;

	@RequestMapping(value = "/searchdistributorpage")
	public String distributorTransfer() {

		return "searchdistributorpage";

	}

	@RequestMapping(value = "/searchdistributor", method = RequestMethod.POST)
	public String searchDistributor(@RequestParam String numberOrDistId,
			ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (numberOrDistId != null
				&& (numberOrDistId.startsWith("R")
						|| numberOrDistId.startsWith("E")
						|| numberOrDistId.length() != 10 || numberOrDistId
							.startsWith("A"))) {
			model.addAttribute("error", "Not Exist " + numberOrDistId);
			return "searchdistributorpage";
		}
		Users users = distributorSearchFranchiseeService
				.searchDistributorBasedOnIdOrMobNum(numberOrDistId);

		if (users != null) {
			model.addAttribute("mobileNumber", users.getUsername());
			model.addAttribute("distId", users.getUserId());
			if(users.getUserDetail()!= null)
				model.addAttribute("firmName", users.getUserDetail().getFirmName());
			else 
				model.addAttribute("firmName", "");
		} else {
			model.addAttribute("error", "Not Exist" + numberOrDistId);
			return "searchdistributorpage";
		}
		if (numberOrDistId.startsWith("D")) {
			model.addAttribute("distId", numberOrDistId);
		} else {
			Long id = users.getUserId();
			String generatedId = ExtractorUtil.generateIdFromString(
					id.toString(), "D");
			model.addAttribute("distId", generatedId);
		}

		return "distributorResultHome";
	}

	@RequestMapping(value = "/transfertodistributor", method = RequestMethod.POST)
	public String transferToDistributor(@RequestParam String distId,
			@RequestParam String mobileNumber, @RequestParam double amount,
			HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String distIdLong = ExtractorUtil.extractIdFromString(distId);
		String result = fundTransferService
				.companyBalanceTransToDistService(Long.parseLong(distIdLong),
						amount, CommonConstants.companyName);
		String pathToFollow = "redirect:distributortransfersuccessadmin";
		if (CommonConstants.FAILURE.equalsIgnoreCase(result)) {
			pathToFollow = "distributorResultHomeAdmin";
		}
		String[] companyInfo = company.companyInfo();
		session.setAttribute("amount", Double.parseDouble(companyInfo[0]));
		session.setAttribute("distributors", Integer.parseInt(companyInfo[1]));
		session.setAttribute("franchisees", Integer.parseInt(companyInfo[2]));
		session.setAttribute("mobileNumber", mobileNumber);
		session.setAttribute("distId", distId);
		session.setAttribute("transferamount", amount);
		return pathToFollow;
	}

	@RequestMapping(value = "/distributortransfersuccessadmin")
	public String finalPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.getAttribute("distId");
		session.getAttribute("mobileNumber");
		session.getAttribute("transferamount");
		return "distributorTransferSuccessAdmin";

	}

	@RequestMapping(value = "/transfertodistributorB2b", method = RequestMethod.POST)
	public String transferToDistributorBusiness(@RequestParam String distId,
			@RequestParam String mobileNumber, @RequestParam double amount,
			HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String distIdLong = ExtractorUtil.extractIdFromString(distId);
		String result = fundTransferService
				.companyBalanceTransToDistServiceBusiness(Long.parseLong(distIdLong),
						amount, CommonConstants.companyName);
		String pathToFollow = "redirect:distributortransfersuccessadmin";
		if (CommonConstants.FAILURE.equalsIgnoreCase(result)) {
			pathToFollow = "distributorResultHomeAdmin";
		}
		String[] companyInfo = company.companyInfo();
		session.setAttribute("amount", Double.parseDouble(companyInfo[0]));
		session.setAttribute("distributors", Integer.parseInt(companyInfo[1]));
		session.setAttribute("franchisees", Integer.parseInt(companyInfo[2]));
		session.setAttribute("mobileNumber", mobileNumber);
		session.setAttribute("distId", distId);
		session.setAttribute("transferamount", amount);
		return pathToFollow;
	}
}
