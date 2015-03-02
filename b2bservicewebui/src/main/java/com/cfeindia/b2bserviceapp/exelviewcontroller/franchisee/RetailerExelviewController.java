package com.cfeindia.b2bserviceapp.exelviewcontroller.franchisee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.entity.RetailerCommison;
import com.cfeindia.b2bserviceapp.model.franchisee.AccountStament;
import com.cfeindia.b2bserviceapp.service.franchisee.FranAccountStatmentService;
import com.cfeindia.b2bserviceapp.service.franchisee.RefundRequestFranchiseeService;

@RequestMapping("/franchisee/**")
@Controller
public class RetailerExelviewController {

	@Autowired
	FranAccountStatmentService accountStatmentService;
	@Autowired
	private RefundRequestFranchiseeService refundRequestFranchiseeService;

	@RequestMapping(value = "/RetailerAcStatement")
	public ModelAndView accountStatementReport(
			@ModelAttribute AccountStament accountStatement, ModelMap model,
			HttpServletRequest req) {
		model.addAttribute("accountstatement", accountStatement);
		if (accountStatement.getFromDate() == null
				|| accountStatement.getToDate() == null) {
			return new ModelAndView("accountstatement");
		}
		String userId = (String) req.getSession().getAttribute("userid");
		List<FranchiseeAccountStatementDto> list = accountStatmentService
				.generateAccountStatementReport(Long.valueOf(userId),
						accountStatement.getFromDate(),
						accountStatement.getToDate());

		return new ModelAndView("RetailerAcStatementSummary", "list", list);
	}

	@RequestMapping(value = "/RetailerRechargeHistry")
	public ModelAndView rechargeHistoryReport(
			@ModelAttribute AccountStament accountStatement, ModelMap model,
			HttpServletRequest req,HttpServletResponse response) {

		String userId = (String) req.getSession().getAttribute("userid");
		List<FranchiseeAccountStatementDto> list = accountStatmentService
				.generateRechargeHistoryReport(Long.valueOf(userId),
						accountStatement.getFromDate(),
						accountStatement.getToDate(),
						accountStatement.getNumber());

		return new ModelAndView("RetailerRechargeHistorySummary", "list", list);
	}

	@RequestMapping(value = "/RetailerRefundRequest", method = RequestMethod.GET)
	public ModelAndView requestRefundList(ModelMap model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		return new ModelAndView("RetailerRefundRequestListSummary", "list",
				refundRequestFranchiseeService.getRefundAllList(Long
						.valueOf(userid)));
	}
	
	@RequestMapping(value = "/RetailerCommissionRequest", method = RequestMethod.GET)
	public ModelAndView retailerCommision(ModelMap model) {
		List<RetailerCommison> retailerCommisons = refundRequestFranchiseeService
				.getRetailerCommisions();
		model.addAttribute("commission", retailerCommisons);
		return new ModelAndView("RetailerCommissionSummary", "list", retailerCommisons);
	}
}
