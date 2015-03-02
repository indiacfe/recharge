package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.entity.SmsRechargeDetail;

public interface SmsDetailsDao {
	
	public List<SmsRechargeDetail> getDetailsdao(Timestamp fromDate, Timestamp toDate);
	
	
}
