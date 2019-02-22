package core.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.model.ListPost;
import core.model.User;
import core.service.BlogWork;
import core.utils.Constants;
import core.utils.LogUtils;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	public void init(HomeServlet fConfig) throws ServletException {
    	LogUtils.logInfo("HomeServlet init!");
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();	
		
		User user = (User) session.getAttribute(session.getId());	
		
		LogUtils.logInfo("(HomeServlet do get()) - User: " + session.getId() + ", Login: " + user.getLogin() + ", Group: " + user.getGroup() + ", isAuth: " + user.isAuth());			
		
		request.setAttribute(Constants.IS_AUTHORIZED_USER, user.isAuth());
		
		if(user.isAuth()){
			request.setAttribute(Constants.AUTHORIZED_USER, user.getLogin());
		}
		
		BlogWork blogList = new BlogWork();
		
		Map<String, ListPost> blogPosts = blogList.getListBlogPosts(user.getConnectionDb());
		
		request.setAttribute("listPost", blogPosts);
				
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response); 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
    public void destroy() {
    	LogUtils.logInfo("HomeServlet destroy!");
    }	

}
