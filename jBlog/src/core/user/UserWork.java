package core.user;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

import core.database.DBUtils;
import core.database.MySQLConn;
import core.utils.Constants;
import core.utils.CookieUtils;
import core.utils.LogUtils;

public class UserWork {
	 
	   private boolean isAuth;
	   private boolean isAdmin;
	   private boolean isblock;
	   private int loginAttempt;
	   private String login;
	   private String password;
	   private String email;
	   private String group;
	   private Data lastLoginDate;
	   private Connection connectionDb;
	    

	public UserWork(String login, String password, String email, String group, Data lastLoginDate, boolean isAuth, Connection connectionDb){  
		this.login = login;
		this.password = password;
		this.email = email;
		this.group = group;
		this.loginAttempt = 1;
		this.lastLoginDate = lastLoginDate;
		
		if(Constants.USER_GROUP[0].equals(group)){
			this.isAuth = false;
		}
		
		if(Constants.USER_GROUP[1].equals(group)){
			this.isAuth = true;
		}		
		
		if(Constants.USER_GROUP[2].equals(group)){
			this.isAdmin = true;
			this.isAuth = true;
		}
		this.connectionDb = connectionDb;
		
	}
	
	public UserWork(){
		login = Constants.DEFAULT_LOGIN;
		password = Constants.DEFAULT_PASSWORD;
		email = Constants.DEFAULT_EMAIL;
		group = Constants.USER_GROUP[0];
		loginAttempt = 1;
		lastLoginDate = null;	
		isAuth = false;
		isAdmin = false;
		connectionDb = null;
	}
	
	public UserWork getUser(String login){
		UserWork user = null;
		try {
			user = DBUtils.findUser(getConnectionDb(), login);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
		
		return user;
	}
	
	public void updateUser(){
		try {
			DBUtils.updateUser(getConnectionDb(), this);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void dataUser(){
		try {
			DBUtils.dataUser(getConnectionDb(), this);
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
				return DBUtils.isUserExists(getConnectionDb(), login, password, false);
			}
			return DBUtils.isUserExists(getConnectionDb(), login, password, true);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	public void setConnectionDb(Connection con){
		connectionDb = con;
	}
	
	public Connection getConnectionDb(){
		if(connectionDb == null){
			connectionDb = MySQLConn.getMySQLConnection();
		}		
		return connectionDb;
	}
	
	public void getUserOnCookies(HttpServletRequest request){
		String[] cookiesData = CookieUtils.getCookieValue(request);
		if(cookiesData == null){
			return;
		}
		
		String login = cookiesData[0];
		String password = cookiesData[1];

		if(checkLoginPassword(login, password, true)){
			setLogin(login);	
			setAuth(true);
			dataUser();			
		}
	}
	
	public void setRandomPass(String login, String rndpass){
		try {
			DBUtils.setRandomPassword(getConnectionDb(), login, rndpass);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setUserDb(){
		try {
			DBUtils.setUser(getConnectionDb(), this);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public boolean isLogin(String login){
		try {
			return DBUtils.isLogin(getConnectionDb(), login);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}		
		return false;
	}
	
	public boolean isEmail(String email){
		try {
			return DBUtils.isEmail(getConnectionDb(), email);
		} catch (SQLException e) {
			LogUtils.logErrore(e.getMessage());
			e.printStackTrace();
		}		
		return false;
	}	
				
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}	

	public boolean isAuth() {
		return isAuth;
	}

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getLoginAttempt() {
		return loginAttempt;
	}

	public void setLoginAttempt() {
		this.loginAttempt++;
	}
	
	public void resetLoginAttempt() {
		this.loginAttempt = 0;
	}

	public boolean isIsblock() {
		return isblock;
	}

	public void setIsblock(boolean isblock) {
		this.isblock = isblock;
	}

	public Data getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Data lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	 

	}