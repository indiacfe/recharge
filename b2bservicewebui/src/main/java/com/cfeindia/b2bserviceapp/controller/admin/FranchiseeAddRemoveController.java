package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;
import com.cfeindia.b2bserviceapp.service.admin.FranchiseeAddRemoveService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class FranchiseeAddRemoveController {
	@Autowired
	AdminUtilityService adminUtilityService;
	@Autowired
	private FranchiseeAddRemoveService franchiseeAddRemoveService;

	@RequestMapping(value = "/retaileraddremove", method = RequestMethod.GET)
	public String getDistributerRetailer() {

		return "retailerbydist";
	}

	@RequestMapping(value = "/getlists", method = RequestMethod.GET)
	public String getDistributerRetailerList(@RequestParam String sel,
			ModelMap model) {
		String return_page = null;
		if (sel.equalsIgnoreCase("ROLE_DISTRIBUTOR")) {
			List<UserDetailDto> userDetailDtoList = adminUtilityService
					.userDetailListDistributor(sel);
			model.addAttribute("userdetail", userDetailDtoList);
			return_page = "retailerbydist";
		} else {
			List<UserDetailDto> userDetailDtoList = franchiseeAddRemoveService
					.getAllRetailers();
			model.addAttribute("userdetail", userDetailDtoList);
			
			
			return_page = "retailerslist";
		}
		return return_page;
	}

	@RequestMapping(value = "/retailerslists", method = RequestMethod.GET)
	public String getRetailerListByDistributors(@RequestParam (value="userId" ,required=false) String userId,
			ModelMap model) {
		Long dUid =Long.parseLong(ExtractorUtil.extractIdFromString(userId));
		List<UserDetailDto> userDetailDtoList = adminUtilityService
				.retailersLists(dUid);
		model.addAttribute("userdetail", userDetailDtoList);
		if(userId!=null)
		{
		model.addAttribute("distDetails", franchiseeAddRemoveService.getDistributor(dUid));
		}
		return "retailerslist";
	}

	@RequestMapping(value = "/removeretailer", method = RequestMethod.GET)
	public String removeRetailerListByDistributors(
			@RequestParam String retailerId, @RequestParam(value="distId" , required=false ) String distId,
			ModelMap model) {
		Long userId = Long.parseLong(ExtractorUtil
				.extractIdFromString(retailerId));
		if(distId!=null)
		{
		Long disuid = Long.parseLong(ExtractorUtil
				.extractIdFromString(distId));
		List<UserDetailDto> userDetailDtoList = adminUtilityService
				.retailersLists(disuid);
		model.addAttribute("userdetail", userDetailDtoList);
		}else
		{
			List<UserDetailDto> userDetailDtoList = franchiseeAddRemoveService
					.getAllRetailers();
			model.addAttribute("userdetail", userDetailDtoList);
		}
		
		franchiseeAddRemoveService.removeFranchisee(userId);
		
		
		return "retailerslist";
	}

	@RequestMapping(value = "/addretailer", method = RequestMethod.GET)
	public String addRetailerListByDistributors(
			@RequestParam String retailerId, ModelMap model) {
		List<UserDetailDto> userDetailDtoList = adminUtilityService
				.userDetailListDistributor("ROLE_DISTRIBUTOR");
		model.addAttribute("userdetail", userDetailDtoList);
		model.addAttribute("retailerId", retailerId);

		return "addretailertofranchisee";
	}

	@RequestMapping(value = "/updateRetailer", method = RequestMethod.GET)
	public String updateRetailerList(@RequestParam String distId,
			@RequestParam String retailerId, ModelMap model) {
		Long disId = Long.parseLong(ExtractorUtil.extractIdFromString(distId));
		Long retaiId = Long.parseLong(ExtractorUtil
				.extractIdFromString(retailerId));
		franchiseeAddRemoveService.addFranchisee(disId, retaiId);
		List<UserDetailDto> userDetailDtoList = adminUtilityService
				.userDetailListDistributor("ROLE_DISTRIBUTOR");
		model.addAttribute("userdetail", userDetailDtoList);

		return "retailerbydist";
	}

}
