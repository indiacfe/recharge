package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.admin.model.AdminFundTrasferReportDTO;
import com.cfeindia.b2bserviceapp.admin.model.AdminReportDataReq;
import com.cfeindia.b2bserviceapp.admin.service.AdminReportService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Controller
@RequestMapping("/admin/**")
public class AdminReportController {

	@Autowired
	private AdminReportService reportService;
	
	@RequestMapping(value = "/adminfundtransfer")
	public ModelAndView accountStatement() {

		return new ModelAndView("adminfundtransfer", "adminfundtransfer",
				new AdminReportDataReq());
	}

	@RequestMapping(value = "/adminfundtransferreport")
	public String accountStatementReport(
			@ModelAttribute AdminReportDataReq adminfundtransfer, ModelMap model, HttpServletRequest req) {
		if(adminfundtransfer.getNumber()!= null && (adminfundtransfer.getNumber().startsWith("D") || adminfundtransfer.getNumber().startsWith("R"))) {
			adminfundtransfer.setNumber(ExtractorUtil.extractIdFromString(adminfundtransfer.getNumber()));
		}
		List<AdminFundTrasferReportDTO> list = reportService.generateFundTransferReport(adminfundtransfer.getNumber(),adminfundtransfer.getFromDate(),
				adminfundtransfer.getToDate());
			model.addAttribute("adminfundtransfer", adminfundtransfer);
		model.addAttribute("reportList", list);
		return "adminfundtransfer";
	}
}
