package com.cfeindia.b2bserviceapp.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutCyberTelController {
	@RequestMapping(value="/aboutcybertel",method=RequestMethod.GET)
	public String aboutCyberTel(){
		
		return "about";
	}
	@RequestMapping(value="/faqcybertel",method=RequestMethod.GET)
	public String faqCyberTel(){
		return "faq";
	}
	@RequestMapping(value="/contactus",method=RequestMethod.GET)
	public String contactUsCyberTel(){
		return "contactus";
	}
}
