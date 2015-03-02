package com.cfeindia.b2bserviceapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dto.PostpaiBillDto;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Controller
public class PostpaidBillFranController {

	@Autowired
	RechargeTransactionService rechargeTransactionService;

	@Autowired
	private FranchiseeInfoService franchiseeInfoService;
	@Autowired
	FundTransferService fundTransferService;

	@Autowired
	CommonService commonService;
	private String XML_VIEW_NAME="postpaidrecharge";
	@RequestMapping(value = "/doPostpaidBillPay", method = RequestMethod.POST)
	public ModelAndView rechargeDetailPostpaidDetail(@RequestBody String body,
			HttpServletRequest req) {
		PostpaiBillDto dto=null;
		TransactionTransportBean result = new TransactionTransportBean();
		ObjectMapper mapper = new ObjectMapper();
		result.setTransactionName("POSTPAID_BILL");
		FranchiseeCurrBal franchiseeCurrBal = null;
		double preBal = 0.0;
		String outcome = "";
		String userId = null;
		try {
			dto = mapper.readValue(body,
					PostpaiBillDto.class);
			userId = dto.getUserId();
			result.setAmount(Double.valueOf(dto.getAmount()));
			result.setOperator(dto.getOperator());
			if(userId != null && !("").equals(userId))
			{
				result.setRetailerId(userId);
			} else {
				throw new Exception("No User Id Found");
			}
			result.setRechargeType("MOBILE_POSTPAID");
			result.setMobileNo(dto.getConnectioNumber());
			result.setConnectionid(dto.getConnectioNumber());
			result.setFranchiseeMobileNum(commonService.getUsername(userId));
			franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
					.valueOf(userId));
			preBal = franchiseeCurrBal.getB2bcurrbal();
			rechargeTransactionService.doRechargeService(result);
		} catch (Exception e) {
			outcome = ";error;";
			result.setErrorMessage("System Error");
			e.printStackTrace();
		}

		if (result.getErrorCode() > 0
				|| result.getThirdpartytransid() == null) {
			outcome = ";error;"
					+ result.getErrorMessage();
		} else {
			franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
					.valueOf(userId));
			outcome = ";" + result.getTransid() + ";"
					+ result.getThirdPartyTransDateTime()
					+ ";";
			outcome += ""
					+ ExtractorUtil.getRoundedDouble(franchiseeCurrBal
							.getB2bcurrbal()) + ";" + preBal + ";";
		}

		return new ModelAndView(XML_VIEW_NAME,"postPaidRecharge",outcome);
	}
}
