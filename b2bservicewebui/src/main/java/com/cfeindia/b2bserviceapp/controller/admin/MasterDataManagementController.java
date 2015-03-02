package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;

@RequestMapping("/admin/**")
@Controller
public class MasterDataManagementController {
	@Autowired
	AdminUtilityService adminUtilityService;

	@RequestMapping(value = "/companyoperatorcommission")
	public String companyOperatorComm(ModelMap model) {
		List<CompanyOperatorComission> operatorComissionList = adminUtilityService
				.companyOperatorComission();
		model.addAttribute("companyOperatorCommissionList", operatorComissionList);
		return "companyoperatorcommission";

	}

	@RequestMapping(value = "/thirdpartyserviceprovider")
	public String thirdPartyService(ModelMap model) {
		List<ThirdPartyServiceProvider> thirdPartySerProviderList = adminUtilityService
				.thirdPartyServiceProvider();
		model.addAttribute("thirdPartyServiceList", thirdPartySerProviderList);
		return "thirdpartyserviceprovider";

	}

}
