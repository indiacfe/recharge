package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cfeindia.b2bserviceapp.common.CacheManager;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;

@RequestMapping("/franchisee/**")
@Controller
public class UtilityBillController {
	@Autowired
	CommonService commonService;

	@RequestMapping(value = "/getOperator", method = RequestMethod.GET)
	public @ResponseBody
	String getOpeartor(@RequestParam String operator)
			throws JsonGenerationException, JsonMappingException, IOException {
		String RETURN_STRING = null;
		ObjectMapper mapper = new ObjectMapper();

		List<String> dataList = new ArrayList<String>();
		if (operator.equals("LANDLINE")) {

			dataList.add("AIRTEL All India+BSNL All India");
			dataList.add("MTNL Delhi+Reliance All India");

		} else if (operator.equals("GAS")) {
			dataList.add("Mahanagar Gas Limited");

		} else if (operator.equals("ELECTRICITY")) {
			dataList.add("BSES Rajdhani");
			dataList.add("BSES Yamuna");
			dataList.add("Reliance Energy Mumbai");
			dataList.add("Tata Power (NDPL) Delhi Distribution Limited");

		} else if (operator.equals("INSURANCE")) {
			dataList.add("Tata AIA Life Insurance");
			dataList.add("ICICI Prudential Life Insurance");

		}

		RETURN_STRING = mapper.writeValueAsString(dataList);

		return RETURN_STRING;
	}

	@RequestMapping(value = "/getretailercommission", method = RequestMethod.GET)
	public @ResponseBody
	String getRetailerCommission(
			@RequestParam("serviceType") String serviceType,
			@RequestParam("operatorName") String operatorName,
			@RequestParam("paymentType") String paymentType,
			@RequestParam("amount") Double amount)
			throws JsonGenerationException, JsonMappingException, IOException {

		String thirdPartyApiName = commonService.getThirdPartyAPISelection(
				serviceType, operatorName);
		Double comissionAmount = null;
		CompanyOperatorComission commission = null;
		try {
			if ("CURRENT".equalsIgnoreCase(paymentType)) {
				commission = commonService.getRetailerCommission(
						thirdPartyApiName, serviceType, operatorName,
						CommonConstants.PERCENTAGE);
				if (commission == null) {
					return "N/A";
				}
				comissionAmount = amount * commission.getRetailercommision()
						/ 100;
			} else {

				commission = commonService.getRetailerCommission(
						thirdPartyApiName, serviceType, operatorName,
						CommonConstants.FLATE);
				if (commission == null) {
					return "N/A";
				} else {
					comissionAmount = commission.getRetailercommision();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comissionAmount.toString();

	}
}
