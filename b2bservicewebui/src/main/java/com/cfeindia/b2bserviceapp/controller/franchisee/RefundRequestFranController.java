package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.entity.RetailerCommison;
import com.cfeindia.b2bserviceapp.service.franchisee.RefundRequestFranchiseeService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/franchisee/**")
@Controller
public class RefundRequestFranController {
	@Autowired
	private RefundRequestFranchiseeService refundRequestFranchiseeService;
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/refundrequesthome")
	public String refundReport() {

		return "refundrequest";
	}

	@RequestMapping(value = "/refundrequestfran", method = RequestMethod.POST)
	public String requestRefund(@RequestParam String tid, ModelMap model,
			HttpServletRequest request) {
		TransactionTransportBean transactionTransportBean = refundRequestFranchiseeService
				.checkTransactionId(tid);
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		FranchiseeRefundRequests refundRequests = refundRequestFranchiseeService
				.getRefundRequestByTransactionId(tid, Long.valueOf(userid));
		if (transactionTransportBean != null && refundRequests == null) {
			refundRequestFranchiseeService.refundRequest(tid,
					Long.valueOf(userid));
			model.addAttribute("success", "sent your request");
			return "successpagefran";
		} else {
			model.addAttribute("error",
					"TID for this Transaction cannot be refunded");
			return "failurefran";
		}
	}

	@RequestMapping(value = "/refundalllist", method = RequestMethod.GET)
	public String requestRefundList(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		model.addAttribute("refundlist", refundRequestFranchiseeService
				.getRefundAllList(Long.valueOf(userid)));
		return "refundlistsuccess";
	}

	@RequestMapping(value = "/refundalllistdetails", method = RequestMethod.GET)
	public String requestRefundtranDetails(@RequestParam String tid,
			ModelMap model) {

		model.addAttribute("refundetails", refundRequestFranchiseeService
				.getTransactionDetailsservice(tid));

		return "refundlistdetails";
	}

	@RequestMapping(value = "/retailercommision", method = RequestMethod.GET)
	public String retailerCommision(ModelMap model) {
		List<RetailerCommison> retailerCommisons = refundRequestFranchiseeService
				.getRetailerCommisions();
		model.addAttribute("commission", retailerCommisons);
		return "commisondetails";
	}

}
