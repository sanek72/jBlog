package core.blog;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import core.database.DBBlog;
import core.model.ListPost;

public class BlogWork {
	
	private DBBlog db;
	
	public BlogWork(){
		db = new DBBlog();
	}

	public Map<String, ListPost> getListBlogPosts(Connection connection){
		Map<String, ListPost> blogPosts = null;
		try {
			blogPosts = db.getListBlogPosts(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blogPosts;
	}
	
//	public ArrayList<String> getTypes(Connection connection){
//		ArrayList<String> s = null;
//		
//		try {
//			s = DBBlogEntry.getTypes(connection);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return s;
//	}
	
//	public ArrayList<String> getSubTypes(Connection connection, String type){
//		ArrayList<String> s = null;
//		
//		try {
//			s = DBBlogEntry.getSubTypes(connection, type);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return s;
//	}	
	
//	public ArrayList<String> getName(Connection connection, String type, String subType){
//		ArrayList<String> s = null;
//		
//		try {
//			s = DBBlogEntry.getName(connection, type, subType);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return s;
//	}		

}
