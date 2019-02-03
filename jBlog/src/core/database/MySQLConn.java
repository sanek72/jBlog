package core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import core.utils.Properies;

public class MySQLConn {	
	
	public static Connection getMySQLConnection(){
		return ConnectionPool.getInstance().getConnection();
	}

}
