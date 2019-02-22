package core.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

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
	
}
