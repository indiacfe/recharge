package com.cfeindia.b2bserviceapp.common.authentication;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.util.ApplicationContextUtils;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

public class CustomUsernamePasswordAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {

	private static final String CATEGORY = "category";
	private static final String DEFAULT_FILTER_PROCESSES_URL = "/login";
	private static final String POST = "POST";

	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "j_password";
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";

	private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

	public CustomUsernamePasswordAuthenticationFilter() {
		super(DEFAULT_FILTER_PROCESSES_URL);
	}

	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(usernameParameter);
	}

	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter(passwordParameter);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();
		
		String category = request.getParameter(CATEGORY);
		
		if(username != null && (username.startsWith("D") || username.startsWith("R") || username.startsWith("E") || username.startsWith("A"))) {
			String userId=ExtractorUtil.extractIdFromString(username);
			CommonService commonService = (CommonService) ApplicationContextUtils.getApplicationContext().getBean("commonService");
			username = commonService.getUsername(userId);
		}

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);

		// Place the last username attempted into HttpSession for views
		HttpSession session = request.getSession(false);

		if (session != null || getAllowSessionCreation()) {
			/*
			 * request.getSession().setAttribute(
			 * SPRING_SECURITY_LAST_USERNAME_KEY,
			 * TextEscapeUtils.escapeEntities(username));
			 */

			if (category != null) {
				request.getSession().setAttribute(CATEGORY,
						category);
			}
		}

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected void setDetails(HttpServletRequest request,
			UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource
				.buildDetails(request));
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		if (request.getMethod().equals(POST)) {
			// If the incoming request is a POST, then we send it up
			// to the AbstractAuthenticationProcessingFilter.
			super.doFilter(request, response, chain);
		} else {
			// If it's a GET, we ignore this request and send it
			// to the next filter in the chain. In this case, that
			// pretty much means the request will hit the /login
			// controller which will process the request to show the
			// login page.
			chain.doFilter(request, response);
		}
	}
}