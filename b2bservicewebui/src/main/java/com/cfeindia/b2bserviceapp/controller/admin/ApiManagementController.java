package com.cfeindia.b2bserviceapp.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.model.distributor.Registration;
import com.cfeindia.b2bserviceapp.service.admin.ApiRegisterService;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Controller
@RequestMapping("/admin/**")
public class ApiManagementController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private ApiRegisterService apiRegisterService;
	@Autowired
	private SMSSendService smsSendService;

	@RequestMapping(value = "/apiregister")
	public String userRegistration(ModelMap model) {

		model.addAttribute("registration", new Registration());
		return "registrationapi";
	}

	@RequestMapping(value = "/apiregister", method = RequestMethod.POST)
	public String userRegistred(
			@ModelAttribute("registration") Registration registration,
			HttpServletRequest request, ModelMap map) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");

		Long userId = Long.parseLong(id);
		System.out.println(userId);
		String pathToFollow = "registrationapi";
		map.addAttribute(pathToFollow, registration);
		try {
			Long newUserId = apiRegisterService.registerationProcessByCompany(
					registration, userId, CommonConstants.ROLE_ADMIN);
			String genId = ExtractorUtil.generateIdFromString(
					newUserId.toString(), "C");
			final String mobNumber = registration.getMobileNo();
			final String tempMessage = String
					.format("Thanks for joining CyberTel family. Your Login Id: %s or %s And Password: %s Feel free to contact us at +91 9310858478, info.cybertelindia@gmail.com",
							registration.getMobileNo(), genId,
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

			map.addAttribute("newregistratonid", genId);
			map.addAttribute("newusertype", CommonConstants.ROLE_CUSTOMER);
			map.addAttribute("newmobilenum", mobNumber);
			pathToFollow = "sucesspageadmin";

		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute("error",
					"User Registration failed. Please contact Administrator.");
			map.addAttribute(pathToFollow, registration);
		}

		return pathToFollow;

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
