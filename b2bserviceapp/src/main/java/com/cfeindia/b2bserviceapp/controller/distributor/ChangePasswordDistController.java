package com.cfeindia.b2bserviceapp.controller.distributor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.distributor.model.ChangePasswordDist;
import com.cfeindia.b2bserviceapp.service.changepassword.ChangePasswordService;

@RequestMapping("/distributor/**")
@Controller
public class ChangePasswordDistController {
	@Autowired
	ChangePasswordService changePasswordService;
	
	@RequestMapping(value="/changepassworddist",method=RequestMethod.GET)
	public ModelAndView chnagePasswordDist(){
		
		return new ModelAndView("changepassworddist","changepass",new ChangePasswordDist());
	}
	@RequestMapping(value="/changepasswordprocessdist",method=RequestMethod.POST)
	public String  changePassword(@ModelAttribute("changepass")ChangePasswordDist changePasswordDist,HttpServletRequest request,ModelMap model) {
		String pathToFollow="changepassworddist";
		HttpSession session=request.getSession();
		String userID=(String)session.getAttribute("userid");
		long userId=Long.parseLong(userID);
		String oldPassword=changePasswordDist.getOldPassword();
		String newPassword=changePasswordDist.getNewPassword();
		boolean check=changePasswordService.changePassword(oldPassword, newPassword, userId);
		if(check){
			pathToFollow="redirect:successpagedist";
		}
		else{
			model.addAttribute("error", "Pls!Try again");
			
		}
		return pathToFollow;
	}
	@RequestMapping(value="/successpagedist",method=RequestMethod.GET)
	public String finalPage(ModelMap model){
		model.addAttribute("success", "changed your password");
	return "successpagedist";	
	}
}
