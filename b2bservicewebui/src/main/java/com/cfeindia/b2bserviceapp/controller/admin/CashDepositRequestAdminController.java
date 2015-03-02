package com.cfeindia.b2bserviceapp.controller.admin;

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

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.model.franchisee.CashDepositRequestFran;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class CashDepositRequestAdminController {

	@Autowired
	CommonService commonService;

	@Autowired
	FundTransferService fundTransferService;

	@RequestMapping(value = "/showcashdepositrequestsadmin", method = RequestMethod.GET)
	public String cashDepositRequests(
			@ModelAttribute CashDepositRequestFran cashDepositRequestFran,
			ModelMap model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userid");
		List<FranchiseeCashDepositRequest> list = commonService
				.trackCashDepositRequests();
		model.addAttribute("depositrequestlist", list);
		return "cashdepositrequestsadmin";
	}
	@RequestMapping(value = "/AdminFundTransferToFranchisee", method = RequestMethod.POST)
	public String AdminFundTransferToFranchisee(@RequestParam Double amount,
			@RequestParam Long requesterId, @RequestParam Long id,
			ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		String adminId = (String) session.getAttribute("userid");
		String generatedFranId = ExtractorUtil.generateIdFromString(
				requesterId.toString(), "R");
		String mobileNo = commonService.getUsername(adminId);
		model.addAttribute("mobileNumber", mobileNo);
		model.addAttribute("franchiseeId", generatedFranId);
		model.addAttribute("amount", amount);
		return "cashrequestranspage";
	}
//	@RequestMapping(value = "/admintofranchiseecashtransfer", method = RequestMethod.POST)
//	public String transferToFranchisee(@RequestParam String franchId,
//			@RequestParam String mobileNo, @RequestParam String amount,
//			ModelMap model, HttpServletRequest request) {
//		String id, result = "";
//		HttpSession session = request.getSession();
//		String adminId = (String) session.getAttribute("userid");
//		if (franchId.startsWith("R")) {
//			id = ExtractorUtil.extractIdFromString(franchId);
//			result = fundTransferService.fundToRetailerCurrService(
//					Long.valueOf(id), Long.valueOf(adminId),
//					Double.parseDouble(amount));
//			model.addAttribute("franchId", franchId);
//		} else {
//			result = fundTransferService.fundToRetailerCurrService(
//					Long.valueOf(franchId), Long.valueOf(adminId),
//					Double.parseDouble(amount));
//			model.addAttribute("franchId", franchId);
//		}
//		model.addAttribute("mobileNo", mobileNo);
//		model.addAttribute("amount", amount);
//		if (result.equals(CommonConstants.SUCCESS)) {
//			DistributorInfo distributorInfo = distributorInfoService
//					.distributorInfo(adminId);
//			Long uniqueId = (Long) session.getAttribute("id");
//			commonService.setStatusToSuccess(uniqueId);
//			session.setAttribute("distributorInfo", distributorInfo);
//			return "redirect:franchiseetransfersuccess";
//		} else {
//			model.addAttribute("franchiseeId", franchId);
//			model.addAttribute("mobileNumber", mobileNo);
//			model.addAttribute("error",
//					"You have insufficient balance to transfer.");
//			return "franchiseeResultHome";
//		}
//	}

	

}
