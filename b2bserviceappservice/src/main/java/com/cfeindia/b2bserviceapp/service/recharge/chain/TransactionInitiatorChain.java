package com.cfeindia.b2bserviceapp.service.recharge.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service("transactionInitiatorChain")
@Transactional
public class TransactionInitiatorChain extends AbstractTransactionProcessorChain
				implements TransactionProcessorChain {
	private final TransactionState transactionState =  TransactionState.START;
	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		Long id = commonDao.save(transactionTransportBean);
		transactionTransportBean.setId(id);
		doSuccess(transactionResponse);
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
