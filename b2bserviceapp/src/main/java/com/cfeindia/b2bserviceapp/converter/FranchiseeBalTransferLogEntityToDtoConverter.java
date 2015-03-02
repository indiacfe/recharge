package com.cfeindia.b2bserviceapp.converter;

import com.cfeindia.b2bserviceapp.accountstatement.Dto.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;


public class FranchiseeBalTransferLogEntityToDtoConverter implements EntityToDTOConverter<FranchiseeBalanceTransferLog, FranchiseeAccountStatementDto>{

	public FranchiseeAccountStatementDto convert(FranchiseeBalanceTransferLog entity) 
	{
		FranchiseeAccountStatementDto dto=new FranchiseeAccountStatementDto();
		dto.setTransactionFrom(entity.getTransactionFrom());
		dto.setTransactionTo(entity.getTransactionTo());
		dto.setPreb2bcurrbal(entity.getPreb2bcurrbal());
		dto.setNewB2bCurrBal(ExtractorUtil.getRoundedDouble(entity.getNewB2bCurrBal()));
		dto.setAmount(ExtractorUtil.getRoundedDouble(entity.getTransferAmount()));
		dto.setCreatedAt(TimeStampUtil.getStringFromTimeStamp(entity.getCreatedAt()));
		dto.setTransactionType(entity.getTransactionType());
		if("CREDIT".equalsIgnoreCase(dto.getTransactionType())) {
			dto.setTransactionType(CommonConstants.TRANSFER_TYPE_CREDIT);
		}
		dto.setTransactionId(entity.getTransactionId());
		return dto;
	}
}
