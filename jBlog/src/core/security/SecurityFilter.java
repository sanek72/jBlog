package core.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import core.user.UserWork;
import core.utils.Constants;
import core.utils.LogUtils;

public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		LogUtils.logInfo("SecurityFilter init!");
	}

	@Override
	public void destroy() {
		LogUtils.logInfo("SecurityFilter destroy!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String servletPath = req.getServletPath();

		HttpSession session = req.getSession();

		UserWork user = (UserWork) session.getAttribute(session.getId());

		if (user == null) {
			user = new UserWork();
			request.setAttribute(Constants.COOKIE_USER_CHECKED, Constants.COOKIE_USER_CHECKED);
			LogUtils.logInfo("(SecurityFilter doFilter()) - USER_INIT_NULL: " + session.getId());
		}

		boolean isCheckCookie = (request.getAttribute(Constants.COOKIE_USER_CHECKED) == null) ? false : true;

		if (isCheckCookie) {
			user.getUserOnCookies(req);
			session.setAttribute(session.getId(), user);
			session.removeAttribute(Constants.COOKIE_USER_CHECKED);
			LogUtils.logInfo("(SecurityFilter doFilter()) - COOKIE_USER_CHECKED: " + session.getId());
		}

		if (isAuthentication(servletPath)) {// cannot access them without
											// authorization

		}
		
		LogUtils.logInfo("(SecurityFilter doFilter()) - ContextPath:" + req.getContextPath() + " ServletPath:"
				+ servletPath + ", URL =" + req.getRequestURL());		

		chain.doFilter(request, response);
	}

	private boolean isAuthentication(String urlPath) {
		String[] authenticationUrls = { "/adminpanel" };
		for (String url : authenticationUrls) {
			if (url.endsWith(urlPath)) {
				return true;
			}
		}
		return false;
	}

	private boolean isJsp(String s) {
		if (s.endsWith(".jsp"))
			return true;
		else
			return false;
	}

}