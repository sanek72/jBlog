package core.servlet;

import java.io.IOException;

 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.utils.Constants;
 
@WebServlet(urlPatterns = { "/" })
public class DefaultServlet extends HttpServlet {


	private static final long serialVersionUID = 1408404428065475989L;

@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

		  request.getRequestDispatcher("/resources/views/nofound.jsp").forward(request, response);   
   }
 
}
