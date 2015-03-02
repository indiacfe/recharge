package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.admin.service.AdminReportService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/admin/**")
@Controller
public class RechargeReportController {

	@Autowired
	AdminReportService adminReportService;

	@RequestMapping(value = "/rechargehistoryreport")
	public String rechargeReport() {
		return "rechargehistoryreport";
	}

	@RequestMapping(value = "/rechargereport", method = RequestMethod.GET)
	public String showRechargeReport(@RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String sel,
			@RequestParam String status, ModelMap model) {
		List<TransactionTransportBean> transportBeansList = adminReportService
				.generateRechargeHistoryReport(fromDate, toDate, sel, status);
		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		for (TransactionTransportBean transactionTransportBean : transportBeansList) {
			// Users user=transactionTransportBean.getUser();
			// Long userId=user.getUserId();
			// userId=Long.valueOf(ExtractorUtil.generateIdFromString(userId.toString(),
			// "R"));
			// user.setUserId(userId);
			// transactionTransportBean.setUser(user);
			totalCredit = totalCredit
					+ transactionTransportBean.getCreditAmountFranchisee();
			totalDebit = totalDebit + transactionTransportBean.getAmount();
		}
		model.addAttribute("rechargeDetail", transportBeansList);
		model.addAttribute("totalCredit", totalCredit);
		model.addAttribute("totalDebit", totalDebit);
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		model.addAttribute("sel", sel);
		model.addAttribute("status", status);
		return "rechargehistoryreport";
	}
}