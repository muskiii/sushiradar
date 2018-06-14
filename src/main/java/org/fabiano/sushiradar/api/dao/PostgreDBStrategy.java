package org.fabiano.sushiradar.api.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class PostgreDBStrategy extends DBStrategy{

	@Override
	public Connection getCon(Connection con) {
		String username = "";
		String password = "";
		String dbUrl = "";
		try {
			Class.forName("org.postgresql.Driver");  
			URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    username = dbUri.getUserInfo().split(":")[0];
	    password = dbUri.getUserInfo().split(":")[1];
	    dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
	    return DriverManager.getConnection(dbUrl, username, password);
		} catch (ClassNotFoundException e) {	
			System.out.println("Database Connection Creation Failed : " + e.getMessage());
			e.printStackTrace();	
		} catch (URISyntaxException e) {
			System.out.println("Database Connection Creation Failed : " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database Connection Creation Failed : " + e.getMessage());
			e.printStackTrace();
		}	  
		return null;
	}
}
