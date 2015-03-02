package com.cfeindia.b2bserviceapp.converter;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.model.franchisee.CashDepositRequestFran;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

public class CashDepoReqToFranOrderProcessingConverter implements DtoToEntityConverter<CashDepositRequestFran, FranchiseeCashDepositRequest> {

	public FranchiseeCashDepositRequest convert(CashDepositRequestFran model)
	{
		FranchiseeCashDepositRequest franchiseeOrderProcessing=new FranchiseeCashDepositRequest();
		franchiseeOrderProcessing.setAmount(Double.parseDouble(model.getAmount()));
		franchiseeOrderProcessing.setBankName(model.getBankName());
		franchiseeOrderProcessing.setRequestType(model.getCashDepositRequestType());
		franchiseeOrderProcessing.setChequeDdDate(TimeStampUtil.getDateFromString(model.getReciptDate()));
		franchiseeOrderProcessing.setCounter(model.getCounterBank());
		franchiseeOrderProcessing.setDepositBranchOffice(model.getDepositBranch());
		franchiseeOrderProcessing.setPaymentMode(model.getPaymentMode());
		//franchiseeOrderProcessing.setReceipt((model.getUploadRecipt()));
		franchiseeOrderProcessing.setRecieptChequeDdNo(model.getReciptNo());
		franchiseeOrderProcessing.setRemark(model.getRemark());
		franchiseeOrderProcessing.setUpdatedAt(TimeStampUtil.getTimestamp());
		franchiseeOrderProcessing.setStatus(CommonConstants.IN_PROCESS);
		return franchiseeOrderProcessing;
	}

}
