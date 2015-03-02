package com.cfeindia.b2bserviceapp.controller.admin;

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

import com.cfeindia.b2bserviceapp.model.admin.AdminFundTrasferReportDTO;
import com.cfeindia.b2bserviceapp.model.admin.AdminReportDataReq;
import com.cfeindia.b2bserviceapp.service.admin.HistoricalFundTransferReportService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class HistoricalFundTransferReportController {
	
	@Autowired
	HistoricalFundTransferReportService historicalFundTransferReportService;
	@RequestMapping(value = "/historicaladminfundtransfer")
	public ModelAndView accountStatement() {

		return new ModelAndView("historicaladminfundtransfer", "adminfundtransfer",
				new AdminReportDataReq());
	}

	@RequestMapping(value = "/historicaladminfundtransferreport")
	public String accountStatementReport(
			@ModelAttribute AdminReportDataReq adminfundtransfer,
			ModelMap model, HttpServletRequest req) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currDate = new Date();
		Date date;
		long diffDays=0;
		//System.out.println("ss"+date);
		Date d2 = currDate;
		try {
			date = dateFormat.parse(adminfundtransfer.getFromDate());
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
		if (adminfundtransfer.getNumber() != null
				&& (adminfundtransfer.getNumber().startsWith("D") || adminfundtransfer
						.getNumber().startsWith("R"))) {
			adminfundtransfer.setNumber(ExtractorUtil
					.extractIdFromString(adminfundtransfer.getNumber()));
		}
		List<AdminFundTrasferReportDTO> list = historicalFundTransferReportService
				.generateHistoricalFundTransferReport(adminfundtransfer.getNumber(),
						adminfundtransfer.getFromDate(),
						adminfundtransfer.getToDate());
		model.addAttribute("adminfundtransfer", adminfundtransfer);
		model.addAttribute("reportList", list);
		return "historicaladminfundtransfer";
	}

}
