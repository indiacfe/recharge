package com.cfeindia.b2bserviceapp.dao.common;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface FranchiseeInfoDao {
	public FranchiseeCurrBal getFranchiseeAccountBalance(Long franchiseeId);
	public List<TransactionTransportBean> getRefundDetail(String id,String rechargeType,Timestamp formDate,Timestamp toDate);
}
