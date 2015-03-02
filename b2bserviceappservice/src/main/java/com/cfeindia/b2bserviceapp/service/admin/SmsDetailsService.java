package com.cfeindia.b2bserviceapp.service.admin;

import java.text.ParseException;
import java.util.List;

import com.cfeindia.b2bserviceapp.entity.SmsRechargeDetail;

public interface SmsDetailsService {
	public List<SmsRechargeDetail> getMessageDetailsservice(String from,
			String todate) throws ParseException;
}
