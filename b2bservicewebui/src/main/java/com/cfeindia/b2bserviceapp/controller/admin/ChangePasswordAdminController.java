package com.cfeindia.b2bserviceapp.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.model.admin.ChangePasswordAdmin;
import com.cfeindia.b2bserviceapp.service.changepassword.ChangePasswordService;

@RequestMapping("/admin/**")
@Controller
public class ChangePasswordAdminController {
	
	@Autowired
	ChangePasswordService changePasswordService;
	
	@RequestMapping(value="/changepasswordadmin",method=RequestMethod.GET)
	public ModelAndView chnagePasswordDist(){
		
		return new ModelAndView("changepasswordadmin","changepass",new ChangePasswordAdmin());
	}
	@RequestMapping(value="/changepasswordprocessadmin",method=RequestMethod.POST)
	public String  changePassword(@ModelAttribute("changepass")ChangePasswordAdmin changePasswordAdmin,HttpServletRequest request,ModelMap model) {
		String pathToFollow="changepasswordadmin";
		HttpSession session=request.getSession();
		String userID=(String)session.getAttribute("userid");
		long userId=Long.parseLong(userID);
		String oldPassword=changePasswordAdmin.getOldPassword();
		String newPassword=changePasswordAdmin.getNewPassword();
		boolean check=changePasswordService.changePassword(oldPassword, newPassword, userId);
		if(check){
			model.addAttribute("success", "changed your password");
			pathToFollow="redirect:successpageadmin";
		}
		else{
			model.addAttribute("error", "Pls!Try again");
			
		}
		return pathToFollow;
	}
	@RequestMapping(value="/successpageadmin",method=RequestMethod.GET)
	public String finalPage(ModelMap model){
		model.addAttribute("success", "changed your password");
	return "successpageadmin";	
	}

}
