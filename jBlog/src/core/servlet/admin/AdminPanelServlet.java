package core.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/adminpanel")
public class AdminPanelServlet extends HttpServlet {       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();		
        	out.println("YES GET");
			//Cookie cookie = new Cookie("cookieName", "007");
			//cookie.setMaxAge(60 * 60);
			//cookie.setPath("/");// ?????
			//response.addCookie(cookie);        	
        	//HttpSession session = request.getSession();	
        	//session.invalidate();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
