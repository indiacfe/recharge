package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.OutletDetail;
import com.cfeindia.b2bserviceapp.entity.OutletDetailPK;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;
import com.cfeindia.b2bserviceapp.thirdparty.operators.ThirdpartyOperatorList;

@RequestMapping("/admin/**")
@Controller
public class OutletManagementController {

	@Autowired
	CommonService commonService;

	@Autowired
	AdminUtilityService adminUtilityService;

	@RequestMapping(value = "/outletconfigpage1", method = RequestMethod.GET)
	public ModelAndView getoutletConfigPage() {
		return new ModelAndView("outletconfigpage1", "command",
				new OutletDetail());
	}

	@RequestMapping(value = "/outletconfigpage2", method = RequestMethod.POST)
	public String searchMobileNumber(@ModelAttribute OutletDetail outletDetail,
			ModelMap model) {
		String userName = outletDetail.getUserName();
		String userResult = commonService.validateRetailer(userName);
		if (CommonConstants.SUCCESS.equalsIgnoreCase(userResult)) {
			int userId = commonService.getUserId(userName);
			UserDetail userDetail = commonService.getUserDetail(String
					.valueOf(userId));
			model.addAttribute("userDetail", userDetail);
			model.addAttribute("userName", userName);
			Set<String> operatorSet = new HashSet<String>();
			Set<String> thirdPartySet = new HashSet<String>();
			List<ThirdpartyOperatorList> thirdpartyOperatorList = adminUtilityService
					.thirdPartyOperatorList();
			for (ThirdpartyOperatorList list : thirdpartyOperatorList) {
				thirdPartySet.add(list.getThirdpartyServiceProvider());
				operatorSet.add(list.getOperatorName());
			}
			List<String> thirdPartyList = new ArrayList<String>(thirdPartySet);
			Collections.sort(thirdPartyList);
			outletDetail.getOutletDetailPK().setFranchiseeId(Long.valueOf(userId));
			List<String> operatorList = new ArrayList<String>(operatorSet);
			Collections.sort(operatorList);
			model.addAttribute("thirdpartyname", thirdPartyList);
			model.addAttribute("operatorname", operatorList);
			model.addAttribute("outletDetail", outletDetail);
			return "outletconfigpage2";
		} else {
			model.addAttribute("message", "Mobile Number Does not exist.");
			return "outletconfigpage1";
		}

	}

	@RequestMapping(value = "/outletconfigfinal", method = RequestMethod.POST)
	public String updateCompanyAccount(
			@ModelAttribute OutletDetail outletDetail, ModelMap model) {

		if (outletDetail.getOutletid() == null) {
			return "outletconfigpage2";
		} else {
			commonService.saveOrUpdateEntity(outletDetail);
			model.addAttribute("message", "Outlet Id updated Successfully.");
			model.addAttribute("command", new OutletDetail());
			return "outletconfigpage1";
		}

	}

}
