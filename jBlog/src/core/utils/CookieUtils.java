package core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public void addCookie(HttpServletResponse response, String login, String password){
		Cookie cookieLogin = new Cookie(Constants.COOKIE_LOGIN_KEY, login);
		Cookie cookiePassword = new Cookie(Constants.COOKIE_PASSWORD_KEY, password);
		cookieLogin.setMaxAge(60 * 60);
		cookiePassword.setMaxAge(60 * 60);
		response.addCookie(cookieLogin);	
		response.addCookie(cookiePassword);
	}
	
	public void removeCookie(HttpServletResponse response){		
		Cookie cookieLogin = new Cookie(Constants.COOKIE_LOGIN_KEY, null);
		Cookie cookiePassword = new Cookie(Constants.COOKIE_PASSWORD_KEY, null);
		cookieLogin.setMaxAge(0);
		cookiePassword.setMaxAge(0);
        response.addCookie(cookieLogin);
        response.addCookie(cookiePassword);
	}
	
	public String[] getCookieValue(Cookie[] cookies){
		String[] s = new String[2];
		
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
