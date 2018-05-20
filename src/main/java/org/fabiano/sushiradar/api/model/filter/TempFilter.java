package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.Column;
import org.fabiano.sushiradar.api.model.ComponentRateable;

public class TempFilter extends ForecastFilter {

	 @Column(name="fk_forecast")
	 private int forecastID; 
	 
	 @Column(name="maxTempC")
	 private float maxTempC;
	 
	 @Column(name="minTempC")
	 private float minTempC;

    public TempFilter(ComponentRateable componentRateable) {
        super(componentRateable);
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
