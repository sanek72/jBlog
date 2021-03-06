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
import core.utils.CookieUtils;
import core.utils.LogUtils;

@WebServlet("/exit")
public class ExitServlet extends HttpServlet {	
	
	public void init(ExitServlet fConfig) throws ServletException {
    	LogUtils.logInfo("ExitServlet init!");
    }
              

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		
		User user = (User) session.getAttribute(session.getId());
		
		UserWork userWork = new UserWork(user);
		
		userWork.getCookies().removeCookie(response);
		
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/home");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

    public void destroy() {
    	LogUtils.logInfo("ExitServlet destroy!");
    }		
	
}
