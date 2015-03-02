package com.cfeindia.b2bserviceapp.controller.distributor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.recharge.mobile.dto.DistributorInfo;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorInfoService;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;

@RequestMapping("/distributor/**")
@Controller
public class TransferAdUnitController {
	@Autowired
	FundTransferService fundTransferService;
	@Autowired
	private DistributorInfoService distributorInfoService;

	@RequestMapping(value = "/transferadunit", method = RequestMethod.GET)
	public String transferUnit() {

		return "transferadunit";
	}

	@RequestMapping(value="/transferadunittob2b",method=RequestMethod.POST)
	public String transferAddUnitToB2B(@RequestParam Double amount,HttpServletRequest request,ModelMap model){
		String pathToFallow="transferadunit";
		HttpSession  session=request.getSession();
		String userId=(String)(session.getAttribute("userid"));
		Long distributorId=Long.parseLong(userId);
		String result=fundTransferService.distAdUnitBalTransTodistCurrBal(distributorId, amount);
		if(("SUCCESS").equalsIgnoreCase(result)){
			DistributorInfo distributorInfo=distributorInfoService.distributorInfo(userId);
			session.setAttribute("distributorInfo", distributorInfo);
		return pathToFallow="redirect:successpage";
		}
		else{
			model.addAttribute("error","Please try again.");
			return pathToFallow;
		}
	}
	@RequestMapping(value="/successpage",method=RequestMethod.GET)
	public String finalPage(ModelMap model){
		model.addAttribute("success","Transferred Amount");
		return "successpagedist";
	}
}
