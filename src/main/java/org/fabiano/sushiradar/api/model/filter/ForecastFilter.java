package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.ComponentRateable;
import org.fabiano.sushiradar.api.model.Decorator;

public abstract class ForecastFilter extends Decorator {
	
    public ForecastFilter(ComponentRateable componentRateable) {
        super(componentRateable);
    }
    
    public abstract int getForecastID();
    

    
}
