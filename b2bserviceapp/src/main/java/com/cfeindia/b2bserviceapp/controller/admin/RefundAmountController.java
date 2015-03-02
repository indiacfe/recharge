package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.admin.service.RefundAmountService;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.RefundAmountDto;

@RequestMapping("/admin/**")
@Controller
public class RefundAmountController {
	@Autowired
	RefundAmountService refundAmountService;

	@RequestMapping(value = "/searchtransactionpage", method = RequestMethod.GET)
	public String searchTransactionPage() {

		return "searchtransactionid";
	}

	@RequestMapping(value = "/transactiondetail", method = RequestMethod.POST)
	public String transactionDetail(@RequestParam String tid, ModelMap model) {
		RefundAmountDto detailForRefund = refundAmountService
				.searchTransactionId(tid);
		if (CommonConstants.SUCCESS.equals(detailForRefund.getStatus())) {
			List<RefundAmountDto> tidDetail = new ArrayList<RefundAmountDto>();
			tidDetail.add(detailForRefund);
			model.addAttribute("detailForRefund", tidDetail);
			return "searchtransactionid";
		} else {
			String errorMessage = "TID for This Transaction was a failure or already refunded.";
			if(detailForRefund.getErrorMessage()!=null) {
				errorMessage += " or " + detailForRefund.getErrorMessage();
			}
			model.addAttribute("error",  errorMessage);
		}
		return "searchtransactionid";
	}

	@RequestMapping(value = "/refundamount")
	public String refundAmount(@RequestParam String franId,
			@RequestParam Double amount, @RequestParam Double creditAmount,
			@RequestParam Long id, @RequestParam String status,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String senderId = (String) session.getAttribute("userid");
		Double companyBalance = (Double) session.getAttribute("amount");
		Double refundedAmount = amount - creditAmount;
		if (CommonConstants.SUCCESS.equalsIgnoreCase(status)) {
			if (companyBalance >= refundedAmount) {
				refundAmountService.refundAmount(id,senderId,refundedAmount, franId);
				return "redirect:successpage";
			} else {
				return "failureAdmin";
			}
		} else {
			return "failureAdmin";
		}

	}

	@RequestMapping(value = "/allrefundrequests", method = RequestMethod.GET)
	public String showAllRefundRequests(ModelMap model) {
		List<RefundAmountDto> refundAmountDtoList = refundAmountService
				.getAllRefundRequests();
		model.addAttribute("detailForRefund", refundAmountDtoList);

		return "allrefundrequestsadmin";
	}

	@RequestMapping(value = "/successpage")
	public String finalPage(ModelMap model) {
		model.addAttribute("success", "refunded.");
		return "successpageadmin";
	}

}
