package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.LicPremiumBean;

public interface LicPremiumDao {
	public String saveLicPremiumDao(LicPremiumBean licPremiumBean);

	public List<LicPremiumBean> getLicPremiumDetails(Long userId); 
}
