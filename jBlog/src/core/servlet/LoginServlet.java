package core.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.user.UserWork;
import core.utils.Constants;
import core.utils.CookieUtils;
import core.utils.LogUtils;
import core.utils.Properies;
import core.utils.StringUtils;
import core.utils.md5Utils;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	public void init(LoginServlet fConfig) throws ServletException {
    	LogUtils.logInfo("LoginServlet init!");
    }	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		
		UserWork user = (UserWork) session.getAttribute(session.getId());
				
		LogUtils.logInfo("(LoginServlet do get()) - User: " + session.getId() + ", Login: " + user.getLogin() + ", Group: " + user.getGroup() + ", isAuth: " + user.isAuth());
		
		if(!user.isAuth()){
			request.getRequestDispatcher("/resources/views/login.jsp").forward(request, response);  
		}else
			response.sendRedirect(request.getContextPath() + "/home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		
		UserWork user = (UserWork) session.getAttribute(session.getId());
		
		LogUtils.logInfo("(LoginServlet do doPost()) - User: " + session.getId() + ", Login: " + user.getLogin() + ", Group: " + user.getGroup() + ", isAuth: " + user.isAuth() + ", Login Attempt Count: " + user.getLoginAttempt());		

		Properies config = new Properies();
		
		if(user.isAuth()){
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}		
		LogUtils.logInfo("(LoginServlet do Post()) - login.attempt: " + Integer.parseInt(config.getProperty("login.attempt")));
		if(user.getLoginAttempt() >= Integer.parseInt(config.getProperty("login.attempt"))){
			request.setAttribute(Constants.CONTEXT_PATCH, request.getContextPath());
			request.getRequestDispatcher("/resources/views/nofound.jsp").forward(request, response);  			
			return;
		}						
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean rememberMe = Boolean.valueOf(request.getParameter("rememberMe"));
		LogUtils.logInfo("(LoginServlet do doPost()) - " + rememberMe);
	
		if(user.checkLoginPassword(login, md5Utils.md5Apache(password), false)){
			user.setLogin(login);	
			user.setAuth(true);
			user.dataUser();
			session.setAttribute(session.getId(), user);
			if(rememberMe){
				String rndpass = md5Utils.md5Apache(StringUtils.passwordGenerator());
				user.setRandomPass(login, rndpass);
				CookieUtils.addCookie(response, user.getLogin(), rndpass);
			}
			response.sendRedirect(request.getContextPath() + "/home");
			LogUtils.logInfo("(LoginServlet do Post()) - successful authorization user: " + user.getLogin());
				    
        } else {
			user.setLoginAttempt();
			request.setAttribute(Constants.NO_AUTHORIZED_USER, true);
			request.getRequestDispatcher("/resources/views/login.jsp").forward(request, response); 
			LogUtils.logInfo("(LoginServlet do get()) - authorization failed: " + user.getLogin());
				    
        }
	}
	
    public void destroy() {
    	LogUtils.logInfo("LoginServlet destroy!");
    }		

}
