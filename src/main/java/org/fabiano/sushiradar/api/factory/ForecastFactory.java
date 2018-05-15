package org.fabiano.sushiradar.api.factory;

import org.fabiano.sushiradar.api.model.Forecast;

public final class ForecastFactory {
	
	public static Forecast createForecast() {
		return new Forecast();
	}

}
