package com.cfeindia.b2bserviceapp.controller.distributor;

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

import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.model.distributor.FundTransferReportDist;
import com.cfeindia.b2bserviceapp.service.distributor.DistBalTransStatementService;

@RequestMapping("/distributor/**")
@Controller
public class FundTransferReportDistController {

	@Autowired
	private DistBalTransStatementService distBalTransStatementService;

	@RequestMapping(value = "/fundtransfer")
	public ModelAndView fundTransferRetailer() {
		return new ModelAndView("fundtransfer", "fundTransferReport",
				new FundTransferReportDist());
	}

	@RequestMapping(value = "/fundTransferReport", method = RequestMethod.GET)
	public String fundTransfertocurr(
			@ModelAttribute("fundTransferReport") FundTransferReportDist fundTransferReportDist,
			HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		List<DistributorBalanceTransferLog> transferlog = distBalTransStatementService
				.getFranBalTransferStatement(
						fundTransferReportDist.getFromDate(),
						fundTransferReportDist.getToDate(), userId);
		model.addAttribute("distBalanceTransferLog", transferlog);
		return "fundtransfer";
	}

}
