package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;
import com.cfeindia.b2bserviceapp.service.admin.FranchiseeAddRemoveService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class AllDistributorsAndRetailers {
	@Autowired
	AdminUtilityService adminUtilityService;

	@Autowired
	CommonService commonService;
	@Autowired
	private FranchiseeAddRemoveService franchiseeAddRemoveService;

	@RequestMapping(value = "/alldistributorsandretailers", method = RequestMethod.GET)
	public String showSearchPage() {
		return "alldistributorsandretailers";
	}

	@RequestMapping(value = "/distributorsandretailerslist", method = RequestMethod.GET)
	public String showDistributorsAndRetailers(@RequestParam String sel,
			ModelMap model) {
		List<UserDetailDto> userDetailDtoList = null;
		if ("Distributors".equals(sel)) {
			userDetailDtoList = adminUtilityService
					.userDetailListDistributor("ROLE_DISTRIBUTOR");
		} else if ("Retailers".equals(sel)) {
			userDetailDtoList = franchiseeAddRemoveService.getAllRetailers();
		} else {
			userDetailDtoList = adminUtilityService.userDetailList();

		}
		model.addAttribute("userdetail", userDetailDtoList);
		return "alldistributorsandretailers";
	}

	@RequestMapping(value = "/amountdeduction", method = RequestMethod.GET)
	public String deduction(@RequestParam String userId,
			@RequestParam String firmName, @RequestParam String mobileNo,
			@RequestParam String franBal, @RequestParam String distcurrbal,
			@RequestParam String distb2bcurrbal,
			@RequestParam String distAdUnitbal, ModelMap model) {

		if (userId.startsWith("R")) {
			model.addAttribute("userId", userId);
			model.addAttribute("firmName", firmName);
			model.addAttribute("mobileNo", mobileNo);
			model.addAttribute("franBal", distb2bcurrbal);
			model.addAttribute("franAdunitBal", distAdUnitbal);
			return "deductamountfranpage";
		} else if (userId.startsWith("D")) {
			model.addAttribute("userId", userId);
			model.addAttribute("firmName", firmName);
			model.addAttribute("mobileNo", mobileNo);
			model.addAttribute("distcurrbal", distcurrbal);
			model.addAttribute("distb2bcurrbal", distb2bcurrbal);
			model.addAttribute("distAdUnitbal", distAdUnitbal);

			return "deductamountdistpage";
		} else if (userId.startsWith("C")) {
			UserDetailDto userDetailDto = new UserDetailDto();
			userDetailDto.setUserId(userId);
			userDetailDto.setFirmName(firmName);
			userDetailDto.setCurrBal(distcurrbal);
			userDetailDto.setMobileNo(mobileNo);
			model.addAttribute("userdetail", userDetailDto);
			return "deductamountapi";
		} else {
			return "failureAdmin";
		}
	}

	@RequestMapping(value = "/deductamountfran", method = RequestMethod.POST)
	public String deductAmountFranchisee(@RequestParam String retailerid,
			@RequestParam String amount, @RequestParam String remark,
			@RequestParam String franAdunitBal,
			@RequestParam String paymentType, @RequestParam String franBal,
			HttpServletRequest req, ModelMap model) {

		HttpSession session = req.getSession();
		if (paymentType.equalsIgnoreCase("CURRENT")) {

		}
		if (Double.parseDouble(franBal) >= Double.parseDouble(amount)
				&& paymentType.equalsIgnoreCase("CURRENT")
				|| Double.parseDouble(franAdunitBal) >= Double
						.parseDouble(amount)
				&& paymentType.equalsIgnoreCase("ADUNIT")) {
			adminUtilityService.deductFranCurrBal(Long.parseLong((String) req
					.getSession().getAttribute("userid")), Long
					.parseLong(ExtractorUtil.extractIdFromString(retailerid)),
					Double.parseDouble(amount), remark, paymentType);
			model.addAttribute("success", "decuct amount");
			return "deductamountsuccess";
		} else {

			return "failureAdmin";
		}
	}

	@RequestMapping(value = "/deductdistfromadunit", method = RequestMethod.POST)
	public String deductionAmountDistAdUniit(@RequestParam String distId,
			@RequestParam String amount, @RequestParam String remark,
			@RequestParam String distAdUnitbal, ModelMap model) {
		if (Double.parseDouble(distAdUnitbal) >= Double.parseDouble(amount)) {
			adminUtilityService.deductDistAdUnitBal(
					Long.parseLong(ExtractorUtil.extractIdFromString(distId)),
					Double.parseDouble(amount), remark);
			model.addAttribute("success", "decuct amount");
			return "deductamountsuccess";
		} else {

			return "failureAdmin";
		}
	}

	@RequestMapping(value = "/deductdistfromb2bbal", method = RequestMethod.POST)
	public String deductionAmountDistB2bbal(@RequestParam String distId,
			@RequestParam String amount, @RequestParam String remark,
			@RequestParam String distb2bbal, ModelMap model) {
		if (Double.parseDouble(distb2bbal) >= Double.parseDouble(amount)) {
			adminUtilityService.deductDistb2bBal(
					Long.parseLong(ExtractorUtil.extractIdFromString(distId)),
					Double.parseDouble(amount), remark);
			model.addAttribute("success", "decuct amount");
			return "deductamountsuccess";
		} else {

			return "failureAdmin";
		}
	}

	@RequestMapping(value = "/deductdistfromcurrbal", method = RequestMethod.POST)
	public String deductionAmountDistCurrbal(@RequestParam String distId,
			@RequestParam String amount, @RequestParam String remark,
			@RequestParam String distcurrbal, ModelMap model) {
		if (Double.parseDouble(distcurrbal) >= Double.parseDouble(amount)) {
			adminUtilityService.deductDistcurrBal(
					Long.parseLong(ExtractorUtil.extractIdFromString(distId)),
					Double.parseDouble(amount), remark);
			model.addAttribute("success", "decuct amount");
			return "deductamountsuccess";
		} else {

			return "failureAdmin";
		}
	}

}
