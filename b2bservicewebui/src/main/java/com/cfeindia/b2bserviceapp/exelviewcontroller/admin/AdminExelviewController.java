package com.cfeindia.b2bserviceapp.exelviewcontroller.admin;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
import com.cfeindia.b2bserviceapp.dto.CompanyDistributorTransactionDto;
import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.model.admin.AdminFundTrasferReportDTO;
import com.cfeindia.b2bserviceapp.model.admin.AdminReportDataReq;
import com.cfeindia.b2bserviceapp.model.franchisee.CashDepositRequestFran;
import com.cfeindia.b2bserviceapp.service.admin.AdminReportService;
import com.cfeindia.b2bserviceapp.service.admin.AdminUtilityService;
import com.cfeindia.b2bserviceapp.service.admin.SmsDetailsService;
import com.cfeindia.b2bserviceapp.service.admin.TotalFundTransferService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class AdminExelviewController {
	@Autowired
	AdminReportService adminReportService;
	@Autowired
	private AdminUtilityService adminUtilityService;
	@Autowired
	CommonService commonService;
	@Autowired
	private AdminReportService reportService;
	@Autowired
	private TotalFundTransferService totalFundTransferService;
	@Autowired
	private SmsDetailsService smsDetailsService;

	@RequestMapping(value = "/AdminRechargeHistoryReportList", method = RequestMethod.GET)
	public ModelAndView showRechargeReport(@RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String sel,
			@RequestParam String status, ModelMap model) {

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

		return new ModelAndView("RechargeHistorySummary", "list",
				transportBeansList);
	}

	@RequestMapping(value = "/AdminMergeRechargeHistory", method = RequestMethod.POST)
	public ModelAndView meargeRechargeHistoryReport(
			@RequestParam String fromDate, @RequestParam String toDate,
			@RequestParam String customerId, @RequestParam String status,
			@RequestParam String thirdPartyName, ModelMap model) {

		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		List<ReportBeanDto> rList = adminReportService
				.generateMergeRechargeHistoryReport(fromDate, toDate,
						customerId, status, thirdPartyName);
		for (ReportBeanDto dto : rList) {
			totalCredit = totalCredit + dto.getCreditAmountFranchisee();
			totalDebit = totalDebit + dto.getAmount();
		}

		if ("ALL".equalsIgnoreCase(customerId)) {
			model.addAttribute("customerName", customerId);
		} else {
			Users users = (Users) commonService.getEntityByPrimaryKey(
					Users.class, Long.parseLong(ExtractorUtil
							.extractIdFromString(customerId)));
			model.addAttribute("customerName", users.getUserDetail()
					.getFirmName());
		}

		return new ModelAndView("MergeRechargeHistorySummary", "list", rList);
	}

	@RequestMapping(value = "/AdminCashDepositRequest", method = RequestMethod.GET)
	public ModelAndView cashDepositRequests(
			@ModelAttribute CashDepositRequestFran cashDepositRequestFran,
			ModelMap model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userid");
		List<FranchiseeCashDepositRequest> list = commonService
				.trackCashDepositRequests();

		return new ModelAndView("CashDepositReportSummary", "list", list);
	}

	@RequestMapping(value = "/AdminUserDetailsReportList", method = RequestMethod.GET)
	public ModelAndView userDetail(
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			ModelMap model) {

		Number number = adminUtilityService.countUserList();
		int totalPage;
		int remainder = number.intValue() % 50;
		int divide = number.intValue() / 50;
		if (remainder <= 0) {
			totalPage = divide;
		} else {
			totalPage = divide + 1;
		}

		List<UserDetailDto> userDetailDto = adminUtilityService
				.userDetailList(pageNo);

		return new ModelAndView("UserDetailSummary", "list", userDetailDto);
	}

	@RequestMapping(value = "/AdminFundTransfer")
	public ModelAndView accountStatementReport(
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

		return new ModelAndView("FundTransferReportSummary", "list", list);
	}

	@RequestMapping(value = "/AdminThirdPartyServiceProviderList")
	public ModelAndView thirdPartyService(ModelMap model) {
		List<ThirdPartyServiceProvider> thirdPartySerProviderList = adminUtilityService
				.thirdPartyServiceProvider();

		return new ModelAndView("AdminThirdPartyServiceProviderSummary",
				"list", thirdPartySerProviderList);

	}

	@RequestMapping(value = "/AdminCompanyOperatorCommissionList")
	public ModelAndView companyOperatorComm(ModelMap model) {
		List<CompanyOperatorComission> operatorComissionList = adminUtilityService
				.companyOperatorComission();

		return new ModelAndView("AdminCompanyOperatorCommSummary", "list",
				operatorComissionList);

	}

	@RequestMapping(value = "/AdminGenrateReport")
	public ModelAndView getReport(ModelMap model) {

		return new ModelAndView("AdminGenrateReportSummary", "list",
				adminUtilityService.userDetailList());
	}

	@RequestMapping(value = "/AdminTotalFundTransfer", method = RequestMethod.POST)
	public ModelAndView getTotalFundTransfered(
			@ModelAttribute("daymonthreport") AdminReportDataReq adminReportDataReq,
			ModelMap model) {
		Double totalAmount = 0.0;
		if (adminReportDataReq.getReportType().equalsIgnoreCase("day")) {
			List<CompanyDistributorTransactionDto> list = totalFundTransferService
					.getTransferedDetails(adminReportDataReq);
			for (CompanyDistributorTransactionDto cDto : list) {
				totalAmount = totalAmount + cDto.getTransferAmount();
			}
			return new ModelAndView("AdminFundTransReportSummary", "list", list);

		} else {
			List<CompanyDistributorTransactionDto> list = totalFundTransferService
					.getTransferedDetails(adminReportDataReq.getMonth(),
							adminReportDataReq.getYear());
			for (CompanyDistributorTransactionDto cDto : list) {
				totalAmount = totalAmount + cDto.getTransferAmount();
			}

			return new ModelAndView("AdminFundTransReportSummary", "list", list);
		}

	}

	@RequestMapping(value = "/AdminDistAndRetailerList", method = RequestMethod.GET)
	public ModelAndView showDistributorsAndRetailers(@RequestParam String sel,
			ModelMap model) {

		if (sel.equals("Distributors")) {

		} else if (sel.equals("Retailers")) {

		} else {
			List<UserDetailDto> userDetailDtoList = adminUtilityService
					.userDetailList();
			model.addAttribute("userdetail", userDetailDtoList);

		}
		return new ModelAndView("AdminDistAndRetailerSummary", "list",
				adminUtilityService.userDetailList());
	}

	@RequestMapping(value = "/SMSRechargeDetails", method = RequestMethod.GET)
	public ModelAndView getSmsdetails(@RequestParam String fromdate,
			@RequestParam String toDate, ModelMap model) throws ParseException {
		
		return new ModelAndView("AdminSMSRechargeReportSummary", "list",
				smsDetailsService.getMessageDetailsservice(fromdate, toDate));
	}
	
	@RequestMapping(value = "/AdminOperatorWiseRechargeHistory", method = RequestMethod.POST)
	public ModelAndView operatorWiseRechargeHistoryReport(
			@RequestParam String fromDate, @RequestParam String toDate,
			@RequestParam String customerId, @RequestParam String status,
			@RequestParam String thirdPartyName, @RequestParam String operator, ModelMap model) {

		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		List<ReportBeanDto> rList = adminReportService
				.operatorWiseRechargeReport(fromDate, toDate, customerId, operator,
						status, thirdPartyName);
		for (ReportBeanDto dto : rList) {
			totalCredit = totalCredit + dto.getCreditAmountFranchisee();
			totalDebit = totalDebit + dto.getAmount();
		}

		if ("ALL".equalsIgnoreCase(customerId)) {
			model.addAttribute("customerName", customerId);
		} else {
			Users users = (Users) commonService.getEntityByPrimaryKey(
					Users.class, Long.parseLong(ExtractorUtil
							.extractIdFromString(customerId)));
			model.addAttribute("customerName", users.getUserDetail()
					.getFirmName());
		}

		return new ModelAndView("AdminOperatorWiseRechargeHistorySummary", "list", rList);
	}
	@RequestMapping(value = "/AdminElectricityRechargeHistory", method = RequestMethod.POST)
	public ModelAndView electricityRechargeHistoryReport(
			@RequestParam String fromDate, @RequestParam String toDate,
			@RequestParam String customerId, @RequestParam String status,
			@RequestParam String thirdPartyName, @RequestParam String operator, ModelMap model) {

		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		List<ReportBeanDto> rList = adminReportService
				.operatorWiseRechargeReport(fromDate, toDate, customerId, operator,
						status, thirdPartyName);
		for (ReportBeanDto dto : rList) {
			totalCredit = totalCredit + dto.getCreditAmountFranchisee();
			totalDebit = totalDebit + dto.getAmount();
		}

		if ("ALL".equalsIgnoreCase(customerId)) {
			model.addAttribute("customerName", customerId);
		} else {
			Users users = (Users) commonService.getEntityByPrimaryKey(
					Users.class, Long.parseLong(ExtractorUtil
							.extractIdFromString(customerId)));
			model.addAttribute("customerName", users.getUserDetail()
					.getFirmName());
		}

		return new ModelAndView("AdminElectricityRechargeHistorySummary", "list", rList);
	}
}
