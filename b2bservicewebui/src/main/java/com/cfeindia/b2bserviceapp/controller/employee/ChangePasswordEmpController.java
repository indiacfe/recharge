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

import com.cfeindia.b2bserviceapp.model.employee.ChangePasswordEmp;
import com.cfeindia.b2bserviceapp.service.changepassword.ChangePasswordService;

@RequestMapping("/employee/**")
@Controller
public class ChangePasswordEmpController {
	
	@Autowired
	ChangePasswordService changePasswordService;
	
	@RequestMapping(value="/changepasswordemployee",method=RequestMethod.GET)
	public ModelAndView chnagePasswordDist(){
		
		return new ModelAndView("changepasswordemp","changepass",new ChangePasswordEmp());
	}
	@RequestMapping(value="/changepasswordprocessemp",method=RequestMethod.POST)
	public String  changePassword(@ModelAttribute("changepass")ChangePasswordEmp changePasswordEmp,HttpServletRequest request,ModelMap model) {
		String pathToFollow="changepasswordemp";
		HttpSession session=request.getSession();
		String userID=(String)session.getAttribute("userid");
		long userId=Long.parseLong(userID);
		String oldPassword=changePasswordEmp.getOldPassword();
		String newPassword=changePasswordEmp.getNewPassword();
		boolean check=changePasswordService.changePassword(oldPassword, newPassword, userId);
		if(check){
			
			pathToFollow="redirect:successpageemp";
		}
		else{
			model.addAttribute("error", "Pls!Try again");
			
		}
		return pathToFollow;
	}
	@RequestMapping(value="/successpageemp",method=RequestMethod.GET)
	public String finalPage(ModelMap model){
		model.addAttribute("success", "changed your password");
		return "successpageemp";
	}

}
