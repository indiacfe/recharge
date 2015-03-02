package com.cfeindia.b2bserviceapp.service.recharge.chain;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Transactional
@Service("moneyTransTransactionThirdPartyProcessingChain")
public class MoneyTransTransactionThirdPartyProcessingChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {

	private final TransactionState transactionState = TransactionState.THIRDPARTYCALLED;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		/*try {
			
			 transactionResponse.getMoneyTransETransactionRequestService().doTransaction(transactionTransportBean);
		} catch (MalformedURLException e) {
			transactionTransportBean.setErrorCode(2);
			transactionTransportBean.setErrorcode("2");
			transactionTransportBean.setMessage("System Error");
			e.printStackTrace();
		} catch (IOException e) {
			transactionTransportBean.setErrorCode(2);
			transactionTransportBean.setErrorcode("2");
			transactionTransportBean.setMessage("System Error");
			e.printStackTrace();
		}
		doSuccess(transactionResponse);*/
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
