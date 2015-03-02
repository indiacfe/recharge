package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.entity.LicPremiumBean;

public interface LicManagementDao {
	public List<LicPremiumBean> getDetailsdao(Timestamp fromDate, Timestamp toDate,String status);
	public List<LicPremiumBean> getDetailStatusSuccess(); 
	
}
