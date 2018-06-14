package org.fabiano.sushiradar.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.fabiano.sushiradar.api.config.SRConfiguration;

public class MSSQLDBStrategy extends DBStrategy{

	@Override
	public Connection getCon() {
		
		try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	String url = SRConfiguration.getConfiguration().get("databse_url") + ";databaseName=" + SRConfiguration.getConfiguration().get("database_name");
       System.out.print("connecting to:");
       System.out.println(url);
    	return DriverManager.getConnection(url,
        		SRConfiguration.getConfiguration().get("database_user"),
        		SRConfiguration.getConfiguration().get("database_password"));
    } catch (ClassNotFoundException e) {
    	e.printStackTrace();
        System.out.println("Database Connection Creation Failed : " + e.getMessage());
    } catch (SQLException e) {
    	 System.out.println("Database Connection Creation Failed : " + e.getMessage());
		e.printStackTrace();
	}
    return null;
	}

}
