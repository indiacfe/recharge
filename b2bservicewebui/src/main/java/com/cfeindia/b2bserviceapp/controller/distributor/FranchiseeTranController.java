package com.cfeindia.b2bserviceapp.controller.distributor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.dto.FranchiseeDetailAsDist;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.DistributorInfo;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.NewIdCreationBalanceLog;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorInfoService;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorSearchFranchiseeService;
import com.cfeindia.b2bserviceapp.service.distributor.FundTransferService;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendRequest;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendResponse;
import com.cfeindia.b2bserviceapp.sms.thirdparty.SMSSendService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/distributor/**")
@Controller
public class FranchiseeTranController {
	@Autowired
	FundTransferService fundTransferService;
	@Autowired
	CommonService commonService;
	@Autowired
	private DistributorInfoService distributorInfoService;
	@Autowired
	DistributorSearchFranchiseeService distributorSearchFranchiseeService;

	@Autowired
	SMSSendService smsSendService;

	@RequestMapping(value = "/franchiseetransfer")
	public String franchiseeTransfer() {
		return "franchiseetransfer";
	}

	@RequestMapping(value = "/searchfranchisee", method = RequestMethod.POST)
	public String searchFranchisee(@RequestParam String franchIdOrmobileNo,
			ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (franchIdOrmobileNo != null
				&& (franchIdOrmobileNo.startsWith("D")
						|| franchIdOrmobileNo.startsWith("E")
						|| franchIdOrmobileNo.length() != 10 || franchIdOrmobileNo
							.startsWith("A"))) {
			model.addAttribute("error", "Not Exist " + franchIdOrmobileNo);
			return "franchiseetransfer";
		}

		Users users = distributorSearchFranchiseeService
				.searchUserBasedOnIdOrMobNum(franchIdOrmobileNo);
		if (users != null) {
			model.addAttribute("mobileNumber", users.getUsername());
			if (users.getUserDetail() != null) {
				model.addAttribute("firmName", users.getUserDetail()
						.getFirmName());
				session.setAttribute("firmName", users.getUserDetail()
						.getFirmName());
			} else
				model.addAttribute("firmName", "");
			session.setAttribute("firmName", "");
			model.addAttribute("distId", users.getUserId());
		} else {
			model.addAttribute("error", "Not Exist " + franchIdOrmobileNo);
			return "franchiseetransfer";
		}
		if (franchIdOrmobileNo.startsWith("R")) {
			model.addAttribute("franchiseeId", franchIdOrmobileNo);
			String id = ExtractorUtil.extractIdFromString(franchIdOrmobileNo);
			FranchiseeCurrBal franchiseeCurrBal = fundTransferService
					.getCurrentDetail(Long.valueOf(id));
			model.addAttribute("retailerBal", franchiseeCurrBal.getB2bcurrbal());
			model.addAttribute("adunitbal",
					franchiseeCurrBal.getB2bcurrbaladunit());
		} else {
			Long id = users.getUserId();
			String generatedId = ExtractorUtil.generateIdFromString(
					id.toString(), "R");
			model.addAttribute("franchiseeId", generatedId);
			FranchiseeCurrBal franchiseeCurrBal = fundTransferService
					.getCurrentDetail(Long.valueOf(id));
			model.addAttribute("retailerBal", franchiseeCurrBal.getB2bcurrbal());
			model.addAttribute("adunitbal",
					franchiseeCurrBal.getB2bcurrbaladunit());
		}
		return "franchiseeResultHome";

	}

	@RequestMapping(value = "/searchallfranchisee", method = RequestMethod.GET)
	public String searchAllFranchise(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String distId = (String) session.getAttribute("userid");
		List<FranchiseeDetailAsDist> allFrancisee = distributorSearchFranchiseeService
				.searchAllFranchisee(distId);
		model.addAttribute("franlistdetail", allFrancisee);
		return "franchiseetransfer";
	}

	@RequestMapping(value = "/transfertofranchisee", method = RequestMethod.POST)
	public String transferToFranchisee(@RequestParam String franchId,
			@RequestParam String mobileNo, @RequestParam String amount,
			@RequestParam String firmName, ModelMap model,
			HttpServletRequest request) {
		String id = franchId, result = "";

		model.addAttribute("firmName", firmName);
		FranchiseeCurrBal franchiseeCurrBal;

		id = ExtractorUtil.extractIdFromString(franchId);
		HttpSession session = request.getSession();
		if (Long.valueOf(amount) >= 0) {
			String senderId = (String) session.getAttribute("userid");
			boolean checkedDuplicate = fundTransferService.checkDuplicate(Long
					.valueOf(id));
			if (checkedDuplicate) {
				franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
						.valueOf(id));
				model.addAttribute("retailerBal",
						franchiseeCurrBal.getB2bcurrbal());
				model.addAttribute("mobileNumber", mobileNo);
				model.addAttribute("error","Duplicate number " +mobileNo+" "+ "Please try after 2 minutes");
				return "franchiseeResultHome";

			} else {
				if (franchId.startsWith("R")) {

					result = fundTransferService.fundToRetailerCurrService(
							Long.valueOf(id), Long.valueOf(senderId),
							Double.parseDouble(amount));
					franchiseeCurrBal = fundTransferService
							.getCurrentDetail(Long.valueOf(id));
					Double preRetailerBal = franchiseeCurrBal.getB2bcurrbal()
							- Long.valueOf(amount);
					model.addAttribute("preRetailerBal", preRetailerBal);
					Double newRetailerBal = franchiseeCurrBal.getB2bcurrbal();
					model.addAttribute("newRetailerBal", newRetailerBal);

				} else {
					franchiseeCurrBal = fundTransferService
							.getCurrentDetail(Long.valueOf(id));
					Double preRetailerBal = franchiseeCurrBal.getB2bcurrbal()
							- Long.valueOf(amount);
					model.addAttribute("preRetailerBal", preRetailerBal);
					Double newRetailerBal = franchiseeCurrBal.getB2bcurrbal();
					model.addAttribute("newRetailerBal", newRetailerBal);
					result = fundTransferService.fundToRetailerCurrService(
							Long.valueOf(franchId), Long.valueOf(senderId),
							Double.parseDouble(amount));
				}
			}
			model.addAttribute("franchId", franchId);
			model.addAttribute("mobileNo", mobileNo);
			model.addAttribute("amount", amount);
			if (result.equals(CommonConstants.SUCCESS)) {
				DistributorInfo distributorInfo = distributorInfoService
						.distributorInfo(senderId);
				session.setAttribute("distributorInfo", distributorInfo);
				/*	final String mobNumber = mobileNo;
				final String tempMessage = String
						.format("CyberTel You have received a credit for Rs. %s in your account.",
								amount);

				new Thread() {
					public void run() {
						try {
							sendSMS(mobNumber, tempMessage);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();*/

				return "redirect:franchiseetransfersuccess";
			} else {
				if (franchId.startsWith("R")) {
					model.addAttribute("franchiseeId", id);
				} else {
					model.addAttribute("franchiseeId", franchId);
				}
				franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
						.valueOf(id));
				model.addAttribute("retailerBal",
						franchiseeCurrBal.getB2bcurrbal());
				model.addAttribute("mobileNumber", mobileNo);
				model.addAttribute("error",
						"You have insufficient balance to transfer.");
				return "franchiseeResultHome";
			}

		} else {
			if (franchId.startsWith("R")) {
				model.addAttribute("franchiseeId", id);
			} else {
				model.addAttribute("franchiseeId", franchId);
			}
			model.addAttribute("mobileNumber", mobileNo);
			model.addAttribute("error", "please enter amount greater than zero");
			return "franchiseeResultHome";
		}
	}

	@RequestMapping(value = "/transfertofranchiseeadunit", method = RequestMethod.POST)
	public String transferToFranchiseeAddUnit(@RequestParam String franchId,
			@RequestParam String mobileNo, @RequestParam String amount,
			@RequestParam String firmName, ModelMap model,
			HttpServletRequest request) {
		String id = franchId, result = "";
		model.addAttribute("firmName", firmName);
		FranchiseeCurrBal franchiseeCurrBal;
		id = ExtractorUtil.extractIdFromString(franchId);
		HttpSession session = request.getSession();
		if (Long.valueOf(amount) >= 0) {
			String senderId = (String) session.getAttribute("userid");
			boolean checkedDuplicate = fundTransferService.checkDuplicate(Long
					.valueOf(id));
			if (checkedDuplicate) {
				franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
						.valueOf(id));
				model.addAttribute("retailerBal",
						franchiseeCurrBal.getB2bcurrbal());
				model.addAttribute("mobileNumber", mobileNo);
				model.addAttribute("error","Duplicate number " +mobileNo+" "+ "Please try after 2 minutes");
				return "franchiseeResultHome";

			} else {
			if (franchId.startsWith("R")) {
				result = fundTransferService.fundToRetailerAdUnitService(
						Long.valueOf(id), Long.valueOf(senderId),
						Double.parseDouble(amount));
				franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
						.valueOf(id));
				Double preRetailerBal = franchiseeCurrBal.getB2bcurrbaladunit()
						- Long.valueOf(amount);
				model.addAttribute("preRetailerBal", preRetailerBal);
				Double newRetailerBal = franchiseeCurrBal.getB2bcurrbal();
				model.addAttribute("newRetailerBal", newRetailerBal);
			} else {
				franchiseeCurrBal = fundTransferService.getCurrentDetail(Long
						.valueOf(id));
				Double preRetailerBal = franchiseeCurrBal.getB2bcurrbaladunit()
						- Long.valueOf(amount);
				model.addAttribute("preRetailerBal", preRetailerBal);
				Double newRetailerBal = franchiseeCurrBal.getB2bcurrbal();
				model.addAttribute("newRetailerBal", newRetailerBal);
				result = fundTransferService.fundToRetailerAdUnitService(
						Long.valueOf(franchId), Long.valueOf(senderId),
						Double.parseDouble(amount));
			}
			}
			model.addAttribute("franchId", franchId);
			model.addAttribute("mobileNo", mobileNo);
			model.addAttribute("amount", amount);
			if (result.equals(CommonConstants.SUCCESS)) {
				DistributorInfo distributorInfo = distributorInfoService
						.distributorInfo(senderId);
				session.setAttribute("distributorInfo", distributorInfo);
				/*final String mobNumber = mobileNo;
				final String tempMessage = String
						.format("CyberTel You have received a credit for Rs. %s in your account.",
								amount);

				new Thread() {
					public void run() {
						try {
							sendSMS(mobNumber, tempMessage);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();*/

				return "redirect:franchiseetransfersuccess";
			} else {
				if (franchId.startsWith("R")) {
					model.addAttribute("franchiseeId", id);
				} else {
					model.addAttribute("franchiseeId", franchId);
				}
				model.addAttribute("retailerBal",
						franchiseeCurrBal.getB2bcurrbal());
				model.addAttribute("adunitbal",
						franchiseeCurrBal.getB2bcurrbaladunit());
				model.addAttribute("mobileNumber", mobileNo);
				model.addAttribute("error",
						"You have insufficient balance to transfer.");
				return "franchiseeResultHome";
			}

		} else {
			if (franchId.startsWith("R")) {
				model.addAttribute("franchiseeId", id);
			} else {
				model.addAttribute("franchiseeId", franchId);
			}
			model.addAttribute("mobileNumber", mobileNo);
			model.addAttribute("error", "please enter amount greater than zero");
			return "franchiseeResultHome";
		}
	}

	private void sendSMS(String mobileNumber, String message) {
		ThirdPartyDetailObject thirdPartyDetailObject = (ThirdPartyDetailObject) commonService
				.getEntityByPrimaryKey(ThirdPartyDetailObject.class, new Long(
						5l));
		SMSSendRequest smsSendRequest = new SMSSendRequest();
		smsSendRequest.setApiUrl(thirdPartyDetailObject.getConnectURL());
		smsSendRequest.setUserName(thirdPartyDetailObject.getToken());
		smsSendRequest.setPassword(thirdPartyDetailObject.getToken1());
		smsSendRequest.setSenderId(thirdPartyDetailObject.getToken2());
		smsSendRequest.setDestination("91" + mobileNumber);
		smsSendRequest.setMessage(message);
		SMSSendResponse smsSendResponse = smsSendService
				.sendSMS(smsSendRequest);
		commonService.saveEntity(smsSendResponse);
	}

	@RequestMapping(value = "/franchiseetransfersuccess", method = RequestMethod.GET)
	public String finalPage(HttpServletRequest request, ModelMap map,
			@RequestParam String franchId, @RequestParam String mobileNo,
			@RequestParam String amount, @RequestParam String firmName,
			@RequestParam String preRetailerBal,
			@RequestParam String newRetailerBal) {
		HttpSession session = request.getSession();

		map.addAttribute("firmName", firmName);
		map.addAttribute("franchId", franchId);
		map.addAttribute("mobileNo", mobileNo);
		map.addAttribute("amount", amount);
		map.addAttribute("preRetailerBal", preRetailerBal);
		map.addAttribute("newRetailerBal", newRetailerBal);
		return "franchiseeTransferSuccess";
	}

}
