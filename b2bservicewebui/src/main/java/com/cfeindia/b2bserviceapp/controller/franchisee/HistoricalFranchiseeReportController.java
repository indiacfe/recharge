package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.model.franchisee.AccountStament;
import com.cfeindia.b2bserviceapp.service.franchisee.HistoricalFranReportService;


@RequestMapping("/franchisee/**")
@Controller
public class HistoricalFranchiseeReportController {
	
	@Autowired
	HistoricalFranReportService historicalFranReportService;
	
	@RequestMapping(value = "/historicalrechargehistory")
	public ModelAndView rechargeHistory() {

		return new ModelAndView("historicalrechargehistory", "accountstatement",
				new AccountStament());
	}

	@RequestMapping(value = "/historicalrechargehistoryretrieve")
	public String rechargeHistoryReport(
			@ModelAttribute AccountStament accountStatement, ModelMap model, HttpServletRequest req) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currDate = new Date();
		Date date;
		long diffDays=0;
		//System.out.println("ss"+date);
		Date d2 = currDate;
		try {
			date = dateFormat.parse(accountStatement.getFromDate());
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
		
		String userId = (String)req.getSession().getAttribute("userid");
		List<FranchiseeAccountStatementDto> list = historicalFranReportService
				.getHistoricalFranBalTransferStatement(Long.valueOf(userId),accountStatement.getFromDate(),
						accountStatement.getToDate(), accountStatement.getNumber());
		model.addAttribute("accountstatement", accountStatement);
		model.addAttribute("reportList", list);
		return "historicalrechargehistory";
	}

}
