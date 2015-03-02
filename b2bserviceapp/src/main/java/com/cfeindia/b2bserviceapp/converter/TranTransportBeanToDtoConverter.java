package com.cfeindia.b2bserviceapp.converter;

import com.cfeindia.b2bserviceapp.dto.RefundAmountDto;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public class TranTransportBeanToDtoConverter implements EntityToDTOConverter<TransactionTransportBean,RefundAmountDto>{

	public RefundAmountDto convert(TransactionTransportBean tBean) {
		RefundAmountDto refundAmountDto=new RefundAmountDto();
		refundAmountDto.setId(tBean.getId());
		refundAmountDto.setTransid(tBean.getTransid());
		refundAmountDto.setRetailerId(tBean.getRetailerId());
		refundAmountDto.setMobileNo(tBean.getMobileNo());
		refundAmountDto.setConnectionid(tBean.getConnectionid());
		refundAmountDto.setTransactionName(tBean.getTransactionName());
		refundAmountDto.setOperator(tBean.getOperator());
		refundAmountDto.setCreatedAt(tBean.getCreatedAt());
		refundAmountDto.setCreditAmountFranchisee(tBean.getCreditAmountFranchisee());
		refundAmountDto.setAmount(tBean.getAmount());
		refundAmountDto.setStatus(tBean.getStatus());
		return refundAmountDto;
	}
	

}
