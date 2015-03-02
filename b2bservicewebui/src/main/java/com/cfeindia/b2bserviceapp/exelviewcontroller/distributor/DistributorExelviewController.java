package com.cfeindia.b2bserviceapp.exelviewcontroller.distributor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.model.distributor.FundTransferReportDist;
import com.cfeindia.b2bserviceapp.model.franchisee.CashDepositRequestFran;
import com.cfeindia.b2bserviceapp.service.distributor.DistBalTransStatementService;


@RequestMapping("/distributor/**")
@Controller
public class DistributorExelviewController {
	
	@Autowired
	private DistBalTransStatementService distBalTransStatementService;
	@Autowired
	CommonService commonService;
	
	@RequestMapping(value = "/DistFundTransferReport", method = RequestMethod.GET)
	public ModelAndView fundTransfertocurr(
			@ModelAttribute("fundTransferReport") FundTransferReportDist fundTransferReportDist,
			HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		List<DistributorBalanceTransferLog> transferlog = distBalTransStatementService
				.getFranBalTransferStatement(
						fundTransferReportDist.getFromDate(),
						fundTransferReportDist.getToDate(), userId);
		
		return new ModelAndView("DistributorFundTransReportSummary", "list", transferlog);
	}
	@RequestMapping(value = "/DistCashDepositRequest", method = RequestMethod.GET)
	public ModelAndView cashDepositRequests(
			@ModelAttribute CashDepositRequestFran cashDepositRequestFran,
			ModelMap model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userid");
		List<FranchiseeCashDepositRequest> list = commonService
				.trackCashDepositRequests();
		
		return new ModelAndView("DistCashDepositSummary", "list", list);
	}
}
