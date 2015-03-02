package com.cfeindia.b2bserviceapp.service.recharge.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service("transactionCheckRetailerEnabledChain")
@Transactional
public class TransactionCheckRetailerEnabledChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {
	private final TransactionState transactionState = TransactionState.PREAUTHORIZATION;

	@Autowired
	private CommonDao commonDao;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		Users users = (Users) commonDao.getEntityByPrimaryKey(Users.class,
				Long.parseLong(transactionTransportBean.getRetailerId()));
		if (users.getEnabled() == 1) {
			doSuccess(transactionResponse);
		} else {
			transactionTransportBean.setErrorCode(1);
			transactionTransportBean
					.setMessage("Retailer is not enabled, Please contact to admin");
			doFail(transactionResponse);
		}
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
