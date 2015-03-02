package com.cfeindia.b2bserviceapp.service.recharge.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.service.recharge.TransactionResponse;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.BalanceCheckingService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionState;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service("transactionCommissionConfiguredChain")
@Transactional
public class TransactionCommissionConfiguredChain extends
		AbstractTransactionProcessorChain implements TransactionProcessorChain {

	private final TransactionState transactionState = TransactionState.COMMISSIONCONFIGURED;
	@Autowired
	private BalanceCheckingService balanceCheckingService;
	@Autowired
	private CommonService commonService;

	@Override
	public void doProcess(TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		CompanyOperatorComission commision = retailerCommission(
				transactionTransportBean, transactionResponse);
		if (commision != null) {
			if (transactionResponse.isBalanceAvaliable()) {
				transactionResponse.setCommissionDeductType(commision
						.getDeductionType());
				transactionTransportBean.setCommissionDeductType(commision
						.getDeductionType());
				transactionResponse.setCommision(commision
						.getRetailercommision());
				doSuccess(transactionResponse);
			} else {
				transactionTransportBean.setErrorCode(1);
				transactionTransportBean
						.setMessage("No Balance Available in your account or Some unexpected error has come");
				doFail(transactionResponse);
			}

		} else {
			transactionTransportBean.setErrorCode(1);
			transactionTransportBean
					.setMessage("Product is not configured, Please contact to admin");
			doFail(transactionResponse);

		}
	}

	private CompanyOperatorComission retailerCommission(
			TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse) {
		FranchiseeCurrBal franchiseeCurrbal = balanceCheckingService
				.getFrachiseeCurrbalObj(Long.valueOf(transactionTransportBean
						.getRetailerId()));
		CompanyOperatorComission commision;
		if (CommonConstants.ADUNIT.equalsIgnoreCase(transactionTransportBean
				.getPaymentType())) {
			commision = commonService.getRetailerCommission(
					transactionTransportBean.getThirdPartyServiceProvider(),
					transactionTransportBean.getRechargeType(),
					transactionTransportBean.getOperator(),
					CommonConstants.FLATE);
			if (commision != null) {
				checkFromAdUnitBalance(transactionTransportBean,
						transactionResponse, franchiseeCurrbal, commision);

			}

		} else if (CommonConstants.CURRENT
				.equalsIgnoreCase(transactionTransportBean.getPaymentType())) {
			commision = commonService.getRetailerCommission(
					transactionTransportBean.getThirdPartyServiceProvider(),
					transactionTransportBean.getRechargeType(),
					transactionTransportBean.getOperator(),
					CommonConstants.PERCENTAGE);
			if (commision != null) {
				checkFromCurrentBalance(transactionTransportBean,
						transactionResponse, franchiseeCurrbal, commision);
			}
		}

		else {
			commision = commonService.getRetailerCommission(
					transactionTransportBean.getThirdPartyServiceProvider(),
					transactionTransportBean.getRechargeType(),
					transactionTransportBean.getOperator(),
					CommonConstants.PERCENTAGE);
			if (commision != null) {
				checkFromCurrentBalance(transactionTransportBean,
						transactionResponse, franchiseeCurrbal, commision);
			}
		}
		return commision;
	}

	private void checkFromCurrentBalance(
			TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse,
			FranchiseeCurrBal franchiseeCurrbal,
			CompanyOperatorComission commision) {
		if (CommonConstants.DEBIT
				.equalsIgnoreCase(commision.getDeductionType())) {
			transactionResponse
					.setBalanceAvaliable(franchiseeCurrbal.getB2bcurrbal() >= (transactionTransportBean
							.getAmount() + commision.getRetailercommision()));
		} else {
			transactionResponse
					.setBalanceAvaliable(franchiseeCurrbal.getB2bcurrbal() >= (transactionTransportBean
							.getAmount() - commision.getRetailercommision()));
		}
	}

	private void checkFromAdUnitBalance(
			TransactionTransportBean transactionTransportBean,
			TransactionResponse transactionResponse,
			FranchiseeCurrBal franchiseeCurrbal,
			CompanyOperatorComission commision) {
		if (CommonConstants.DEBIT
				.equalsIgnoreCase(commision.getDeductionType())) {
			transactionResponse.setBalanceAvaliable(franchiseeCurrbal
					.getB2bcurrbaladunit() >= (transactionTransportBean
					.getAmount() + commision.getRetailercommision()));
		} else {
			transactionResponse.setBalanceAvaliable(franchiseeCurrbal
					.getB2bcurrbaladunit() >= (transactionTransportBean
					.getAmount() - commision.getRetailercommision()));
		}
	}

	@Override
	public TransactionState getTransactionState() {
		return transactionState;
	}

}
