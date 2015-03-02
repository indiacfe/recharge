package com.cfeindia.b2bserviceapp.converter;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.accountstatement.CustomerAccountStatementDto;
import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

public class CustomerBalTransferLogEntityToDtoConverter
		implements
		EntityToDTOConverter<CustomerBalanceTransferLog, CustomerAccountStatementDto> {

	@Override
	public CustomerAccountStatementDto convert(CustomerBalanceTransferLog entity) {
		CustomerAccountStatementDto dto = new CustomerAccountStatementDto();
		dto.setTransactionFrom(entity.getTransferFrom());
		dto.setTransactionTo(entity.getTransferTo());
		dto.setPrecurrbal(entity.getPrecurrbal());
		dto.setNewCurrBal(ExtractorUtil.getRoundedDouble(entity
				.getNewCurrBal()));
		dto.setAmount(ExtractorUtil.getRoundedDouble(entity.getTransferAmount()));
		dto.setCreatedAt(TimeStampUtil.getStringFromTimeStamp(entity
				.getCreatedAt()));
		dto.setTransactionType(entity.getTransferType());
		if ("CREDIT".equalsIgnoreCase(dto.getTransactionType())) {
			dto.setTransactionType(CommonConstants.TRANSFER_TYPE_CREDIT);
		}
		dto.setTransactionId(entity.getTransactionId());
		return dto;
	}

}
