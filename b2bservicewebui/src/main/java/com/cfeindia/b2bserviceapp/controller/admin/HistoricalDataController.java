package com.cfeindia.b2bserviceapp.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;
import com.cfeindia.b2bserviceapp.service.admin.HistoricalDataService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class HistoricalDataController {
	@Autowired
	private HistoricalDataService historicalDataService;
	
	@RequestMapping(value="/historicaldata", method=RequestMethod.GET)
	public String historicaldata() {
		return "historicaldata";
	}

	@RequestMapping(value="/showhistoricaldata", method=RequestMethod.GET)
	public String historicalData(@RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String sel,
			@RequestParam String status, ModelMap model){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currDate = new Date();
		Date date;
		long diffDays=0;
		//System.out.println("ss"+date);
		Date d2 = currDate;
		try {
			date = dateFormat.parse(fromDate);
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
		
		
		List<TransactionTransportBean> transportBeansList = historicalDataService
				.generateHistoricalReport(fromDate, toDate, sel, status);
		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		for (TransactionTransportBean transactionTransportBean : transportBeansList) {
			totalCredit = totalCredit
					+ transactionTransportBean.getCreditAmountFranchisee();
			totalDebit = totalDebit + transactionTransportBean.getAmount();
			transactionTransportBean.setRetailerId(ExtractorUtil
					.generateIdFromString(
							transactionTransportBean.getRetailerId(), "R"));
		}
		
		model.addAttribute("rechargeDetail", transportBeansList);
		model.addAttribute("totalCredit", totalCredit);
		model.addAttribute("totalDebit", totalDebit);
		model.addAttribute("fromdate", fromDate);
		model.addAttribute("todate", toDate);
		model.addAttribute("sel", sel);
		model.addAttribute("status", status);
		
		
		return "historicaldatareport";
		
	}
	@RequestMapping(value="/historicalmergedata", method=RequestMethod.GET)
	public String historicalmergedata(Model model) {
		model.addAttribute("customerList",
				historicalDataService.customerHistoricalDetailList());
		return "historicalmergedata";
	}
	@RequestMapping(value = "/historicalmergedatareport", method = RequestMethod.POST)
	public String meargeRechargeHistoryReport(@RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String customerId,
			@RequestParam String status, @RequestParam String thirdPartyName,
			ModelMap model) {
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date currDate = new Date();
		Date date;
		long diffDays=0;
		Date d2 = currDate;
		try {
			date = dateFormat.parse(fromDate);
			long diff = d2.getTime() - date.getTime();
			  diffDays = diff / (24 * 60 * 60 * 1000);
      if(diffDays<180){
    	  model.addAttribute("customerList",
  				historicalDataService.customerHistoricalDetailList());
    	  model.addAttribute("error", "You can see only 6 months ago data");
    	  return "historicaldatareport";
      }
		}
		catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		
		
		

		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		List<ReportBeanDto> rList = historicalDataService
				.generateHistoricalMergeReport(fromDate, toDate,
						customerId, status, thirdPartyName);
		for (ReportBeanDto dto : rList) {
			totalCredit = totalCredit + dto.getCreditAmountFranchisee();
			totalDebit = totalDebit + dto.getAmount();
		}
		model.addAttribute("rechargeDetail", rList);
		model.addAttribute("totalCredit", totalCredit);
		model.addAttribute("totalDebit", totalDebit);
		model.addAttribute("fromdate", fromDate);
		model.addAttribute("todate", toDate);
		model.addAttribute("status", status);
		model.addAttribute("serviceProvider", thirdPartyName);
		if ("ALL".equalsIgnoreCase(customerId)) {
			model.addAttribute("customerName", customerId);
		} else {
			String users =  historicalDataService.getEntityByPrimaryKeyHistoricalData(customerId);
			model.addAttribute("customerName", users);
		}

		model.addAttribute("customerList",
				historicalDataService.customerHistoricalDetailList());
		return "historicalmergedata";
	}

}
