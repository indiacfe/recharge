package com.cfeindia.b2bserviceapp.controller.distributor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.DistributorInfo;
import com.cfeindia.b2bserviceapp.model.distributor.Registration;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorInfoService;
import com.cfeindia.b2bserviceapp.service.distributor.RegistrationService;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/distributor/**")
@Controller
public class RegistrationController {
	@Autowired
	RegistrationService registrationService;
	@Autowired
	private DistributorInfoService distributorInfoService;

	@Autowired
	SMSSendService smsSendService;

	@Autowired
	CommonService commonService;

	public RegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@RequestMapping(value = "/registration")
	public ModelAndView userRegistration() {
		return new ModelAndView("registration", "registration",
				new Registration());
	}

	@RequestMapping(value = "/registrationProcess", method = RequestMethod.POST)
	public String registrationProcess(
			@ModelAttribute("registration") Registration registration,
			ModelMap map, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");
		DistributorInfo distributorInfo = (DistributorInfo) session
				.getAttribute("distributorInfo");
		String pathToFollow = "registration";

		map.addAttribute("registration", registration);
		boolean balanceAvailable = false;
		String typeOfUser = "";
		if (("ROLE_DISTRIBUTOR").equalsIgnoreCase(registration
				.getRegistrationType())
				&& distributorInfo.getCurrAcBalance() >= CommonConstants.DISTRIBUTOR_CREATION_CHARGE) {
			balanceAvailable = true;
			typeOfUser = "D";
			session.setAttribute("newusertype", "distributor");
		} else if (("ROLE_FRANCHISEE").equalsIgnoreCase(registration
				.getRegistrationType())
				&& distributorInfo.getCurrAcBalance() >= CommonConstants.RETAILER_CREATION_CHARGE) {
			balanceAvailable = true;
			typeOfUser = "R";
			session.setAttribute("newusertype", "retailer");
		} else {
			map.addAttribute("error",
					"No Sufficient Balance for User Creation.");
			map.addAttribute(pathToFollow, registration);
		}
		if (balanceAvailable) {
			Long currDistid = Long.parseLong(id);
			try {
				Long newUserId = registrationService.registerationProcess(
						registration, currDistid);
				String newid = newUserId.toString();
				String generatedId = ExtractorUtil.generateIdFromString(newid,
						typeOfUser);
				// Thanks for joining Cybertel family. Your Login Id: <UserId>
				// or <Mobile Num> And Password: <Password> Feel free to contact
				// us at +91 9310858478, info.cybertelindia@gmail.com
				session.setAttribute("newregistratonid", generatedId);
				session.setAttribute("newmobilenum", registration.getMobileNo());

				final String mobNumber = registration.getMobileNo();
				final String tempMessage = String
						.format("Thanks for joining CyberTel family. Your Login Id: %s or %s And Password: %s Feel free to contact us at +91 9310858478, info.cybertelindia@gmail.com",
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

				String userId = (String) session.getAttribute("userid");
				DistributorInfo distributorInforamation = distributorInfoService
						.distributorInfo(userId);
				session.setAttribute("distributorInfo", distributorInforamation);

				pathToFollow = "redirect:sucesspage";
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("error",
						"Pls try another mobile no! This mobile no is already registered");
				map.addAttribute(pathToFollow, registration);
			}
		}
		return pathToFollow;

	}

	@RequestMapping(value = "/sucesspage", method = RequestMethod.GET)
	public String finalpage() {

		return "sucesspage";

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
	};

	
}
