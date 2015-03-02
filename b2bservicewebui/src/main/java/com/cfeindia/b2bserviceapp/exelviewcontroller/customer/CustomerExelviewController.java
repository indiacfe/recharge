package com.cfeindia.b2bserviceapp.exelviewcontroller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dto.accountstatement.CustomerAccountStatementDto;
import com.cfeindia.b2bserviceapp.model.customer.CustomerAccountStatement;
import com.cfeindia.b2bserviceapp.service.customer.CustAccountStatementService;


@Controller
@RequestMapping("/api/**")
public class CustomerExelviewController {
	
	@Autowired
	private CustAccountStatementService custAccountStatementService;
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/CustomerAcStmt", method = RequestMethod.POST)
	public ModelAndView accountStatementRetrive(
			@ModelAttribute CustomerAccountStatement customerAccountStatement,
			ModelMap model, HttpServletRequest req) {
		model.addAttribute("customerAccountStatement", customerAccountStatement);
		if (customerAccountStatement.getFromDate() == null
				|| customerAccountStatement.getToDate() == null) {
			return new ModelAndView("customerAccountStatement");
		}
		String userId = (String) req.getSession().getAttribute("userid");
		List<CustomerAccountStatementDto> list = custAccountStatementService
				.generateAccountStatementReport(Long.valueOf(userId),
						customerAccountStatement.getFromDate(),
						customerAccountStatement.getToDate());
		
		return new ModelAndView("CustomerAcStatementSummary", "list", list);
	}
	@RequestMapping(value = "/CustomerRechargeRistory", method = RequestMethod.POST)
	public ModelAndView rechargeHistoryReport(
			@ModelAttribute CustomerAccountStatement customerAccountStatement,
			ModelMap model, HttpServletRequest req) {
		String userId = (String) req.getSession().getAttribute("userid");
		List<CustomerAccountStatementDto> list = custAccountStatementService
				.generateRechargeHistoryReport(Long.valueOf(userId),
						customerAccountStatement.getFromDate(),
						customerAccountStatement.getToDate(),
						customerAccountStatement.getNumber());
		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		for (CustomerAccountStatementDto dto : list) {
			totalCredit = totalCredit + dto.getCreditAmountCustomer();
			totalDebit = totalDebit + dto.getAmount();
		}
		
		return new ModelAndView("CustomerRechargeHistorySummary", "list", list);
	}

}
