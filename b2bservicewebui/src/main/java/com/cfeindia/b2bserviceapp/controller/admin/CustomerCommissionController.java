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

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;
import com.cfeindia.b2bserviceapp.service.admin.CustomerFundTransferService;
import com.cfeindia.b2bserviceapp.thirdparty.operators.ThirdpartyOperatorList;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Controller
@RequestMapping("/admin/**")
public class CustomerCommissionController {
	@Autowired
	private AdminUtilityService adminUtilityService;
	@Autowired
	private CustomerFundTransferService customerFundTransferService;
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/commission", method = RequestMethod.GET)
	public String getCustomerCommision(ModelMap model) {
		model.addAttribute("customercommisionAdmin", new CustomerCommision());

		Set<String> thirdPartySet = new HashSet<String>();
		Set<String> operatorSet = new HashSet<String>();
		Set<String> rechargeTypeSet = new HashSet<String>();

		List<ThirdpartyOperatorList> thirdpartyOperatorList = adminUtilityService
				.thirdPartyOperatorList();
		for (ThirdpartyOperatorList list : thirdpartyOperatorList) {
			thirdPartySet.add(list.getThirdpartyServiceProvider());
			operatorSet.add(list.getOperatorName());
			rechargeTypeSet.add(list.getRechargeType());
		}
		List<String> thirdPartyList = new ArrayList<String>(thirdPartySet);
		Collections.sort(thirdPartyList);
		List<String> operatorList = new ArrayList<String>(operatorSet);
		Collections.sort(operatorList);
		List<String> rechargeTypeList = new ArrayList<String>(rechargeTypeSet);
		Collections.sort(rechargeTypeList);
		model.addAttribute("thirdpartyname", thirdPartyList);
		model.addAttribute("operatorname", operatorList);
		model.addAttribute("rechargetype", rechargeTypeList);
		model.addAttribute("customers", customerFundTransferService.getUsers());

		return "customercommission";
	}

	@RequestMapping(value = "/customercommission", method = RequestMethod.POST)
	public String getCustomerCommision(
			@ModelAttribute("customercommisionAdmin") CustomerCommision customerCommision,
			ModelMap model) {
		String result = null;
		try {
			commonService.updateCustomerCommissionObject(customerCommision);
			model.addAttribute("success", "Updated successfully");

			Set<String> thirdPartySet = new HashSet<String>();
			Set<String> operatorSet = new HashSet<String>();
			Set<String> rechargeTypeSet = new HashSet<String>();

			List<ThirdpartyOperatorList> thirdpartyOperatorList = adminUtilityService
					.thirdPartyOperatorList();
			for (ThirdpartyOperatorList list : thirdpartyOperatorList) {
				thirdPartySet.add(list.getThirdpartyServiceProvider());
				operatorSet.add(list.getOperatorName());
				rechargeTypeSet.add(list.getRechargeType());
			}
			List<String> thirdPartyList = new ArrayList<String>(thirdPartySet);
			Collections.sort(thirdPartyList);
			List<String> operatorList = new ArrayList<String>(operatorSet);
			Collections.sort(operatorList);
			List<String> rechargeTypeList = new ArrayList<String>(
					rechargeTypeSet);
			Collections.sort(rechargeTypeList);
			model.addAttribute("thirdpartyname", thirdPartyList);
			model.addAttribute("operatorname", operatorList);
			model.addAttribute("rechargetype", rechargeTypeList);
			model.addAttribute("customers",
					customerFundTransferService.getUsers());
			model.addAttribute("message", "Updated Successfully");
			result = "customercommission";
		} catch (Exception e) {

			result = "redirect:commission";
			model.addAttribute("message", "Not Updated ! Please try again.");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/commisionpercustomer", method = RequestMethod.GET)
	public String getCommisions(ModelMap model) {
		model.addAttribute("custdetails",
				adminUtilityService.customerDetailList());
		return "commisionpercustomer";

	}

	@RequestMapping(value = "/commisionpercustomerdetails", method = RequestMethod.GET)
	public String getCommisionsDetails(@RequestParam String custId,
			ModelMap model) {
		model.addAttribute("commission", customerFundTransferService
				.getCommisions(Long.parseLong(ExtractorUtil
						.extractIdFromString(custId))));
		model.addAttribute("custdetails",
				adminUtilityService.customerDetailList());
		return "commisionpercustomer";

	}
}
