package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.ArrayList;
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
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.dto.RefundAmountDto;
import com.cfeindia.b2bserviceapp.service.admin.RefundAmountService;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;

@RequestMapping("/admin/**")
@Controller
public class RefundAmountController {
	@Autowired
	RefundAmountService refundAmountService;

	@Autowired
	private CommonService commonService;

	@Autowired
	SMSSendService smsSendService;

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
			if (detailForRefund.getErrorMessage() != null) {
				errorMessage += " or " + detailForRefund.getErrorMessage();
			}
			model.addAttribute("error", errorMessage);
		}
		return "searchtransactionid";
	}

	@RequestMapping(value = "/refundamount")
	public String refundAmount(@RequestParam String franId,
			@RequestParam Double amount, @RequestParam Double creditAmount,
			@RequestParam Long id, @RequestParam String status,
			@RequestParam String thirdPartyServiceProviderName, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String senderId = (String) session.getAttribute("userid");
		Double companyBalance = (Double) session.getAttribute("amount");
		Double refundedAmount = amount - creditAmount;
		if (CommonConstants.SUCCESS.equalsIgnoreCase(status)
				|| "ERROR".equalsIgnoreCase(status)) {
			if (companyBalance >= refundedAmount) {
				refundAmountService.refundAmount(id, senderId, refundedAmount,
						franId, thirdPartyServiceProviderName);
				try {
					String mobileNumber = commonService.getUsername(franId);
					final String mobNumber = mobileNumber;
					final String tempMessage = "Cybertel Trasaction of Refund done from company to your cybertel account of amount  "
							+ amount;
					new Thread() {
						public void run() {
							sendSMS(mobNumber, tempMessage);
						};
					}.start();
				} catch (Exception e) {
					e.printStackTrace();
				}

				return "redirect:successpage";
			} else {
				return "failureAdmin";
			}
		} else {
			return "failureAdmin";
		}

	}

	private void sendSMS(String mobileNumber, String message) {
		ThirdPartyDetailObject thirdPartyDetailObject = (ThirdPartyDetailObject) commonService
				.getEntityByPrimaryKey(ThirdPartyDetailObject.class, new Long(
						5l));
		SMSSendRequest smsSendRequest = new SMSSendRequest();
		smsSendRequest.setApiUrl(thirdPartyDetailObject.getConnectURL());
		smsSendRequest.setUserName(thirdPartyDetailObject.getToken());
		smsSendRequest.setPassword(thirdPartyDetailObject.getToken1());
		smsSendRequest.setSenderId(thirdPartyDetailObject.getToken2());
		smsSendRequest.setDestination("91" + mobileNumber);
		smsSendRequest.setMessage(message);
		SMSSendResponse smsSendResponse = smsSendService
				.sendSMS(smsSendRequest);
		commonService.saveEntity(smsSendResponse);
	}

	@RequestMapping(value = "/rejected", method = RequestMethod.POST)
	public String rejected(@RequestParam Long id, @RequestParam String remark,
			HttpServletRequest request, ModelMap model) {
		refundAmountService.rejectedRemark(id, remark);
		/*
		 * try { String mobileNumber = commonService.getUsername(id+""); final
		 * String mobNumber = mobileNumber; final String tempMessage =
		 * "Cybertel Refund request rejected with comment  " + remark; new
		 * Thread() { public void run() { sendSMS(mobNumber, tempMessage); };
		 * }.start(); } catch(Exception e) { e.printStackTrace(); }
		 */
		List<RefundAmountDto> refundAmountDtoList = refundAmountService
				.getAllRefundRequests();
		model.addAttribute("detailForRefund", refundAmountDtoList);
		return "allrefundrequestsadmin";
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

	@RequestMapping(value = "/rejectedRefundRequest")
	public String rejectRefundRequest() {
		return "rejectedrefundrequest";
	}

	@RequestMapping(value = "/rejectedRefundRequestList", method = RequestMethod.GET)
	public String showRejectedRefundRequestList(@RequestParam String fromDate,
			@RequestParam String toDate, ModelMap model) {
		List<RefundAmountDto> rejectedRefundDtoList = refundAmountService
				.getAllRejctedRefundRequests(fromDate, toDate);
		model.addAttribute("detailForRejectedRefundList", rejectedRefundDtoList);
		return "rejectedrefundrequest";
	}
}
