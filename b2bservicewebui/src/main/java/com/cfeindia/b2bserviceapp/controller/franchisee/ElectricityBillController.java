package com.cfeindia.b2bserviceapp.controller.franchisee;

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

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/franchisee/**")
@Controller
public class ElectricityBillController {

	@Autowired
	RechargeTransactionService rechargeTransactionService;

	@Autowired
	private FranchiseeInfoService franchiseeInfoService;

	@RequestMapping(value = "/electricitybill", method = RequestMethod.GET)
	public ModelAndView electricitytvRecharge() {
		return new ModelAndView("electricitybill", "electricitybillbean",
				new TransactionTransportBean());

	}

	@RequestMapping(value = "/electricitybillsubmit", method = RequestMethod.POST)
	public String electricityrechargeDetail(
			@ModelAttribute("electricitybill") TransactionTransportBean transactionTransport,
			@RequestParam(value = "cycleNumber", required = false) String cycleNumber,
			ModelMap model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String pathToFollow = null;
		Long suuid = (Long) session.getAttribute("suuid");
		if (suuid != null) {
			session.setAttribute("suuid", null);

			transactionTransport.setCycleNumber(cycleNumber);
			session.setAttribute("rechargeType",
					transactionTransport.getRechargeType());
			session.setAttribute("operator", transactionTransport.getOperator());
			session.setAttribute("amount", transactionTransport.getAmount());
			session.setAttribute("connectionid",
					transactionTransport.getConnectionid());

			transactionTransport.setRetailerId((String) req.getSession()
					.getAttribute("userid"));
			transactionTransport.setTransactionName("ELECTRICITY");
			transactionTransport.setFranchiseeMobileNum((String) session
					.getAttribute("userName"));
			if ("BSES Rajdhani Power Limited - DELHI"
					.equalsIgnoreCase(transactionTransport.getOperator())) {
				session.setAttribute("logo", "bses_rajdhani.jpg");

			} else if ("BSES Yamuna Power Limited - DELHI"
					.equalsIgnoreCase(transactionTransport.getOperator())) {
				session.setAttribute("logo", "bses_yamuna.jpg");

			} else if ("Reliance Energy Limited - MUMBAI"
					.equalsIgnoreCase(transactionTransport.getOperator())) {
				session.setAttribute("logo", "reliance_eng.jpg");

			} else {
				session.setAttribute("logo", "tata-power-logo1.jpg");
                   
			}

			model.addAttribute("electricitybillbean", transactionTransport);
			transactionTransport.setBillPayment(CommonConstants.BILLPAYMENT);
			try {
				rechargeTransactionService
						.doRechargeService(transactionTransport);
			} catch (Exception e) {
				e.printStackTrace();
				transactionTransport.setMessage("System Error");
				transactionTransport.setErrorCode(3);
			}

			if (transactionTransport.getErrorCode() > 0) {
				model.addAttribute("Error", transactionTransport.getMessage());
				pathToFollow = "electricitybill";
			} else {
				session.setAttribute("transId",
						transactionTransport.getTransid());
				// SimpleDateFormat sdf = new
				// SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				session.setAttribute("datetime",
						transactionTransport.getThirdPartyTransDateTime());
				// model.addAttribute("datetime",
				// sdf.format(transactionTransport.getCreatedAt()));
				String userId = (String) session.getAttribute("userid");
				FranchiseeInfo franchiseeInfo = franchiseeInfoService
						.getFranchiseeInfo(userId);
				session.setAttribute("franchiseeInfo", franchiseeInfo);
				pathToFollow = "redirect:electricitybillsuccess";
			}
		} else {
			pathToFollow = "redirect:electricitybill";

		}

		return pathToFollow;

	}

	@RequestMapping(value = "/electricitybillsuccess", method = RequestMethod.GET)
	public String finalPage(ModelMap model) {
		model.addAttribute("Message", "Recharge Successful");
		return "electricitybillsuccess";
	}

	public RechargeTransactionService getdoRechargeTransactionService() {
		return rechargeTransactionService;
	}

	public void setdoRechargeTransactionService(
			RechargeTransactionService rechargeTransactionService) {
		this.rechargeTransactionService = rechargeTransactionService;
	}

}
