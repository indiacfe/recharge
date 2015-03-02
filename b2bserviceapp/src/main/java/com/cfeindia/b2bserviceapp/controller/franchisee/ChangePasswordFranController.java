package com.cfeindia.b2bserviceapp.controller.franchisee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.franchisee.model.ChangePasswordFran;
import com.cfeindia.b2bserviceapp.service.changepassword.ChangePasswordService;

@RequestMapping("/franchisee/**")
@Controller
public class ChangePasswordFranController {
	
	@Autowired
	ChangePasswordService changePasswordService;
	
	@RequestMapping(value="/changepasswordfran")
	public ModelAndView changePasswordFran(){
		
		return new ModelAndView("changepasswordfran","changepass",new ChangePasswordFran());
	}
	
	@RequestMapping(value="/changepasswordprocessfran",method=RequestMethod.POST)
	public String  changePassword(@ModelAttribute("changepass")ChangePasswordFran changePasswordFran,HttpServletRequest request,ModelMap model) {
		String pathToFollow="changepasswordfran";
		HttpSession session=request.getSession();
		String userID=(String)session.getAttribute("userid");
		long userId=Long.parseLong(userID);
		String oldPassword=changePasswordFran.getOldPassword();
		String newPassword=changePasswordFran.getNewPassword();
		boolean check=changePasswordService.changePassword(oldPassword, newPassword, userId);
		if(check){
			pathToFollow="redirect:successpagefran";
		}
		else{
			model.addAttribute("error", "Pls!Try again");
			}
		return pathToFollow;
	}
	@RequestMapping(value="/successpagefran",method=RequestMethod.GET)
	public String finalPage(ModelMap model){
		model.addAttribute("success", "changed your password");
	return "successpagefran";	
	}

}
