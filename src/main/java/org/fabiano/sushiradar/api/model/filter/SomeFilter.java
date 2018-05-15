package org.fabiano.sushiradar.api.model.filter;

import org.fabiano.sushiradar.api.model.ComponentRateable;

public class SomeFilter extends ForecastFilter {

    public SomeFilter(ComponentRateable componentRateable) {
        super(componentRateable);
    }

    @Override
    public float calculateFishingRate() {
        return 0;
    }
}
