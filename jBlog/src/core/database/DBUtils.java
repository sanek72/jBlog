package core.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import core.user.UserWork;

public class DBUtils {

	public DBUtils() {

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
		
		
	
    public static UserWork findUser(Connection conn, String userName) throws SQLException {
   	 
        String sql = "select * from `users` where login = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("password");
            String group = rs.getString("group");
            String email = rs.getString("email");
            UserWork user = new UserWork();
            user.setLogin(userName);
            user.setPassword(password);
            user.setGroup(group);
            user.setEmail(email);
            return user;
        }
        return null;
    }		
    
    public static void updateUser(Connection conn, UserWork user) throws SQLException{
    	 	
    }
    
    public static void dataUser(Connection conn, UserWork user) throws SQLException{
    	
        String sql = "select * from `users` where login = ? ";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getLogin());
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
        	user.setPassword(rs.getString("password"));
        	user.setGroup(rs.getString("group"));
            user.setEmail(rs.getString("email"));
        }    	
    }    
	
	

}
