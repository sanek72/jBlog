package core.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import core.utils.LogUtils;
import net.sf.uadetector.*;
import net.sf.uadetector.service.UADetectorServiceFactory;
public class Plug implements Filter {

	private boolean browserCheck;
	private UserAgentStringParser parser;

	public Plug() {

	}

	public void init(FilterConfig fConfig) throws ServletException {
		browserCheck = Boolean.valueOf(fConfig.getInitParameter("browserCheck"));
		parser = UADetectorServiceFactory.getResourceModuleParser();
		LogUtils.logInfo("Plug init! browserCheck = " + browserCheck);
	}

	public void destroy() {
		LogUtils.logInfo("Plug destroy!");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (browserCheck) {
			
			HttpServletRequest req = (HttpServletRequest) request;
			ReadableUserAgent agent = parser.parse(req.getHeader("user-agent"));
			
			String browserName = agent.getName();
			int browserVersion = Integer.parseInt(agent.getVersionNumber().getMajor());
			
			if(browserName.equals("IE") && browserVersion < 11){
				LogUtils.logInfo("!Plug stop! " + " Browser name: " + browserName + " Browser version: " + browserVersion);
				request.getRequestDispatcher("/WEB-INF/views/oldbrowser.jsp").forward(request, response);
				return;				
			}
			
		}

		chain.doFilter(request, response);
	}

}
