package com.cfeindia.b2bserviceapp.controller.franchisee;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@RequestMapping("/franchisee/**")
@Controller
public class PrintTidReceiptFranController {

	@Autowired
	CommonService commonService;

	@RequestMapping(value = "/tidreceipt", method = RequestMethod.GET)
	public String printTidReceipt(
			@RequestParam(value = "transId", required = false) String transId,
			HttpSession session, ModelMap model) {

		if (transId != null) {
			String userId = (String) session.getAttribute("userid");
			TransactionTransportBean transactionTransportBean = commonService
					.getFranTranstionDetail(transId, Long.valueOf(userId));
			if (transactionTransportBean != null) {
				model.addAttribute("transactionTransportBean",
						transactionTransportBean);
				return "printtidreceipt";
			} else {
				model.addAttribute("message", "No Such Transaction Id found");
				return "tidreceipt";
			}
		} else {
			return "tidreceipt";
		}

	}
}
