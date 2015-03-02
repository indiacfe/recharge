package com.cfeindia.b2bserviceapp.service.recharge.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service
public class TransactionCompletionChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {
	@Autowired
	private CommonDao commonDao;

	private final TransactionState transactionState = TransactionState.COMPLETED;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		transactionTransportBean.setTransactionState(getTransactionState());
		commonDao.saveOrUpdateEntity(transactionTransportBean);
		doSuccess(transactionResponse);
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
