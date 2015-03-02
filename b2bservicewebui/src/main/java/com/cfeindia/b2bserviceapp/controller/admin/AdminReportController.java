package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.model.admin.AdminFundTrasferReportDTO;
import com.cfeindia.b2bserviceapp.model.admin.AdminReportDataReq;
import com.cfeindia.b2bserviceapp.service.admin.AdminReportService;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Controller
@RequestMapping("/admin/**")
public class AdminReportController {

	@Autowired
	private AdminReportService reportService;
	@Autowired
	private AdminUtilityService adminUtilityService;

	@RequestMapping(value = "/adminfundtransfer")
	public ModelAndView accountStatement() {

		return new ModelAndView("adminfundtransfer", "adminfundtransfer",
				new AdminReportDataReq());
	}

	@RequestMapping(value = "/adminfundtransferreport")
	public String accountStatementReport(
			@ModelAttribute AdminReportDataReq adminfundtransfer,
			ModelMap model, HttpServletRequest req) {
		if (adminfundtransfer.getNumber() != null
				&& (adminfundtransfer.getNumber().startsWith("D") || adminfundtransfer
						.getNumber().startsWith("R"))) {
			adminfundtransfer.setNumber(ExtractorUtil
					.extractIdFromString(adminfundtransfer.getNumber()));
		}
		List<AdminFundTrasferReportDTO> list = reportService
				.generateFundTransferReport(adminfundtransfer.getNumber(),
						adminfundtransfer.getFromDate(),
						adminfundtransfer.getToDate());
		model.addAttribute("adminfundtransfer", adminfundtransfer);
		model.addAttribute("reportList", list);
		return "adminfundtransfer";
	}

	@RequestMapping(value = "/daybasereport")
	public String getReport(ModelMap model) {
		model.addAttribute("userdetail", adminUtilityService.userDetailList());
		return "dayreport";
	}

	@RequestMapping(value = "/franchiseedayreport")
	public String getFranchiseeReport(@RequestParam String userId,
			ModelMap model) {
		AdminReportDataReq request = new AdminReportDataReq();
		request.setNumber(userId);
		model.addAttribute("datewisereport", request);

		return "franchiseedayreportdetails";
	}

	@RequestMapping(value = "/franchiseedayreport", method = RequestMethod.POST)
	public String getFranchiseeReportDetails(
			@ModelAttribute("datewisereport") AdminReportDataReq adminfundtransfer,
			ModelMap model) {
		String showIdWithType = adminfundtransfer.getNumber();
		if (adminfundtransfer.getNumber() != null
				&& (adminfundtransfer.getNumber().startsWith("D") || adminfundtransfer
						.getNumber().startsWith("R"))) {
			adminfundtransfer.setNumber(ExtractorUtil
					.extractIdFromString(adminfundtransfer.getNumber()));
		}
		List<TransactionTransportBean> list = reportService
				.generateFranTransactionReportService(adminfundtransfer);
		double totalAmount = 0.0;
		for (TransactionTransportBean tranBean : list) {
			totalAmount = totalAmount + tranBean.getAmount();
		}
		model.addAttribute("reportList", list);
		model.addAttribute("totalamount", totalAmount); 
		adminfundtransfer.setNumber(showIdWithType);
		model.addAttribute("datewisereport", adminfundtransfer);
		return "franchiseedayreportdetails";
	}

	@RequestMapping(value = "/apifundtranserreport", method = RequestMethod.GET)
	public String fundTransferReportGet(ModelMap model) {
		model.addAttribute("adminfundtransfer", new AdminReportDataReq());
		return "apiFundTransferReport";
	}

	@RequestMapping(value = "/apifundtranserreport", method = RequestMethod.POST)
	public String fundTransferReportPost(
			@ModelAttribute AdminReportDataReq adminfundtransfer, ModelMap model) {
		if (adminfundtransfer.getNumber() != null
				&& (adminfundtransfer.getNumber().startsWith("C"))) {
			adminfundtransfer.setNumber(ExtractorUtil
					.extractIdFromString(adminfundtransfer.getNumber()));
		}
		List<AdminFundTrasferReportDTO> adminFundTrasferReportDTOList = reportService
				.getCustFundTransferReport(adminfundtransfer.getNumber(),
						adminfundtransfer.getFromDate(),
						adminfundtransfer.getToDate());
		model.addAttribute("reportList", adminFundTrasferReportDTOList);
		model.addAttribute("adminfundtransfer", new AdminReportDataReq());
		return "apiFundTransferReport";
	}
}
