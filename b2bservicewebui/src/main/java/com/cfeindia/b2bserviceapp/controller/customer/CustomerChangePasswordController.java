package com.cfeindia.b2bserviceapp.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cfeindia.b2bserviceapp.model.admin.ChangePasswordAdmin;
import com.cfeindia.b2bserviceapp.service.changepassword.ChangePasswordService;

@Controller
@RequestMapping("/api/**")
public class CustomerChangePasswordController {
	@Autowired
	private ChangePasswordService changePasswordService;

	@RequestMapping(value = "/changepasswordcustomer", method = RequestMethod.GET)
	public String chnagePasswordDist(ModelMap model) {
		model.addAttribute("changepass", new ChangePasswordAdmin());
		return "changepasswordcustomer";
	}

	@RequestMapping(value = "/changepasswordcustomer", method = RequestMethod.POST)
	public String changePassword(
			@ModelAttribute("changepass") ChangePasswordAdmin ChangePasswordAdmin,
			HttpServletRequest request, ModelMap model) {
		String pathToFollow = "changepasswordcustomer";
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userid");
		long userId = Long.parseLong(userID);
		boolean check = changePasswordService.changePassword(
				ChangePasswordAdmin.getOldPassword(),
				ChangePasswordAdmin.getNewPassword(), userId);
		if (check) {
			model.addAttribute("success", "changed your password");
			pathToFollow = "changepasswordcustomer";
		} else {
			model.addAttribute("error", "Pls!Try again");
		}
		return pathToFollow;
	}
}
