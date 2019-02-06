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
import core.utils.StringUtils;
import core.utils.Validator;
import core.utils.md5Utils;

/**
 * Servlet implementation class LoginPageServlet
 */

@WebServlet("/registration")
public class RgistrationServlet extends HttpServlet {

	public void init(RgistrationServlet fConfig) throws ServletException {
		LogUtils.logInfo("RgistrationServlet init!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		UserWork user = (UserWork) session.getAttribute(session.getId());

		LogUtils.logInfo("(RgistrationServlet do get()) - User: " + session.getId() + ", Login: " + user.getLogin()
				+ ", Group: " + user.getGroup() + ", isAuth: " + user.isAuth());

		if (user.isAuth()) {
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();	
		
		UserWork user = (UserWork) session.getAttribute(session.getId());
				
		LogUtils.logInfo("(LoginServlet do doPost()) - User: " + session.getId() + ", Login: " + user.getLogin() + ", Group: " + user.getGroup() + ", isAuth: " + user.isAuth());
		
		if(user.isAuth()){
			response.sendRedirect(request.getContextPath() + "/home");
		}
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		
		request.setAttribute("login", login);
		request.setAttribute("password", password);
		request.setAttribute("password2", password);	
		request.setAttribute("email", email);			
		
		if(!Validator.loginValid(login)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Login not correctly entered!");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}
		
		if(user.isLogin(login)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "This login already exists!");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}				
		
		if(!Validator.passwordValid(password)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Password not entered correctly!");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}		
		
		if(!password.equals(password2)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Passwords do not match!");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}			
				
		if(!Validator.emailValid(email)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Email not entered correctly!");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}	
		
		if(user.isEmail(email)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "This email is already in use!");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}			
		
		
		boolean rememberMe = Boolean.valueOf(request.getParameter("rememberMe"));		
		
		user.setAuth(true);
		user.setLogin(login);
		user.setPassword(md5Utils.md5Apache(password));
		user.setGroup(Constants.USER_GROUP[1]);
		user.setEmail(email);
		user.setUserDb();
		
		if(rememberMe){
			String rndpass = md5Utils.md5Apache(StringUtils.passwordGenerator());
			user.setRandomPass(login, rndpass);
			CookieUtils.addCookie(response, user.getLogin(), rndpass);
		}		
		
		request.getRequestDispatcher("/WEB-INF/views/registrationOn.jsp").forward(request, response);						
		LogUtils.logInfo("(LoginServlet do doPost()) - New User: " + session.getId() + ", Login: " + user.getLogin());
		
	}		
	

	public void destroy() {
		LogUtils.logInfo("RgistrationServlet destroy!");
	}

}
