package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.ComponentRateable;

public class WindFilter extends ForecastFilter {

    private float aveWindKPH;
    private String aveWindDir;

    public WindFilter(ComponentRateable componentRateable) {
        super(componentRateable);
    }
    
    public float getAveWindKPH() {
		return aveWindKPH;
	}



	public void setAveWindKPH(float aveWindKPH) {
		this.aveWindKPH = aveWindKPH;
	}



	public String getAveWindDir() {
		return aveWindDir;
	}



	public void setAveWindDir(String aveWindDir) {
		this.aveWindDir = aveWindDir;
	}

	@Override
    public float calculateFishingRate() {
        return 0;
    }
}
