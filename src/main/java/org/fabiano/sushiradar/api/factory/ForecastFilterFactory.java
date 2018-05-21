package org.fabiano.sushiradar.api.factory;

import org.fabiano.sushiradar.api.model.filter.ForecastFilter;
import org.fabiano.sushiradar.api.model.filter.TempFilter;

public final class ForecastFilterFactory {
	
	public static ForecastFilter createForecast(String type) {
		switch (type) {
		case "temp":
			return new TempFilter(null);
		default:
			return null;
		}		
	}
}
