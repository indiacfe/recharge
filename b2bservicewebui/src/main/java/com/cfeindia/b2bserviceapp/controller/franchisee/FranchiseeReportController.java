package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.model.franchisee.AccountStament;
import com.cfeindia.b2bserviceapp.service.franchisee.FranAccountStatmentService;

@RequestMapping("/franchisee/**")
@Controller
public class FranchiseeReportController {

	@Autowired
	FranAccountStatmentService accountStatmentService;

	@RequestMapping(value = "/accountstatement")
	public ModelAndView accountStatement() {

		return new ModelAndView("accountstatement", "accountstatement",
				new AccountStament());
	}

	@RequestMapping(value = "/accountstatementretrieve")
	public String accountStatementReport(
			@ModelAttribute AccountStament accountStatement, ModelMap model,
			HttpServletRequest req) {
		model.addAttribute("accountstatement", accountStatement);
		if (accountStatement.getFromDate() == null
				|| accountStatement.getToDate() == null) {
			return "accountstatement";
		}
		String userId = (String) req.getSession().getAttribute("userid");
		List<FranchiseeAccountStatementDto> list = accountStatmentService
				.generateAccountStatementReport(Long.valueOf(userId),
						accountStatement.getFromDate(),
						accountStatement.getToDate());
		model.addAttribute("reportList", list);
		return "accountstatement";
	}

	@RequestMapping(value = "/rechargehistory")
	public ModelAndView rechargeHistory() {

		return new ModelAndView("rechargehistory", "accountstatement",
				new AccountStament());
	}

	@RequestMapping(value = "/rechargehistoryretrieve")
	public String rechargeHistoryReport(
			@ModelAttribute AccountStament accountStatement, ModelMap model,
			HttpServletRequest req) {
		String userId = (String) req.getSession().getAttribute("userid");
		List<FranchiseeAccountStatementDto> list = accountStatmentService
				.generateRechargeHistoryReport(Long.valueOf(userId),
						accountStatement.getFromDate(),
						accountStatement.getToDate(),
						accountStatement.getNumber());
		Map<String, FranchiseeRefundRequests> mapList = accountStatmentService
				.getFranchiseeRefundRequest(Long.valueOf(userId));
		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		for (FranchiseeAccountStatementDto dto : list) {
			FranchiseeRefundRequests franchiseeRefundRequests = mapList.get(dto
					.getTransactionId());
			if (CommonConstants.SUCCESS.equalsIgnoreCase(dto.getStatus())) {
				totalCredit = totalCredit + dto.getCreditAmountFranchisee();
				totalDebit = totalDebit + dto.getAmount();
			}
			if (franchiseeRefundRequests != null) {
				dto.setRefunded(true);
			}
		}
		model.addAttribute("accountstatement", accountStatement);
		model.addAttribute("reportList", list);
		model.addAttribute("totalCredit", totalCredit);
		model.addAttribute("totalDebit", totalDebit);
		return "rechargehistory";
	}

}
