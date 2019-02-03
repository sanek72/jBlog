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
import core.utils.LogUtils;
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

		UserWork user = (UserWork) session.getAttribute(session.getId());

		LogUtils.logInfo("(RgistrationServlet do get()) - User: " + session.getId() + ", Login: " + user.getLogin()
				+ ", Group: " + user.getGroup() + ", isAuth: " + user.isAuth());

		if (user.isAuth()) {
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			request.getRequestDispatcher("/resources/views/registration.jsp").forward(request, response);
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
		
		if(!Validator.loginValid(login)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Login not correctly entered!");
			request.getRequestDispatcher("/resources/views/registration.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("login", login);
		
		if(!Validator.passwordValid(password)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Password not entered correctly!");
			request.getRequestDispatcher("/resources/views/registration.jsp").forward(request, response);
			return;
		}		
		
		if(!password.equals(password2)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Passwords do not match!");
			request.getRequestDispatcher("/resourcesF/views/registration.jsp").forward(request, response);
			return;
		}			
		
		request.setAttribute("password", password);
		request.setAttribute("password2", password);
		
		if(!Validator.emailValid(email)){
			request.setAttribute(Constants.REGISTRATION_ERRORE, "Email not entered correctly!");
			request.getRequestDispatcher("/resources/views/registration.jsp").forward(request, response);
			return;
		}	
		
		request.setAttribute("email", email);
		
		
		
		LogUtils.logInfo("(LoginServlet do doPost()) - !!!!YES!!!! ");
		
	}		
	

	public void destroy() {
		LogUtils.logInfo("RgistrationServlet destroy!");
	}

}
