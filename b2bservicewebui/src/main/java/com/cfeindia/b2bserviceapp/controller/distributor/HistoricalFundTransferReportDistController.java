package com.cfeindia.b2bserviceapp.controller.distributor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.model.distributor.FundTransferReportDist;
import com.cfeindia.b2bserviceapp.model.distributor.TransactionSummaryDist;
import com.cfeindia.b2bserviceapp.service.distributor.DistBalTransStatementService;
import com.cfeindia.b2bserviceapp.service.distributor.HistoricalAllReportDistService;

@RequestMapping("/distributor/**")
@Controller
public class HistoricalFundTransferReportDistController {
	@Autowired
	private DistBalTransStatementService distBalTransStatementService;
	
	@Autowired
	private HistoricalAllReportDistService historicalAllReportDistService;

	@RequestMapping(value = "/historicalfundtransfer")
	public ModelAndView fundTransferRetailer() {
		return new ModelAndView("historicalfundtransfer", "fundTransferReport",
				new FundTransferReportDist());
	}

	@RequestMapping(value = "/historicalfundTransferReport", method = RequestMethod.GET)
	public String fundTransfertocurr(
			@ModelAttribute("fundTransferReport") FundTransferReportDist fundTransferReportDist,
			HttpServletRequest request, ModelMap model) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currDate = new Date();
		Date date;
		long diffDays=0;
		//System.out.println("ss"+date);
		Date d2 = currDate;
		try {
			date = dateFormat.parse(fundTransferReportDist.getFromDate());
			long diff = d2.getTime() - date.getTime();
			  diffDays = diff / (24 * 60 * 60 * 1000);
      if(diffDays<180){
    	  model.addAttribute("error", "You can see only 6 months ago data");
    	  return "historicaldatareport";
      }
		}
		catch (ParseException e) {
			
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		List<DistributorBalanceTransferLog> transferlog = historicalAllReportDistService
				.getFranBalTransferStatement(
						fundTransferReportDist.getFromDate(),
						fundTransferReportDist.getToDate(), userId);
		model.addAttribute("distBalanceTransferLog", transferlog);
		return "historicalfundtransfer";
	}
	@RequestMapping(value="/historicaltransactionsummary")
	public ModelAndView transactionSummaryDist(){
		
		return new ModelAndView("historicaltransactionsummary","command",new TransactionSummaryDist());
	}


}
