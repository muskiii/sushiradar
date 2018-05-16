package org.fabiano.sushiradar;

import org.fabiano.sushiradar.api.controller.ForecastController;
import org.fabiano.sushiradar.api.service.ForecastService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        ForecastController fc = new ForecastController();
//        System.out.println(fc.getForecast("buenos_aires","Argentina"));
        System.out.println(new ForecastService().fromJson(fc.getForecast("buenos_aires","Argentina")));
        while(true) {
        fc.saveForecast(fc.getForecast("buenos_aires","Argentina"));
        }
}
}
