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
import core.utils.StringUtils;
import core.utils.Validator;

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

		User user = (User) session.getAttribute(session.getId());

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
		
		User user = (User) session.getAttribute(session.getId());
				
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
		
		UserWork userWork = new UserWork(user);
		
		LogUtils.logInfo("(LoginServlet do doPost()) - Registration User: Login - " + login + 
				", password - " + password + ", password2 - " + password2 + ", email - " + email);
		
		if(!userWork.getValid().loginValid(login)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Введённый вами логин некорректен. Пожалуйста, введите другой логин.");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}
		
		if(userWork.isLogin(login)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Такой логин уже существует. Пожалуйста, введите другой логин.");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}				
		
		if(!userWork.getValid().passwordValid(password)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Введённый вами пароль некорректен. Пожалуйста, введите другой пароль.");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}		
		
		if(!password.equals(password2)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Введенные вами пароли не совподают. Пожалуйста, введите пароли еще раз.");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}			
				
		if(!userWork.getValid().emailValid(email)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Введённый вами email некорректен. Пожалуйста, введите другой email.");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}	
		
		if(userWork.isEmail(email)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Такой email уже существует. Пожалуйста, введите другой email.");
			request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			return;
		}			
		
		
		boolean rememberMe = Boolean.valueOf(request.getParameter("rememberMe"));		
		
		user.setAuth(true);
		user.setLogin(login);
		user.setPassword(userWork.md5Apache(password));
		user.setGroup(Constants.USER_GROUP[1]);
		user.setEmail(email);
		userWork.setUserDb();
		
		if(rememberMe){
			String rndpass = userWork.md5Apache(StringUtils.passwordGenerator());
			userWork.setRandomPass(login, rndpass);
			userWork.getCookies().addCookie(response, user.getLogin(), rndpass);
		}		
		
		request.getRequestDispatcher("/WEB-INF/views/registrationOn.jsp").forward(request, response);						
		LogUtils.logInfo("(LoginServlet do doPost()) - New User: " + session.getId() + ", Login: " + user.getLogin());
		
	}		
	

	public void destroy() {
		LogUtils.logInfo("RgistrationServlet destroy!");
	}

}
