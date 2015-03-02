package com.cfeindia.b2bserviceapp.service.recharge.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service
public class TransactionalExceptionHandler {

	@Autowired
	private SMSSendService smsSendService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private JavaMailSender mailSender;
	@Value("${contactus.to.emailid}")
	private String contactUsEmailId;

	// Mail Sending Impl

	private static final Logger LOG = LoggerFactory
			.getLogger(TransactionalExceptionHandler.class);

	public void processException(
			TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse, Throwable e) {
		sendMail(transactionTransportBean, e);

		// sendSMS(transactionTransportBean);
		LOG.error("Transaction Transport Bean: {} ", transactionTransportBean);
	}

	private void sendMail(TransactionTransportBean transactionTransportBean,
			Throwable e) {
		String messageText = "Number " + transactionTransportBean.getMobileNo()
				+ " / " + transactionTransportBean.getConnectionid()
				+ " Operator: " + transactionTransportBean.getOperator()
				+ ", Recharge amount: " + transactionTransportBean.getAmount()
				+ ", id is: " + transactionTransportBean.getTransid()
				+ ",recharge type: "
				+ transactionTransportBean.getRechargeType() + " is failed. "
				+ e;

		try {
			final SimpleMailMessage message = new SimpleMailMessage();
			String cc[] = new String[] { "dileepdixit000@gmail.com","vikas.cool@gmail.com"};
			message.setTo("help@cybertelindia.com");
			message.setCc(cc);
			message.setFrom(contactUsEmailId);
			message.setSubject("Error at recharge on cybertel");
			message.setText(messageText);

			new Thread() {
				public void run() {
					mailSender.send(message);
				}
			}.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void sendSMS(TransactionTransportBean transactionTransportBean) {
		final String smsMessage = "Cybertel Password has been reset Your New Password Is 123456"
				/*
				 * "Number" + transactionTransportBean.getMobileNo() + "/" +
				 * transactionTransportBean.getConnectionid() + "Operator: " +
				 * transactionTransportBean.getOperator() +
				 * ", Recharge amount: " + transactionTransportBean.getAmount()
				 * + ", id is: " + transactionTransportBean.getTransid() +
				 * ",recharge type: "+transactionTransportBean.getRechargeType()
				 * + " is failed.";
				 */
				+ transactionTransportBean.getRechargeType() + " is failed.";
		ThirdPartyDetailObject thirdPartyDetailObject = (ThirdPartyDetailObject) commonService
				.getEntityByPrimaryKey(ThirdPartyDetailObject.class, new Long(
						5l));
		final SMSSendRequest smsSendRequest = new SMSSendRequest();
		smsSendRequest.setApiUrl(thirdPartyDetailObject.getConnectURL());
		smsSendRequest.setUserName(thirdPartyDetailObject.getToken());
		smsSendRequest.setPassword(thirdPartyDetailObject.getToken1());
		smsSendRequest.setSenderId(thirdPartyDetailObject.getToken2());
		smsSendRequest.setDestination("919990669026");
		smsSendRequest.setMessage(smsMessage);
		new Thread() {
			public void run() {
				SMSSendResponse smsSendResponse = smsSendService
						.sendSMS(smsSendRequest);
				commonService.saveEntity(smsSendResponse);
			}
		}.start();

	}

}
