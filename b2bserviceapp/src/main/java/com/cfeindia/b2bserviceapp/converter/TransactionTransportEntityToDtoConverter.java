package com.cfeindia.b2bserviceapp.converter;

import com.cfeindia.b2bserviceapp.accountstatement.Dto.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

public class TransactionTransportEntityToDtoConverter
		implements
		EntityToDTOConverter<TransactionTransportBean, FranchiseeAccountStatementDto> {

	public FranchiseeAccountStatementDto convert(TransactionTransportBean entity) {
		FranchiseeAccountStatementDto dto = new FranchiseeAccountStatementDto();
		dto.setAmount(ExtractorUtil.getRoundedDouble(entity.getAmount()));
		dto.setCreatedAt(TimeStampUtil.getStringFromTimeStamp(entity
				.getCreatedAt()));
		dto.setCreditAmountFranchisee(entity.getCreditAmountFranchisee());
		dto.setMobileNo(entity.getMobileNo());
		dto.setOperator(entity.getOperator());
		dto.setNewB2bCurrBal(entity.getRetailerNewBal());
		dto.setConnectionid(entity.getConnectionid());
		if (entity.getTransactionName().equals("MOBILE_RECHARGE")) {
			if (entity.getStatus().equals("SUCCESS"))
				dto.setParticulars("RECHARGE SUCCESS:" + entity.getMobileNo());
			else
				dto.setParticulars("RECHARGE FAILED:" + entity.getMobileNo());
		} else {
			if (entity.getStatus().equals("SUCCESS"))
				dto.setParticulars("RECHARGE SUCCESS:" + entity.getConnectionid());
			else
				dto.setParticulars("RECHARGE FAILED:" + entity.getConnectionid());
		}
		dto.setTransactionType(CommonConstants.TRANSFER_TYPE_DEBIT);
		dto.setTransactionId(entity.getTransid());
		dto.setNewB2bCurrBal(entity.getRetailerNewBal());
		dto.setPreb2bcurrbal(entity.getRetailerPreBal());
		return dto;
	}
}
