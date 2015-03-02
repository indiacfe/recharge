package com.cfeindia.b2bserviceapp.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.service.changepassword.ChangePasswordService;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Controller
public class AboutCyberTelController {
	@Autowired
	private JavaMailSender mailSender;
	@Value("${contactus.to.emailid}")
	private String contactUsEmailId;

	@Autowired
	SMSSendService smsSendService;

	@Autowired
	CommonService commonService;

	public String getContactUsEmailId() {
		return contactUsEmailId;
	}

	public void setContactUsEmailId(String contactUsEmailId) {
		this.contactUsEmailId = contactUsEmailId;
	}

	@Autowired
	private ChangePasswordService changePasswordService;

	@RequestMapping(value = "/aboutcybertel", method = RequestMethod.GET)
	public String aboutCyberTel() {

		return "about";
	}

	@RequestMapping(value = "/faqcybertel", method = RequestMethod.GET)
	public String faqCyberTel() {
		return "faq";
	}

	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public String contactUsCyberTel() {
		return "contactus";
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgotpassword() {

		return "forgotpassword";
	}
	
	@RequestMapping(value = "/customlogin", method = RequestMethod.GET)
	public String getLoginPage() {

		return "customlogin";
	}

	@RequestMapping(value = "/admincustomlogin", method = RequestMethod.GET)
	public String adminLogin() {

		return "admincustomlogin";
	}
	
	
	@RequestMapping(value = "/customercustomlogin", method = RequestMethod.GET)
	public String customerLogin() {

		return "customercustomlogin";
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public String sendEmail(@RequestParam String userId, ModelMap model) {

		String uid = null;
		if (userId.startsWith("D") || userId.startsWith("A")
				|| userId.startsWith("E") || userId.startsWith("R")) {
			uid = ExtractorUtil.extractIdFromString(userId);
		} else {
			uid = userId;
		}

		String emailPassword[] = changePasswordService.ResetPassword(uid);

		if (emailPassword != null) {
			// changePasswordService.ResetPassword(userId);
			boolean emailSent = false;
			try {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(emailPassword[0]);
				// message.setFrom(toEmailId);
				message.setFrom(contactUsEmailId);
				message.setSubject("Cybertel: Password has been reset");
				message.setText("Your New Password Is :-    "
						+ emailPassword[1]);
				mailSender.send(message);
				emailSent = true;
				model.addAttribute("message",
						"Your New Password is sent on your registered email id.");
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message",
						"There was an error in sending your new password to email. ");
			}

			boolean smsSent = false;
			try {
				final String mobNumber = emailPassword[2];
				final String tempMessage = "Cybertel Password has been reset Your New Password Is  "
						+ emailPassword[1];
				new Thread() {
					public void run() {
						sendSMS(mobNumber, tempMessage);
					}
				}.start();
				smsSent = true;
				model.addAttribute("message",
						"Your New Password is sent to your registered mobile number.");
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message",
						"There was an error in sending your new password to mobile number. ");
			}
			if (emailSent && smsSent)
				model.addAttribute(
						"message",
						"Your New Password is sent on your registered email id and your registered mobile number.");

		} else {

			model.addAttribute("message", "Please Enter a valid Email Id!");
		}

		return "forgotpassword";

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
