package com.cfeindia.b2bserviceapp.controller.franchisee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.franchisee.model.PrintTidReciptFran;


@RequestMapping("/franchisee/**")
@Controller
public class PrintTidReceiptFranController {

	@RequestMapping(value="/tidreceipt")
	public ModelAndView printTidReceipt(){
		
		return new ModelAndView("tidreceipt","command",new PrintTidReciptFran());
	}
}
