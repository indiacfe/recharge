package com.cfeindia.b2bserviceapp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cfeindia.b2bserviceapp.dto.CompanyDistributorTransactionDto;
import com.cfeindia.b2bserviceapp.model.admin.AdminReportDataReq;
import com.cfeindia.b2bserviceapp.service.admin.AdminReportService;
import com.cfeindia.b2bserviceapp.service.admin.TotalFundTransferService;

@Controller
@RequestMapping("/admin/**")
public class TotalFundTransferReport {
	@Autowired
	private TotalFundTransferService totalFundTransferService;
	@Autowired
	private AdminReportService adminReportService;

	@RequestMapping(value = "/totalfundtransferreport", method = RequestMethod.GET)
	public String getTotalFundTransfered(ModelMap model) {

		model.addAttribute("daymonthreport", new AdminReportDataReq());

		return "totalfundtransferreport";
	}

	@RequestMapping(value = "/totalfundtransferreport", method = RequestMethod.POST)
	public String getTotalFundTransfered(
			@ModelAttribute("daymonthreport") AdminReportDataReq adminReportDataReq,
			ModelMap model) {
		Double totalAmount = 0.0;
		if (adminReportDataReq.getReportType().equalsIgnoreCase("day")) {
			List<CompanyDistributorTransactionDto> list = totalFundTransferService
					.getTransferedDetails(adminReportDataReq);
			for (CompanyDistributorTransactionDto cDto : list) {
				totalAmount = totalAmount + cDto.getTransferAmount();
			}
			model.addAttribute("totalAmount", totalAmount);
			model.addAttribute("transferedreport", list);
			model.addAttribute("fromDate", adminReportDataReq.getFromDate());
			model.addAttribute("toDate", adminReportDataReq.getToDate());

		} else {
			List<CompanyDistributorTransactionDto> list = totalFundTransferService
					.getTransferedDetails(adminReportDataReq.getMonth(),
							adminReportDataReq.getYear());
			for (CompanyDistributorTransactionDto cDto : list) {
				totalAmount = totalAmount + cDto.getTransferAmount();
			}
			model.addAttribute("totalAmount", totalAmount);
			model.addAttribute("transferedreport", list);
            
		}
		return "totalfundtransferreport";
	}
}
