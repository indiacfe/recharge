package com.cfeindia.b2bserviceapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.entity.NoticeInfo;
import com.cfeindia.b2bserviceapp.service.admin.NoticeService;

@RequestMapping("/admin/**")
@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value = "/newnotice", method = RequestMethod.GET)
	public String newNotice(ModelMap model) {
		model.addAttribute("noticebean", new NoticeInfo());
		return "notice";

	}

	@RequestMapping(value = "/newnotice", method = RequestMethod.POST)
	public String createNotice(@ModelAttribute NoticeInfo noticeInfo,
			ModelMap model) {
		
		
		String result = noticeService.createNews(noticeInfo);
		if (result.equalsIgnoreCase("success")) {
			model.addAttribute("message", "Notice Created Successfully");
		} else {
			model.addAttribute("message", "Notice Not Creation Fail");
		}
		model.addAttribute("noticebean", new NoticeInfo());
		return "notice";

	}

	@RequestMapping(value = "/noticelist", method = RequestMethod.GET)
	public String noticeList(ModelMap model) {
		model.addAttribute("noticelist", noticeService.getAllNotice());

		return "activeinactivenotice";

	}

	@RequestMapping(value = "/activeNotice", method = RequestMethod.GET)
	public String noticeAcitveDeactive(@RequestParam Integer noticeId,
			ModelMap model) {

		noticeService.activeDeactive(noticeId);

		model.addAttribute("noticelist", noticeService.getAllNotice());

		return "redirect:noticelist";

	}

}
