package com.cfeindia.b2bserviceapp.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfeindia.b2bserviceapp.entity.TokenDetails;
import com.cfeindia.b2bserviceapp.service.customer.TokenService;

@Controller
@RequestMapping("/api/**")
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/tokengenerator", method = RequestMethod.GET)
	public String getPageToken(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long userId = (Long.parseLong((String) session.getAttribute("userid")));
		List<TokenDetails> tokenDetailsList = tokenService.getTokens(userId);
		if (tokenDetailsList.size() != 0)
			model.addAttribute("tokens", tokenDetailsList);
		else
			model.addAttribute("error", "You have not generate token");
		return "tokengenerator";

	}

	@RequestMapping(value = "/tokengenerator", method = RequestMethod.POST)
	public String generateToken(@RequestParam String ipAddress, ModelMap model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();

		Long userId = (Long.parseLong((String) session.getAttribute("userid")));
		try {
			tokenService.generateToken(ipAddress, userId);
			List<TokenDetails> tokenDetailsList = tokenService
					.getTokens(userId);
			if (tokenDetailsList.size() != 0)
				model.addAttribute("tokens", tokenDetailsList);
			else
				model.addAttribute("error", "You have not generate token");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Pls Try Again");
		}

		return "tokengenerator";
	}

	@RequestMapping(value = "/changetokenstatus", method = RequestMethod.POST)
	public String changeTokenStatus(@RequestParam Long tokenId, ModelMap model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();

		try {
			if (tokenService.changeTokenStatus(tokenId) == 0) {
				model.addAttribute("message", "You have successfully Enabled");
			} else {
				model.addAttribute("message", "You have successfully Disabled");
			}
			Long userId = (Long.parseLong((String) session
					.getAttribute("userid")));
			List<TokenDetails> tokenDetailsList = tokenService
					.getTokens(userId);
			if (tokenDetailsList.size() != 0)
				model.addAttribute("tokens", tokenDetailsList);
			else
				model.addAttribute("error", "You have not generate token");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Pls Try Again");
		}

		return "tokengenerator";
	}

	@RequestMapping(value = "/deletetoken", method = RequestMethod.GET)
	public String deleteToken(@RequestParam Long tokenId, ModelMap model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(tokenId);
		Long userId = (Long.parseLong((String) session.getAttribute("userid")));

		try {
			tokenService.deleteToken(tokenId);
			List<TokenDetails> tokenDetailsList = tokenService
					.getTokens(userId);
			if (tokenDetailsList.size() != 0)
				model.addAttribute("tokens", tokenDetailsList);
			else
				model.addAttribute("error", "You have not generate token");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Pls Try Again");
		}
		return "tokengenerator";
	}
}
