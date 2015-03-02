package com.cfeindia.b2bserviceapp.converter;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;
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
		dto.setCreditAmountFranchisee(ExtractorUtil.getRoundedDouble(entity
				.getCreditAmountFranchisee()));
		dto.setMobileNo(entity.getMobileNo());
		dto.setOperator(entity.getOperator());
		dto.setNewB2bCurrBal(ExtractorUtil.getRoundedDouble(entity
				.getRetailerNewBal()));
		dto.setAccount(entity.getCommissionType());
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
			dto.setNewB2bCurrBal(ExtractorUtil.getRoundedDouble(entity
					.getRetailerPreBal() - entity.getAmount()));
		dto.setTransactionType(CommonConstants.TRANSFER_TYPE_DEBIT);
		dto.setTransactionId(entity.getTransid());
		return dto;
	}

	public FranchiseeAccountStatementDto crConvert(
			TransactionTransportBean entity) {
		FranchiseeAccountStatementDto dto = new FranchiseeAccountStatementDto();
		dto.setAmount(ExtractorUtil.getRoundedDouble(entity
				.getCreditAmountFranchisee())); // for showing the creadit part
		dto.setCreatedAt(TimeStampUtil.getStringFromTimeStamp(entity
				.getCreatedAt()));
		dto.setCreditAmountFranchisee(ExtractorUtil.getRoundedDouble(entity
				.getCreditAmountFranchisee()));
		dto.setMobileNo(entity.getMobileNo());
		dto.setOperator(entity.getOperator());
		double newB2bCurrBal;
		if (CommonConstants.DEBIT.equalsIgnoreCase(entity
				.getCommissionDeductType())) {
			newB2bCurrBal = (entity.getRetailerPreBal() - entity.getAmount() - entity
					.getCreditAmountFranchisee());
			dto.setTransactionType(CommonConstants.TRANSFER_TYPE_DEBIT);
		} else {
			newB2bCurrBal = (entity.getRetailerPreBal() - entity.getAmount())
					+ entity.getCreditAmountFranchisee();
			dto.setTransactionType(CommonConstants.TRANSFER_TYPE_CREDIT);
		}

		dto.setNewB2bCurrBal(ExtractorUtil.getRoundedDouble(newB2bCurrBal));
		dto.setAccount(entity.getCommissionType());
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

		dto.setTransactionId(entity.getTransid());
		return dto;
	}

	public FranchiseeAccountStatementDto RefundConvert(
			TransactionTransportBean entity) {
		FranchiseeAccountStatementDto dto = new FranchiseeAccountStatementDto();
		if (entity.getRefundAmount() != null)
			dto.setAmount(ExtractorUtil.getRoundedDouble(entity
					.getRefundAmount())); // for showing the credit part
		else {
			if (entity.getAmount() != null)
				dto.setAmount(ExtractorUtil.getRoundedDouble(entity.getAmount()
						- entity.getCreditAmountFranchisee()));
		}

		dto.setCreatedAt(TimeStampUtil.getStringFromTimeStamp(entity
				.getUpdatedAt()));
		dto.setMobileNo(entity.getMobileNo());
		dto.setOperator(entity.getOperator());
		dto.setNewB2bCurrBal(entity.getRetailerNewBal());
		dto.setConnectionid(entity.getConnectionid());
		if (entity.getTransactionName().equals("MOBILE_RECHARGE")) {
			dto.setParticulars("REFUND AGAINST: " + entity.getMobileNo());

		} else {
			dto.setParticulars("REFUND AGAINST: " + entity.getConnectionid());
		}

		dto.setNewB2bCurrBal(ExtractorUtil.getRoundedDouble(entity
				.getRetailerNewBal())); // Actual final amount
		// will be shown
		dto.setTransactionType(CommonConstants.REFUND);
		dto.setTransactionId(entity.getTransid());
		return dto;
	}

}
