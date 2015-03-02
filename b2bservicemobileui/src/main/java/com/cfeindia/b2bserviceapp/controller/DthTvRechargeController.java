package com.cfeindia.b2bserviceapp.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dto.DthRechargeDto;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Controller
public class DthTvRechargeController {

	@Autowired
	RechargeTransactionService rechargeTransactionService;

	@Autowired
	private FranchiseeInfoService franchiseeInfoService;

	@Autowired
	FundTransferService fundTransferService;

	@Autowired
	CommonService commonService;
	private String XML_VIEW_NAME = "dthRecharge";

	@RequestMapping(value = "/doDthRecharge", method = RequestMethod.POST)
	public ModelAndView doDthRecharge(@RequestBody String body)
			throws JsonParseException, JsonMappingException, IOException {
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		ObjectMapper mapper = new ObjectMapper();
		DthRechargeDto dto = mapper.readValue(body, DthRechargeDto.class);
		FranchiseeCurrBal franchiseeCurrBal = null;
		double preBal = 0.0;
		String outcome = "";
		String userId = null;
		if (dto != null && !dto.getUserId().equals("")) {
			userId = dto.getUserId();
			transactionTransportBean.setRetailerId(userId);
			transactionTransportBean.setOperator(dto.getOperator());
			transactionTransportBean.setAmount(Double.valueOf(dto.getAmount()));
			transactionTransportBean.setConnectionid(dto.getDthNumber());
			transactionTransportBean.setFranchiseeMobileNum(commonService.getUsername(userId));
			transactionTransportBean.setRechargeType("DTH");
			transactionTransportBean.setTransactionName("DTH_RECHARGE");
			try {
				franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
						.valueOf(userId));
				preBal = franchiseeCurrBal.getB2bcurrbal();
				rechargeTransactionService
						.doRechargeService(transactionTransportBean);
			} catch (Exception e) {
				outcome = ";error;";
				transactionTransportBean.setErrorMessage("System Error");
				e.printStackTrace();
			}

			if (transactionTransportBean.getErrorCode() > 0
					|| transactionTransportBean.getThirdpartytransid() == null) {
				outcome = ";error;"
						+ transactionTransportBean.getErrorMessage();
			} else {
				franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
						.valueOf(userId));
				outcome = ";" + transactionTransportBean.getTransid() + ";"
						+ transactionTransportBean.getThirdPartyTransDateTime()
						+ ";";
				outcome += ""
						+ ExtractorUtil.getRoundedDouble(franchiseeCurrBal
								.getB2bcurrbal()) + ";" + preBal + ";";
			}
			
		}
		return new ModelAndView(XML_VIEW_NAME, "dthRecharge", outcome);
	}

}
