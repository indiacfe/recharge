package com.cfeindia.b2bserviceapp.controller.login;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.Users;

@Controller
public class LoginController {
	@Autowired
	CommonService commonservice;

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest httpServletRequest) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String category = (String) httpServletRequest.getSession()
				.getAttribute("category");
		Collection<GrantedAuthority> authorities = auth.getAuthorities();
		String username = auth.getName();
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("category", category);
		session.setAttribute("userName", username);
		int userid = commonservice.getUserId(username);
		Users users = (Users) commonservice.getEntityByPrimaryKey(Users.class,
				userid);
		int resetPass = users.getResetPasswordBit();
		session.setAttribute("userid", String.valueOf(userid));
		String pageToForward = null;

		for (GrantedAuthority authority : authorities) {

			if (category != null && category.equals(authority.getAuthority())) {

				if (authority.getAuthority().equalsIgnoreCase(
						CommonConstants.ROLE_EMPLOYEE)) {
					if (resetPass == 1) {
						pageToForward = "franchisee/changepasswordemployee";

					} else {
						pageToForward = "employee/home";
					}
				} else if (authority.getAuthority().equalsIgnoreCase(
						CommonConstants.ROLE_DISTRIBUTOR)) {
					if (resetPass == 1) {
						pageToForward = "distributor/changepassworddist";
					} else {
						pageToForward = "distributor/home";
					}

				} else if (authority.getAuthority().equalsIgnoreCase(
						CommonConstants.ROLE_FRANCHISEE)) {
					if (resetPass == 1) {
						pageToForward = "franchisee/changepasswordfran";
					} else {
						pageToForward = "franchisee/home";
					}

				} else if (authority.getAuthority().equalsIgnoreCase(
						CommonConstants.ROLE_ADMIN)) {
					if (resetPass == 1) {
						pageToForward = "admin/changepasswordadmin";

					} else {
						pageToForward = "admin/home";
					}

				} else if (authority.getAuthority().equalsIgnoreCase(
						CommonConstants.ROLE_CUSTOMER)) {

					if (resetPass == 1) {
						pageToForward = "admin/changepasswordadmin";

					} else {
						pageToForward = "api/home";
					}

				} else {
					pageToForward = "logout";
				}
			}
		}
		if (pageToForward == null) {
			pageToForward = "logout";
		}

		return "redirect:/" + pageToForward;

	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(ModelMap model) {
		model.addAttribute("login", "true");
		model.addAttribute("message", "Welcome Distributors and Retailers");
		model.addAttribute("newses", commonservice.getNoticeInfo());
		return "welcome";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model, HttpServletRequest request) {
		model.addAttribute("error", "true");
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("category");
		if ("ROLE_ADMIN".equalsIgnoreCase(userType)) {
			return "admin";
		} else {
			return "welcome";
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("category");
		if ("ROLE_ADMIN".equalsIgnoreCase(userType)) {
			return "admin";
		} else {
			return "welcome";
		}

	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminLogin(ModelMap model) {

		model.addAttribute("login", "true");

		return "admin";

	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String customerLogin(ModelMap model) {

		model.addAttribute("login", "true");

		return "api";

	}
}