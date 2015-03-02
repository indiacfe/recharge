package com.cfeindia.b2bserviceapp.service.recharge.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.recharge.mobile.BalanceCheckingServiceDao;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.BalanceCheckingService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service("transactionBalanceManageChain")
@Transactional
public class TransactionBalanceManageChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {
	@Autowired
	private BalanceCheckingServiceDao balanceCheckingServiceDao;
	@Autowired
	private CommonDao commonDao;

	private final TransactionState transactionState = TransactionState.BALANCEMANAGED;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		if (transactionTransportBean.getErrorCode() == 2) {
			doFail(transactionResponse);
			FranchiseeCurrBal franchiseeCurrbal = balanceCheckingServiceDao
					.getFrachiseeCurrbalObj(Long
							.valueOf(transactionTransportBean.getRetailerId()));
			transactionTransportBean.setStatus(CommonConstants.FAILED);
			refundToRetailer(transactionTransportBean, transactionResponse,
					franchiseeCurrbal);
			transactionTransportBean.setCreditAmountFranchisee(0);
			transactionTransportBean.setRetailerPreBal(transactionResponse
					.getRetailerOldBalance());
			transactionTransportBean.setRetailerNewBal(transactionResponse
					.getRetailerOldBalance());

			if (transactionResponse.getDistributoLogId() != 0) {
				deductFromDistributor(transactionResponse, franchiseeCurrbal);
			}
		} else {
			doSuccess(transactionResponse);
		}

	}

	private void deductFromDistributor(TransactionResponse transactionResponse,
			FranchiseeCurrBal franchiseeCurrbal) {
		DistributorCurrbal distributorCurrbal = balanceCheckingServiceDao
				.getDistributorCurrbalObj(franchiseeCurrbal.getCreatorId());
		distributorCurrbal.setB2bCurrAcAdUnitBal(transactionResponse
				.getDistributorOldBalance());

		DistributorBalanceTransferLog distributorBalanceTransferLog = (DistributorBalanceTransferLog) commonDao
				.getEntityByPrimaryKey(DistributorBalanceTransferLog.class,
						transactionResponse.getDistributoLogId());
		commonDao.save(distributorCurrbal);
		commonDao.delete(distributorBalanceTransferLog);
	}

	private void refundToRetailer(
			TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse,
			FranchiseeCurrBal franchiseeCurrbal) {
		if (CommonConstants.ADUNIT.equalsIgnoreCase(transactionResponse
				.getRetailerAccount())) {
			franchiseeCurrbal.setB2bcurrbaladunit(transactionResponse
					.getRetailerOldBalance());

		} else {
			franchiseeCurrbal.setB2bcurrbal(transactionResponse
					.getRetailerOldBalance());
		}
		commonDao.save(franchiseeCurrbal);
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
