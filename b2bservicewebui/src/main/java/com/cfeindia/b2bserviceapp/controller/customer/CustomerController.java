package com.cfeindia.b2bserviceapp.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.model.customer.CustomerInfo;
import com.cfeindia.b2bserviceapp.service.customer.CustomerInfoService;

@Controller
@RequestMapping("/api/**")
public class CustomerController {

	@Autowired
	CustomerInfoService customerInfoService;

	@Autowired
	CommonService commonService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String redirect(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");
		CustomerInfo customerInfo = customerInfoService.getCustomerInfo(userId);
		session.setAttribute("customerInfo", customerInfo);
		return "customerhome";
	}

	@RequestMapping(value = "/transacionReceipt", method = RequestMethod.GET)
	public String transacionReceipt(
			@RequestParam(value = "clientTransId", required = false) String clientTransId,
			HttpSession session, ModelMap model) {
		if (clientTransId != null) {
			String userId = (String) session.getAttribute("userid");
			CustomerTransactionTransportBean transactionTransportBean = commonService
					.getCustTransationDetails(clientTransId,
							Long.valueOf(userId));
			if (transactionTransportBean != null) {
				model.addAttribute("transactionTransportBean",
						transactionTransportBean);
				return "printTransacionReceipt";
			} else {
				model.addAttribute("message", "No Such Transaction Id found");
				return "transacionReceipt";
			}
		} else {
			return "transacionReceipt";
		}
	}
}
