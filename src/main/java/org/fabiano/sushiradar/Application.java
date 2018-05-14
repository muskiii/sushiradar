package org.fabiano.sushiradar;

import java.sql.SQLException;

import org.fabiano.sushiradar.api.config.SRConfiguration;
import org.fabiano.sushiradar.api.controller.ForecastController;
import org.fabiano.sushiradar.api.dao.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
    	 ApplicationContext ctx = SpringApplication.run(Application.class, args);
//         try {
//			DatabaseConnection.getInstance().getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
        ForecastController forecastService = new ForecastController();
//        new ForecastController().getForecast("buenos_aires","Argentina");
        System.out.println(new ForecastController().saveForecast(null));
    }
}
