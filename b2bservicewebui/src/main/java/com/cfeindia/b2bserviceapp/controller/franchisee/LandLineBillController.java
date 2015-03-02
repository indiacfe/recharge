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

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/franchisee/**")
@Controller
public class LandLineBillController {

	@Autowired
	CommonService commonservice;

	@Autowired
	RechargeTransactionService rechargeTransactionService;

	@Autowired
	private FranchiseeInfoService franchiseeInfoService;

	@RequestMapping(value = "/landlinebill")
	public ModelAndView landLineBill() {

		return new ModelAndView("landlinebill", "landline",
				new TransactionTransportBean());
	}

	@RequestMapping(value = "/landlinebilldetail", method = RequestMethod.POST)
	public String rechargeDetail(
			@ModelAttribute("landline") TransactionTransportBean transactionTransport,
			@RequestParam(value = "stdCode", required = false) String stdCode,
			@RequestParam String connectionid,
			@RequestParam(value = "canumber", required = false) Long canumber,
			@RequestParam(value = "paymentType", required = false) String paymentType,
			@RequestParam(value = "commissionType", required = false) String commissionType,
			ModelMap model, HttpServletRequest req) {
		    
		String pathToFollow = null;
		HttpSession session = req.getSession();
		Long suuid = (Long) session.getAttribute("suuid");
		if (suuid != null) {
			session.setAttribute("suuid", null);
		
		session.setAttribute("rechargeType",
				transactionTransport.getRechargeType());
		session.setAttribute("connectionid", connectionid);
		session.setAttribute("amount", transactionTransport.getAmount());
		session.setAttribute("operator", transactionTransport.getOperator());

		// session.setAttribute("canumber", canumber);


		transactionTransport.setRetailerId((String) req.getSession()
				.getAttribute("userid"));
		transactionTransport.setTransactionName("LANDLINE");
		transactionTransport.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));
		transactionTransport.setBillPayment(CommonConstants.BILLPAYMENT);
		if (stdCode != null ) {
			transactionTransport.setStdCode(stdCode);
		}
		if (connectionid != null) {
			transactionTransport.setConnectionid(connectionid);
		}
		if (canumber != null) {
			transactionTransport.setCanumber(canumber);
		}
		try {
			rechargeTransactionService.doRechargeService(transactionTransport);
		} catch (Exception e) {
			e.printStackTrace();
			transactionTransport.setMessage("System Error");
			//transactionTransport.setErrorCode(3);
		}

		if (transactionTransport.getErrorCode() > 0
				|| transactionTransport.getThirdpartytransid() == null) {
			String errorMsg = "Error in Transaction</br>";
			if (transactionTransport.getMessage() != null) {
				errorMsg += transactionTransport.getMessage();
			}
			model.addAttribute("Error", errorMsg);
			model.addAttribute("landlinebill", transactionTransport);
			pathToFollow = "landlinebill";
		} else {
			session.setAttribute("transId", transactionTransport.getTransid());
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

			pathToFollow = "redirect:landlinebillfran";
		}
		}else {
			
			pathToFollow = "redirect:landlinebill";
		}

		return pathToFollow;
	}

	@RequestMapping(value = "/landlinebillfran", method = RequestMethod.GET)
	public String finalPage(ModelMap model) {
		model.addAttribute("Message", "Recharge Successful");
		return "landlinebillsuccess";
	}

	public RechargeTransactionService getdoRechargeTransactionService() {
		return rechargeTransactionService;
	}

	public void setdoRechargeTransactionService(
			RechargeTransactionService rechargeTransactionService) {
		this.rechargeTransactionService = rechargeTransactionService;
	}

}
