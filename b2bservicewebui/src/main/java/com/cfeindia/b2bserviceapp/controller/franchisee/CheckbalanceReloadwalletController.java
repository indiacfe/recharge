package com.cfeindia.b2bserviceapp.controller.franchisee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cfeindia.b2bserviceapp.dto.RemittanceUserDto;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.MoneyTransferOTPGenerationTransactionService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.MoneyTransferTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/franchisee/**")
@Controller
public class CheckbalanceReloadwalletController {

	@Autowired
	MoneyTransferTransactionService moneyTransferTransactionService;
	@Autowired
	MoneyTransferOTPGenerationTransactionService transferOTPGenerationTransactionService;

	/*@RequestMapping(value = "/checksenderbalance", method = RequestMethod.GET)
	public String checksenderbalance(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {

		return "checksenderbalance";
	}

	@RequestMapping(value = "/checksenderbalancedetails", method = RequestMethod.POST)
	public String checksenderbalancedetails(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {

		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String) session.getAttribute("cardno"));
		rUserDto.setUrlStatus("checkSenderBalanceOTP");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setOperator("SENDER_BALANCE_TOPUP_WALLET");
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
			model.addAttribute("alert", errorMsg);
			model.addAttribute("moneytransfer", transactionTransportBean);
			model.addAttribute("alert",
					"You OTP is incorrect please enter correct OTP");
			pathToFollow = "checksenderbalance";
		} else {
			session.setAttribute("OTP", rUserDto.getOTP());
			session.setAttribute("cardno", rUserDto.getCardno());
			model.addAttribute("alert",
					"You are successfully registered on Money Transfer");
			pathToFollow = "redirect:checksenderbalance";
		}

		return pathToFollow;

	}*/

	@RequestMapping(value = "/topsenderwallet", method = RequestMethod.GET)
	public String reloadsenderwallet(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {

		return "reloadWallet";
	}
	
	@RequestMapping(value = "/topupsenderwallet", method = RequestMethod.POST)
	public String reloadSenderWallet(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model,HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String)session.getAttribute("cardno"));
		rUserDto.setUrlStatus("topUpSenderWallet");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean.setOperator("SENDER_BALANCE_TOPUP_WALLET");
		transactionTransportBean.setMobileNo(rUserDto.getMobileNO());
		transactionTransportBean.setAmount(Double.parseDouble(rUserDto.getAmount()));
		transactionTransportBean
				.setTransactionName("RELOAD_WALLET");
		transactionTransportBean.setRetailerId(userId);
		transactionTransportBean.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));
		transactionTransportBean.setRemittanceUserDto(rUserDto);
		transactionTransportBean.getRemittanceUserDto();
		transactionTransportBean.setPaymentType("ADUNIT");
		try {
			transferOTPGenerationTransactionService.getreloadOTP(transactionTransportBean);
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
			model.addAttribute("alert", errorMsg+" Please enter more amount");
			pathToFollow = "reloadWallet";
		} else {
			session.setAttribute("cardno", rUserDto.getCardno());
			model.addAttribute("alert", "Your wallet is successfully reload");
			pathToFollow = "reloadWallet";
		}

		return pathToFollow;
	}

	/*@RequestMapping(value = "/fundtransferotp", method = RequestMethod.GET)
	public String fundtransferotp(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {

		return "fundtransferotp";
	}*/
	@RequestMapping(value = "/fundtransferotp", method = RequestMethod.GET)
	public String fundtransferotp(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model,HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String)session.getAttribute("cardno"));
	rUserDto.setUrlStatus("fundTransferGetOTP");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		rUserDto.setUrlStatus("viewBeneficiary");
		rUserDto.setOperatorName("VIEW_BENEFICIARY");
		transactionTransportBean.setOperator("VIEW_BENEFICIARY");
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
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
			if (transactionTransportBean.getMessage() != null) {
				errorMsg += transactionTransportBean.getMessage();
			}
			model.addAttribute("alert", errorMsg);
			pathToFollow = "fundtransferotp";
		} else {
			session.setAttribute("reportList",transactionTransportBean.getRemittanceUserDtoList());
			session.setAttribute("cardno", rUserDto.getCardno());
			pathToFollow = "fundtransfergetotp";
		}

		return pathToFollow;
	}

	@RequestMapping(value = "/fundtransfertransaction", method = RequestMethod.GET)
	public String fundtransfertransaction(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {

		return "fundtransfertransaction";
	}
	
	@RequestMapping(value = "/fundtransfertransactionforifsc", method = RequestMethod.POST)
	public String fundtransfertransactionforifsc(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model,HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String)session.getAttribute("cardno"));
		rUserDto.setUrlStatus("fundTransferTransactionForIFSC");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean.setMobileNo(rUserDto.getMobileNO());
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setOperator("FUND_TRANSFER");
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
			model.addAttribute("alert", errorMsg);
			pathToFollow = "fundtransfergetotp";
		} else {
			session.setAttribute("cardno", rUserDto.getCardno());
			model.addAttribute("moneytransfer", transactionTransportBean);
			model.addAttribute("alert", "Your Transfer is successfully");
			pathToFollow = "fundtransfergetotp";
		}

		return pathToFollow;
	}
	@RequestMapping(value = "/fundtransfertransactionformmid", method = RequestMethod.POST)
	public String fundtransfertransactionformmid(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model,HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String)session.getAttribute("cardno"));
		rUserDto.setUrlStatus("fundTransferTransactionForMMID");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean.setMobileNo(rUserDto.getMobileNO());
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setOperator("FUND_TRANSFER");
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
			model.addAttribute("alert", "Please enter correct otp");
			pathToFollow = "fundtransfergetotp";
		} else {
			session.setAttribute("cardno", rUserDto.getCardno());
			model.addAttribute("alert", "Your Transfer is successfully");
			pathToFollow = "fundtransfergetotp";
		}

		return pathToFollow;
	}
	@RequestMapping(value = "/moneytransferhistory", method = RequestMethod.GET)
	public String moneytransferhistory(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model) {

		return "moneytransferhistory";
	}

	@RequestMapping(value = "/moneytransferhistory", method = RequestMethod.POST)
	public String moneyTransferGetHistory(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String)session.getAttribute("cardno"));
		rUserDto.setUrlStatus("transferHistory");
		rUserDto.setOperatorName("TRANSFER_HISTORY_UPGRADE");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean
				.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setOperator("TRANSFER_HISTORY_UPGRADE");
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
			model.addAttribute("alert",errorMsg);
			pathToFollow = "moneytransferhistory";
		} else {
			model.addAttribute("alert", "Your transfer history is");
			model.addAttribute("moneytransfer", transactionTransportBean.getRemittanceUserDtoList());
			pathToFollow = "moneytransferhistory";
		}

		return pathToFollow;

	}

	@RequestMapping(value = "/upgradesender", method = RequestMethod.GET)
	public String upgragesender(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String mobileNo = (String) session.getAttribute("mobileNo");
		model.addAttribute("mobileNo",mobileNo);
		return "upgradesender";
	}

	@RequestMapping(value = "/upgradesender", method = RequestMethod.POST)
	public String upgragesendersave(
			@ModelAttribute("remittanceuserdto") RemittanceUserDto rUserDto,
			Model model, HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		rUserDto.setCardno((String)session.getAttribute("cardno"));
		rUserDto.setUrlStatus("transferHistory");
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setRechargeType("MONEY_TRANSFER");
		transactionTransportBean.setTransactionName("MONEY_TRANSFER_USER_REGISTRATION");
		transactionTransportBean.setOperator("TRANSFER_HISTORY_UPGRADE");
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
			model.addAttribute("alert", "Please enter correct mobile no.");
			pathToFollow = "moneytransferhistory";
		} else {
			session.setAttribute("cardno", rUserDto.getCardno());
			model.addAttribute("alert", "Your transfer history is");
			pathToFollow = "moneytransferhistory";
		}

		return pathToFollow;

	}
}
