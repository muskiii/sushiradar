package org.fabiano.sushiradar.api.controller;

import java.util.List;

import org.fabiano.sushiradar.api.config.SRConfiguration;
import org.fabiano.sushiradar.api.factory.PersistStrategyFactory;
import org.fabiano.sushiradar.api.model.Forecast;
import org.fabiano.sushiradar.api.service.ForecastService;
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
public class ForecastController {

	private static ForecastService forecastService = new ForecastService(
			new PersistStrategyFactory<Forecast>(Forecast.class)
					.createStrategy(SRConfiguration.getConfiguration().get("database_provider")));

	@CrossOrigin
	@RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Forecast>> getForecast(@RequestParam(value = "field", required = false) String field,
			@RequestParam(value = "value", required = false) String value) {
		List<Forecast> fs = null;
		if (field == null) {
			fs = forecastService.getAll();
		} else {
			fs = forecastService.getWhere(field, value);
		}
		return new ResponseEntity<>(fs, HttpStatus.OK);
	}

	@RequestMapping(value = "/forecast", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Forecast> saveForecast(@RequestBody String body) {
		Forecast f = forecastService.fromJson(body);

		String status = forecastService.save(f);
		if (status.equals("ok")) {
			return new ResponseEntity<>(f, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(f, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(value = "/forecast", method = RequestMethod.DELETE)
	public ResponseEntity<Forecast> deleteAllForecast() {
		forecastService.deleteAll();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}