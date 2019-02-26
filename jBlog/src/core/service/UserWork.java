package core.service;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import core.model.User;
import core.utils.CookieUtils;
import core.utils.LogUtils;
import core.utils.StringUtils;
import core.utils.Validator;

public class UserWork {
	
	private DBUser db;
	private Validator valid;
	private CookieUtils cookies;
	private User user;

	public UserWork(User user){
		db = new DBUser();
		valid = new Validator();
		cookies = new CookieUtils();	
		this.user = user;
	}	
	
	public User getUser(String login){
		try {
			user = db.findUser(user.getConnectionDb(), login);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
		
		return user;
	}
	
	public void updateUser(){
		try {
			db.updateUser(user);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void dataUser(){
		try {
			db.dataUser(user);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
	}	
	
	public boolean checkLoginPassword(String login, String password, boolean rndpass){
		if(login == null || password == null){
			return false;
		}
		try {
			if(!rndpass){
				return db.isUserExists(user.getConnectionDb(), login, password, false);
			}
			return db.isUserExists(user.getConnectionDb(), login, password, true);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void getUserOnCookies(Cookie[] cookies){
		String[] cookiesData = getCookies().getCookieValue(cookies);
		if(cookiesData == null){
			return;
		}
		
		String login = cookiesData[0];
		String password = cookiesData[1];

		if(checkLoginPassword(login, password, true)){
			user.setLogin(login);	
			user.setAuth(true);
			dataUser();			
		}
	}
	
	public void setRandomPass(String login, String rndpass){
		try {
			db.setRandomPassword(user.getConnectionDb(), login, rndpass);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setUserDb(){
		try {
			db.setUser(user);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public boolean isLogin(String login){
		try {
			return db.isLogin(user.getConnectionDb(), login);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}		
		return false;
	}
	
	public boolean isEmail(String email){
		try {
			return db.isEmail(user.getConnectionDb(), email);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}		
		return false;
	}		
	
	public String md5Apache(String password) {
		return DigestUtils.md5Hex(password);
	}
	

	public Validator getValid() {
		return valid;
	}
	
	public CookieUtils getCookies() {
		return cookies;
	}	

	
}

