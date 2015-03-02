package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.dto.RemittanceUserDto;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.MoneyTransferOTPGenerationTransactionService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.MoneyTransferTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/franchisee/**")
@Controller
public class MoneytransRegistrationController {
	@Autowired
	MoneyTransferTransactionService moneyTransferTransactionService;
	@Autowired
	MoneyTransferOTPGenerationTransactionService transferOTPGenerationTransactionService;

	@RequestMapping(value = "/moneytransferhome", method = RequestMethod.GET)
	public String remittanceUserRegistration(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		String userName = ((String) session.getAttribute("userName"));
		String pathToFollow = null;
		
		if (userId.equalsIgnoreCase("R000000105")
				|| userName.equals("9310858478")) {
			pathToFollow = "moneytransferhome";
		} else {
			pathToFollow = "welcomeFran";
		}
		return pathToFollow;
	}

	@RequestMapping(value = "/registrationotp", method = RequestMethod.GET)
	public String registrationotp(Model model) {
		model.addAttribute("remittanceuserdto", new RemittanceUserDto());
		return "registrationotp";
	}

	@RequestMapping(value = "/remittanceotp", method = RequestMethod.GET)
	public String remittanceotp(Model model) {
		model.addAttribute("remittanceuserdto", new RemittanceUserDto());
		return "remittanceotp";
	}

	@RequestMapping(value = "/moneytransferregistration", method = RequestMethod.GET)
	public String remittanceUserRegistration(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto reUserDto,
			HttpServletRequest request, Model model) {
		model.addAttribute("mobileno", reUserDto.getMobileNO());
		model.addAttribute("remittanceuserdto", reUserDto);
		return "moneytransferregistration";
	}

	@RequestMapping(value = "enterotp", method = RequestMethod.POST)
	public ModelAndView remittanceSaveUserDetail(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {

		System.out.println("Data");
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");

		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));
		transactionTransportBean.setOperator("USER_REGISTRATION");
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		rUserDto.setUrlStatus("getDataFromURL");
		transactionTransportBean.setMobileNo(rUserDto.getMobileNO().trim());
		transactionTransportBean.setRetailerId(userId);
		transactionTransportBean.setRemittanceUserDto(rUserDto);
		try {
			transferOTPGenerationTransactionService
					.getOTPRegistrationService(transactionTransportBean);
		} catch (Exception e) {
			e.printStackTrace();
			transactionTransportBean.setMessage("System Error");
		}
		System.out.println("transactionTransportBean.getErrorCode() >>>>"
				+ transactionTransportBean.getErrorCode());
		if (transactionTransportBean.getErrorCode() > 0) {
			String errorMsg = "";
			if (transactionTransportBean.getStdCode().equalsIgnoreCase("20")) {
				model.addAttribute("alert", errorMsg);
				session.setAttribute("transactionid",
						transactionTransportBean.getMessage());
				model.addAttribute("moneytransfer", transactionTransportBean);
				pathToFollow = "redirect:resendotprequest";
			} else {
				if (transactionTransportBean.getMessage() != null) {
					errorMsg += transactionTransportBean.getMessage();
				}
				model.addAttribute("Error", errorMsg
						+ " Registration Failed Please contact admin.");
				System.out.println("error on controller");
				pathToFollow = "moneytransferregistration";
			}
		} else {
			List<Object> l = new ArrayList<Object>();
			l.add(rUserDto.getMobileNO());
			l.add(transactionTransportBean.getThirdpartytransid());
			l.add(transactionTransportBean.getRemittanceUserDto().getCardno());
			session.setAttribute("list", l);
			session.setAttribute("transactionid",
					transactionTransportBean.getThirdpartytransid());
			session.setAttribute("cardno", transactionTransportBean
					.getRemittanceUserDto().getCardno());
			System.out.println("Success on controller");
			pathToFollow = "redirect:enterOTP";
		}
		return new ModelAndView(pathToFollow, "remittanceuserdto", rUserDto);
	}

	@RequestMapping(value = "/enterOTP", method = RequestMethod.GET)
	public String enterotp(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("mobileNO",
				(String) session.getAttribute("mobileNO"));
		model.addAttribute("alert",
				"Please check OTP sent on your mobile no. And Enter OTP here.");
		return "enterOTP";
	}

	@RequestMapping(value = "/regenerateotp", method = RequestMethod.GET)
	public String regenerateotp(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {

		return "enterOTP";
	}

	@RequestMapping(value = "/submitotp", method = RequestMethod.POST)
	public String submitRegistrationOTP(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String) session.getAttribute("cardno"));
		rUserDto.setUrlStatus("verifyOTPURL");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setThirdpartytransid((String) session
				.getAttribute("transactionid"));
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setOperator("USER_REGISTRATION");
		transactionTransportBean.setRetailerId(userId);
		transactionTransportBean.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));
		transactionTransportBean.setRemittanceUserDto(rUserDto);
		try {
			transferOTPGenerationTransactionService
					.getOTPRegistrationService(transactionTransportBean);
		} catch (Exception e) {
			e.printStackTrace();
			transactionTransportBean.setMessage("System Error");
		}
		System.out.println("transactionTransportBean.getErrorCode() >>>>"
				+ transactionTransportBean.getErrorCode());
		if (transactionTransportBean.getErrorCode() > 0) {
			String errorMsg = "";
			if (transactionTransportBean.getMessage() != null) {
				errorMsg += transactionTransportBean.getMessage();
			}
			model.addAttribute("alert", errorMsg);
			System.out.println("error on controller");
			pathToFollow = "enterOTP";
		} else {
			model.addAttribute("bean",
					transactionTransportBean.getRemittanceUserDto());
			session.setAttribute("mobileNO", rUserDto.getMobileNO());
			model.addAttribute("alert",
					"You are successfully registered on Money Transfer");
			pathToFollow = "redirect:userdetails";
		}

		return pathToFollow;
	}

	@RequestMapping(value = "/userdetails", method = RequestMethod.GET)
	public String showDetails(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {
		return "showdetails";
	}

	@RequestMapping(value = "/resendotprequest", method = RequestMethod.GET)
	public String resendOTPRequest(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		rUserDto.setUrlStatus("resendOTPURL");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		rUserDto.setTransactionId((String) session
				.getAttribute("transactionid"));
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setOperator("USER_RESEND_OTP");
		transactionTransportBean.setRetailerId(userId);
		transactionTransportBean.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));
		transactionTransportBean.setRemittanceUserDto(rUserDto);
		try {
			transferOTPGenerationTransactionService
					.getOTPRegistrationService(transactionTransportBean);
		} catch (Exception e) {
			e.printStackTrace();
			transactionTransportBean.setMessage("System Error");
		}
		System.out.println("transactionTransportBean.getErrorCode() >>>>"
				+ transactionTransportBean.getErrorCode());
		if (transactionTransportBean.getErrorCode() > 0) {
			String errorMsg = "";
			if (transactionTransportBean.getMessage() != null) {
				errorMsg += transactionTransportBean.getMessage();
			}
			model.addAttribute("alert", errorMsg);
			pathToFollow = "registrationotp";
		} else {
			model.addAttribute("alert",
					"Please check OTP sent on your mobile no. And Enter OTP here.");
			pathToFollow = "redirect:enterOTP";
		}

		return pathToFollow;
	}

	/* LOG IN ************************ */
	@RequestMapping(value = "/moneytransferlogin", method = RequestMethod.GET)
	public String enterremitanceotp(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		String userName = ((String) session.getAttribute("userName"));
		String pathToFollow = null;

		if (userId.equalsIgnoreCase("R000000105")
				|| userName.equals("9310858478")) {
			pathToFollow = "enterremitanceotp";
		} else {
			pathToFollow = "welcomeFran";
		}
		return pathToFollow;
	}

	@RequestMapping(value = "/getloginotp", method = RequestMethod.POST)
	public String loginGetOtp(@RequestParam String mobileNO, String pin,
			Model model, HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		session.setAttribute("mobileNo", mobileNO);
		RemittanceUserDto rUserDto = new RemittanceUserDto();

		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();

		rUserDto.setPin(pin);
		rUserDto.setMobileNO(mobileNO);
		rUserDto.setUrlStatus("senderLoginGetOTPURL");
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setOperator("USER_LOGIN");
		transactionTransportBean.setRetailerId(userId);
		transactionTransportBean.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));
		transactionTransportBean.setRemittanceUserDto(rUserDto);
		try {
			transferOTPGenerationTransactionService
					.getOTPRegistrationService(transactionTransportBean);
		} catch (Exception e) {
			e.printStackTrace();
			transactionTransportBean.setMessage("System Error");
		}
		System.out.println("transactionTransportBean.getErrorCode() >>>>"
				+ transactionTransportBean.getErrorCode());
		if (transactionTransportBean.getErrorCode() > 0) {
			String errorMsg = "";
			if (transactionTransportBean.getStdCode().equalsIgnoreCase("20")) {
				model.addAttribute("alert", errorMsg);
				session.setAttribute("transactionid",
						transactionTransportBean.getMessage());
				model.addAttribute("moneytransfer", transactionTransportBean);
				pathToFollow = "redirect:resendotprequest";
			} else {
				if (transactionTransportBean.getMessage() != null) {
					errorMsg += transactionTransportBean.getMessage();
				}
				model.addAttribute("alert", errorMsg);
				pathToFollow = "moneytransferhome";
			}
		} else {
			session.setAttribute("bean",
					transactionTransportBean.getRemittanceUserDto());
			session.setAttribute("cardno", transactionTransportBean
					.getRemittanceUserDto().getCardno());
			session.setAttribute("success", "login on Money Transfer");
			model.addAttribute("alert",
					"You are successfully login on Money Transfer");
			request.setAttribute("value", "my name");
			pathToFollow = "redirect:userdetails";
		}

		return pathToFollow;
	}

	/*
	 * @RequestMapping(value = "/loginenterotp", method = RequestMethod.GET)
	 * public String loginenterotp(
	 * 
	 * @ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto, Model
	 * model) {
	 * 
	 * return "loginenterotp"; }
	 * 
	 * @RequestMapping(value = "/loginsubmitotp", method = RequestMethod.POST)
	 * public String loginSubmitOtp(@RequestParam String OTP, Model model,
	 * HttpServletRequest request) { String pathToFollow = null; HttpSession
	 * session = request.getSession(); String userId = (String)
	 * session.getAttribute("userid"); String mobileNo = (String)
	 * session.getAttribute("mobileNo"); RemittanceUserDto rUserDto = new
	 * RemittanceUserDto(); rUserDto.setMobileNO(mobileNo);
	 * rUserDto.setOTP(OTP); TransactionTransportBean transactionTransportBean =
	 * new TransactionTransportBean(); rUserDto.setOperatorName("USER_LOGIN");
	 * rUserDto.setUrlStatus("submitLoginotp");
	 * transactionTransportBean.setMobileNo(mobileNo);
	 * transactionTransportBean.setRechargeType("MONEY_TRANSFER");
	 * transactionTransportBean
	 * .setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
	 * transactionTransportBean.setOperator("USER_RESEND_OTP");
	 * transactionTransportBean.setRetailerId(userId);
	 * transactionTransportBean.setFranchiseeMobileNum((String) session
	 * .getAttribute("userName"));
	 * transactionTransportBean.setRemittanceUserDto(rUserDto); try {
	 * transferOTPGenerationTransactionService
	 * .getOTPRegistrationService(transactionTransportBean); } catch (Exception
	 * e) { e.printStackTrace();
	 * transactionTransportBean.setMessage("System Error"); }
	 * System.out.println("transactionTransportBean.getErrorCode() >>>>" +
	 * transactionTransportBean.getErrorCode()); if
	 * (transactionTransportBean.getErrorCode() > 0) { String errorMsg =
	 * "Error in Transaction</br>"; if (transactionTransportBean.getMessage() !=
	 * null) { errorMsg += transactionTransportBean.getMessage(); }
	 * model.addAttribute("Error", errorMsg); model.addAttribute("alert",
	 * "You OTP is incorrect please enter correct OTP");
	 * System.out.println("error on controller"); pathToFollow =
	 * "loginenterotp"; } else {
	 * System.out.println("Success enter otp controller");
	 * session.setAttribute("bean",
	 * transactionTransportBean.getRemittanceUserDto());
	 * session.setAttribute("success","login on Money Transfer");
	 * model.addAttribute("alert",
	 * "You are successfully login on Money Transfer"); pathToFollow =
	 * "redirect:userdetails"; }
	 * 
	 * return pathToFollow; }
	 */
	/******************* Forgot PIN ************************ */

	@RequestMapping(value = "/forgotpin", method = RequestMethod.GET)
	public String forgotpin(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {
		return "forgotpin";
	}

	@RequestMapping(value = "/forgotpin", method = RequestMethod.POST)
	public String forgotpin(
			@RequestParam String mobileNO,
			@ModelAttribute("remittanceuserdto") RemittanceUserDto remittanceUserDto,
			Model model, HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		RemittanceUserDto rUserDto = new RemittanceUserDto();
		rUserDto.setMobileNO(mobileNO);
		rUserDto.setUrlStatus("forgotpin");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setOperator("USER_RESEND_OTP");
		transactionTransportBean.setRetailerId(userId);
		transactionTransportBean.setRemittanceUserDto(rUserDto);
		transactionTransportBean.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));
		try {
			transferOTPGenerationTransactionService
					.getOTPRegistrationService(transactionTransportBean);
		} catch (Exception e) {
			e.printStackTrace();
			transactionTransportBean.setMessage("System Error");
		}
		System.out.println("transactionTransportBean.getErrorCode() >>>>"
				+ transactionTransportBean.getErrorCode());
		if (transactionTransportBean.getErrorCode() > 0) {
			String errorMsg = "";
			if (transactionTransportBean.getMessage() != null) {
				errorMsg += transactionTransportBean.getMessage();
			}
			model.addAttribute("Error", errorMsg);
			model.addAttribute("alert", "please enter correct Mobile Number");
			pathToFollow = "forgotpin";
		} else {
			model.addAttribute("alert", transactionTransportBean.getMessage());
			pathToFollow = "moneytransferhome";
		}

		return pathToFollow;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("cardno");
		session.removeAttribute("mobileNO");
		session.removeAttribute("alert");
		session.removeAttribute("Error");
		session.removeAttribute("success");
		session.removeAttribute("bean");
		session.removeAttribute("reportList");
		model.addAttribute("alert", "You are logout successfully");
		return "moneytransferhome";
	}

}
