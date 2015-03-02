package com.cfeindia.b2bserviceapp.service.recharge.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.service.recharge.DuplicateCheckService;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service("transactionDuplicateCheckChain")
@Transactional
public class TransactionDuplicateCheckChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {

	private static final Logger LOG = LoggerFactory
			.getLogger(TransactionDuplicateCheckChain.class);
	private final TransactionState transactionState = TransactionState.CHECKEDDUPLICATE;

	@Autowired
	private DuplicateCheckService duplicateCheckService;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Checking duplicates for "
					+ transactionTransportBean.getMobileNo() + " "
					+ transactionTransportBean.getConnectionid());
		}
		boolean checkedDuplicate = duplicateCheckService
				.checkDuplicate(transactionTransportBean);
		if (checkedDuplicate) {
			doFail(transactionResponse);
			transactionTransportBean.setErrorCode(1);
			transactionTransportBean.setMessage("Duplicate number "
					+ transactionTransportBean.getMobileNo()
					+ ", Please try after 5 minutes");
			transactionResponse.setMessage(String.format(
					"Duplicate Check Failed - %s", duplicateCheckService
							.getClass().getSimpleName()));
		} else {
			doSuccess(transactionResponse);
		}
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
