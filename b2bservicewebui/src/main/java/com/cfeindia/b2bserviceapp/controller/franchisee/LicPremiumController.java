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

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.service.admin.LicManagementService;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.franchisee.LicPremiumService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Controller
@RequestMapping("/franchisee/**")
public class LicPremiumController {
	@Autowired
	private LicPremiumService licPremiumService;
	@Autowired
	private FranchiseeInfoService franchiseeInfoService;
	@Autowired
	private LicManagementService licManagementService;

	@RequestMapping(value = "/licpremium", method = RequestMethod.GET)
	public String postpaidBill(ModelMap model) {
		model.addAttribute("licpremiumbean", new TransactionTransportBean());
		return "licpremium";
	}

	@RequestMapping(value = "/licpremiumpay", method = RequestMethod.POST)
	public String postpaidBill(
			@ModelAttribute TransactionTransportBean transactionTransportBean,
			HttpServletRequest request, HttpSession session, ModelMap model) {
		String returnPage = null;
		transactionTransportBean.setRetailerId((String) request.getSession()
				.getAttribute("userid"));
		String result = null;
		try {
			result = licPremiumService.saveDetails(transactionTransportBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (CommonConstants.SUCCESS.equalsIgnoreCase(result)) {
			model.addAttribute("datetime",
					transactionTransportBean.getCreatedAt());
			model.addAttribute("connectionid",
					transactionTransportBean.getConnectionid());
			model.addAttribute("operator",
					transactionTransportBean.getOperator());
			model.addAttribute("transId", transactionTransportBean.getTransid());

			model.addAttribute("amount", transactionTransportBean.getAmount());
			if (transactionTransportBean.getStatus().equalsIgnoreCase(
					CommonConstants.SUCCESS)) {
				String userId = (String) session.getAttribute("userid");
				FranchiseeInfo franchiseeInfo = franchiseeInfoService
						.getFranchiseeInfo(userId);
				session.setAttribute("franchiseeInfo", franchiseeInfo);
				returnPage = "licpremiumsuccess";
			} else {
				model.addAttribute("Error",
						transactionTransportBean.getMessage());

				model.addAttribute("licpremiumbean", transactionTransportBean);
				returnPage = "licpremium";

			}
		}
		return returnPage;
	}

	@RequestMapping(value = "/licpremiumreportlist", method = RequestMethod.GET)
	public String getLicPremiumReportList(ModelMap model,
			HttpServletRequest request) {

		Long userId = Long.parseLong(String.valueOf((request.getSession()
				.getAttribute("userid"))));
		model.addAttribute("licreportlist",
				licPremiumService.getLicPremiumDetails(userId));
		return "licpremiumreportlist";
	}

	@RequestMapping(value = "/licpremiumreport", method = RequestMethod.GET)
	public String getLicPremiumReportDateTime(@RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String status,
			ModelMap model, HttpServletRequest request) {
		model.addAttribute("licreportlist",
				licManagementService.getDetailservice(fromDate, toDate, status));
		model.addAttribute("fromdate", fromDate);
		model.addAttribute("todate", toDate);
		model.addAttribute("status", status);

		return "licpremiumreportlist";
	}

	@RequestMapping(value = "/licpremiumreportstaussuccess", method = RequestMethod.POST)
	public String getDetailsSuccess(ModelMap model, HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("Id"));
		licManagementService.getDetailStatusSuccess(id);
		model.addAttribute("licreportlist",
				licManagementService.getDetailStatusSuccess(id));
		// model.addAttribute("success", "success");
		return "licpremiumreportlist";
	}

	@RequestMapping(value = "/licpremiumreportstatusreject", method = RequestMethod.POST)
	public String getDetailsRejected(HttpServletRequest request, ModelMap model) {

		Long id = Long.parseLong(request.getParameter("Id"));
		licManagementService.getDetailStatusRejected(id);
		model.addAttribute("licreportlist",
				licManagementService.getDetailStatusRejected(id));

		return "licpremiumreportlist";

	}
}
