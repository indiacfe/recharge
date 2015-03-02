package com.cfeindia.b2bserviceapp.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.admin.service.CompanyAccount;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.distributor.model.Registration;
import com.cfeindia.b2bserviceapp.recharge.mobile.dto.DistributorInfo;
import com.cfeindia.b2bserviceapp.service.distributor.RegistrationService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/admin/**")
@Controller
public class RegistrationAdminController {

	@Autowired
	RegistrationService registrationService;
	@Autowired
	private CompanyAccount company;

	@RequestMapping(value = "/registrationadmin")
	public ModelAndView userRegistration() {
		return new ModelAndView("registrationadmin", "registration",
				new Registration());
	}

	@RequestMapping(value = "/registrationproccessadmin", method = RequestMethod.POST)
	public String userRegistred(
			@ModelAttribute("registration") Registration registration,
			HttpServletRequest request, ModelMap map) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");
		Long userId = Long.parseLong(id);
		String category = (String) session.getAttribute("category");
		Double comAmount = (Double) session.getAttribute("amount");
		String pathToFollow = "registrationadmin";
		map.addAttribute(pathToFollow, registration);
		String typeOfUser = "";
		boolean balanceAvailable = false;
		if (("ROLE_DISTRIBUTOR").equalsIgnoreCase(registration
				.getRegistrationType())
				&& comAmount >= CommonConstants.DISTRIBUTOR_CREATION_CHARGE) {
			balanceAvailable = true;
			typeOfUser = "D";
			session.setAttribute("newusertype", "distributor");
		} else if (("ROLE_FRANCHISEE").equalsIgnoreCase(registration
				.getRegistrationType())
				&& comAmount >= CommonConstants.RETAILER_CREATION_CHARGE) {
			balanceAvailable = true;
			typeOfUser = "R";
			session.setAttribute("newusertype", "retailer");
		} else {
			map.addAttribute("error",
					"No Sufficient Balance for User Creation.");
			map.addAttribute(pathToFollow, registration);
		}
		if (balanceAvailable) {

			try {
				Long newUserId = registrationService
						.registerationProcessByCompany(registration, userId,
								category);
				String generatedId = ExtractorUtil.generateIdFromString(
						newUserId.toString(), typeOfUser);
				session.setAttribute("newregistratonid", generatedId);
				session.setAttribute("newmobilenum", registration.getMobileNo());
				pathToFollow = "redirect:sucesspageadmin";
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("error",
						"User Registration failed. Please contact Administrator.");
				map.addAttribute(pathToFollow, registration);
			}
		}

		return pathToFollow;
	}

	@RequestMapping(value = "/sucesspageadmin", method = RequestMethod.GET)
	public String finalpage(HttpServletRequest request, ModelMap map) {
		HttpSession session = request.getSession();
		String[] companyInfo = company.companyInfo();
		session.setAttribute("amount", Double.parseDouble(companyInfo[0]));
		session.setAttribute("distributors", Integer.parseInt(companyInfo[1]));
		session.setAttribute("franchisees", Integer.parseInt(companyInfo[2]));
		return "sucesspageadmin";

	}

}