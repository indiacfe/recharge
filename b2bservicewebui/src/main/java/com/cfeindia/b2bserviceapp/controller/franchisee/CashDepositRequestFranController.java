package com.cfeindia.b2bserviceapp.controller.franchisee;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.model.franchisee.CashDepositRequestFran;
import com.cfeindia.b2bserviceapp.service.franchisee.CashDepositRequestFranchiseeService;

@RequestMapping("/franchisee/**")
@Controller
public class CashDepositRequestFranController {
	@Autowired
	CashDepositRequestFranchiseeService cashDepositRequestFranchiseeService;

	@RequestMapping(value="/cashdepositrequestfran")
	public ModelAndView cashDeposit(){
		
		return new ModelAndView("cashdepositrequestfran","command",new CashDepositRequestFran());
	}
	
	@RequestMapping(value="/submitrequest", method=RequestMethod.POST)
	public String submitRequest(@ModelAttribute CashDepositRequestFran cashDepositRequestFran,ModelMap modelMap, HttpServletRequest req)
	{
		String userId=(String)req.getSession().getAttribute("userid");
		try
		{
			
		cashDepositRequestFranchiseeService.saveCashDepositRequest(userId, cashDepositRequestFran);
		modelMap.addAttribute("success","sent your request");
		return "successpagefran";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "failure";
		}	
	}
}
