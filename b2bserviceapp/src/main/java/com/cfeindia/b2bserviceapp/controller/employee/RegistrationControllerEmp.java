package com.cfeindia.b2bserviceapp.controller.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.admin.service.CompanyAccount;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.distributor.model.Registration;
import com.cfeindia.b2bserviceapp.service.distributor.RegistrationService;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@RequestMapping("/employee/**")
@Controller
public class RegistrationControllerEmp {
	@Autowired
	RegistrationService registrationService;
	@Autowired
	private CompanyAccount company;

	public RegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@RequestMapping(value = "/registrationemp")
	public ModelAndView userRegistration() {
		return new ModelAndView("registrationemp", "registration",
				new Registration());
	}

	@RequestMapping(value = "/registrationproccessemp", method = RequestMethod.POST)
	public String userRegistred(
			@ModelAttribute("registration") Registration registration,
			HttpServletRequest request, ModelMap map) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");
		Long userId = Long.parseLong(id);
		String category = (String) session.getAttribute("category");
		Double comAmount = (Double) session.getAttribute("amount");
		String pathToFollow = "registrationemp";
		map.addAttribute(pathToFollow, registration);
		boolean balanceAvailable = false;
		String typeOfUser="";
		if (("ROLE_DISTRIBUTOR").equalsIgnoreCase(registration
				.getRegistrationType())
				&& comAmount >= CommonConstants.DISTRIBUTOR_CREATION_CHARGE) {
			balanceAvailable = true;
			typeOfUser="D";
			session.setAttribute("newusertype", "distributor");
		} else if (("ROLE_FRANCHISEE").equalsIgnoreCase(registration
				.getRegistrationType())
				&& comAmount >= CommonConstants.RETAILER_CREATION_CHARGE) {
			balanceAvailable = true;
			typeOfUser="R";
			session.setAttribute("newusertype", "retailer");
		} else {
			map.addAttribute("error",
					"No Sufficient Balance for User Creation.");
			map.addAttribute(pathToFollow, registration);
		}
		if (balanceAvailable) {

			try {
				Long newloginId=registrationService.registerationProcessByCompany(registration,
						userId, category);
				String generatedId=ExtractorUtil.generateIdFromString(newloginId.toString(),typeOfUser);
				System.out.println(newloginId+"+"+generatedId);
				session.setAttribute("newmobilenum", registration.getMobileNo());
				session.setAttribute("newregistratonid", generatedId);
				String[] companyInfo = company.companyInfo();
				session.setAttribute("amount", Double.parseDouble(companyInfo[0]));
				session.setAttribute("distributors",
						Integer.parseInt(companyInfo[1]));
				session.setAttribute("franchisees",
						Integer.parseInt(companyInfo[2]));
				pathToFollow = "redirect:sucesspageregemp";
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("error",
						"Pls try another mobile no!This mobile no already registered");
				map.addAttribute(pathToFollow, registration);
			}
		}

		return pathToFollow;
	}
	@RequestMapping(value = "/sucesspageregemp", method = RequestMethod.GET)
	public String finalpage() {
		
		return "sucesspageregemp";

	}

}