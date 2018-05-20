package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.ComponentRateable;

public class WindFilter extends ForecastFilter {

    private float aveWindHPH;
    private String aveWindDir;

    public WindFilter(ComponentRateable componentRateable) {
        super(componentRateable);
    }
    
    public float getAveWindHPH() {
		return aveWindHPH;
	}



	public void setAveWindHPH(float aveWindHPH) {
		this.aveWindHPH = aveWindHPH;
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
