package com.cfeindia.b2bserviceapp.converter;

import com.cfeindia.b2bserviceapp.dto.RefundAmountDto;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;

public class CustomerTransTransportToDtoCoverter {
	public RefundAmountDto convert(CustomerTransactionTransportBean tBean) {
		RefundAmountDto refundAmountDto = new RefundAmountDto();
		refundAmountDto.setId(tBean.getId());
		refundAmountDto.setTransid(tBean.getClientTransId());
		refundAmountDto.setRetailerId(tBean.getCustomerId());
		refundAmountDto.setMobileNo(tBean.getMobileNo());
		refundAmountDto.setConnectionid(tBean.getConnectionid());
		refundAmountDto.setTransactionName(tBean.getTransactionName());
		refundAmountDto.setOperator(tBean.getOperator());
		refundAmountDto.setCreatedAt(tBean.getCreatedAt());
		refundAmountDto.setCreditAmountFranchisee(tBean
				.getCreditAmountFranchisee());
		refundAmountDto.setAmount(tBean.getAmount());
		refundAmountDto.setStatus(tBean.getStatus());
		refundAmountDto.setThirdPartyServiceProviderName(tBean
				.getThirdPartyServiceProvider());
		return refundAmountDto;
	}

}
