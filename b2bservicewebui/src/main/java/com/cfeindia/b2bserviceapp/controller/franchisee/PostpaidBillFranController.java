package com.cfeindia.b2bserviceapp.controller.franchisee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/franchisee/**")
@Controller
public class PostpaidBillFranController {

	@Autowired
	RechargeTransactionService rechargeTransactionService;

	@Autowired
	private FranchiseeInfoService franchiseeInfoService;

	@RequestMapping(value = "/postpaidbillpayment")
	public ModelAndView postpaidBill() {

		return new ModelAndView("postpaidbill", "postpaidbean",
				new TransactionTransportBean());
	}

	@RequestMapping(value = "/postpaidbillsubmit", method = RequestMethod.POST)
	public String rechargeDetailPostpaidDetail(
			@ModelAttribute("postpaidbean") TransactionTransportBean transactionTransport,
			ModelMap model, HttpServletRequest req) {
		String pathToFollow = null;
		HttpSession session = req.getSession();
		Long suuid = (Long) session.getAttribute("suuid");
		transactionTransport.setBillPayment(CommonConstants.BILLPAYMENT);
		if (suuid != null) {
			session.setAttribute("suuid", null);
		model.addAttribute("rechargeType",
				transactionTransport.getRechargeType());
		model.addAttribute("operator", transactionTransport.getOperator());
		model.addAttribute("amount", transactionTransport.getAmount());
		model.addAttribute("connectionid",
				transactionTransport.getConnectionid());

		

		transactionTransport.setRetailerId((String) req.getSession()
				.getAttribute("userid"));
		transactionTransport.setTransactionName("POSTPAID_BILL");
		transactionTransport.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));
		transactionTransport.setMobileNo(transactionTransport.getConnectionid());
		transactionTransport.setBillPayment(CommonConstants.BILLPAYMENT);

		try {
			rechargeTransactionService.doRechargeService(transactionTransport);
		} catch (Exception e) {
			e.printStackTrace();
			transactionTransport.setMessage("System Error");
			//transactionTransport.setErrorCode(3);
		}

		if (transactionTransport.getErrorCode() > 0) {
			model.addAttribute("postpaidbean", transactionTransport);
			model.addAttribute("Error", transactionTransport.getMessage());
			pathToFollow = "postpaidbill";
		} else {
			model.addAttribute("Message", "Recharge Successful");
			model.addAttribute("transId", transactionTransport.getTransid());
			// SimpleDateFormat sdf = new
			// SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			model.addAttribute("datetime",
					transactionTransport.getThirdPartyTransDateTime());
			// model.addAttribute("datetime",
			// sdf.format(transactionTransport.getCreatedAt()));

			String userId = (String) session.getAttribute("userid");
			FranchiseeInfo franchiseeInfo = franchiseeInfoService
					.getFranchiseeInfo(userId);
			session.setAttribute("franchiseeInfo", franchiseeInfo);
			pathToFollow = "postpaidbillsuccess";
		}
		}else {
			
			pathToFollow = "redirect:postpaidbillpayment";
		}

		return pathToFollow;
	}

	public RechargeTransactionService getdoRechargeTransactionService() {
		return rechargeTransactionService;
	}

	public void setdoRechargeTransactionService(
			RechargeTransactionService rechargeTransactionService) {
		this.rechargeTransactionService = rechargeTransactionService;
	}

}
