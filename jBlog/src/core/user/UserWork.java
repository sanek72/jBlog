package core.user;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import core.database.DBUsers;
import core.utils.CookieUtils;
import core.utils.LogUtils;

public class UserWork {
	 	    	
	public User getUser(User user, String login){
		try {
			user = DBUsers.findUser(user.getConnectionDb(), login);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
		
		return user;
	}
	
	public void updateUser(User user){
		try {
			DBUsers.updateUser(user);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void dataUser(User user){
		try {
			DBUsers.dataUser(user);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
	}	
	
	public boolean checkLoginPassword(User user, String login, String password, boolean rndpass){
		if(login == null || password == null){
			return false;
		}
		try {
			if(!rndpass){
				return DBUsers.isUserExists(user.getConnectionDb(), login, password, false);
			}
			return DBUsers.isUserExists(user.getConnectionDb(), login, password, true);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void getUserOnCookies(User user, HttpServletRequest request){
		String[] cookiesData = CookieUtils.getCookieValue(request);
		if(cookiesData == null){
			return;
		}
		
		String login = cookiesData[0];
		String password = cookiesData[1];

		if(checkLoginPassword(user, login, password, true)){
			user.setLogin(login);	
			user.setAuth(true);
			dataUser(user);			
		}
	}
	
	public void setRandomPass(User user, String login, String rndpass){
		try {
			DBUsers.setRandomPassword(user.getConnectionDb(), login, rndpass);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setUserDb(User user){
		try {
			DBUsers.setUser(user);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public boolean isLogin(User user, String login){
		try {
			return DBUsers.isLogin(user.getConnectionDb(), login);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}		
		return false;
	}
	
	public boolean isEmail(User user, String email){
		try {
			return DBUsers.isEmail(user.getConnectionDb(), email);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}		
		return false;
	}	
				


	 

	}