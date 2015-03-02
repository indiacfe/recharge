package com.cfeindia.b2bserviceapp.controller.franchisee;

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
public class MoneyTransBeneficiaryController {

	@Autowired
	MoneyTransferTransactionService moneyTransferTransactionService;
	@Autowired
	MoneyTransferOTPGenerationTransactionService transferOTPGenerationTransactionService;

	/*@RequestMapping(value = "/getbeneficiary/{mobileNO}", method = RequestMethod.GET)
	public @ResponseBody
	String getbeneficiary(@PathVariable("mobileNO") String mobileNO)
			throws IOException {
		System.out.println("called " + mobileNO);
		String userlist = null;
		ObjectMapper mapper = new ObjectMapper();
		List<RemittanceUserDto> dtos = moneyTransferTransactionService
				.getAllBeniesOfSender(mobileNO);
		try {
			userlist = mapper.writeValueAsString(dtos);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return userlist;
	}
*/
	@RequestMapping(value = "/beneficiary", method = RequestMethod.GET)
	public String beneficiary(Model model,HttpServletRequest request) {
		return "beneficiary";
	}

	@RequestMapping(value = "/addbeneficiary", method = RequestMethod.GET)
	public String addbeneficiary(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {

		return "addbeneficiary";
	}

	@RequestMapping(value = "/addbeneficiaryforifsc", method = RequestMethod.POST)
	public ModelAndView addbeneficiaryForIFSC(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		System.out.println("Data");
		String pathToFollow = null;
		HttpSession session = request.getSession();
		session.removeAttribute("alert");
		String userId = (String) session.getAttribute("userid");
		rUserDto.setUrlStatus("addBeneficiaryforIFSC");
		rUserDto.setCardno((String)session.getAttribute("cardno"));
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));
		transactionTransportBean.setOperator("ADD_BENEFICIARY");
		transactionTransportBean.setRemittanceUserDto(rUserDto);
		transactionTransportBean.setRetailerId(userId);
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
			model.addAttribute("alert", errorMsg+" Add Beneficiary Failed Please contact admin.");
			pathToFollow = "addbeneficiary";
		} else {
			session.setAttribute("beneId", transactionTransportBean
					.getRemittanceUserDto().getBeneId());
			session.setAttribute("cardno", rUserDto.getCardno());
			model.addAttribute("alert",transactionTransportBean.getMessage());
			session.setAttribute("alert","For Verify Add Beneficiary Please Input OTP");
			pathToFollow = "redirect:verifyaddbeneficiary";
		}

		return new ModelAndView(pathToFollow, "remittanceuserdto", rUserDto);
	}

	@RequestMapping(value = "/addbeneficiaryformmid", method = RequestMethod.POST)
	public ModelAndView addbeneficiaryForMMID(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		System.out.println("Data");
		String pathToFollow = null;
		HttpSession session = request.getSession();
		session.removeAttribute("alert");
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String)session.getAttribute("cardno"));
		rUserDto.setUrlStatus("addBeneficiaryForMMID");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));
		transactionTransportBean.setOperator("ADD_BENEFICIARY");
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setRemittanceUserDto(rUserDto);
		transactionTransportBean.setRetailerId(userId);
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
			model.addAttribute("alert", errorMsg+" Add Beneficiary Failed Please contact admin.");
			pathToFollow = "addbeneficiary";
		} else {
			session.setAttribute("beneId", transactionTransportBean
					.getRemittanceUserDto().getBeneId());
			session.setAttribute("cardno", rUserDto.getCardno());
			session.setAttribute("alert","For Verify Add MMID Please Input OTP");
			pathToFollow = "redirect:verifyaddbeneficiary";
		}

		return new ModelAndView(pathToFollow, "remittanceuserdto", rUserDto);
	}

	@RequestMapping(value = "/verifyaddbeneficiary", method = RequestMethod.GET)
	public String verifyaddbeneficiary(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {

		return "verifyaddbeneficiary";
	}

	@RequestMapping(value = "/verifyaddbeneficiarydetails", method = RequestMethod.POST)
	public String verifyaddbeneficiarydetails(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {

		String pathToFollow = null;
		HttpSession session = request.getSession();
		session.removeAttribute("alert");
		String userId = (String) session.getAttribute("userid");
		rUserDto.setBeneId((String)session.getAttribute("beneId"));
		rUserDto.setCardno((String)session.getAttribute("cardno"));
		rUserDto.setUrlStatus("verifyBeneficiaryOTP");
		rUserDto.setOTP(rUserDto.getOTP());
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean.setOperator("ADD_BENEFICIARY");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
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
			model.addAttribute("alert", errorMsg+" Your OTP is incorrect please enter correct OTP");
			pathToFollow = "verifyaddbeneficiary";
		} else {
			session.setAttribute("cardno", rUserDto.getCardno());
			session.setAttribute("alert","Beneficiary successfully added.");
			pathToFollow = "redirect:addbeneficiary";
		}

		return pathToFollow;

	}
/*
	@RequestMapping(value = "/viewbeneficiary", method = RequestMethod.GET)
	public String viewbeneficiary(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {

		return "viewbeneficiary";
	}
*/
	@RequestMapping(value = "/viewbeneficiarydetails", method = RequestMethod.GET)
	public String viewbeneficiarydetails(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		
		String pathToFollow = null;
		HttpSession session = request.getSession();
		session.removeAttribute("alert");
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String) session.getAttribute("cardno"));
		rUserDto.setUrlStatus("viewBeneficiary");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setOperator("VIEW_BENEFICIARY");
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
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
			String errorMsg = "Error in Transaction</br>";
			if (transactionTransportBean.getMessage() != null) {
				errorMsg += transactionTransportBean.getMessage();
			}
			model.addAttribute("Error", errorMsg);
			model.addAttribute("moneytransfer", transactionTransportBean);
			model.addAttribute("alert",
					"Your OTP is incorrect please enter correct OTP");
			pathToFollow = "beneficiary";
		} else {
			session.setAttribute("OTP", rUserDto.getOTP());
			session.removeAttribute("reportList");
			session.removeAttribute("reportsingleitem");
			session.setAttribute("reportList",transactionTransportBean.getRemittanceUserDtoList());
			session.setAttribute("cardno", rUserDto.getCardno());
			pathToFollow = "redirect:beneficiary";
		}

		return pathToFollow;

	}

	@RequestMapping(value = "/removebeneficiary", method = RequestMethod.GET)
	public String removebeneficiary(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {

		return "removebeneficiary";
	}

	@RequestMapping(value = "/removebeneficiarydetails", method = RequestMethod.GET)
	public String removebeneficiarydetails(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,@RequestParam String beneId,
			@RequestParam String cardno,
			Model model, HttpServletRequest request) {

		String pathToFollow = null;
		HttpSession session = request.getSession();
		session.removeAttribute("alert");
		session.setAttribute("beneId", rUserDto.getBeneId());
		String userId = (String) session.getAttribute("userid");
		rUserDto.setUrlStatus("removeBeneficiary");
		System.out.println(rUserDto.getOTP());
		rUserDto.setCardno(cardno);
		rUserDto.setBeneId(beneId);
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setOperator("REMOVE_BENEFICIARY");
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
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
			pathToFollow = "removebeneficiary";
		} else {
			session.setAttribute("cardno",rUserDto.getCardno());
			session.setAttribute("alert","For remove  beneficiary Please enter OTP");
			pathToFollow = "redirect:verifyremovebeneficiary";
		}

		return pathToFollow;

	}

	@RequestMapping(value = "/verifyremovebeneficiary", method = RequestMethod.GET)
	public String verifyremovebeneficiary(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {

		return "verifyremovebeneficiary";
	}

	@RequestMapping(value = "/verifyremovebeneficiarydetails", method = RequestMethod.POST)
	public String verifyremovebeneficiarydetails(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		session.removeAttribute("alert");
		session.removeAttribute("reportList");
		session.removeAttribute("reportsingleitem");
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String) session.getAttribute("cardno"));
		rUserDto.setBeneId((String) session.getAttribute("beneId"));
		rUserDto.setUrlStatus("verifyRemoveBeneficiaryOTP");
		System.out.println(rUserDto.getOTP());
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setOperator("REMOVE_BENEFICIARY");
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
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
			pathToFollow = "verifyremovebeneficiary";
			
		} else {
			session.setAttribute("OTP", rUserDto.getOTP());
			session.setAttribute("alert", "Beneficiary Deleted Successfully");
			session.setAttribute("cardno", rUserDto.getCardno());
			pathToFollow = "redirect:beneficiary";
		}

		return pathToFollow;

	}
}
