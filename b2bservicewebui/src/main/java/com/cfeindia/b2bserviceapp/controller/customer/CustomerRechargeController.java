package com.cfeindia.b2bserviceapp.controller.customer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cfeindia.b2bserviceapp.common.CacheManager;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.B2bOperatorsCodeBean;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.service.customer.CustomerRechargeService;
import com.cfeindia.b2bserviceapp.service.customer.OperatorsNamServiceTypeService;
import com.cfeindia.b2bserviceapp.service.customer.RequestValidationService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Controller
@RequestMapping("/api/**")
public class CustomerRechargeController {
	@Autowired
	private RequestValidationService requestValidationService;
	@Autowired
	private OperatorsNamServiceTypeService operatorsNamServiceTypeService;
	@Autowired
	private RechargeTransactionService rechargeTransactionService;
	@Autowired
	private CustomerRechargeService customerRechargeService;
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/recharge", method = RequestMethod.GET)
	public @ResponseBody
	String generateToken(
			HttpServletRequest request,
			@RequestParam String token,
			@RequestParam String agentId,
			@RequestParam String account,
			@RequestParam Double amount,
			@RequestParam String opcode,
			@RequestParam String clientTransId,
			@RequestParam(value = "originator", required = false) String originator) {
		String ip = request.getRemoteAddr();
		StringBuilder response = new StringBuilder();
		TransactionTransportBean transactionTransport = new TransactionTransportBean();
		String validResult = requestValidationService.getValidate(ip, token,
				agentId, account);
		try {

			if (CommonConstants.SUCCESS.equalsIgnoreCase(validResult)) {
				CustomerCurrentBalance currentBalance = requestValidationService
						.getBalance(agentId);
				if (currentBalance.getCurrAcBalance() >= amount) {
					B2bOperatorsCodeBean b2bOperatorsCodeBean = operatorsNamServiceTypeService
							.getServiceType(opcode);
					if (b2bOperatorsCodeBean != null
							&& checkOriginatorName(
									b2bOperatorsCodeBean.getRechargeType(),
									b2bOperatorsCodeBean.getOperatorName(),
									originator)) {
						if (b2bOperatorsCodeBean != null) {
							transactionTransport.setRetailerId(agentId);
							transactionTransport
									.setRechargeType(b2bOperatorsCodeBean
											.getRechargeType());
							transactionTransport
									.setOperator(b2bOperatorsCodeBean
											.getOperatorName());
							transactionTransport
									.setTransactionName(b2bOperatorsCodeBean
											.getTransactionName());
							transactionTransport.setMobileNo(account);
							transactionTransport.setConnectionid(account);
							transactionTransport
									.setClientTransId(clientTransId);
							transactionTransport.setAmount(amount);

							customerRechargeService
									.doRechargeService(transactionTransport);

							if (transactionTransport.getErrorCode() > 0
									|| transactionTransport
											.getThirdpartytransid() == null) {
								if (transactionTransport.getMessage() != null) {
									response.append(
											transactionTransport.getStatus())
											.append("~")
											.append(transactionTransport
													.getTransid())
											.append("~")
											.append(transactionTransport
													.getCreatedAt())
											.append("~")
											.append(transactionTransport
													.getErrorCode())
											.append("~").append("FAILED");
								}

							} else {
								response.append(
										transactionTransport.getStatus())
										.append("~")
										.append(transactionTransport
												.getTransid())
										.append("~")
										.append(transactionTransport
												.getCreatedAt())
										.append("~")
										.append(transactionTransport
												.getResponsecode())
										.append("~")
										.append(transactionTransport
												.getMessage());
							}

						}
					} else {
						response.append("FAILED")
								.append("~")
								.append(transactionTransport.getTransid())
								.append("~")
								.append(transactionTransport
										.getThirdpartytransid())
								.append("~")
								.append(transactionTransport.getCreatedAt())
								.append("~")
								.append(transactionTransport.getErrorCode())
								.append("~")
								.append("You can't recharge for this operator type, Please contact with admin.");
					}

				} else {
					response.append(transactionTransport.getStatus())
							.append("~")
							.append(transactionTransport.getTransid())
							.append("~")
							.append(transactionTransport.getCreatedAt())
							.append("~")
							.append(transactionTransport.getErrorCode())
							.append("~")
							.append(transactionTransport.getErrorMessage());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.append(transactionTransport.getStatus()).append("~")
					.append(transactionTransport.getTransid()).append("~")
					.append(transactionTransport.getCreatedAt()).append("~")
					.append(transactionTransport.getErrorCode()).append("~")
					.append(transactionTransport.getErrorMessage());
		}
		return response.toString();
	}

	private boolean checkOriginatorName(String rechargeType,
			String operatorName, String originatore) {
		String thirdPartyName = commonService.getThirdPartyAPISelection(
				rechargeType, operatorName);
		if (originatore == null || "null".equalsIgnoreCase(originatore)) {
			return true;
		} else {
			if (originatore.equalsIgnoreCase(thirdPartyName)) {
				return false;
			} else {
				return true;
			}
		}

	}
}
