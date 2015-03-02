package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.thirdparty.DthNewConnectionDetails;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.FranchiseeInfo;
import com.cfeindia.b2bserviceapp.service.franchisee.FranchiseeInfoService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;

@RequestMapping("/franchisee/**")
@Controller
public class DthNewConnectionController {
	@Autowired
	CommonService commonservice;

	@Autowired
	RechargeTransactionService rechargeTransactionService;

	@Autowired
	private FranchiseeInfoService franchiseeInfoService;
	@Autowired
	private DthNewConnectionDetails dthNewConnectionDetails;

	@RequestMapping(value = "/connectiondetailsfromthirdparty")
	public @ResponseBody
	String getConnectionDetails() throws JsonParseException,
			JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		/*List<DthNewConnectionModel> list = new ArrayList<DthNewConnectionModel>();
		List<DthNewConnetionSubService> dthsubser = new ArrayList<DthNewConnetionSubService>();
		List<DthNewConnectionPackages> dthpacks = new ArrayList<DthNewConnectionPackages>();
		DthNewConnectionModel dncm = new DthNewConnectionModel();
		DthNewConnetionSubService dncss1 = new DthNewConnetionSubService();
		DthNewConnetionSubService dncss2 = new DthNewConnetionSubService();

		DthNewConnectionPackages dncp1 = new DthNewConnectionPackages();
		DthNewConnectionPackages dncp2 = new DthNewConnectionPackages();

		dncm.setServiceType("CONNECTION");
		dncm.setServiceProvider("Dish TV");
		dncm.setServiceDesc("pass Mobile Number in account, Package MRP in amount, Package Key in optional1, PIN Code in 'optional2, URL encoded Full Name in optional3 and URL encoded Full Installation Address in optional4");
		dncm.setProviderKey("DTK");
		dncss1.setSubServiceName("Dish TV Standard Set Top Box (ROI)");
		dncss1.setSubServiceDesc("Installation is FREE with cable free upto 10 meters and > 10 meters @ Rs.12 meter");

		dncss2.setSubServiceName("Dish TV truHD+ Set Top Box with Recorder (ROI)");
		dncss2.setSubServiceDesc("Installation is FREE with cable free upto 10 meters and > 10 meters @ Rs.12 /meter");

		dncp1.setPackageName("Super Family Package | 60 Days Validity");
		dncp1.setPackageKey("DTK1P1");
		dncp1.setPackageMrp("1999");
		dncp1.setPackageDesc("Number of Channels - 170");
		dncp1.setMargin("15.00");

		dncp2.setPackageName("Super Gold Package | 60 Days Validity");
		dncp2.setPackageKey("DTK1P2");
		dncp2.setPackageMrp("1999");
		dncp2.setPackageDesc("Number of Channels - 195");
		dncp2.setMargin("15.00");

		dthpacks.add(dncp1);
		dthpacks.add(dncp2);
		dthsubser.add(dncss1);
		dthsubser.add(dncss2);
		dncss1.setDthpackages(dthpacks);
		dncm.setSubServices(dthsubser);
		list.add(dncm);*/

		
		  String result = mapper.writeValueAsString(dthNewConnectionDetails.getDthDetails());
		
		return mapper.writeValueAsString(result);
	}

	@RequestMapping(value = "/newdthconnections")
	public String getConnection(ModelMap model) {
		model.addAttribute("dthConnetion", new TransactionTransportBean());

		return "newdthconnection";
	}

	@RequestMapping(value = "/newdthconnectionsdetail", method = RequestMethod.POST)
	public String dthConnection(
			@ModelAttribute("dthconnection") TransactionTransportBean transactionTransport,
			@RequestParam String subservice, ModelMap model,
			HttpServletRequest req) {
		HttpSession session = req.getSession();
		String pathToFollow = null;
		Long suuid = (Long) session.getAttribute("suuid");
		if (suuid != null) {
			session.setAttribute("suuid", null);
			session.setAttribute("rechargeType",
					transactionTransport.getRechargeType());
			session.setAttribute("connectionid",
					transactionTransport.getConnectionid());
			session.setAttribute("amount", CyberTelUtil
					.removeDecimalFromNumber(transactionTransport.getAmount()));
			session.setAttribute("operator", transactionTransport.getOperator());
			// session.setAttribute("canumber", canumber);

			transactionTransport.setRetailerId((String) req.getSession()
					.getAttribute("userid"));
			transactionTransport.setTransactionName("CONNECTION");
			transactionTransport.setFranchiseeMobileNum((String) session
					.getAttribute("userName"));
			transactionTransport.setSubservice(subservice);
			// transactionTransport.setConnectionid(connectionid);

			try {
				rechargeTransactionService
						.doRechargeService(transactionTransport);
			} catch (Exception e) {
				e.printStackTrace();
				transactionTransport.setMessage("System Error");
				// transactionTransport.setErrorCode(3);
			}

			if (transactionTransport.getErrorCode() > 0
					|| transactionTransport.getThirdpartytransid() == null) {
				String errorMsg = "Error in Transaction</br>";
				if (transactionTransport.getMessage() != null) {
					errorMsg += transactionTransport.getMessage();
				}
				model.addAttribute("Error", errorMsg);
				model.addAttribute("dthConnetion", transactionTransport);
				pathToFollow = "newdthconnection";
			} else {
				session.setAttribute("transId",
						transactionTransport.getTransid());
				// SimpleDateFormat sdf = new
				// SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				session.setAttribute("datetime",
						transactionTransport.getThirdPartyTransDateTime());
				// model.addAttribute("datetime",
				// sdf.format(transactionTransport.getCreatedAt()));

				String userId = (String) session.getAttribute("userid");
				FranchiseeInfo franchiseeInfo = franchiseeInfoService
						.getFranchiseeInfo(userId);
				session.setAttribute("franchiseeInfo", franchiseeInfo);

				pathToFollow = "redirect:newdthcon";
			}
		} else {
			pathToFollow = "redirect:newdthconnections";

		}

		return pathToFollow;
	}

	@RequestMapping(value = "/newdthcon", method = RequestMethod.GET)
	public String finalPage(ModelMap model) {
		model.addAttribute("Message", "Successful");
		return "dthconnectionsuccess";
	}

	public void setdoRechargeTransactionService(
		RechargeTransactionService rechargeTransactionService) {
		this.rechargeTransactionService = rechargeTransactionService;
	}

}
