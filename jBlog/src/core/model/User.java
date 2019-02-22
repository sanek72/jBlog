package core.model;

import java.sql.Connection;
import java.util.Date;

import core.database.MySQLConn;
import core.utils.Constants;


public class User {

	   private boolean isAuth;
	   private boolean isAdmin;
	   private boolean isblock;
	   private int loginAttempt;
	   private String login;
	   private String password;
	   private String email;
	   private String group;
	   private Date lastLoginDate;
	   private Connection connectionDb;	
	
		public User(String login, String password, String email, String group, Date lastLoginDate, boolean isAuth, Connection connectionDb){  
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
		
		public User(){
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
		
		public void setConnectionDb(Connection con){
			connectionDb = con;
		}
		
		public Connection getConnectionDb(){
			if(connectionDb == null){
				connectionDb = MySQLConn.getMySQLConnection();
			}		
			return connectionDb;
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

		public Date getLastLoginDate() {
			return lastLoginDate;
		}

		public void setLastLoginDate(Date lastLoginDate) {
			this.lastLoginDate = lastLoginDate;
		}		

}
