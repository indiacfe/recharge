package com.cfeindia.b2bserviceapp.controller.customer;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.RetailerCommison;
import com.cfeindia.b2bserviceapp.service.customer.CustomerRefundService;

@Controller
@RequestMapping("/api/**")
public class CustomerRefundController {

	@Autowired
	CustomerRefundService customerRefundService;

	@RequestMapping(value = "/refundrequestcust", method = RequestMethod.GET)
	public String getRefundRequestCust(ModelMap model) {
		return "refundRequestCust";
	}

	@RequestMapping(value = "/refundrequestcust", method = RequestMethod.POST)
	public String postRefundRequestCust(
			@RequestParam("clientTransId") String clientTransId,
			ModelMap model, HttpSession session) {
		CustomerTransactionTransportBean customerTransactionTransportBean = customerRefundService
				.checkTrasactionId(clientTransId);
		String userId = (String) session.getAttribute("userid");
		if (customerTransactionTransportBean != null) {
			String status = customerRefundService.refundRequest(clientTransId,
					Long.valueOf(userId));
			if (status.equalsIgnoreCase(CommonConstants.SUCCESS)) {
				model.addAttribute("success", "Sent your request");
				return "customerSuccessPage";
			} else {
				model.addAttribute("error",
						"Already Requested for this Transaction");
				return "refundRequestCust";
			}
		} else {
			model.addAttribute("error",
					"TID for this Transaction cannot be refunded");
			return "refundRequestCust";
		}

	}

	@RequestMapping(value = "/refundreportcust", method = RequestMethod.GET)
	public String getAllRefunds(ModelMap model, HttpSession session) {
		String userId = (String) session.getAttribute("userid");
		model.addAttribute("refundlist",
				customerRefundService.getAllRefunds(Long.valueOf(userId)));
		return "refundReportCust";
	}

	@RequestMapping(value = "/refundetailscust", method = RequestMethod.GET)
	public String getRefundDetail(
			@RequestParam("clientTransId") String clientTransId,
			ModelMap model, HttpSession session) {
		model.addAttribute("refundetails",
				customerRefundService.getTransactionDetails(clientTransId));
		return "refundDetailsCust";
	}

}