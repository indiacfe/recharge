package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.entity.ICashRecharge;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.franchisee.ICashService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@RequestMapping("/franchisee/**")
@Controller
public class IcashCardController {
	@Autowired
	private RechargeTransactionService service;
	@Autowired
	private FranchiseeInfoService franchiseeInfoService;
	@Autowired
	private ICashService iCashService;

	@RequestMapping(value = "/cashcardregister", method = RequestMethod.GET)
	public ModelAndView userRegister() {
		return new ModelAndView("cashcardregister", "registration",
				new ICashRecharge());
	}

	@RequestMapping(value = "/icashregister", method = RequestMethod.POST)
	public ModelAndView userRegistration(Model model,
			@RequestParam("birthDate") String birthDate,
			@ModelAttribute("registration") ICashRecharge iRecharge,
			HttpServletRequest request) {
		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		iRecharge.setUserId(userId);
		iRecharge.setDateOfBirth(TimeStampUtil
				.getTimeStampFromStringFromdate(birthDate));
		iRecharge.setAmount(CommonConstants.I_CASH_REGISTRATION_CHARGE);
		String lastName = iRecharge.getLastName();
		if(lastName != null && lastName.split(" ").length > 1) {
			iRecharge.setMiddleName(lastName.split(" ")[0]);
			iRecharge.setLastName(lastName.split(" ")[1]);
		}
		if(lastName != null && lastName.split(" ").length == 1) {
			iRecharge.setMiddleName(lastName);
		}
		if(lastName == null) {
			iRecharge.setMiddleName("NA");
			iRecharge.setLastName("NA");
		}
			
		iRecharge.setAmountAll(CommonConstants.I_CASH_REGISTRATION_CHARGE);
		iRecharge.setComment(CommonConstants.I_CASH_COMMENT);
		iRecharge.setDocumentDetail("NA");
	    iRecharge.setDocumentType("NA");

		ICashRecharge icash = iCashService.checkICashRegistration(iRecharge
				.getMobileNumber());
		if (icash != null
				&& CommonConstants.SUCCESS.equalsIgnoreCase(icash
						.getRegistrationStatus())) {
			model.addAttribute("Error", "Mobile number already registered");
			pathToFollow = "cashcardregister";

		} else {
			iRecharge.setAmountAll(iRecharge.getAmount());
			TransactionTransportBean transactionTransportBean = null;
			try {
				transactionTransportBean = getTransactionTransportBean(iRecharge);
				transactionTransportBean
						.setFranchiseeMobileNum((String) session
								.getAttribute("userName"));
				iCashService.iCashRegistrationService(transactionTransportBean,
						iRecharge);
			} catch (Exception e) {
				e.printStackTrace();
				transactionTransportBean.setMessage("System Error");
			}
			if (transactionTransportBean.getErrorCode() > 0) {
				String errorMsg = "Error in Transaction</br>";
				if (transactionTransportBean.getMessage() != null) {
					errorMsg += transactionTransportBean.getMessage();
				}
				model.addAttribute("Error", errorMsg);
				model.addAttribute("telecomrecharge", transactionTransportBean);
				pathToFollow = "cashcardregister";
			} else {
				session.setAttribute("transId",
						transactionTransportBean.getTransid()); 
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				TimeStampUtil date = new TimeStampUtil();
				transactionTransportBean.setCreatedAt(date.getTimestamp());
				session.setAttribute("mobileNo",iRecharge.getMobileNumber());
				session.setAttribute("amount",CommonConstants.I_CASH_REGISTRATION_CHARGE);
				session.setAttribute("datetime", sdf.format(transactionTransportBean.getCreatedAt()));
				FranchiseeInfo franchiseeInfo = franchiseeInfoService
						.getFranchiseeInfo(userId);
				session.setAttribute("franchiseeInfo", franchiseeInfo);

				pathToFollow = "redirect:icashrechargedetail";
			}

		}

		return new ModelAndView(pathToFollow, "registration", iRecharge);
	}

	@RequestMapping(value = "/icashrechargedetail", method = RequestMethod.GET)
	public String finalPage(ModelMap model) {
		model.addAttribute("Message", "Registration Successful");
		return "rechargedetailicash";
	}

	@RequestMapping(value = "/icashcardrecharge", method = RequestMethod.GET)
	public ModelAndView cardRecharge() {

		return new ModelAndView("icashcardrecharge", "iCash",
				new ICashRecharge());
	}

	@RequestMapping(value = "/icashcardrecharge", method = RequestMethod.POST)
	public ModelAndView cardRecharge(
			@ModelAttribute("iCash") ICashRecharge iCashRecharge, Model model,
			HttpServletRequest request) {

		String pathToFollow = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		iCashRecharge.setAmountAll(iCashRecharge.getAmount());
		TransactionTransportBean bean = new TransactionTransportBean();
		bean.setMobileNo(iCashRecharge.getMobileNumber());
		bean.setRetailerId(userId);
		bean.setAmount(iCashRecharge.getAmount());
		bean.setiCashRecharge(iCashRecharge);
		bean.setRechargeType("ICASH_CARD");
		bean.setTransactionName("ICASH_CARD_RECHARGE");
		bean.setOperator("ICASH_CARD_IRCTC"); 
		bean.setFranchiseeMobileNum((String) session.getAttribute("userName"));
		try {
			if (CommonConstants.I_CASH_CARD_MAX_AMOUNT_RECHARGE < iCashRecharge
					.getAmount()) {
				bean.setErrorCode(2);
				bean.setMessage("Amount sohuld not more than Rs.10000/-");

			} else {
				service.doRechargeService(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
			bean.setMessage("System Error");
		}
		if (bean.getErrorCode() > 0 || bean.getThirdpartytransid() == null) {
			String errorMsg = "Error in Transaction</br>";
			if (bean.getMessage() != null) {
				errorMsg += bean.getMessage();
			}
			model.addAttribute("Error", errorMsg);
			model.addAttribute("telecomrecharge", bean);
			pathToFollow = "icashcardrecharge";
		} else {
			session.setAttribute("transId", bean.getTransid());

			session.setAttribute("datetime", bean.getThirdPartyTransDateTime());

			FranchiseeInfo franchiseeInfo = franchiseeInfoService
					.getFranchiseeInfo(userId);
			session.setAttribute("franchiseeInfo", franchiseeInfo);

			pathToFollow = "redirect:icashcardrechargedetail";
		}

		return new ModelAndView(pathToFollow, "iCash", iCashRecharge);
	}

	@RequestMapping(value = "/icashcardrechargedetail", method = RequestMethod.GET)
	public String finalPageCard(ModelMap model) {
		model.addAttribute("Message", "Card Recharge Successful");
		return "rechargedetailicashcard";
	}

	private TransactionTransportBean getTransactionTransportBean(
			ICashRecharge eRegistration) {
		TransactionTransportBean bean = new TransactionTransportBean();
		bean.setRechargeType("ICASH");
		bean.setTransactionName("ICASH_RECHARGE");
		bean.setOperator("ICASH_IRCTC");
		bean.setMobileNo(eRegistration.getMobileNumber());
		bean.setRetailerId(eRegistration.getUserId());
		bean.setAmount(eRegistration.getAmount());
		bean.setiCashRecharge(eRegistration);
		return bean;
	}

}
