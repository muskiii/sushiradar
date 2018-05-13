package org.fabiano.sushiradar.api.service;

import org.fabiano.sushiradar.api.utils.HttpHelper;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/forecast")
public class ForecastService {
    @GET
    @Produces("application/json")
    public void getForecast() {
        String url = "http://api.wunderground.com/api/2f0a571de3193464/geolookup/conditions/forecast/q/Argentina/buenos_aires.json";
        JSONObject jsonObject = HttpHelper.get(url);
        System.out.println(jsonObject);
    }
}