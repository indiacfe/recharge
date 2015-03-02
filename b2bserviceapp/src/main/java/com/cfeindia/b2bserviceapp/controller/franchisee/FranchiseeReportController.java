package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.accountstatement.Dto.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.franchisee.model.AccountStament;
import com.cfeindia.b2bserviceapp.franchisee.service.FranAccountStatmentService;

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
			@ModelAttribute AccountStament accountStatement, ModelMap model, HttpServletRequest req) {
		String userId = (String)req.getSession().getAttribute("userid");
		List<FranchiseeAccountStatementDto> list = accountStatmentService
				.generateAccountStatementReport(Long.valueOf(userId),accountStatement.getFromDate(),
						accountStatement.getToDate());
		model.addAttribute("accountstatement", accountStatement);
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
			@ModelAttribute AccountStament accountStatement, ModelMap model, HttpServletRequest req) {
		String userId = (String)req.getSession().getAttribute("userid");
		List<FranchiseeAccountStatementDto> list = accountStatmentService
				.generateRechargeHistoryReport(Long.valueOf(userId),accountStatement.getFromDate(),
						accountStatement.getToDate(), accountStatement.getNumber());
		model.addAttribute("accountstatement", accountStatement);
		model.addAttribute("reportList", list);
		return "rechargehistory";
	}	
}
