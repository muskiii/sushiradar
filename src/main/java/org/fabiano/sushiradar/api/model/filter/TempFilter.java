package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.Column;
import org.fabiano.sushiradar.api.model.ComponentRateable;
import org.fabiano.sushiradar.api.model.FK;

public class TempFilter extends ForecastFilter {

	@Column(name="fk_forecast")
	@FK(name="fk_forecast")
	 private int forecastID;  	 
	
	 @Column(name="maxTempC")
	 private float maxTempC;
	 
	 @Column(name="minTempC")
	 private float minTempC;

    public TempFilter(ComponentRateable componentRateable) {
        super(componentRateable);
    }
    
    public int getForecastID() {
		return forecastID;
	}

	public void setForecastID(int forecastID) {
		this.forecastID = forecastID;
	}

	public float getMaxTempC() {
		return maxTempC;
	}

	public void setMaxTempC(float maxTempC) {
		this.maxTempC = maxTempC;
	}

	public float getMinTempC() {
		return minTempC;
	}

	public void setMinTempC(float minTempC) {
		this.minTempC = minTempC;
	}

	@Override
    public float calculateFishingRate() {
        return 0;
    }
}
