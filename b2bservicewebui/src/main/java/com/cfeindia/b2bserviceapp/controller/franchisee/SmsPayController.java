package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.entity.SmsRechargeDetail;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.franchisee.RefundRequestFranchiseeService;
import com.cfeindia.b2bserviceapp.service.franchisee.SmsPayService;
import com.cfeindia.b2bserviceapp.service.franchisee.SmsPayServiceImpl;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;
import com.cfeindia.b2bserviceapp.transport.bean.SmsOperatorBean;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Controller
public class SmsPayController {
	@Autowired
	private SmsPayService smsPayService;
	@Autowired
	RechargeTransactionService rechargeTransactionService;
	@Autowired
	SMSSendService smsSendService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private FranchiseeInfoService franchiseeInfoService;

	@Autowired
	private RefundRequestFranchiseeService refundRequestFranchiseeService;
	Map<Integer, SmsOperatorBean> operatorMap;

	{
		operatorMap = SmsPayServiceImpl.setValues();
	}

	// private static Map<Integer, SmsOperatorBean> operators = new
	// HashMap<Integer, SmsOperatorBean>();

	@RequestMapping(value = "/sms/pay", method = RequestMethod.GET)
	public @ResponseBody
	String smsReacharge(@RequestParam(required = false) String send,
			@RequestParam(required = false) String msg,
			@RequestParam(required = false) String dest,
			@RequestParam(required = false) String Send,
			@RequestParam(required = false) String VN,
			@RequestParam(required = false) String sTime,
			@RequestParam(required = false) String Time,
			@RequestParam(required = false) String Rawmessage,
			@RequestParam(required = false) String MID) {

		if (send == null)
			send = Send;
		if (dest == null)
			dest = VN;
		if (msg == null)
			msg = Rawmessage;

		if (sTime == null)
			sTime = Time;

		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();

		SmsRechargeDetail smsRechargeDetail = new SmsRechargeDetail();
		smsRechargeDetail.setSender(send.substring(2));
		smsRechargeDetail.setMessage(msg);
		smsRechargeDetail.setDestination(dest);
		smsRechargeDetail.setVn(VN);
		smsRechargeDetail.setStime(sTime);
		smsRechargeDetail.setTime(Time);
		smsRechargeDetail.setMid(MID);
		smsRechargeDetail.setRawMessage(Rawmessage);
		smsPayService.saveSmsDetails(smsRechargeDetail);
		SmsOperatorBean smsopBeanoperator = null;
		if ((msg != null)) {
			String msgarr[] = msg.split("\\.");
			if ("BAL".equalsIgnoreCase(msg.trim())) {

				checkBalance(send);
			} else if (msgarr.length == 2)

			{
				if ("REFUND".equalsIgnoreCase(msgarr[0])) {
					if (msgarr[1].length() == 25) {
						String frannumber = msgarr[1].substring(3, 13);

						if (frannumber.equals(send.substring(2))) {

							String userResult = smsPayService.validateUser(send
									.substring(2));

							if (CommonConstants.SUCCESS.equals(userResult)) {
								TransactionTransportBean transactionTransport = refundRequestFranchiseeService
										.checkTransactionId(msgarr[1]);
								if (transactionTransportBean != null) {

									refundRequestFranchiseeService
											.refundRequest(
													transactionTransport.getTransid(),
													Long.valueOf(commonService
															.getUserId(frannumber)));
									String retrmess = String
											.format("Your refund request has been submitted successfully.Your request no is %s.",
													transactionTransport
															.getMobileNo());
									sendSMS(frannumber, retrmess);

								}

							} else {
								sendSMS(frannumber,
										"Your refund request has been rejected.");
							}
						}
					}
				}

			}

			else if (msgarr.length == 3) {
				String smsResult = smsPayService.validateMessage(msg);

				if (CommonConstants.SUCCESS.equalsIgnoreCase(smsResult)) {
					String userResult = smsPayService.validateUser(send
							.substring(2));

					if (CommonConstants.SUCCESS.equalsIgnoreCase(userResult)) {

						smsopBeanoperator = operatorMap.get(Integer
								.parseInt(msgarr[0]));
						if (smsopBeanoperator != null) {
							if (CommonConstants.SUCCESS
									.equalsIgnoreCase(smsPayService
											.validateMessageLevel2(msg,
													smsopBeanoperator
															.getRechargeType()))) {
								transactionTransportBean.setRetailerId(String
										.valueOf(commonService.getUserId(send
												.substring(2))));
								transactionTransportBean
										.setTransactionName(smsopBeanoperator
												.getTransactionName());
								transactionTransportBean
										.setFranchiseeMobileNum(send
												.substring(2));
								transactionTransportBean
										.setOperator(smsopBeanoperator
												.getOperatorName());
								transactionTransportBean.setMobileNo(msgarr[1]);
								transactionTransportBean
										.setConnectionid(msgarr[1]);
								transactionTransportBean.setAmount(Double
										.parseDouble(msgarr[2]));
								transactionTransportBean
										.setRechargeType(smsopBeanoperator
												.getRechargeType());
								if ("MOBILE_PREPAID"
										.equalsIgnoreCase(smsopBeanoperator
												.getRechargeType())) {
									transactionTransportBean.setCircleCode("5");
								}

								doSmsRecharge(transactionTransportBean, send);
							}
						}
					}
				}

			}
		}
		return "DONE";

	}

