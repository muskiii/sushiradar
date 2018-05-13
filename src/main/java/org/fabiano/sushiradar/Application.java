package org.fabiano.sushiradar;

import org.fabiano.sushiradar.api.config.SRConfiguration;
import org.fabiano.sushiradar.api.service.ForecastService;

public class Application {
    public static void main(String[] args) {
        System.out.println(SRConfiguration.getConfiguration().get("url"));
        ForecastService forecastService = new ForecastService();
        forecastService.getForecast();

    }
}
