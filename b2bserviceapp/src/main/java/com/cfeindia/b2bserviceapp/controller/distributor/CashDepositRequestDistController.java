package com.cfeindia.b2bserviceapp.controller.distributor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.franchisee.model.CashDepositRequestFran;
import com.cfeindia.b2bserviceapp.franchisee.service.CashDepositRequestFranchiseeService;
import com.cfeindia.b2bserviceapp.recharge.mobile.dto.DistributorInfo;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorInfoService;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/distributor/**")
@Controller
public class CashDepositRequestDistController {

	@Autowired
	CommonService commonService;

	@Autowired
	FundTransferService fundTransferService;

	@Autowired
	DistributorInfoService distributorInfoService;

	@Autowired
	CashDepositRequestFranchiseeService cashDepositRequestFranchiseeService;

	@SuppressWarnings("unused")
	@RequestMapping(value = "/distshowcashdepositrequests", method = RequestMethod.GET)
	public String cashDepositRequests(
			@ModelAttribute CashDepositRequestFran cashDepositRequestFran,
			ModelMap model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userid");
		List<FranchiseeCashDepositRequest> list = commonService
				.trackCashDepositRequests();
		model.addAttribute("depositrequestlist", list);
		return "distcashdepositrequests";
	}

	@RequestMapping(value = "/showTransferPage", method = RequestMethod.POST)
	public String ShowTransferPage(@RequestParam Double amount,
			@RequestParam Long requesterId, @RequestParam Long id,
			ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		String distributorId = (String) session.getAttribute("userid");
		String generatedFranId = ExtractorUtil.generateIdFromString(
				requesterId.toString(), "R");
		String mobileNo = commonService.getUsername(distributorId);
		model.addAttribute("mobileNumber", mobileNo);
		model.addAttribute("distId", distributorId);
		model.addAttribute("franchiseeId", generatedFranId);
		model.addAttribute("amount", amount);
		return "cashrequestranspage";
	}

	@RequestMapping(value = "/depositrequesttransfertofranchisee", method = RequestMethod.POST)
	public String transferToFranchisee(@RequestParam String franchId,
			@RequestParam String mobileNo, @RequestParam String amount,
			ModelMap model, HttpServletRequest request) {
		String id, result = "";
		HttpSession session = request.getSession();
		String distributorId = (String) session.getAttribute("userid");
		if (franchId.startsWith("R")) {
			id = ExtractorUtil.extractIdFromString(franchId);
			result = fundTransferService.fundToRetailerCurrService(
					Long.valueOf(id), Long.valueOf(distributorId),
					Double.parseDouble(amount));
			model.addAttribute("franchId", franchId);
		} else {
			result = fundTransferService.fundToRetailerCurrService(
					Long.valueOf(franchId), Long.valueOf(distributorId),
					Double.parseDouble(amount));
			model.addAttribute("franchId", franchId);
		}
		model.addAttribute("mobileNo", mobileNo);
		model.addAttribute("amount", amount);
		if (result.equals(CommonConstants.SUCCESS)) {
			DistributorInfo distributorInfo = distributorInfoService
					.distributorInfo(distributorId);
			Long uniqueId = (Long) session.getAttribute("id");
			System.out.println(uniqueId);
			commonService.setStatusToSuccess(uniqueId);
			session.setAttribute("distributorInfo", distributorInfo);
			return "redirect:franchiseetransfersuccess";
		} else {
			model.addAttribute("franchiseeId", franchId);
			model.addAttribute("mobileNumber", mobileNo);
			model.addAttribute("error",
					"You have insufficient balance to transfer.");
			return "franchiseeResultHome";
		}
	}

	@RequestMapping(value = "/showallcashdepositrequestsdist", method = RequestMethod.GET)
	public String showallcashDepositRequests(ModelMap model,
			HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userid");
		List<FranchiseeCashDepositRequest> list = commonService
				.trackAllCashDepositRequests();
		model.addAttribute("depositrequestlist", list);
		return "allcashdepositrequestsdist";
	}

	@RequestMapping(value = "/cashdepositrequestdist")
	public ModelAndView cashDeposit() {

		return new ModelAndView("cashdepositdist", "command",
				new CashDepositRequestFran());
	}

	@RequestMapping(value = "/submitrequestdist", method = RequestMethod.POST)
	public String submitRequest(
			@ModelAttribute CashDepositRequestFran cashDepositRequestFran,
			ModelMap modelMap, HttpServletRequest req) {
		String userId = (String) req.getSession().getAttribute("userid");
		try {

			cashDepositRequestFranchiseeService.saveCashDepositRequest(userId,
					cashDepositRequestFran);
			modelMap.addAttribute("success", "sent your request");
			return "successpagefran";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

}
