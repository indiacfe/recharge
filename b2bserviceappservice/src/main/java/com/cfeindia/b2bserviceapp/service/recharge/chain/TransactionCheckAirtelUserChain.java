package com.cfeindia.b2bserviceapp.service.recharge.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.entity.AirTelUserDetail;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service("transactionCheckAirtelUserChain")
@Transactional
public class TransactionCheckAirtelUserChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {
	@Autowired
	private CommonDao commonDao;
	private final TransactionState transactionState = TransactionState.CHECKAIRTELUSER;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		if ("mom".equalsIgnoreCase(transactionTransportBean
				.getThirdPartyServiceProvider())
				&& "AIRTEL".equalsIgnoreCase(transactionTransportBean
						.getOperator())) {
			transactionTransportBean
					.setAirtelUserName(getAirtelUserName(transactionTransportBean
							.getFranchiseeMobileNum()));
			if (CommonConstants.AIRTEL_USER_NOT_EXIST
					.equalsIgnoreCase(transactionTransportBean
							.getAirtelUserName())) {
				doFail(transactionResponse);
				transactionTransportBean.setErrorCode(2);
				transactionTransportBean.setErrorcode("2");
				transactionTransportBean
						.setMessage("Airtel product is not configured,Please contact to admin");

			} else {
				doSuccess(transactionResponse);
			}
		} else {
			doSuccess(transactionResponse);
		}

	}

	private String getAirtelUserName(String franchiseeMobileNumber) {
		AirTelUserDetail airTelUserDetail = commonDao
				.getAirtelUserName(franchiseeMobileNumber);
		if (airTelUserDetail != null) {
			return airTelUserDetail.getAirtelUserName();
		} else {
			return CommonConstants.AIRTEL_USER_NOT_EXIST;
		}
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
