package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.LicPremiumBean;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface LicPremiumService {
	public String saveDetails(TransactionTransportBean transactionTransportBean);
	public List<LicPremiumBean> getLicPremiumDetails(Long userId);

}
