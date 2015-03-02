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
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.model.distributor.Registration;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;
import com.cfeindia.b2bserviceapp.service.admin.CompanyAccount;
import com.cfeindia.b2bserviceapp.service.distributor.RegistrationService;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class RegistrationAdminController {

	@Autowired
	RegistrationService registrationService;
	@Autowired
	private CompanyAccount company;
	@Autowired
	AdminUtilityService adminUtilityService;
	@Autowired
	private CommonService commonService;
	@Autowired
	SMSSendService smsSendService;

	@RequestMapping(value = "/registrationadmin")
	public ModelAndView userRegistration() {
		return new ModelAndView("registrationadmin", "registration",
				new Registration());
	}

	@RequestMapping(value = "/registrationproccessadmin", method = RequestMethod.POST)
	public String userRegistred(
			@ModelAttribute("registration") Registration registration,
			HttpServletRequest request, ModelMap map) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");
		Long userId = Long.parseLong(id);
		String category = (String) session.getAttribute("category");
		Double comAmount = (Double) session.getAttribute("amount");
		String pathToFollow = "registrationadmin";
		map.addAttribute(pathToFollow, registration);
		String typeOfUser = "";
		boolean balanceAvailable = false;
		if (("ROLE_DISTRIBUTOR").equalsIgnoreCase(registration
				.getRegistrationType())
				&& comAmount >= CommonConstants.DISTRIBUTOR_CREATION_CHARGE) {
			balanceAvailable = true;
			typeOfUser = "D";
			session.setAttribute("newusertype", "distributor");
		} else if (("ROLE_FRANCHISEE").equalsIgnoreCase(registration
				.getRegistrationType())
				&& comAmount >= CommonConstants.RETAILER_CREATION_CHARGE) {
			balanceAvailable = true;
			typeOfUser = "R";
			session.setAttribute("newusertype", "retailer");
		} else {
			map.addAttribute("error",
					"No Sufficient Balance for User Creation.");
			map.addAttribute(pathToFollow, registration);
		}
		if (balanceAvailable) {

			try {
				Long newUserId = registrationService
						.registerationProcessByCompany(registration, userId,
								category);
				String generatedId = ExtractorUtil.generateIdFromString(
						newUserId.toString(), typeOfUser);
				session.setAttribute("newregistratonid", generatedId);
				session.setAttribute("newmobilenum", registration.getMobileNo());
				final String mobNumber = registration.getMobileNo();
				final String tempMessage = String
						.format("Thanks for joining CyberTel family. Your Login Id: %s or %s And Password: %s Feel free to contact us at +91 9310858478, info.cybertelindia@gmail.com",
								generatedId, registration.getMobileNo(),
								registration.getPassword());

				new Thread() {
					public void run() {
						try {
							sendSMS(mobNumber, tempMessage);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();

				pathToFollow = "redirect:sucesspageadmin";
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("error",
						"User Registration failed. Please contact Administrator.");
				map.addAttribute(pathToFollow, registration);
			}
		}

		return pathToFollow;
	}

	@RequestMapping(value = "/sucesspageadmin", method = RequestMethod.GET)
	public String finalpage(HttpServletRequest request, ModelMap map) {
		HttpSession session = request.getSession();
		String[] companyInfo = company.companyInfo();
		session.setAttribute("amount", Double.parseDouble(companyInfo[0]));
		session.setAttribute("distributors", Integer.parseInt(companyInfo[1]));
		session.setAttribute("franchisees", Integer.parseInt(companyInfo[2]));
		return "sucesspageadmin";

	}

	@RequestMapping(value = "/deactivate", method = RequestMethod.POST)
	public String userDeactivate(@RequestParam String userId,
			@RequestParam int enabledisable, ModelMap model,
			@RequestParam(required = false, defaultValue = "false") boolean flag) {
		long id = Long.parseLong(ExtractorUtil.extractIdFromString(userId));
		String userRole = registrationService.userDeactivate(id, enabledisable);

		if (userRole.equalsIgnoreCase(CommonConstants.ROLE_CUSTOMER) && !flag) {
			List<UserDetailDto> userDetailDto = adminUtilityService
					.customerDetailList();
			model.addAttribute("userdetail", userDetailDto);
			return "customers";
		} else if (flag) {
			model.addAttribute("Change done successfully");
			return "successpageadmin";
		} else {
			List<UserDetailDto> userDetailDto = adminUtilityService
					.userDetailList();
			model.addAttribute("userdetail", userDetailDto);
			return "users";
		}

	}

	@RequestMapping(value = "/showuserdetails", method = RequestMethod.GET)
	public String editUser(@RequestParam String userId, ModelMap model) {

		long id = Long.parseLong(ExtractorUtil.extractIdFromString(userId));

		model.addAttribute("registration",
				registrationService.getUserDetail(id));
		model.addAttribute("id", id);
		/* registrationService.getUserDetail(id); */
		return "edituser";
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute Registration registration,
			ModelMap model) {
		String RETURN_RESULT = null;
		String res = registrationService.updateUser(registration);
		if (res.equalsIgnoreCase("success")) {
			List<UserDetailDto> userDetailDtoList = adminUtilityService
					.userDetailList();
			model.addAttribute("userdetail", userDetailDtoList);
			RETURN_RESULT = "alldistributorsandretailers";

		} else {
			long id = Long.parseLong(ExtractorUtil
					.extractIdFromString(registration.getUserId()));
			model.addAttribute("registration",
					registrationService.getUserDetail(id));
			RETURN_RESULT = "edituser";
		}
		return RETURN_RESULT;
	}

	private void sendSMS(String mobNumber, String tempMessage) {
		ThirdPartyDetailObject thirdPartyDetailObject = (ThirdPartyDetailObject) commonService
				.getEntityByPrimaryKey(ThirdPartyDetailObject.class, new Long(
						5l));
		SMSSendRequest smsSendRequest = new SMSSendRequest();
		smsSendRequest.setApiUrl(thirdPartyDetailObject.getConnectURL());
		smsSendRequest.setUserName(thirdPartyDetailObject.getToken());
		smsSendRequest.setPassword(thirdPartyDetailObject.getToken1());
		smsSendRequest.setSenderId(thirdPartyDetailObject.getToken2());
		smsSendRequest.setDestination("91" + mobNumber);
		smsSendRequest.setMessage(tempMessage);
		SMSSendResponse smsSendResponse = smsSendService
				.sendSMS(smsSendRequest);
		commonService.saveEntity(smsSendResponse);
	}

}