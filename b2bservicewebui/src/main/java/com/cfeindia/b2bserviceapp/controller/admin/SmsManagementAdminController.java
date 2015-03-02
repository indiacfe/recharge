package com.cfeindia.b2bserviceapp.controller.admin;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.service.admin.SmsDetailsService;

@RequestMapping("/admin/**")
@Controller
public class SmsManagementAdminController {
	@Autowired
	private SmsDetailsService smsDetailsService;

	@RequestMapping(value = "/smsrechargedetails", method = RequestMethod.GET)
	public String getSmsdetails() {

		return "smsdetails";
	}

	@RequestMapping(value = "/smsdetailsreport", method = RequestMethod.GET)
	public String getSmsdetails(@RequestParam String fromdate,
			@RequestParam String toDate, ModelMap model) throws ParseException {

		model.addAttribute("smsdetails",
				smsDetailsService.getMessageDetailsservice(fromdate, toDate));
		model.addAttribute("fromdate", fromdate);
		model.addAttribute("toDate", toDate);
		return "smsdetails";
	}
}
