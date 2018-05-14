package org.fabiano.sushiradar;

import java.sql.SQLException;

import org.fabiano.sushiradar.api.config.SRConfiguration;
import org.fabiano.sushiradar.api.controller.ForecastController;
import org.fabiano.sushiradar.api.dao.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//    	 ApplicationContext ctx = SpringApplication.run(Application.class, args);
         
    	
        System.out.println(SRConfiguration.getConfiguration().get("databse_url")+";databaseName="+ SRConfiguration.getConfiguration().get("database_name"));
        try {
			DatabaseConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ForecastController forecastService = new ForecastController();
//        new ForecastController().getForecast("buenos_aires","Argentina");

    }
}
