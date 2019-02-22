package core.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.model.User;
import core.user.UserWork;

public class DBUsers {

	public DBUsers() {

	}
	
	public static boolean isUserExists(Connection connection, String login, String password, boolean rndpass) throws SQLException{
	
		PreparedStatement ps;
        boolean isUserExists = false;
        if(!rndpass){
            ps = connection.prepareStatement("select * from `Users` where `login` = ? and `password` = ?");
        }else{
    	    ps = connection.prepareStatement("select * from `Users` where `login` = ? and `randpass` = ?");
        }

        ps.setString(1, login);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery(); 
        if (rs.next()) {
            isUserExists = true;
        }                       
         return isUserExists;
	}
	
	public static void setRandomPassword(Connection conn, String login, String rndpass) throws SQLException{
	    PreparedStatement ps = conn.prepareStatement("UPDATE users SET randpass = ? WHERE login = ?");
	    ps.setString(1,rndpass);
	    ps.setString(2,login);
	    ps.executeUpdate();
	    ps.close();		
	}
		
		
	
    public static User findUser(Connection conn, String login) throws SQLException {
   	 
        String sql = "select * from `users` where login = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, login);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("password");
            String group = rs.getString("group");
            String email = rs.getString("email");
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setGroup(group);
            user.setEmail(email);
            return user;
        }
        return null;
    }		
    
    public static void updateUser(User user) throws SQLException{
    	 	
    }
    
    public static boolean isLogin(Connection conn, String login) throws SQLException{
    	boolean isLoginExists = false;
        String sql = "select * from `users` where login = ? ";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, login);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
        	isLoginExists = true;
        }
        return isLoginExists;
    }
    
    public static boolean isEmail(Connection conn, String email) throws SQLException{
    	boolean isLoginExists = false;
        String sql = "select * from `users` where email = ? ";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
        	isLoginExists = true;
        }
        return isLoginExists;
    }    
    
    public static void setUser(User user) throws SQLException{
    	String sql = "INSERT INTO `users` (`login`, `password`, `group`, `email`) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = user.getConnectionDb().prepareStatement(sql);
        ps.setString(1, user.getLogin());    
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getGroup());
        ps.setString(4, user.getEmail());
	    ps.executeUpdate();
	    ps.close();		       
    }
    
    public static void dataUser(User user) throws SQLException{
    	
        String sql = "select * from `users` where login = ? ";
        
        PreparedStatement pstm = user.getConnectionDb().prepareStatement(sql);
        pstm.setString(1, user.getLogin());
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
        	user.setPassword(rs.getString("password"));
        	user.setGroup(rs.getString("group"));
            user.setEmail(rs.getString("email"));
        }    	
    }    
	
	

}
