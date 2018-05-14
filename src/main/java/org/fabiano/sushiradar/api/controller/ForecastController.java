package org.fabiano.sushiradar.api.controller;

import java.sql.SQLException;

import org.fabiano.sushiradar.api.dao.DatabaseConnection;
import org.fabiano.sushiradar.api.utils.HttpHelper;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForecastController {

	@GetMapping("/forecast")
	public void getForecast(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name = "country", required = true) String country) {

		String url = "http://api.wunderground.com/api/2f0a571de3193464/geolookup/conditions/forecast/q/"+country+"/"+city+".json";
//		JSONObject jsonObject = HttpHelper.get(url);
//		System.out.println(jsonObject);
		try {
			DatabaseConnection.getInstance().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}