package org.fabiano.sushiradar.api.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.fabiano.sushiradar.api.config.SRConfiguration;
import org.fabiano.sushiradar.api.factory.DBStrategyFactory;

public class DatabaseConnection {

	private static DatabaseConnection instance;
	private Connection connection;
	private DBStrategy strategy;

	private DatabaseConnection(){
        this.strategy = new DBStrategyFactory().createStrategy(SRConfiguration.getConfiguration().get("database_provider"));
        this.connection =  this.strategy.getCon();
    }

	public Connection getConnection() {
		return connection;
	}

	public static synchronized DatabaseConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new DatabaseConnection();
		} else if (instance.getConnection().isClosed()) {
			instance = new DatabaseConnection();
		}

		return instance;
	}
}
