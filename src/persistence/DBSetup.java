package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DBSetup {
    
	static String url = "jdbc:mysql://localhost:3306/rimaDB";
	static String user = "root";
	static String password = "";//change password
	static String query;
	static Connection con;
	static Statement statement;
	static ResultSet result;

}
