package com.cfeindia.b2bserviceapp.controller.franchisee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfeindia.b2bserviceapp.model.franchisee.ActivityHistoryFran;

@RequestMapping("/franchisee/**")
@Controller
public class ActivityHistoryController {

	@RequestMapping(value="/activityhistory")
	public ModelAndView activityHistory(){
		
		return new ModelAndView("activityhistory","command",new ActivityHistoryFran());
	}
}
