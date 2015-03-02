package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.admin.service.RefundAmountService;
import com.cfeindia.b2bserviceapp.franchisee.model.RefundReportFran;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/franchisee/**")
@Controller
public class RefundReportFranController {
	@Autowired
	RefundAmountService refundAmountService;

	@RequestMapping(value = "/refundreport")
	public ModelAndView refundReport() {

		return new ModelAndView("refundreport", "refund",
				new RefundReportFran());
	}

	@RequestMapping(value = "/refundedlist")
	public String refundedList(
			@ModelAttribute("refund") RefundReportFran refundReportFran,
			HttpServletRequest request, ModelMap map) {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		String rechargeType = refundReportFran.getServiceType();
		String fromDate = refundReportFran.getFromDate();
		String toDate = refundReportFran.getToDate();
	//	List<TransactionTransportBean> refundlist = refundAmountService
	//			.getrefundDetail(userid, rechargeType, fromDate, toDate);
	//	map.addAttribute("detailForRefund", refundlist);
		return "refundreport";

	}
}
