package com.cfeindia.b2bserviceapp.controller;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dto.WaterBillDto;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Controller
public class WaterBillController {
	
	@Autowired
	RechargeTransactionService rechargeTransactionService;
	@Autowired
	private FranchiseeInfoService franchiseeInfoService;
	@Autowired
	FundTransferService fundTransferService;

	@Autowired
	CommonService commonService;
	
	private String XML_VIEW_NAME = "waterBillPay";

	@RequestMapping(value = "/doWaterBillPay", method = RequestMethod.POST)
	public ModelAndView doDataCardRecharge(@RequestBody String body) {
		
		ObjectMapper mapper = new ObjectMapper();
		WaterBillDto dto = null;
		TransactionTransportBean result = new TransactionTransportBean();
		result.setTransactionName("WATER");
		FranchiseeCurrBal franchiseeCurrBal = null;
		double preBal = 0.0;
		String outcome = "";
		String userId = null;
		try {
			dto = mapper.readValue(body, WaterBillDto.class);
			userId = dto.getUserId();
			result.setAmount(Double.valueOf(dto.getAmount()));
			result.setOperator(dto.getOperator());
			result.setRetailerId(userId);
			result.setConnectionid(dto.getConnectionNumber());
			result.setRechargeType("WATER");
			Users usr = (Users) commonService.getEntityByPrimaryKey(
					Users.class, Long.parseLong(userId));
			result.setFranchiseeMobileNum(usr.getUsername());
			franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
					.valueOf(userId));
			preBal = franchiseeCurrBal.getB2bcurrbal();

			rechargeTransactionService.doRechargeService(result);
		} catch (Exception e) {
			outcome = ";error;";
			result.setErrorMessage("System Error");
			e.printStackTrace();
		}

		if (result.getErrorCode() > 0 ) {
			outcome = ";error;" + result.getErrorMessage();
		} else {
			franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
					.valueOf(userId));
			outcome = ";" + result.getTransid() + ";"
					+ result.getThirdPartyTransDateTime() + ";";
			outcome += ""
					+ ExtractorUtil.getRoundedDouble(franchiseeCurrBal
							.getB2bcurrbal()) + ";" + preBal + ";";
		}
		return new ModelAndView(XML_VIEW_NAME, "waterBillPay", outcome);

	}

}
