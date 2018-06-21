package org.fabiano.sushiradar.api.controller;

import java.util.List;

import org.fabiano.sushiradar.api.model.Forecast;
import org.fabiano.sushiradar.api.model.filter.ForecastFilter;
import org.fabiano.sushiradar.api.service.ForecastFilterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FilterController {	
	
	ForecastFilterService forecastFilterService = new ForecastFilterService();
	
	@CrossOrigin
	@RequestMapping(value = "/filters", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<ForecastFilter>> getFilter(@RequestParam(value = "field", required = false) String field,
			@RequestParam(value = "value", required = false) String value) {
		List<ForecastFilter> fs = null;
		if (field == null) {
			fs = forecastFilterService.getAll();
		} else {
			fs = forecastFilterService.getWhere(field, value);
		}
		return new ResponseEntity<>(fs, HttpStatus.OK);
	}

	@RequestMapping(value = "/filters", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<ForecastFilter> createFilter(@RequestBody String body) {
		ForecastFilter f = forecastFilterService.fromJson(body);
		forecastFilterService.save(f);
		return new ResponseEntity<>(f, HttpStatus.OK);
	}
	
}
