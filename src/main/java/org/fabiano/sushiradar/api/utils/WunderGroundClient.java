package org.fabiano.sushiradar.api.utils;

import org.json.JSONObject;

public class WunderGroundClient {
	
	private static final String BASE_URL = "http://api.wunderground.com/api/2f0a571de3193464/geolookup/conditions/forecast/q/";

	public static String get4DayForecast(String country, String city) {
		String url = BASE_URL + country + "/"
				+ city + ".json";
		System.out.println(url);
		JSONObject jsonObject = HttpHelper.get(url);
			return jsonObject.toString();
	}

}
