package core.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBBlogEntry {

	public DBBlogEntry() {

	}
	
//	public static ArrayList<Entry> getEntrys(Connection connection) throws SQLException{
//	    ArrayList<Entry> s = new ArrayList<>();
//	    Entry entry = null;
//	    
//        String sql = "select * from `blogentry`";
//        
//        PreparedStatement pstm = connection.prepareStatement(sql);
// 
//        ResultSet rs = pstm.executeQuery();
// 
//        while (rs.next()) {
//        	entry = new Entry();
//        	entry.setId(rs.getInt("id"));       	
//        	entry.setType(rs.getString("type"));
//        	entry.setSubtype(rs.getString("subtype"));
//        	entry.setShow(rs.getBoolean("show"));
//        	entry.setName(rs.getString("name"));     
//        	s.add(entry);
//        }
//	    
//	    return s;
//	}
	
	public static ArrayList<String> getTypes(Connection connection) throws SQLException{
	    ArrayList<String> s = new ArrayList<>();
        String sql = "select type from `blogentry`";
        
        PreparedStatement pstm = connection.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	String type = rs.getString("type");
        	if(!s.contains(type)){
        		s.add(type);
        	}

        }	    
	    return s;	    
	}
	
	public static ArrayList<String> getSubTypes(Connection connection, String type) throws SQLException{
	    ArrayList<String> s = new ArrayList<>();
        String sql = "select subtype from `blogentry` where type = ?";
        
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, type);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	String subType = rs.getString("subtype");
        	if(!s.contains(subType)){
        		s.add(subType);
        	}

        }	    
	    return s;	    
	}	
	
	public static ArrayList<String> getName(Connection connection, String type, String subType) throws SQLException{
	    ArrayList<String> s = new ArrayList<>();
        String sql = "select name from `blogentry` where type = ? and `subType` = ?";
        
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, type);
        pstm.setString(2, subType);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	String name = rs.getString("name");
        	if(!s.contains(name)){
        		s.add(name);
        	}

        }	    
	    return s;	    
	}		
	
}