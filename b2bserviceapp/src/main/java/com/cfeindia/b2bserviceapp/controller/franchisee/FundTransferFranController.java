package com.cfeindia.b2bserviceapp.controller.franchisee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.franchisee.model.FundTransferFran;

@RequestMapping("/franchisee/**")
@Controller
public class FundTransferFranController {

	@RequestMapping(value="/fundtransferfran")
	public ModelAndView fundTransferFran(){
		
		return new ModelAndView("fundtransferfran","command",new FundTransferFran());
	}
	
}
