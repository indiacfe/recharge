package com.cfeindia.b2bserviceapp.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.admin.AdminReportService;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;
import com.cfeindia.b2bserviceapp.thirdparty.operators.ThirdpartyOperatorList;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class RechargeReportController {

	@Autowired
	AdminReportService adminReportService;
	@Autowired
	private AdminUtilityService adminUtilityService;
	@Autowired
	CommonService commonService;

	@RequestMapping(value = "/rechargehistoryreport")
	public String rechargeReport() {
		return "rechargehistoryreport";
	}

	@RequestMapping(value = "/rechargereport", method = RequestMethod.GET)
	public String showRechargeReport(@RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String sel,
			@RequestParam String status, ModelMap model) {

		/*
		 * SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); Date d1
		 * = null; Date d2 = null; String Error = "Error"; long diffDays = 0;
		 * try { d1 = format.parse(fromDate); d2 = format.parse(toDate); long
		 * diff = d2.getTime() - d1.getTime(); diffDays = diff / (24 * 60 * 60 *
		 * 1000); System.out.print(diffDays + " days, ");
		 * 
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		/* if(diffDays<=30 && diffDays>0){ */
		List<TransactionTransportBean> transportBeansList = adminReportService
				.generateRechargeHistoryReport(fromDate, toDate, sel, status);
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
		/*
		 * }else{ model.addAttribute("Error",Error); }
		 */
		return "rechargehistoryreport";
	}

	@RequestMapping(value = "/customerrechargereport", method = RequestMethod.GET)
	public String customerRechargeReprtController(Model model) {
		List<UserDetailDto> userDetailDto = adminUtilityService
				.customerDetailList();
		model.addAttribute("customerList", userDetailDto);
		return "customerRechargeReport";
	}

	@RequestMapping(value = "/customerrechargereport", method = RequestMethod.POST)
	public String customerRechargeReprtController(
			@RequestParam String fromDate, @RequestParam String toDate,
			@RequestParam String customerId, @RequestParam String status,
			@RequestParam String thirdPartyName, ModelMap model) {
		List<CustomerTransactionTransportBean> transportBeansList = adminReportService
				.generateCustomerRechargeReport(fromDate, toDate, customerId,
						status, thirdPartyName);
		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		for (CustomerTransactionTransportBean transactionTransportBean : transportBeansList) {
			totalCredit = totalCredit
					+ transactionTransportBean.getCreditAmountFranchisee();
			totalDebit = totalDebit + transactionTransportBean.getAmount();
			transactionTransportBean.setCustomerId(ExtractorUtil
					.generateIdFromString(
							transactionTransportBean.getCustomerId(), "C"));
		}
		model.addAttribute("rechargeDetail", transportBeansList);
		model.addAttribute("totalCredit", totalCredit);
		model.addAttribute("totalDebit", totalDebit);
		model.addAttribute("fromdate", fromDate);
		model.addAttribute("todate", toDate);
		model.addAttribute("sel", customerId);
		model.addAttribute("status", status);
		model.addAttribute("serviceProvider", thirdPartyName);
		if ("ALL".equalsIgnoreCase(customerId)) {
			model.addAttribute("customerName", customerId);
		} else {
			Users users = (Users) commonService.getEntityByPrimaryKey(
					Users.class, Long.parseLong(ExtractorUtil
							.extractIdFromString(customerId)));
			model.addAttribute("customerName", users.getUserDetail()
					.getFirmName());
		}

		List<UserDetailDto> userDetailDto = adminUtilityService
				.customerDetailList();
		model.addAttribute("customerList", userDetailDto);
		return "customerRechargeReport";
	}

	@RequestMapping(value = "/mergerechargehistoryreport", method = RequestMethod.GET)
	public String meargeRechargeHistoryReport(Model model) {
		model.addAttribute("customerList",
				adminUtilityService.customerDetailList());
		return "mergerechargehistoryreport";
	}

	@RequestMapping(value = "/mergerechargehistoryreport", method = RequestMethod.POST)
	public String meargeRechargeHistoryReport(@RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String customerId,
			@RequestParam String status, @RequestParam String thirdPartyName,
			ModelMap model) {

		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		List<ReportBeanDto> rList = adminReportService
				.generateMergeRechargeHistoryReport(fromDate, toDate,
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
			Users users = (Users) commonService.getEntityByPrimaryKey(
					Users.class, Long.parseLong(ExtractorUtil
							.extractIdFromString(customerId)));
			model.addAttribute("customerName", users.getUserDetail()
					.getFirmName());
		}

		model.addAttribute("customerList",
				adminUtilityService.customerDetailList());
		return "mergerechargehistoryreport";
	}

	@RequestMapping(value = "/operatorwiserechargereport", method = RequestMethod.GET)
	public String operatorwiserechargereport(Model model) {
		Set<String> operatorSet = new HashSet<String>();
		List<ThirdpartyOperatorList> thirdpartyOperatorList = adminUtilityService
				.thirdPartyOperatorList();
		for (ThirdpartyOperatorList list : thirdpartyOperatorList) {

			operatorSet.add(list.getOperatorName());

		}
		List<String> operatorList = new ArrayList<String>(operatorSet);
		Collections.sort(operatorList);
		model.addAttribute("operatorname", operatorList);
		model.addAttribute("customerList",
				adminUtilityService.customerDetailList());
		return "operatorwiserechargereport";
	}

	@RequestMapping(value = "/operatorwisereport", method = RequestMethod.POST)
	public String operatorwisereport(@RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String customerId,
			@RequestParam String operator, @RequestParam String status,
			@RequestParam String thirdPartyName, ModelMap model) {

		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		List<ReportBeanDto> rList = adminReportService
				.operatorWiseRechargeReport(fromDate, toDate, customerId,
						operator, status, thirdPartyName);
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
		model.addAttribute("indivOperator", operator);
		model.addAttribute("serviceProvider", thirdPartyName);
		if ("ALL".equalsIgnoreCase(customerId)) {
			model.addAttribute("customerName", customerId);
		} else {
			Users users = (Users) commonService.getEntityByPrimaryKey(
					Users.class, Long.parseLong(ExtractorUtil
							.extractIdFromString(customerId)));
			model.addAttribute("customerName", users.getUserDetail()
					.getFirmName());
		}

		model.addAttribute("customerList",
				adminUtilityService.customerDetailList());
		return "operatorwiserechargereport";
	}

	@RequestMapping(value = "/electricityrechargereport", method = RequestMethod.GET)
	public String electricityRechargeHistoryReport(Model model) {
		Set<String> operatorSet = new HashSet<String>();
		List<ThirdpartyOperatorList> thirdpartyOperatorList = adminUtilityService
				.thirdPartyOperatorList();
		for (ThirdpartyOperatorList list : thirdpartyOperatorList) {

			operatorSet.add(list.getOperatorName());

		}
		List<String> operatorList = new ArrayList<String>(operatorSet);
		Collections.sort(operatorList);
		model.addAttribute("operatorname", operatorList);
		model.addAttribute("customerList",
				adminUtilityService.customerDetailList());
		return "electricityrechargereport";
	}

	@RequestMapping(value = "/elctricityreport", method = RequestMethod.POST)
	public String electricityRechargeReport(@RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String customerId,
			@RequestParam String operator, @RequestParam String status,
			@RequestParam String thirdPartyName, ModelMap model) {
		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		List<ReportBeanDto> rList = adminReportService
				.electricityRechargeReport(fromDate, toDate, customerId,
						operator, status, thirdPartyName);
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
		model.addAttribute("indivOperator", operator);
		model.addAttribute("serviceProvider", thirdPartyName);
		if ("ALL".equalsIgnoreCase(customerId)) {
			model.addAttribute("customerName", customerId);
		} else {
			Users users = (Users) commonService.getEntityByPrimaryKey(
					Users.class, Long.parseLong(ExtractorUtil
							.extractIdFromString(customerId)));
			model.addAttribute("customerName", users.getUserDetail()
					.getFirmName());
		}

		model.addAttribute("customerList",
				adminUtilityService.customerDetailList());
		return "electricityrechargereport";
	}
}