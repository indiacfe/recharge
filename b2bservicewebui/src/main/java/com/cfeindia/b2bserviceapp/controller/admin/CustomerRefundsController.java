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
import com.cfeindia.b2bserviceapp.service.admin.AdminCustomerRefundService;
import com.cfeindia.b2bserviceapp.service.admin.RefundAmountService;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;

@Controller
@RequestMapping("/admin/**")
public class CustomerRefundsController {
	@Autowired
	private AdminCustomerRefundService customerRefundService;

	@Autowired
	private CommonService commonService;

	@Autowired
	SMSSendService smsSendService;

	@RequestMapping(value = "/searchcustomertransaction", method = RequestMethod.GET)
	public String searchTransactionPage() {

		return "searchcustomertransactionid";
	}

	@RequestMapping(value = "/searchcustomertransactiondetails", method = RequestMethod.POST)
	public String transactionDetail(@RequestParam String tid, ModelMap model) {
		RefundAmountDto detailForRefund = customerRefundService
				.searchcustomerTransactionId(tid);
		if (CommonConstants.SUCCESS.equalsIgnoreCase(detailForRefund
				.getStatus())
				|| CommonConstants.ERROR.equalsIgnoreCase(detailForRefund
						.getStatus())) {
			List<RefundAmountDto> tidDetail = new ArrayList<RefundAmountDto>();
			tidDetail.add(detailForRefund);
			model.addAttribute("detailForRefund", tidDetail);
			return "allCustomerRefundRequest";
		} else {
			String errorMessage = "TID for This Transaction was a failure or already refunded.";
			if (detailForRefund.getErrorMessage() != null) {
				errorMessage += " or " + detailForRefund.getErrorMessage();
			}
			model.addAttribute("error", errorMessage);
			return "searchcustomertransactionid";
		}

	}

	@RequestMapping(value = "/allrefundrequestscust", method = RequestMethod.GET)
	public String showAllRefundRequests(ModelMap model) {
		List<RefundAmountDto> refundAmountDtoList = customerRefundService
				.getAllRefundRequests();
		model.addAttribute("detailForRefund", refundAmountDtoList);

		return "allCustomerRefundRequest";
	}

	@RequestMapping(value = "/customerrefundamount")
	public String refundAmount(@RequestParam String custId,
			@RequestParam Double amount, @RequestParam Double creditAmount,
			@RequestParam Long id, @RequestParam String status,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String senderId = (String) session.getAttribute("userid");
		Double companyBalance = (Double) session.getAttribute("amount");
		Double refundedAmount = amount - creditAmount;
		if (CommonConstants.SUCCESS.equalsIgnoreCase(status)
				|| "ERROR".equalsIgnoreCase(status)) {
			if (companyBalance >= refundedAmount) {
				customerRefundService.refundAmount(id, senderId,
						refundedAmount, custId);
				try {
					String mobileNumber = commonService.getUsername(custId);
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

	@RequestMapping(value = "/rejectrefund", method = RequestMethod.POST)
	public String rejected(@RequestParam Long id, @RequestParam String remark,
			HttpServletRequest request, ModelMap model) {
		customerRefundService.rejectedRemark(id, remark);
		/*
		 * try { String mobileNumber = commonService.getUsername(id+""); final
		 * String mobNumber = mobileNumber; final String tempMessage =
		 * "Cybertel Refund request rejected with comment  " + remark; new
		 * Thread() { public void run() { sendSMS(mobNumber, tempMessage); };
		 * }.start(); } catch(Exception e) { e.printStackTrace(); }
		 */
		List<RefundAmountDto> refundAmountDtoList = customerRefundService
				.getAllRefundRequests();
		model.addAttribute("detailForRefund", refundAmountDtoList);
		return "allrefundrequestsadmin";
	}

}
