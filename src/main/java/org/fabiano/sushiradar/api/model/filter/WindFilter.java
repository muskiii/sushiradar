package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.ComponentRateable;
import org.fabiano.sushiradar.api.utils.Column;
import org.fabiano.sushiradar.api.utils.Entity;
import org.fabiano.sushiradar.api.utils.FK;

@Entity
public class WindFilter extends ForecastFilter {

	@Column(name="fk_forecast")
	@FK(name="fk_forecast")
	private int forecastID; 
	
	@Column(name="maxWindKPH")
    private float maxWindKPH;
	
	@Column(name="minWindKPH")
    private float minWindKPH;
	
	@Column(name="windDir")
    private String windDir;

    public WindFilter(ComponentRateable componentRateable) {
        super(componentRateable);
    }
	public WindFilter() {
		super(null);
	}
    
    public int getForecastID() {
		return forecastID;
	}

	public void setForecastID(int forecastID) {
		this.forecastID = forecastID;
	}

	public float getMaxWindKPH() {
		return maxWindKPH;
	}

	public void setMaxWindKPH(float maxWindKPH) {
		this.maxWindKPH = maxWindKPH;
	}

	public float getMinWindKPH() {
		return minWindKPH;
	}

	public void setMinWindKPH(float minWindKPH) {
		this.minWindKPH = minWindKPH;
	}

	public String getWindDir() {
		return windDir;
	}

	public void setWindDir(String windDir) {
		this.windDir = windDir;
	}

	@Override
    public float calculateFishingRate() {
        return 0;
    }
}
