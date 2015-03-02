package com.cfeindia.b2bserviceapp.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.service.admin.LicManagementService;

@Controller
@RequestMapping("/admin/**")
public class LicPremiumMgmt {

  
	
	@Autowired
	private LicManagementService licManagementService;

	
	@RequestMapping(value = "/licpremiums", method = RequestMethod.GET)
	public String getDetails() {
		return "licpremiumdetails";
	}

	@RequestMapping(value = "/licpremiumsdetails", method = RequestMethod.GET)
	public String getDetails(@RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String status,
			ModelMap model, HttpServletRequest request) {

		model.addAttribute("licpremiumdetails",
				licManagementService.getDetailservice(fromDate, toDate, status));
		model.addAttribute("fromdate", fromDate);
		model.addAttribute("todate", toDate);
		model.addAttribute("status", status);

		return "licpremiumdetails";
	}

	@RequestMapping(value = "/changestatussuccess", method = RequestMethod.POST)
	public String getDetailsSuccess(ModelMap model, HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("Id"));
		licManagementService.getDetailStatusSuccess(id);
		model.addAttribute("licpremiumdetails",
				licManagementService.getDetailStatusSuccess(id));
		// model.addAttribute("success", "success");
		return "licpremiumdetails";
	}

	@RequestMapping(value = "/changestatusrejected", method = RequestMethod.POST)
	public String getDetailsRejected( @RequestParam Long Id,ModelMap model) {
		//licManagementService.getDetailStatusRejected(Id);
		model.addAttribute("licpremiumdetails",
				licManagementService.getDetailStatusRejected(Id));
		

		return "licpremiumdetails";
	}

}
