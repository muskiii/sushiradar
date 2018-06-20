package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.ComponentRateable;
import org.fabiano.sushiradar.api.utils.Column;
import org.fabiano.sushiradar.api.utils.FK;

public class PrecipHumidityFilter extends ForecastFilter {

	@Column(name="fk_forecast")
	@FK(name="fk_forecast")
	private int forecastID; 
	
	@Column(name="precipitation")
	private boolean precipitation;
	
	@Column(name="minHumidity")
	private float minHumidity;
	
	@Column(name="maxHumidity")
	private float maxHumidity;

    public PrecipHumidityFilter(ComponentRateable componentRateable) {
        super(componentRateable);
    }
    
	public boolean isPrecipitation() {
		return precipitation;
	}
	
	public int getForecastID() {
		return forecastID;
	}

	public void setForecastID(int forecastID) {
		this.forecastID = forecastID;
	}

	public void setPrecipitation(boolean precipitation) {
		this.precipitation = precipitation;
	}

	public float getMinHumidity() {
		return minHumidity;
	}

	public void setMinHumidity(float minHumidity) {
		this.minHumidity = minHumidity;
	}

	public float getMaxHumidity() {
		return maxHumidity;
	}

	public void setMaxHumidity(float maxHumidity) {
		this.maxHumidity = maxHumidity;
	}

	@Override
    public float calculateFishingRate() {
        return 0;
    }
}
