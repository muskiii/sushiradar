package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.ComponentRateable;

public class PrecipHumidity extends ForecastFilter {

	private String precipAllDay;
    private float aveHumidity;

    public PrecipHumidity(ComponentRateable componentRateable) {
        super(componentRateable);
    }
    

	public String getPrecipAllDay() {
		return precipAllDay;
	}


	public void setPrecipAllDay(String precipAllDay) {
		this.precipAllDay = precipAllDay;
	}


	public float getAveHumidity() {
		return aveHumidity;
	}


	public void setAveHumidity(float aveHumidity) {
		this.aveHumidity = aveHumidity;
	}


	@Override
    public float calculateFishingRate() {
        return 0;
    }
}
