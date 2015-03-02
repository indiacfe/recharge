package com.cfeindia.b2bserviceapp.controller.franchisee;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/franchisee/**")
@Controller
public class MobileAppDownloadController {

	@RequestMapping(value = "/downloadmobileapp", method = RequestMethod.GET)
	public void handleFileDownload(HttpServletResponse res) {
		try {
			String fn = "/cybertel.apk";
			URL url = getClass().getResource(fn);
			File f = new File(url.toURI());
			if (f.exists()) {
				res.setContentType("application/vnd.android.package-archive");
				res.setContentLength(new Long(f.length()).intValue());
				res.setHeader("Content-Disposition",
						"attachment; filename=cybertel.apk");
				FileCopyUtils.copy(new FileInputStream(f),
						res.getOutputStream());
			} else {
			}
		} catch (Exception e) {
		}
	}
}
