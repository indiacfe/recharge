package com.cfeindia.b2bserviceapp.controller.distributor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.recharge.mobile.dto.DistributorInfo;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorInfoService;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorSearchFranchiseeDao;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorSearchFranchiseeService;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/distributor/**")
@Controller
public class FranchiseeTranController {
	@Autowired
	FundTransferService fundTransferService;
	@Autowired
	CommonService commonService;
	@Autowired
	private DistributorInfoService distributorInfoService;
	@Autowired
	DistributorSearchFranchiseeService distributorSearchFranchiseeService;

	@RequestMapping(value = "/franchiseetransfer")
	public String franchiseeTransfer() {
		return "franchiseetransfer";
	}

	@RequestMapping(value = "/searchfranchisee", method = RequestMethod.POST)
	public String searchFranchisee(@RequestParam String franchIdOrmobileNo,
			ModelMap model, HttpServletRequest request) {
		HttpSession session= request.getSession();
		if (franchIdOrmobileNo != null
				&& (franchIdOrmobileNo.startsWith("D")
						|| franchIdOrmobileNo.startsWith("E")
						|| franchIdOrmobileNo.length() != 10 || franchIdOrmobileNo
							.startsWith("A"))) {
			model.addAttribute("error", "Not Exist "+franchIdOrmobileNo);
			return "franchiseetransfer";
		}
		Users users = distributorSearchFranchiseeService
				.searchUserBasedOnIdOrMobNum(franchIdOrmobileNo);
		if (users != null) {
			model.addAttribute("mobileNumber", users.getUsername());
			if(users.getUserDetail()!= null)
			{	
				model.addAttribute("firmName", users.getUserDetail().getFirmName());
				session.setAttribute("firmName",users.getUserDetail().getFirmName() );
			}
			else 
				model.addAttribute("firmName","");
			session.setAttribute("firmName","");
			model.addAttribute("distId", users.getUserId());
		} else {
			model.addAttribute("error", "Not Exist " + franchIdOrmobileNo);
			return "franchiseetransfer";
		}
		if (franchIdOrmobileNo.startsWith("R")) {
			model.addAttribute("franchiseeId", franchIdOrmobileNo);
			String id=ExtractorUtil.extractIdFromString(franchIdOrmobileNo);
			FranchiseeCurrBal franchiseeCurrBal=fundTransferService.getCurrentDetail(Long.valueOf(id));		
				model.addAttribute("retailerBal",franchiseeCurrBal.getB2bcurrbal());
		} else {
			Long id = users.getUserId();
			String generatedId = ExtractorUtil.generateIdFromString(
					id.toString(), "R");
			model.addAttribute("franchiseeId", generatedId);
			FranchiseeCurrBal franchiseeCurrBal=fundTransferService.getCurrentDetail(Long.valueOf(id));
			model.addAttribute("retailerBal",franchiseeCurrBal.getB2bcurrbal());
		}
		return "franchiseeResultHome";
	}

	@RequestMapping(value = "/transfertofranchisee", method = RequestMethod.POST)
	public String transferToFranchisee(@RequestParam String franchId,
			@RequestParam String mobileNo, @RequestParam String amount, @RequestParam String firmName,
			ModelMap model, HttpServletRequest request) {
		String id = franchId, result = "";
		model.addAttribute("firmName", firmName);
		FranchiseeCurrBal franchiseeCurrBal;
		id = ExtractorUtil.extractIdFromString(franchId);
		HttpSession session = request.getSession();
		if(Long.valueOf(amount)>=0)
		{	
			String senderId = (String) session.getAttribute("userid");
			if (franchId.startsWith("R")) {
				result = fundTransferService.fundToRetailerCurrService(
						Long.valueOf(id), Long.valueOf(senderId),
						Double.parseDouble(amount));
				franchiseeCurrBal=fundTransferService.getCurrentDetail(Long.valueOf(id));
				Double preRetailerBal= franchiseeCurrBal.getB2bcurrbal()-Long.valueOf(amount);
				model.addAttribute("preRetailerBal",preRetailerBal);
				Double newRetailerBal=franchiseeCurrBal.getB2bcurrbal();
				model.addAttribute("newRetailerBal",newRetailerBal);
			} else {
				franchiseeCurrBal=fundTransferService.getCurrentDetail(Long.valueOf(franchId));
				Double preRetailerBal= franchiseeCurrBal.getB2bcurrbal()-Long.valueOf(amount);
				model.addAttribute("preRetailerBal",preRetailerBal);
				Double newRetailerBal=franchiseeCurrBal.getB2bcurrbal();
				model.addAttribute("newRetailerBal",newRetailerBal);
				result = fundTransferService.fundToRetailerCurrService(
						Long.valueOf(franchId), Long.valueOf(senderId),
						Double.parseDouble(amount));
			}
			model.addAttribute("franchId", franchId);
			model.addAttribute("mobileNo", mobileNo);
			model.addAttribute("amount", amount);
			if (result.equals(CommonConstants.SUCCESS)) {
				DistributorInfo distributorInfo = distributorInfoService
						.distributorInfo(senderId);
				session.setAttribute("distributorInfo", distributorInfo);
				return "redirect:franchiseetransfersuccess";
			} else {
				if (franchId.startsWith("R")) {
					model.addAttribute("franchiseeId", id);
				} else {
					model.addAttribute("franchiseeId", franchId);
				}
				model.addAttribute("retailerBal",franchiseeCurrBal.getB2bcurrbal());
				model.addAttribute("mobileNumber", mobileNo);
				model.addAttribute("error",
						"You have insufficient balance to transfer.");
				return "franchiseeResultHome";
			}
			
		}	
		else
		{
			if (franchId.startsWith("R")) {
				model.addAttribute("franchiseeId", id);
			} else {
				model.addAttribute("franchiseeId", franchId);
			}			
			model.addAttribute("mobileNumber", mobileNo);
			model.addAttribute("error","please enter amount greater than zero");
			return "franchiseeResultHome";
		}	
	}
	
	@RequestMapping(value = "/franchiseetransfersuccess", method = RequestMethod.GET)
	public String finalPage(HttpServletRequest request, ModelMap map,
			@RequestParam String franchId, @RequestParam String mobileNo,
			@RequestParam String amount, @RequestParam String firmName, @RequestParam String preRetailerBal,@RequestParam String newRetailerBal) {
		HttpSession session=request.getSession();
		
		System.out.println("firm name is"+firmName);
		map.addAttribute("firmName", firmName);
		map.addAttribute("franchId", franchId);
		map.addAttribute("mobileNo", mobileNo);
		map.addAttribute("amount", amount);
		map.addAttribute("preRetailerBal",preRetailerBal);
		map.addAttribute("newRetailerBal",newRetailerBal	);
		return "franchiseeTransferSuccess";
	}


}
