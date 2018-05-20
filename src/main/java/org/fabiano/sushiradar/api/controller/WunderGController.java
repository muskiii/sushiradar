package org.fabiano.sushiradar.api.controller;

import org.fabiano.sushiradar.api.utils.WunderGroundClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WunderGController {
	
	@RequestMapping(value = "/4daysforecast", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getForecast(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name = "country", required = true) String country) {
		return WunderGroundClient.get4DayForecast(country, city);
	}

}
