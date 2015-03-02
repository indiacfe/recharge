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

import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;

@RequestMapping("/franchisee/**")
@Controller
public class DthTvRechargeController {

	@Autowired
	RechargeTransactionService rechargeTransactionService;

	@Autowired
	private FranchiseeInfoService franchiseeInfoService;

	@RequestMapping(value = "/tvrecharge", method = RequestMethod.GET)
	public ModelAndView DThtvRecharge() {
		return new ModelAndView("dthtvFran", "dthtv",
				new TransactionTransportBean());

	}

	@RequestMapping(value = "/rechargedetaildth", method = RequestMethod.POST)
	public String dthrechargeDetail(
			@ModelAttribute("dthtv") TransactionTransportBean transactionTransport,
			ModelMap model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("rechargeType",
				transactionTransport.getRechargeType());
		session.setAttribute("type", transactionTransport.getType());
		session.setAttribute("operator", transactionTransport.getOperator());
		session.setAttribute("amount", CyberTelUtil.removeDecimalFromNumber(transactionTransport.getAmount()));
		session.setAttribute("connectionid",
				transactionTransport.getConnectionid());

		String pathToFollow = null;

		transactionTransport.setRetailerId((String) req.getSession()
				.getAttribute("userid"));
		transactionTransport.setTransactionName("DTH_RECHARGE");
		transactionTransport.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));

		model.addAttribute("dthtv", transactionTransport);
		try {
			rechargeTransactionService.doRechargeService(transactionTransport);
		} catch (Exception e) {
			e.printStackTrace();
			transactionTransport.setMessage("System Error");
			//transactionTransport.setErrorCode(3);
		}

		if (transactionTransport.getErrorCode() > 0) {
			model.addAttribute("Error", transactionTransport.getMessage());
			pathToFollow = "dthtvFran";
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
			pathToFollow = "redirect:rechargedetaildth";
		}

		return pathToFollow;
	}

	@RequestMapping(value = "/rechargedetaildth", method = RequestMethod.GET)
	public String finalPage(ModelMap model) {
		model.addAttribute("Message", "Recharge Successful");
		return "rechargedetaildth";
	}

	public RechargeTransactionService getdoRechargeTransactionService() {
		return rechargeTransactionService;
	}

	public void setdoRechargeTransactionService(
			RechargeTransactionService rechargeTransactionService) {
		this.rechargeTransactionService = rechargeTransactionService;
	}
}
