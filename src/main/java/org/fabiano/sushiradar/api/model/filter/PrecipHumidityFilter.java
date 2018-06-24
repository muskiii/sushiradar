package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.ComponentRateable;
import org.fabiano.sushiradar.api.utils.Column;
import org.fabiano.sushiradar.api.utils.Entity;
import org.fabiano.sushiradar.api.utils.FK;

@Entity
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
    
    public PrecipHumidityFilter() {
		super(null);
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
	public void setComponentRateable(ComponentRateable componentRateable) {
		super.componentRateable = componentRateable;		
	}
	
	@Override
    public float calculateFishingRate() {
		return super.componentRateable.calculateFishingRate() + 10;
    }

	@Override
	public ComponentRateable getComponentRateable() {
		return super.componentRateable;
	}
}
