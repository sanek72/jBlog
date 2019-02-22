package core.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import core.model.ListPost;
import core.model.Post;

public class DBBlog {

	public Map<String, ListPost> getListBlogPosts(Connection connection) throws SQLException {
		Map<String, ListPost> blogPosts = new ConcurrentHashMap<String, ListPost>();

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
	
}
