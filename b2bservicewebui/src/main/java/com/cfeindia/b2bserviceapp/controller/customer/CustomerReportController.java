package com.cfeindia.b2bserviceapp.controller.customer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.accountstatement.CustomerAccountStatementDto;
import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerRefundRequest;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.model.customer.CustomerAccountStatement;
import com.cfeindia.b2bserviceapp.service.customer.CustAccountStatementService;
import com.cfeindia.b2bserviceapp.service.customer.CustomerRefundService;

@Controller
@RequestMapping("/api/**")
public class CustomerReportController {

	@Autowired
	private CustAccountStatementService custAccountStatementService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private CustomerRefundService customerRefundService;

	@RequestMapping(value = "/accountstatement", method = RequestMethod.GET)
	public String accountStatement(ModelMap model) {

		model.addAttribute("customerAccountStatement",
				new CustomerAccountStatement());
		return "customerAccountStatement";
	}

	@RequestMapping(value = "/accountstatement", method = RequestMethod.POST)
	public String accountStatementRetrive(
			@ModelAttribute CustomerAccountStatement customerAccountStatement,
			ModelMap model, HttpServletRequest req) {
		model.addAttribute("customerAccountStatement", customerAccountStatement);
		if (customerAccountStatement.getFromDate() == null
				|| customerAccountStatement.getToDate() == null) {
			return "customerAccountStatement";
		}
		String userId = (String) req.getSession().getAttribute("userid");
		List<CustomerAccountStatementDto> list = custAccountStatementService
				.generateAccountStatementReport(Long.valueOf(userId),
						customerAccountStatement.getFromDate(),
						customerAccountStatement.getToDate());
		model.addAttribute("reportList", list);
		return "customerAccountStatement";
	}

	@RequestMapping(value = "/rechargehistory", method = RequestMethod.GET)
	public String rechargeHistory(ModelMap model) {

		model.addAttribute("customerAccountStatement",
				new CustomerAccountStatement());
		return "customerRechargeHistory";
	}

	@RequestMapping(value = "/rechargehistory", method = RequestMethod.POST)
	public String rechargeHistoryReport(
			@ModelAttribute CustomerAccountStatement customerAccountStatement,
			ModelMap model, HttpServletRequest req) {
		String userId = (String) req.getSession().getAttribute("userid");
		List<CustomerAccountStatementDto> list = custAccountStatementService
				.generateRechargeHistoryReport(Long.valueOf(userId),
						customerAccountStatement.getFromDate(),
						customerAccountStatement.getToDate(),
						customerAccountStatement.getNumber());
		Map<String, CustomerRefundRequest> mapList = customerRefundService
				.getCustomerRefundRequest(Long.valueOf(userId));

		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		for (CustomerAccountStatementDto dto : list) {
			CustomerRefundRequest customerRefundRequest = mapList.get(dto
					.getClientTransId());
			if (customerRefundRequest != null) {
				dto.setRefunded(true);
			}
			if (CommonConstants.SUCCESS.equalsIgnoreCase(dto.getStatus())) {
				totalCredit = totalCredit + dto.getCreditAmountCustomer();
				totalDebit = totalDebit + dto.getAmount();
			}

		}
		model.addAttribute("customerAccountStatement", customerAccountStatement);
		model.addAttribute("reportList", list);
		model.addAttribute("totalCredit", totalCredit);
		model.addAttribute("totalDebit", totalDebit);
		model.addAttribute("fromDate", customerAccountStatement.getFromDate());
		model.addAttribute("toDate", customerAccountStatement.getToDate());
		return "customerRechargeHistory";
	}

	@RequestMapping(value = "/customercommission", method = RequestMethod.GET)
	public String retailerCommision(ModelMap model, HttpSession session) {
		String userId = (String) session.getAttribute("userid");
		List<CustomerCommision> customerCommisons = custAccountStatementService
				.getCustomerCommisions(Long.valueOf(userId));
		model.addAttribute("commission", customerCommisons);
		return "customerCommissionDetails";
	}

	@RequestMapping(value = "/searchtransaction", method = RequestMethod.GET)
	public String searchTransaction() {
		return "searchTransaction";
	}

	@RequestMapping(value = "/searchtransaction", method = RequestMethod.POST)
	public String searchTransaction(@RequestParam String transactionId,
			HttpServletRequest request, Model model) {
		String userId = (String) request.getSession().getAttribute("userid");
		Long id = Long.parseLong(userId);
		CustomerTransactionTransportBean bean = commonService
				.getCustTransationDetails(transactionId, id);
		model.addAttribute("beanValue", bean);
		return "searchTransaction";
	}
}
