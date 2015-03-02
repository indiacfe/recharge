package com.cfeindia.b2bserviceapp.converter;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.accountstatement.CustomerAccountStatementDto;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

public class CustomerTransactionTransportEntityToDtoConverter
		implements
		EntityToDTOConverter<CustomerTransactionTransportBean, CustomerAccountStatementDto> {

	@Override
	public CustomerAccountStatementDto convert(
			CustomerTransactionTransportBean entity) {
		CustomerAccountStatementDto dto = new CustomerAccountStatementDto();
		dto.setAmount(ExtractorUtil.getRoundedDouble(entity.getAmount()));
		dto.setCreatedAt(TimeStampUtil.getStringFromTimeStamp(entity
				.getCreatedAt()));
		dto.setCreditAmountCustomer(ExtractorUtil.getRoundedDouble(entity
				.getCreditAmountFranchisee()));
		dto.setMobileNo(entity.getMobileNo());
		dto.setOperator(entity.getOperator());
		dto.setNewCurrBal(ExtractorUtil.getRoundedDouble(entity
				.getRetailerNewBal()));
		dto.setConnectionid(entity.getConnectionid());
		StringBuilder sb = new StringBuilder("RECHARGE ");
		if (entity.getTransactionName().equals("MOBILE_RECHARGE")) {
			if (entity.getStatus().equals(CommonConstants.SUCCESS)
					|| entity.getStatus().equals(CommonConstants.REFUND)) {
				dto.setParticulars(sb.append(CommonConstants.SUCCESS)
						.append(": ").append(entity.getMobileNo()).toString());
			} else {
				dto.setParticulars(sb.append(entity.getStatus()).append(": ")
						.append(entity.getMobileNo()).toString());
			}
		} else {
			if (entity.getStatus().equals(CommonConstants.SUCCESS)
					|| entity.getStatus().equals(CommonConstants.REFUND)) {
				dto.setParticulars(sb.append(CommonConstants.SUCCESS)
						.append(": ").append(entity.getConnectionid())
						.toString());
			} else {
				dto.setParticulars(sb.append(entity.getStatus()).append(": ")
						.append(entity.getConnectionid()).toString());
			}
		}
		if (entity.getRetailerPreBal() != null)
			dto.setNewCurrBal(ExtractorUtil.getRoundedDouble(entity
					.getRetailerPreBal() - entity.getAmount()));
		dto.setTransactionType(CommonConstants.TRANSFER_TYPE_DEBIT);
		dto.setTransactionId(entity.getTransid());
		return dto;
	}

	public CustomerAccountStatementDto crConvert(
			CustomerTransactionTransportBean entity) {
		CustomerAccountStatementDto dto = new CustomerAccountStatementDto();
		dto.setAmount(ExtractorUtil.getRoundedDouble(entity
				.getCreditAmountFranchisee())); // for showing the creadit part
		dto.setCreatedAt(TimeStampUtil.getStringFromTimeStamp(entity
				.getCreatedAt()));
		dto.setCreditAmountCustomer(ExtractorUtil.getRoundedDouble(entity
				.getCreditAmountFranchisee()));
		dto.setMobileNo(entity.getMobileNo());
		dto.setOperator(entity.getOperator());
		double newB2bCurrBal = (entity.getRetailerPreBal() - entity.getAmount())
				+ entity.getCreditAmountFranchisee();
		dto.setNewCurrBal(ExtractorUtil.getRoundedDouble(newB2bCurrBal));
		dto.setConnectionid(entity.getConnectionid());
		StringBuilder sb = new StringBuilder("RECHARGE ");
		if (entity.getTransactionName().equals("MOBILE_RECHARGE")) {
			if (entity.getStatus().equals(CommonConstants.SUCCESS)
					|| entity.getStatus().equals(CommonConstants.REFUND)) {
				dto.setParticulars("DISCOUNT:" + entity.getMobileNo());
			} else
				dto.setParticulars(sb.append(entity.getStatus()).append(": ")
						.append(entity.getMobileNo()).toString());
		} else {
			if (entity.getStatus().equals(CommonConstants.SUCCESS)
					|| entity.getStatus().equals(CommonConstants.REFUND)) {
				dto.setParticulars("DISCOUNT:" + entity.getConnectionid());
			} else
				dto.setParticulars(sb.append(entity.getStatus()).append(": ")
						.append(entity.getMobileNo()).toString());
		}
		dto.setTransactionType(CommonConstants.TRANSFER_TYPE_CREDIT);
		dto.setTransactionId(entity.getTransid());
		return dto;
	}

}