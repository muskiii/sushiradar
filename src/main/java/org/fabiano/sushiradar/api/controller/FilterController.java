package org.fabiano.sushiradar.api.controller;

import org.fabiano.sushiradar.api.config.SRConfiguration;
import org.fabiano.sushiradar.api.factory.PersistStrategyFactory;
import org.fabiano.sushiradar.api.model.filter.ForecastFilter;
import org.fabiano.sushiradar.api.service.ForecastFilterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FilterController {
	
	
	ForecastFilterService forecastFilterService = new ForecastFilterService(
    		new PersistStrategyFactory<ForecastFilter>(ForecastFilter.class)
    		.createStrategy(SRConfiguration.getConfiguration().get("database_provider")));

	@RequestMapping(value = "/filters", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<ForecastFilter> createFilter(@RequestBody String body) {
		ForecastFilter f = forecastFilterService.fromJson(body);
		forecastFilterService.save(f);
		return new ResponseEntity<>(f, HttpStatus.OK);
	}
	
}
