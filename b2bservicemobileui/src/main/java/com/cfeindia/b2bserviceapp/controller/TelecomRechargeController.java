package com.cfeindia.b2bserviceapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CacheManager;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dto.TransactionTransportBeanDto;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Controller
public class TelecomRechargeController {

	@Autowired
	CommonService commonservice;

	@Autowired
	RechargeTransactionService rechargeTransactionService;

	@Autowired
	FundTransferService fundTransferService;
	
	@Autowired
	CommonService commonService;

	@Autowired
	private FranchiseeInfoService franchiseeInfoService;
	static String XML_VIEW_NAME = "recharge";

	@RequestMapping(value = "/getcirclename/{operator}", method = RequestMethod.GET)
	public ModelAndView getCircleNames(
			@PathVariable("operator") String operatorName) {
		String circles = CacheManager.getOperatorCircles(operatorName,
				"MOBILE_PREPAID");
		return new ModelAndView(XML_VIEW_NAME, "circleNames", circles);
	}

	@RequestMapping(value = "/doTelecomRecharge", method = RequestMethod.POST)
	public ModelAndView doTelecomRecharge(@RequestBody String body,
			HttpServletRequest req) {
		TransactionTransportBean result = new TransactionTransportBean();
		ObjectMapper mapper = new ObjectMapper();
		result.setTransactionName("MOBILE_RECHARGE");
		result.setRechargeType("MOBILE_PREPAID");
		
		FranchiseeCurrBal franchiseeCurrBal = null;
		double preBal = 0.0;
		String outcome = "";
		String userId = "";
		try {
			TransactionTransportBeanDto dto = mapper.readValue(body,
					TransactionTransportBeanDto.class);
			userId = dto.getUserId();
			if (!("").equals(userId)) {
				result.setRetailerId(userId);
			} else {
				throw new Exception("No User Id Found");
			}
			result.setRetailerId(userId);
			result.setFranchiseeMobileNum(commonService.getUsername(userId));
			result.setAmount(Double.valueOf(dto.getAmount()));
			result.setOperator(dto.getOperator());
			if (!dto.getCircle().equals(""))
				result.setCircleCode(dto.getCircle());
			result.setMobileNo(dto.getMobileNo());
			franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
					.valueOf(userId));
			preBal = franchiseeCurrBal.getB2bcurrbal();
			rechargeTransactionService.doRechargeService(result);
		} catch (Exception e) {
			outcome = ";error;";
			result.setErrorMessage("System Error");
			e.printStackTrace();
		}

		if (result.getErrorCode() > 0 || result.getThirdpartytransid() == null) {
			outcome = ";error;" + result.getErrorMessage();
		} else {
			franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
					.valueOf(userId));
			outcome = ";" + result.getTransid() + ";" + result.getThirdPartyTransDateTime() + ";";
			outcome += "" + ExtractorUtil.getRoundedDouble(franchiseeCurrBal
					.getB2bcurrbal()) + ";" + preBal + ";";
		}
		return new ModelAndView(XML_VIEW_NAME, "telecomRecharge", outcome);
	}

	public RechargeTransactionService getdoRechargeTransactionService() {
		return rechargeTransactionService;
	}

	public void setdoRechargeTransactionService(
			RechargeTransactionService rechargeTransactionService) {
		this.rechargeTransactionService = rechargeTransactionService;
	}
}
