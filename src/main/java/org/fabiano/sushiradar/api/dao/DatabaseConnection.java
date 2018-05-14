package org.fabiano.sushiradar.api.dao;

import org.fabiano.sushiradar.api.config.SRConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	String url = SRConfiguration.getConfiguration().get("databse_url") + ";databaseName=" + SRConfiguration.getConfiguration().get("database_name");
           System.out.print("connecting to:");
           System.out.println(url);
        	this.connection = DriverManager.getConnection(url,
            		SRConfiguration.getConfiguration().get("database_user"),
            		SRConfiguration.getConfiguration().get("database_password"));
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
            System.out.println("Database Connection Creation Failed : " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}
