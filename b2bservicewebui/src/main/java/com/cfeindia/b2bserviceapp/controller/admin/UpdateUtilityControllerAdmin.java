package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.ThirdpartyServiceProviderUpdateDTO;
import com.cfeindia.b2bserviceapp.model.admin.UpdateAccountCompany;
import com.cfeindia.b2bserviceapp.model.admin.UpdateCommissionDistributor;
import com.cfeindia.b2bserviceapp.model.admin.UpdateCommissionOperator;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;
import com.cfeindia.b2bserviceapp.service.admin.CompanyAccount;
import com.cfeindia.b2bserviceapp.thirdparty.operators.ThirdpartyOperatorList;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class UpdateUtilityControllerAdmin {

	@Autowired
	private CompanyAccount company;

	@Autowired
	AdminUtilityService adminUtilityService;

	@RequestMapping(value = "/updateAdmin", method = RequestMethod.GET)
	public ModelAndView updateUtility(ModelMap map) {
		ModelAndView model = new ModelAndView("updateAdmin");
		model.addObject("companyaccountAdmin", new UpdateAccountCompany());
		model.addObject("distributoCommissionAdmin",
				new UpdateCommissionDistributor());
		model.addObject("operatorCommisionAdmin",
				new UpdateCommissionOperator());

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
		map.addAttribute("thirdpartyname", thirdPartyList);
		map.addAttribute("operatorname", operatorList);
		map.addAttribute("rechargetype", rechargeTypeList);

		map.addAttribute("thirdpartyServiceProviderUpdateDTO",
				new ThirdpartyServiceProviderUpdateDTO());
		return model;

	}

	@RequestMapping(value = "/companyaccountupdateAdmin", method = RequestMethod.POST)
	public String updateCompanyAccount(
			@ModelAttribute("companyaccountAdmin") UpdateAccountCompany updateAccountCompany,
			HttpSession session) {
		String balance = updateAccountCompany.getCompanyAmount();
		Double addBalance = Double.parseDouble(balance);
		String check = adminUtilityService.updateCompanyAccount(addBalance);
		if (CommonConstants.SUCCESS.equalsIgnoreCase(check)) {
			String[] companyInfo = company.companyInfo();
			session.setAttribute("amount", Float.parseFloat(companyInfo[0]));
			session.setAttribute("distributors",
					Integer.parseInt(companyInfo[1]));
			session.setAttribute("franchisees",
					Integer.parseInt(companyInfo[2]));
			return "redirect:successadmin";
		} else
			return "failureAdmin";
	}

	@RequestMapping(value = "/successadmin", method = RequestMethod.GET)
	public String finalPage() {

		return "successAdmin";
	}

	@RequestMapping(value = "/distributoCommissionupdateAdmin", method = RequestMethod.POST)
	public String setDistributorCommission(
			@ModelAttribute("distributoCommissionAdmin") UpdateCommissionDistributor updateCommissionDistributor) {
		String distId = updateCommissionDistributor.getDistributorId();
		if (distId.startsWith("R") || distId.startsWith("A")) {
			return "failureAdmin";
		} else if (distId.startsWith("D")) {
			distId = ExtractorUtil.extractIdFromString(distId);
		}
		Long distributorId = Long.parseLong(distId);
		Double amount = Double.parseDouble(updateCommissionDistributor
				.getAmount());
		String check = adminUtilityService.setDistributorCommission(
				distributorId, amount);
		if (CommonConstants.SUCCESS.equalsIgnoreCase(check))
			return "redirect:successadmin";
		else
			return "failureAdmin";
	}

	@RequestMapping(value = "/operatorCommisionupdateAdmin", method = RequestMethod.POST)
	public String setOperatorCommission(
			@ModelAttribute("operatorCommisionAdmin") UpdateCommissionOperator updateCommissionOperator) {
		String check = adminUtilityService
				.setOperatorCommission(updateCommissionOperator);
		if (CommonConstants.SUCCESS.equalsIgnoreCase(check))
			return "redirect:successadmin";
		else
			return "failureAdmin";
	}

	@RequestMapping(value = "/updatethirdpartyserviceprovider", method = RequestMethod.POST)
	public String setThirdPartyServiceProvider(
			@ModelAttribute("thirdpartyServiceProviderUpdateDTO") ThirdpartyServiceProviderUpdateDTO thirdpartyServiceProviderUpdateDTO) {
		adminUtilityService.updateThirdPartyAPISelection(
				thirdpartyServiceProviderUpdateDTO.getRechargeType(),
				thirdpartyServiceProviderUpdateDTO.getOperatorName(),
				thirdpartyServiceProviderUpdateDTO.getThirdPartyName());

		return "redirect:successadmin";
	}
}
