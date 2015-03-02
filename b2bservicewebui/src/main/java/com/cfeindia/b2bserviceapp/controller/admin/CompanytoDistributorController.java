package com.cfeindia.b2bserviceapp.controller.admin;

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
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.DistributorInfo;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.model.customer.CustomerInfo;
import com.cfeindia.b2bserviceapp.service.admin.CompanyAccount;
import com.cfeindia.b2bserviceapp.service.customer.CustomerInfoService;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorInfoService;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorSearchFranchiseeService;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;
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
	private CommonService commonService;

	@Autowired
	SMSSendService smsSendService;
	
	@Autowired
	CustomerInfoService customerInfoService;
	@Autowired
	private DistributorInfoService distributorInfoService;
	
	@Autowired
	private FranchiseeInfoService franchiseeInfoService;

	@RequestMapping(value = "/searchdistributorpage")
	public String distributorTransfer() {

		return "searchdistributorpage";

	}

	@RequestMapping(value = "/searchdistributor", method = RequestMethod.POST)
	public String searchDistributor(@RequestParam String numberOrDistId,
			ModelMap model, HttpServletRequest request) {

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
			model.addAttribute("funds",
					fundTransferService.getDistCurrBal(users.getUserId()));
			model.addAttribute("mobileNumber", users.getUsername());
			model.addAttribute("distId", users.getUserId());
			if (users.getUserDetail() != null)
				model.addAttribute("firmName", users.getUserDetail()
						.getFirmName());
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

		/*try {
			final String mobNumber = mobileNumber;
			final String tempMessage = "Cybertel Trasaction done from company to your cybertel account of amount  "
					+ amount;

			new Thread() {
				public void run() {
					sendSMS(mobNumber, tempMessage);
				};
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		return pathToFollow;
	}

	private void sendSMS(String mobileNumber, String message) {
		ThirdPartyDetailObject thirdPartyDetailObject = (ThirdPartyDetailObject) commonService
				.getEntityByPrimaryKey(ThirdPartyDetailObject.class, new Long(
						5l));
		SMSSendRequest smsSendRequest = new SMSSendRequest();
		smsSendRequest.setApiUrl(thirdPartyDetailObject.getConnectURL());
		smsSendRequest.setUserName(thirdPartyDetailObject.getToken());
		smsSendRequest.setPassword(thirdPartyDetailObject.getToken1());
		smsSendRequest.setSenderId(thirdPartyDetailObject.getToken2());
		smsSendRequest.setDestination("91" + mobileNumber);
		smsSendRequest.setMessage(message);
		SMSSendResponse smsSendResponse = smsSendService
				.sendSMS(smsSendRequest);
		commonService.saveEntity(smsSendResponse);
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
				.companyBalanceTransToDistServiceBusiness(
						Long.parseLong(distIdLong), amount,
						CommonConstants.companyName);
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
		/*try {
			final String mobNumber = mobileNumber;
			final String tempMessage = "Cybertel Trasaction of Business Bal done from company to your cybertel account of amount  "
					+ amount;
			new Thread() {
				public void run() {
					sendSMS(mobNumber, tempMessage);
				};
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		return pathToFollow;
	}

	@RequestMapping(value = "/searchretailer", method = RequestMethod.GET)
	public String searchretailerdetails() {

		return "searchretailer";

	}

	@RequestMapping(value = "/searchretailerdetail", method = RequestMethod.POST)
	public String searchRetaler(
			@RequestParam("numberOrRetailerId") String numberOrRetailerId,
			ModelMap model, HttpServletRequest request) {

		if (numberOrRetailerId != null
				&& (numberOrRetailerId.length() != 10 || numberOrRetailerId
						.startsWith("A"))) {
			model.addAttribute("error", "Not Exist " + numberOrRetailerId);
			return "searchretailer";
		}
		Users users = distributorSearchFranchiseeService
				.searchRetailerBasedOnIdOrMobNum(numberOrRetailerId);

		if (users != null) {
			model.addAttribute("reportItem", users);
			Long id = users.getUserId();
			if (CommonConstants.ROLE_CUSTOMER.equalsIgnoreCase(users
					.getUserRole().getAuthority())) {
				String generatedId = ExtractorUtil.generateIdFromString(
						id.toString(), "C");
				CustomerInfo customerInfo = customerInfoService.getCustomerInfo(id.toString());
				model.addAttribute("retailerId", generatedId);
				model.addAttribute("getB2bCurrAcAdUnitBal", 0);
				model.addAttribute("getB2bCurrAcBalance", 0);
				model.addAttribute("getCurrAcBalance",customerInfo.getCurrentBalance());
				return "retailerResultHome";
			} else if (CommonConstants.ROLE_DISTRIBUTOR.equalsIgnoreCase(users
					.getUserRole().getAuthority())) {
				String generatedId = ExtractorUtil.generateIdFromString(
						id.toString(), "D");
				DistributorInfo distributorInfo=distributorInfoService.distributorInfo(id.toString());
				
				model.addAttribute("retailerId", generatedId);
				model.addAttribute("getB2bCurrAcAdUnitBal", distributorInfo.getB2bCurrAcAdUnitBal());
				model.addAttribute("getB2bCurrAcBalance", distributorInfo.getB2bCurrAcBalance());
				model.addAttribute("getCurrAcBalance", distributorInfo.getCurrAcBalance());
				return "retailerResultHome";
			} else if (CommonConstants.ROLE_FRANCHISEE.equalsIgnoreCase(users
					.getUserRole().getAuthority())) {
				String generatedId = ExtractorUtil.generateIdFromString(
						id.toString(), "R");
				FranchiseeInfo franchiseeInfo=franchiseeInfoService.getFranchiseeInfo(id.toString());
				
				model.addAttribute("retailerId", generatedId);
				model.addAttribute("getB2bCurrAcAdUnitBal", franchiseeInfo.getB2bCurrentAdUnitBalance());
				model.addAttribute("getB2bCurrAcBalance", franchiseeInfo.getB2bCurrentBalance());
				model.addAttribute("getCurrAcBalance", 0);
				return "retailerResultHome";
			} else {
				model.addAttribute("error", "User does not exist");
				return "searchretailer";
			}

		} else {
			model.addAttribute("error", "User does not exist");
			return "searchretailer";
		}

	}
}
