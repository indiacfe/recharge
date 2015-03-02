package com.cfeindia.b2bserviceapp.controller.franchisee;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeEarningService;

@Controller
@RequestMapping("/franchisee/**")
public class FranchiseeEarningController {
	@Autowired
	private FranchiseeEarningService franchiseeEarningService;

	@RequestMapping("/earning")
	public String getEarning() {
		return "earningperdayandmonth";
	}

	@RequestMapping("/franearning")
	public String getEarningDetails(@RequestParam String fromDate,
			@RequestParam String toDate, HttpServletRequest request,
			ModelMap model) {
		System.out.println(fromDate + " " + toDate);
		System.out.println(request.getSession().getAttribute("userid"));
		model.addAttribute("earning", franchiseeEarningService.getAmountEarned(
				fromDate, toDate,100l));
		
		return "earningperdayandmonth";
	}

}
