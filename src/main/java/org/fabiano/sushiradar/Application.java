package org.fabiano.sushiradar;

import org.fabiano.sushiradar.api.controller.ForecastController;
import org.fabiano.sushiradar.api.service.ForecastService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application extends WebMvcAutoConfiguration {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

//        ForecastController fc = new ForecastController();
//        System.out.println(fc.getForecast("buenos_aires","Argentina"));
//        System.out.println(new ForecastService().fromJson(fc.getForecast("buenos_aires","Argentina")));
//        fc.saveForecast(fc.getForecast("buenos_aires","Argentina"));
        }
}
