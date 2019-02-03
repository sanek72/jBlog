package core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static void addCookie(HttpServletResponse response, String login, String password){
		Cookie cookieLogin = new Cookie(Constants.COOKIE_LOGIN_KEY, login);
		Cookie cookiePassword = new Cookie(Constants.COOKIE_PASSWORD_KEY, password);
		cookieLogin.setMaxAge(60 * 60);
		cookiePassword.setMaxAge(60 * 60);
		response.addCookie(cookieLogin);	
		response.addCookie(cookiePassword);
	}
	
	public static void removeCookie(HttpServletResponse response){		
		Cookie cookieLogin = new Cookie(Constants.COOKIE_LOGIN_KEY, null);
		Cookie cookiePassword = new Cookie(Constants.COOKIE_PASSWORD_KEY, null);
		cookieLogin.setMaxAge(0);
		cookiePassword.setMaxAge(0);
        response.addCookie(cookieLogin);
        response.addCookie(cookiePassword);
	}
	
	public static String[] getCookieValue(HttpServletRequest request){
		String[] s = new String[2];
		Cookie[] cookies = request.getCookies();
		
		if (cookies == null){ 			
			return null;
		}
		
		for (Cookie c : cookies) { 
	        if (Constants.COOKIE_LOGIN_KEY.equals(c.getName())) {
	        	s[0] = c.getValue();
	        }	     
	        if (Constants.COOKIE_PASSWORD_KEY.equals(c.getName())) {
	        	s[1] = c.getValue();
	        }	  	        
		}
		
		if(s[0] == null || s[1] == null){
			return null;
		}			
		
		return s;
	}

}
