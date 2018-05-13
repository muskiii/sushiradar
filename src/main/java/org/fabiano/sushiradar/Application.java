package org.fabiano.sushiradar;

import org.fabiano.sushiradar.api.config.SRConfiguration;
import org.fabiano.sushiradar.api.service.Forecast;

public class Application {
    public static void main(String[] args) {
        System.out.println(SRConfiguration.getConfiguration().get("url"));
        Forecast forecast = new Forecast();
        forecast.getForecast();

    }
}
