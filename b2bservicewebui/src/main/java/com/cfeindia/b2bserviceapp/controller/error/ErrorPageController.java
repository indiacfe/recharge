package com.cfeindia.b2bserviceapp.controller.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController {

	@RequestMapping(value = "/errorpage")
	public String errorpage(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("category");
		String errorType = request.getParameter("error");
		String errorMessage = "";
		if("500".equalsIgnoreCase(errorType)) {
			errorMessage += " Or Try with another Input.";
		} else if ("400".equalsIgnoreCase(errorType)) {
			errorMessage += " As Page you are looking for not found.";
		}
		model.addAttribute("ErrorMessage", errorMessage);
		if ("ROLE_EMPLOYEE".equalsIgnoreCase(userType)) {
			return "employeeError";
		} else if ("ROLE_DISTRIBUTOR".equalsIgnoreCase(userType)) {

			return "distributorError";
		} else if ("ROLE_FRANCHISEE".equalsIgnoreCase(userType)) {

			return "franchiseeError";
		}else if ("ROLE_ADMIN".equalsIgnoreCase(userType)) {

			return "AdminError";
		}else {

			return "error";
		}
	}
}
