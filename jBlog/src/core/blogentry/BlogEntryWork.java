package core.blogentry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import core.database.DBBlogEntry;

public class BlogEntryWork {

	public BlogEntryWork() {

	}
	
	public ArrayList<String> getTypes(Connection connection){
		ArrayList<String> s = null;
		
		try {
			s = DBBlogEntry.getTypes(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	public ArrayList<String> getSubTypes(Connection connection, String type){
		ArrayList<String> s = null;
		
		try {
			s = DBBlogEntry.getSubTypes(connection, type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}	
	
	public ArrayList<String> getName(Connection connection, String type, String subType){
		ArrayList<String> s = null;
		
		try {
			s = DBBlogEntry.getName(connection, type, subType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}		

}
