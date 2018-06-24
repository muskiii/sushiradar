package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.ComponentRateable;
import org.fabiano.sushiradar.api.utils.Column;
import org.fabiano.sushiradar.api.utils.Entity;
import org.fabiano.sushiradar.api.utils.FK;

@Entity
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
    
    public TempFilter() {
		super(null);
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
	public void setComponentRateable(ComponentRateable componentRateable) {
		super.componentRateable = componentRateable;		
	}
	@Override
	public ComponentRateable getComponentRateable() {
		return super.componentRateable;
	}

	@Override
    public float calculateFishingRate() {
		return super.componentRateable.calculateFishingRate() + 10;
    }
}
