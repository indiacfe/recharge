package com.cfeindia.b2bserviceapp.controller.distributor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cfeindia.b2bserviceapp.recharge.mobile.dto.DistributorInfo;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorInfoService;


@RequestMapping("/distributor/**")
@Controller
public class DistributorController {
	@Autowired
	private DistributorInfoService distributorInfoService;
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String distributorHome(ModelMap model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		String userId=(String)session.getAttribute("userid");
		DistributorInfo distributorInfo=distributorInfoService.distributorInfo(userId);
		session.setAttribute("distributorInfo", distributorInfo);
		return "distributorHome";

	}
	
	

}
