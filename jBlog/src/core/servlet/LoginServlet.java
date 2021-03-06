package core.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.model.User;
import core.service.UserWork;
import core.utils.Constants;
import core.utils.CookieUtils;
import core.utils.LogUtils;
import core.utils.Properies;
import core.utils.StringUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	public void init(LoginServlet fConfig) throws ServletException {
    	LogUtils.logInfo("LoginServlet init!");
    }	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		
		User user = (User) session.getAttribute(session.getId());
				
		LogUtils.logInfo("(LoginServlet do get()) - User: " + session.getId() + ", Login: " + user.getLogin() + ", Group: " + user.getGroup() + ", isAuth: " + user.isAuth());
		
		if(!user.isAuth()){
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);  
		}else
			response.sendRedirect(request.getContextPath() + "/home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		
		User user = (User) session.getAttribute(session.getId());
		
		LogUtils.logInfo("(LoginServlet do doPost()) - User: " + session.getId() + ", Login: " + user.getLogin() + ", Group: " + user.getGroup() + ", isAuth: " + user.isAuth() + ", Login Attempt Count: " + user.getLoginAttempt());		

		Properies config = new Properies();
		
		if(user.isAuth()){
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}		
		LogUtils.logInfo("(LoginServlet do Post()) - login.attempt: " + Integer.parseInt(config.getProperty("login.attempt")));
		if(user.getLoginAttempt() >= Integer.parseInt(config.getProperty("login.attempt"))){
			request.setAttribute(Constants.CONTEXT_PATCH, request.getContextPath());
			request.getRequestDispatcher("/WEB-INF/views/nofound.jsp").forward(request, response);  			
			return;
		}						
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean rememberMe = Boolean.valueOf(request.getParameter("rememberMe"));
		LogUtils.logInfo("(LoginServlet do doPost()) - " + rememberMe);
	
		LogUtils.logInfo("(LoginServlet do doPost()) - user login " + login + " / " + password);
		
		UserWork userWork = new UserWork(user);
		
		if(userWork.checkLoginPassword(login, userWork.md5Apache(password), false)){
			user.setLogin(login);	
			user.setAuth(true);
			userWork.dataUser();
			session.setAttribute(session.getId(), user);
			if(rememberMe){
				String rndpass = userWork.md5Apache(StringUtils.passwordGenerator());
				userWork.setRandomPass(login, rndpass);
				userWork.getCookies().addCookie(response, user.getLogin(), rndpass);
			}
			response.sendRedirect(request.getContextPath() + "/home");
			LogUtils.logInfo("(LoginServlet do Post()) - successful authorization user: " + user.getLogin());
				    
        } else {
			user.setLoginAttempt();
			request.setAttribute(Constants.NO_AUTHORIZED_USER, true);
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response); 
			LogUtils.logInfo("(LoginServlet do get()) - authorization failed: " + user.getLogin());
				    
        }
	}
	
    public void destroy() {
    	LogUtils.logInfo("LoginServlet destroy!");
    }		

}
