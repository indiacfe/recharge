package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.LicPremiumDetailsDto;
import com.cfeindia.b2bserviceapp.entity.LicPremiumBean;

public interface LicManagementService {
	public List<LicPremiumDetailsDto> getDetailservice(String fromDate, String toDate,String status);
	public List<LicPremiumBean> getDetailStatusRejected(Long Id);
	public List<LicPremiumBean> getDetailStatusSuccess(Long Id);

}
