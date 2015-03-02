package com.cfeindia.b2bserviceapp.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.admin.CompanyAccount;
import com.cfeindia.b2bserviceapp.service.admin.CustomerFundTransferService;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Controller
@RequestMapping("/admin/**")
public class CustomerFundTransferController {
	@Autowired
	private CustomerFundTransferService customerFundTransferService;

	@Autowired
	private CompanyAccount company;

	@Autowired
	SMSSendService smsSendService;

	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/searchcustomerpage")
	public String customerTransfer() {

		return "searchcustomerpage";

	}

	@RequestMapping(value = "/searchcustomerdetailpage")
	public String customerFundTransfer(@RequestParam String numberOrcustomerId,
			ModelMap model) {
		if (numberOrcustomerId != null
				&& (numberOrcustomerId.startsWith("R")
						|| numberOrcustomerId.startsWith("E")
						|| numberOrcustomerId.length() != 10 || numberOrcustomerId
							.startsWith("A"))) {
			model.addAttribute("error", "Not Exist " + numberOrcustomerId);
			return "searchcustomerpage";
		}

		Users users = customerFundTransferService
				.getCustomer(numberOrcustomerId);
		if (users != null) {
			model.addAttribute("funds", customerFundTransferService
					.getCustCurrBal(users.getUserId()));
			model.addAttribute("mobileNumber", users.getUsername());
			model.addAttribute("custId", users.getUserId());
			if (users.getUserDetail() != null)
				model.addAttribute("firmName", users.getUserDetail()
						.getFirmName());
			else
				model.addAttribute("firmName", "");
		} else {
			model.addAttribute("error", "Not Exist " + numberOrcustomerId);
			return "searchcustomerpage";
		}
		if (numberOrcustomerId.startsWith("C")) {
			model.addAttribute("custId", numberOrcustomerId);
		} else {
			Long id = users.getUserId();
			String generatedId = ExtractorUtil.generateIdFromString(
					id.toString(), "C");
			model.addAttribute("custId", generatedId);
		}

		return "customerResultHome";

	}

	@RequestMapping(value = "/transfertocustomer", method = RequestMethod.POST)
	public String transferToCustomer(@RequestParam String custId,
			@RequestParam String mobileNumber, @RequestParam double amount,
			HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String custIdLong = ExtractorUtil.extractIdFromString(custId);
		Long userId = Long.parseLong((String) session.getAttribute("userid"));
		String result = customerFundTransferService
				.companyBalanceTransToCustService(Long.parseLong(custIdLong),
						amount, CommonConstants.companyName, userId);
		String pathToFollow = "redirect:customertransfersuccessadmin";
		if (CommonConstants.FAILURE.equalsIgnoreCase(result)) {
			pathToFollow = "distributorResultHomeAdmin";
		}
		String[] companyInfo = company.companyInfo();
		session.setAttribute("amount", Double.parseDouble(companyInfo[0]));
		session.setAttribute("customers", Integer.parseInt(companyInfo[1]));
		session.setAttribute("franchisees", Integer.parseInt(companyInfo[2]));
		session.setAttribute("mobileNumber", mobileNumber);
		session.setAttribute("custId", custId);
		session.setAttribute("transferamount", amount);

		try {
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
		}

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

	@RequestMapping(value = "/customertransfersuccessadmin")
	public String finalPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.getAttribute("custId");
		session.getAttribute("mobileNumber");
		session.getAttribute("transferamount");
		return "customerTransferSuccessAdmin";
	}

	@RequestMapping(value = "/deductamountapi", method = RequestMethod.POST)
	public ModelAndView deductAmoutFromApi(
			@ModelAttribute("reportItem") UserDetailDto userDetailDto) {
		return new ModelAndView("deductamountapi", "userdetail", userDetailDto);
	}

	@RequestMapping(value = "/deductamountapiaccount", method = RequestMethod.POST)
	public String deductAmountFromApiAccount(
			@ModelAttribute("reportItem") UserDetailDto userDetailDto,
			@RequestParam("amount") double amount,
			@RequestParam("remark") String remark, Model model) {

		String result = customerFundTransferService.deductAmountFromApiAccount(
				Long.parseLong(ExtractorUtil.extractIdFromString(userDetailDto
						.getUserId())), amount, remark);

		if (CommonConstants.SUCCESS.equalsIgnoreCase(result)) {
			model.addAttribute("success", "decuct amount");
			return "deductamountsuccess";
		} else {
			model.addAttribute("Error", "some error occured, please try again");
			model.addAttribute("userdetail", userDetailDto);
			return "deductamountapi";
		}

	}
}
