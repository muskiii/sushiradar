package org.fabiano.sushiradar;

import org.fabiano.sushiradar.api.controller.ForecastController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        ForecastController fc = new ForecastController();
//        fc.getForecast("buenos_aires","Argentina");
        fc.saveForecast(fc.getForecast("buenos_aires","Argentina"));
}
}
