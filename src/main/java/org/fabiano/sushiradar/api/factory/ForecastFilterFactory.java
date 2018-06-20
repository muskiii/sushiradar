package org.fabiano.sushiradar.api.factory;

import org.fabiano.sushiradar.api.model.filter.ForecastFilter;
import org.fabiano.sushiradar.api.model.filter.PrecipHumidityFilter;
import org.fabiano.sushiradar.api.model.filter.TempFilter;
import org.fabiano.sushiradar.api.model.filter.WindFilter;

public final class ForecastFilterFactory {
	
	public static ForecastFilter createForecast(String type) {
		switch (type) {
		case "temp":
			return new TempFilter(null);
		case "hum":
			return new PrecipHumidityFilter(null);
		case "wind":
			return new WindFilter(null);			
		default:
			return null;
		}		
	}
}
