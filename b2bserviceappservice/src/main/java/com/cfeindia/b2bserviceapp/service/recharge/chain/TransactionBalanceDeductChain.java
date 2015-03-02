package com.cfeindia.b2bserviceapp.service.recharge.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.recharge.mobile.BalanceCheckingServiceDao;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.BalanceCheckingService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service("transactionBalanceDeductChain")
@Transactional
public class TransactionBalanceDeductChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {
	@Autowired
	private BalanceCheckingService balanceCheckingService;
	@Autowired
	private BalanceCheckingServiceDao balanceCheckingServiceDao;
	private final TransactionState transactionState = TransactionState.BALANCEDEDUCTED;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		FranchiseeCurrBal franchiseeCurrbal = balanceCheckingService
				.getFrachiseeCurrbalObj(Long.valueOf(transactionTransportBean
						.getRetailerId()));
		boolean isSuccess = retailerCommissionDeduction(transactionTransportBean,
				transactionResponse, franchiseeCurrbal);
		if (isSuccess)
			doSuccess(transactionResponse);
		else {
			transactionResponse.setMessage("No Balance available or some issue in your account.");
			transactionTransportBean.setErrorCode(1);
			transactionTransportBean.setErrorMessage("No Balance available or some issue in your account.");
			doFail(transactionResponse);
		}

	}

	private boolean retailerCommissionDeduction(
			TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse,
			FranchiseeCurrBal franchiseeCurrbal) {
		DistributorCurrbal distributorCurrbal = balanceCheckingServiceDao
				.getDistributorCurrbalObj(franchiseeCurrbal.getCreatorId());

		Double transAmountActual = transactionTransportBean.getAmount();
		Double commission = transactionResponse.getCommision();
		String deductType = transactionResponse.getCommissionDeductType(); 

		if (CommonConstants.ADUNIT.equalsIgnoreCase(transactionTransportBean
				.getPaymentType())) {
			transactionTransportBean.setCommissionType("Adunit");
			return reduceAmountAdUnitBalance(transactionTransportBean,
					transactionResponse, franchiseeCurrbal, distributorCurrbal,
					transAmountActual, commission, deductType);
		} else if (CommonConstants.CURRENT
				.equalsIgnoreCase(transactionTransportBean.getPaymentType())) {
			transactionTransportBean.setCommissionType("B2b");
			return reduceAmountFromCurrentBalance(transactionTransportBean,
					transactionResponse, franchiseeCurrbal, transAmountActual,
					commission, deductType);

		}

		else {
			transactionTransportBean.setCommissionType("B2b");
			return reduceAmountFromCurrentBalance(transactionTransportBean,
					transactionResponse, franchiseeCurrbal, transAmountActual,
					commission, deductType);
		}

	}

	private boolean reduceAmountFromCurrentBalance(
			TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse,
			FranchiseeCurrBal franchiseeCurrbal, Double transAmountActual,
			Double commission, String deductType) {
		double commision;
		transactionResponse.setRetailerOldBalance(franchiseeCurrbal
				.getB2bcurrbal());
		transactionResponse.setRetailerAccount(CommonConstants.CURRENT);
		commision = transAmountActual * commission / 100;
		transactionTransportBean.setRetailerPreBal(franchiseeCurrbal
				.getB2bcurrbal());
		if (CommonConstants.DEBIT.equalsIgnoreCase(deductType)) {
			transAmountActual = transAmountActual + commision;
			if (franchiseeCurrbal.getB2bcurrbal() < transAmountActual) {
				doFail(transactionResponse);
				return false;
			}
			transactionTransportBean.setCreditAmountFranchisee(commision);
			transactionTransportBean.setRetailerNewBal(franchiseeCurrbal
					.getB2bcurrbal() - transAmountActual);
			balanceCheckingServiceDao.reduceFranchiseeCurrentAccountBalance(
					Long.valueOf(transactionTransportBean.getRetailerId()),
					transAmountActual);
		} else {
			if (franchiseeCurrbal.getB2bcurrbal() < transAmountActual) {
				doFail(transactionResponse);
				return false;
			}
			transAmountActual = transAmountActual - commision;
			transactionTransportBean.setCreditAmountFranchisee(commision);
			transactionTransportBean.setRetailerNewBal(franchiseeCurrbal
					.getB2bcurrbal() - transAmountActual);
			balanceCheckingServiceDao.reduceFranchiseeCurrentAccountBalance(
					Long.valueOf(transactionTransportBean.getRetailerId()),
					transAmountActual);
		}
		transactionTransportBean.setStatus("SUCCESS");
		return true;
	}

	private boolean reduceAmountAdUnitBalance(
			TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse,
			FranchiseeCurrBal franchiseeCurrbal,
			DistributorCurrbal distributorCurrbal, Double transAmountActual,
			Double commission, String deductType) {
		transactionResponse.setDistributorOldBalance(distributorCurrbal
				.getB2bCurrAcAdUnitBal());
		transactionResponse.setRetailerOldBalance(franchiseeCurrbal
				.getB2bcurrbaladunit());
		transactionTransportBean.setRetailerPreBal(franchiseeCurrbal
				.getB2bcurrbaladunit());
		transactionResponse.setRetailerAccount(CommonConstants.ADUNIT);
		if (CommonConstants.DEBIT.equalsIgnoreCase(deductType)) {
			transAmountActual = transAmountActual + commission;
			if (franchiseeCurrbal.getB2bcurrbaladunit() < transAmountActual) {
				doFail(transactionResponse);
				return false;
			}
			transactionTransportBean.setRetailerNewBal(franchiseeCurrbal
					.getB2bcurrbaladunit() - transAmountActual);
			transactionTransportBean.setCreditAmountFranchisee(commission);
			balanceCheckingServiceDao.reduceFranchiseeAdUnitAccountBalance(
					Long.valueOf(transactionTransportBean.getRetailerId()),
					transAmountActual);
		} else {
			if (franchiseeCurrbal.getB2bcurrbaladunit() < transAmountActual) {
				doFail(transactionResponse);
				return false;
			}
			transAmountActual = transAmountActual - commission;
			transactionTransportBean.setRetailerNewBal(franchiseeCurrbal
					.getB2bcurrbaladunit() - transAmountActual);
			transactionTransportBean.setCreditAmountFranchisee(commission);
			balanceCheckingServiceDao.reduceFranchiseeAdUnitAccountBalance(
					Long.valueOf(transactionTransportBean.getRetailerId()),
					transAmountActual);
		}

		Long id = balanceCheckingServiceDao
				.addToDistributorAdUnitBalanceByFranchisee(
						Long.valueOf(franchiseeCurrbal.getCreatorId()),
						1);
		transactionResponse.setDistributoLogId(id);
		transactionTransportBean.setStatus("SUCCESS");
		return true;
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
