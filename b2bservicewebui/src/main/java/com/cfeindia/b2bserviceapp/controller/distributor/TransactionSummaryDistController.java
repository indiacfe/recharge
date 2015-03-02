package com.cfeindia.b2bserviceapp.controller.distributor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.model.distributor.TransactionSummaryDist;

@RequestMapping("/distributor/**")
@Controller
public class TransactionSummaryDistController {
	
	@RequestMapping(value="/transactionsummary")
	public ModelAndView transactionSummaryDist(){
		
		return new ModelAndView("transactionsummary","command",new TransactionSummaryDist());
	}

}
