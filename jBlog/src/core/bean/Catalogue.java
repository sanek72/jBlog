package core.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;

import core.blogentry.BlogEntryWork;

public class Catalogue implements Serializable {
	
	private static final long serialVersionUID = -5142442584186727865L;
	
	private BlogEntryWork entryWork;
	private Connection connection;	
	
	public Catalogue(Connection connection){
		entryWork = new BlogEntryWork();
		this.connection = connection;
	}

	// получить типы 
	public ArrayList<String> getTypes() {
		return entryWork.getTypes(connection);
	}

    // получить подтипы 
	public ArrayList<String> getSubTypes(String type) {
		return entryWork.getSubTypes(connection, type);
	}
	
	// получаем имена все записей
	public ArrayList<String> getName(String type, String subType) {
		return entryWork.getName(connection, type, subType);
	}	
}
