package com.cfeindia.b2bserviceapp.controller.franchisee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.franchisee.model.RefundReportFran;
import com.cfeindia.b2bserviceapp.franchisee.service.RefundRequestFranchiseeService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/franchisee/**")
@Controller

public class RefundRequestFranController
{
	@Autowired
	RefundRequestFranchiseeService refundRequestFranchiseeService;
	
	@RequestMapping(value="/refundrequesthome")
	public String refundReport(){
		
		return "refundrequest"; 
	}

	@RequestMapping(value = "/refundrequestfran", method = RequestMethod.POST)
	public String requestRefund(@RequestParam String tid, ModelMap model) {
			TransactionTransportBean transactionTransportBean=refundRequestFranchiseeService.checkTransactionId(tid);
			if(transactionTransportBean!=null)
			{	
				refundRequestFranchiseeService.refundRequest(tid);
				model.addAttribute("success","sent your request");
				return "successpagefran";
			}
			else
			{
				model.addAttribute("error", "TID for this Transaction cannot be refunded");
				return "refundrequest";
			}
		}

}
