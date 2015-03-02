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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CacheManager;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.franchisee.service.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.recharge.mobile.dto.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/franchisee/**")
@Controller
public class TelecomRechargeController {

	@Autowired
	CommonService commonservice;

	@Autowired
	RechargeTransactionService rechargeTransactionService;

	@Autowired
	private FranchiseeInfoService franchiseeInfoService;

	@RequestMapping(value = "/getcirclename", method = RequestMethod.GET)
	public @ResponseBody
	String getCircleNames(@RequestParam("operator") String operatorName) {
		String circles = CacheManager.getOperatorCircles(operatorName,
				"MOBILE_PREPAID");
		return circles;
	}

	@RequestMapping(value = "/telecomrecharge", method = RequestMethod.GET)
	public ModelAndView telecomRecharge(ModelMap map) {

		return new ModelAndView("telecomRechargeFran", "telecomrecharge",
				new TransactionTransportBean());

	}

	@RequestMapping(value = "/rechargedetail", method = RequestMethod.POST)
	public String rechargeDetail(
			@ModelAttribute("telecomrecharge") TransactionTransportBean transactionTransport,
			ModelMap model, HttpServletRequest req) {
		HttpSession session = req.getSession();

		session.setAttribute("rechargeType",
				transactionTransport.getRechargeType());
		session.setAttribute("circle", transactionTransport.getCircleName());
		session.setAttribute("mobileNo", transactionTransport.getMobileNo());
		session.setAttribute("amount", transactionTransport.getAmount());
		session.setAttribute("operator", transactionTransport.getOperator());

		String pathToFollow = null;

		transactionTransport.setRetailerId((String) req.getSession()
				.getAttribute("userid"));
		transactionTransport.setTransactionName("MOBILE_RECHARGE");
		transactionTransport.setFranchiseeMobileNum((String) session
				.getAttribute("userName"));

		try {
			rechargeTransactionService.doRechargeService(transactionTransport);
		} catch (Exception e) {
			e.printStackTrace();
			transactionTransport.setMessage("System Error");
			transactionTransport.setErrorCode(3);
		}

		if (transactionTransport.getErrorCode() > 0
				|| transactionTransport.getThirdpartytransid() == null) {
			String errorMsg = "Error in Transaction</br>";
			if (transactionTransport.getMessage() != null) {
				errorMsg += transactionTransport.getMessage();
			}
			model.addAttribute("Error", errorMsg);
			model.addAttribute("telecomrecharge", transactionTransport);
			pathToFollow = "telecomRechargeFran";
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

			pathToFollow = "redirect:rechargedetailfran";
		}

		return pathToFollow;
	}

	@RequestMapping(value = "/rechargedetailfran", method = RequestMethod.GET)
	public String finalPage(ModelMap model) {
		model.addAttribute("Message", "Recharge Successful");
		return "rechargeDetailFran";
	}

	public RechargeTransactionService getdoRechargeTransactionService() {
		return rechargeTransactionService;
	}

	public void setdoRechargeTransactionService(
			RechargeTransactionService rechargeTransactionService) {
		this.rechargeTransactionService = rechargeTransactionService;
	}
}