	private void doSmsRecharge(
			TransactionTransportBean transactionTransportBean, String send) {
		String tempMessage = null;
		try {
			rechargeTransactionService
					.doRechargeService(transactionTransportBean);

		} catch (Exception e) {

			e.printStackTrace();
			transactionTransportBean.setMessage("System Error");
		}

		if (transactionTransportBean.getErrorCode() > 0
				|| transactionTransportBean.getThirdpartytransid() == null) {

			tempMessage = String
					.format("Your Transaction could not be completed due to %s. The TID is %s for your reference. Please try again with correct input.",
							transactionTransportBean.getMessage(),
							transactionTransportBean.getTransid());
			sendSMS(send.substring(2), tempMessage);

		} else {

			Integer userId = commonService.getUserId(send.substring(2));
			FranchiseeInfo franchiseeInfo = franchiseeInfoService
					.getFranchiseeInfo(userId.toString());

			tempMessage = String
					.format("Recharge Of Rs. %.0f successful for  %s connection %s via TID %s. Your balance is Rs. %.2f",
							transactionTransportBean.getAmount(),
							transactionTransportBean.getOperator(),
							transactionTransportBean.getMobileNo(),
							transactionTransportBean.getTransid(),
							franchiseeInfo.getB2bCurrentBalance());
			sendSMS(send.substring(2), tempMessage);

		}

	}

	private void checkBalance(String userName) {

		String userResult = smsPayService.validateUser(userName.substring(2));
		if (CommonConstants.SUCCESS.equalsIgnoreCase(userResult)) {
			Integer userId = commonService.getUserId(userName.substring(2));
			FranchiseeInfo franchiseeInfo = franchiseeInfoService
					.getFranchiseeInfo(userId.toString());
			String tempMessage = String.format(
					"Balance in your Cybertel Account is Rs. %.2f",
					franchiseeInfo.getB2bCurrentBalance());

			sendSMS(userName.substring(2), tempMessage);

		}
	}

	private void sendSMS(String mobNumber, String tempMessage) {
		ThirdPartyDetailObject thirdPartyDetailObject = (ThirdPartyDetailObject) commonService
				.getEntityByPrimaryKey(ThirdPartyDetailObject.class, new Long(
						5l));
		SMSSendRequest smsSendRequest = new SMSSendRequest();
		smsSendRequest.setApiUrl(thirdPartyDetailObject.getConnectURL());
		smsSendRequest.setUserName(thirdPartyDetailObject.getToken());
		smsSendRequest.setPassword(thirdPartyDetailObject.getToken1());
		smsSendRequest.setSenderId(thirdPartyDetailObject.getToken2());
		smsSendRequest.setDestination("91" + mobNumber);
		smsSendRequest.setMessage(tempMessage);
		SMSSendResponse smsSendResponse = smsSendService
				.sendSMS(smsSendRequest);
		commonService.saveEntity(smsSendResponse);
	}

}
