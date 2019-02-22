package core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ListPost{
	
	private String category;		

	private Map<String, ArrayList<Post>> types;


	public Map<String, ArrayList<Post>> getPosts() {
		return types;
	}		

	public void putPost(String key, Post post) {
		if(types == null){
			types = new HashMap<String, ArrayList<Post>>();
		}
		
		ArrayList<Post> posts;
		
		if(types.containsKey(key)){
			posts = types.get(key);
			posts.add(post);
			types.put(key, posts);
		} else {
			posts = new ArrayList<Post>();
			posts.add(post);
			types.put(key, posts);
		}				
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ListPost [category=" + category + ", types=" + types + "]";
	}
	

}
