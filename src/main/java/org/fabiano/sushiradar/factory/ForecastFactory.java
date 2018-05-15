package org.fabiano.sushiradar.factory;

import org.fabiano.sushiradar.api.model.Forecast;

public final class ForecastFactory {
	
	public static Forecast createForecast() {
		return new Forecast("TEST", "TEST", "TEST", "TEST", "TEST", 0.0f, 0.0f, 0.0f, 0.0f, "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST");
	}

}
