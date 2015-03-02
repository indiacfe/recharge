package com.cfeindia.b2bserviceapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.model.distributor.Registration;
import com.cfeindia.b2bserviceapp.service.distributor.RegistrationService;

@RequestMapping("/admin/**")
@Controller
public class AirtelManagementController {
	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/addnewairteluser", method = RequestMethod.GET)
	public ModelAndView addNewAirtelUser() {
		return new ModelAndView("airtelUser", "addNewAirtelUser",
				new Registration());
	}

	@RequestMapping(value = "/addnewairteluser", method = RequestMethod.POST)
	public ModelAndView addNewAirtelUser(
			@ModelAttribute("addNewAirtelUser") Registration registration,
			Model model) {
		boolean check = registrationService.checkAirtelUser(registration
				.getMobileNo());
		if (check) {
			String result = registrationService.addNewAirtelUser(registration);
			if (CommonConstants.SUCCESS.equalsIgnoreCase(result)) {
				model.addAttribute("info", "Airtel user successfuly added");
			} else {
				model.addAttribute("info", "Please try again");
			}

		} else {
			model.addAttribute("info", "User already exist");
		}

		return new ModelAndView("airtelUser", "addNewAirtelUser", registration);
	}

	@RequestMapping(value = "/updateairteluser", method = RequestMethod.GET)
	public ModelAndView showAirtelUser(
			@ModelAttribute("addNewAirtelUser") Registration registration,
			Model model) {
		if (registration.getUserName().trim() != ""
				|| registration.getMobileNo().trim() != "") {

			if (registration.getUserName().trim() != "") {
				registration = registrationService.getAirTelUser(registration
						.getUserName());
				if (registration == null) {
					model.addAttribute("info", "User does not exist");
					return new ModelAndView("airtelUser", "addNewAirtelUser",
							registration);
				} else {
					model.addAttribute("updatebutton", "updatebutton");
					model.addAttribute("detail", registration);
					return new ModelAndView("airtelUser", "addNewAirtelUser",
							registration);
				}

			} else {
				registration = registrationService.getAirTelUser(registration
						.getMobileNo());
				if (registration == null) {
					model.addAttribute("info", "User does not exist");
					return new ModelAndView("airtelUser", "addNewAirtelUser",
							registration);
				} else {
					model.addAttribute("updatebutton", "updatebutton");
					model.addAttribute("detail", registration);
					return new ModelAndView("airtelUser", "addNewAirtelUser",
							registration);
				}
			}

		} else {
			model.addAttribute("info",
					"Please enter user name or mobile number");
			return new ModelAndView("airtelUser", "addNewAirtelUser",
					registration);

		}

	}

	@RequestMapping(value = "/updateairteluser", method = RequestMethod.POST)
	public ModelAndView updateAirtelUser(
			@ModelAttribute("addNewAirtelUser") Registration registration,
			Model model) {
		String result = registrationService.updateAirTelUser(registration);
		if (CommonConstants.SUCCESS.equalsIgnoreCase(result)) {
			model.addAttribute("info",
					"You have updated airteluser successfully");
		} else {
			model.addAttribute("info", "Please try again");
		}
		return new ModelAndView("airtelUser", "addNewAirtelUser", registration);
	}

}
