package com.cfeindia.b2bserviceapp.service.recharge;

import com.cfeindia.b2bserviceapp.thirdparty.recharge.ERegistrationRequestService;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.MoneyTransERegistrationRequestService;

/**
 * set results from processing of last chain element.
 * 
 * @author vikas
 * 
 */
public class TransactionResponse {
	private boolean isSuccessful = false;
	private String message;
	private ETransactionRequestService eTransactionRequestService;
	private double commision;
	private String commissionType;
	private String commissionDeductType;
	private boolean isBalanceAvaliable;
	private String thirdPartyAPISelection;
	private String retailerAccount;
	private double retailerOldBalance;
	private double distributorOldBalance;
	private long distributoLogId;
	private ERegistrationRequestService eRegistrationRequestService;
	private MoneyTransERegistrationRequestService moneyTransERegistrationRequestService;

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ETransactionRequestService geteTransactionRequestService() {
		return eTransactionRequestService;
	}

	public void seteTransactionRequestService(
			ETransactionRequestService eTransactionRequestService) {
		this.eTransactionRequestService = eTransactionRequestService;
	}

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

	public String getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}

	public String getCommissionDeductType() {
		return commissionDeductType;
	}

	public void setCommissionDeductType(String commissionDeductType) {
		this.commissionDeductType = commissionDeductType;
	}

	public boolean isBalanceAvaliable() {
		return isBalanceAvaliable;
	}

	public void setBalanceAvaliable(boolean isBalanceAvaliable) {
		this.isBalanceAvaliable = isBalanceAvaliable;
	}

	public String getThirdPartyAPISelection() {
		return thirdPartyAPISelection;
	}

	public void setThirdPartyAPISelection(String thirdPartyAPISelection) {
		this.thirdPartyAPISelection = thirdPartyAPISelection;
	}

	public String getRetailerAccount() {
		return retailerAccount;
	}

	public void setRetailerAccount(String retailerAccount) {
		this.retailerAccount = retailerAccount;
	}

	public double getRetailerOldBalance() {
		return retailerOldBalance;
	}

	public void setRetailerOldBalance(double retailerOldBalance) {
		this.retailerOldBalance = retailerOldBalance;
	}

	public double getDistributorOldBalance() {
		return distributorOldBalance;
	}

	public void setDistributorOldBalance(double distributorOldBalance) {
		this.distributorOldBalance = distributorOldBalance;
	}

	public long getDistributoLogId() {
		return distributoLogId;
	}

	public void setDistributoLogId(long distributoLogId) {
		this.distributoLogId = distributoLogId;
	}

	public ERegistrationRequestService geteRegistrationRequestService() {
		return eRegistrationRequestService;
	}

	public void seteRegistrationRequestService(
			ERegistrationRequestService eRegistrationRequestService) {
		this.eRegistrationRequestService = eRegistrationRequestService;
	}

	public MoneyTransERegistrationRequestService getMoneyTransERegistrationRequestService() {
		return moneyTransERegistrationRequestService;
	}

	public void setMoneyTransERegistrationRequestService(
			MoneyTransERegistrationRequestService moneyTransERegistrationRequestService) {
		this.moneyTransERegistrationRequestService = moneyTransERegistrationRequestService;
	}

}
