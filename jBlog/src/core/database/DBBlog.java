package core.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import core.model.ListPost;
import core.model.Post;

public class DBBlog {

	public Map<String, ListPost> getListBlogPosts(Connection connection) throws SQLException {
		Map<String, ListPost> blogPosts = new HashMap<String, ListPost>();

		String sql = "select * from `blogposts`";

		PreparedStatement pstm = connection.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
				
		while (rs.next()) {	
			
			int id = rs.getInt("id");
			boolean show = rs.getBoolean("show");
			String namePost = rs.getString("name");
			String categoryPost = rs.getString("category");
			String subcategoryPost = rs.getString("subcategory");
			
			Post post = new Post();
			post.setId(id);
			post.setShow(show);
			post.setName(namePost);
			post.setType(subcategoryPost);
			
			ListPost list;
			
			if(blogPosts.containsKey(categoryPost)){
				list = blogPosts.get(categoryPost);
				list.putPost(subcategoryPost, post);// key как подкатегория
				blogPosts.put(categoryPost, list);
			} else {
				list = new ListPost();
				list.setCategory(categoryPost);
				list.putPost(subcategoryPost, post);
				blogPosts.put(categoryPost, list);// key как категория
			}
		}
		
		return blogPosts;
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
	
//	public static ArrayList<String> getTypes(Connection connection) throws SQLException{
//	    ArrayList<String> s = new ArrayList<>();
//        String sql = "select type from `blogentry`";
//        
//        PreparedStatement pstm = connection.prepareStatement(sql);
// 
//        ResultSet rs = pstm.executeQuery();
// 
//        while (rs.next()) {
//        	String type = rs.getString("type");
//        	if(!s.contains(type)){
//        		s.add(type);
//        	}
//
//        }	    
//	    return s;	    
//	}
//	
//	public static ArrayList<String> getSubTypes(Connection connection, String type) throws SQLException{
//	    ArrayList<String> s = new ArrayList<>();
//        String sql = "select subtype from `blogentry` where type = ?";
//        
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1, type);
// 
//        ResultSet rs = pstm.executeQuery();
// 
//        while (rs.next()) {
//        	String subType = rs.getString("subtype");
//        	if(!s.contains(subType)){
//        		s.add(subType);
//        	}
//
//        }	    
//	    return s;	    
//	}	
	
//	public static ArrayList<String> getName(Connection connection, String type, String subType) throws SQLException{
//	    ArrayList<String> s = new ArrayList<>();
//        String sql = "select name from `blogentry` where type = ? and `subType` = ?";
//        
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1, type);
//        pstm.setString(2, subType);
// 
//        ResultSet rs = pstm.executeQuery();
// 
//        while (rs.next()) {
//        	String name = rs.getString("name");
//        	if(!s.contains(name)){
//        		s.add(name);
//        	}
//
//        }	    
//	    return s;	    
//	}		
	
}
