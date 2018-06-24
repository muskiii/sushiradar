package org.fabiano.sushiradar.api.model;

public abstract class Decorator implements ComponentRateable{
	
    protected ComponentRateable componentRateable;

    public Decorator(ComponentRateable componentRateable){

    }
    public abstract void setComponentRateable(ComponentRateable componentRateable);
    public abstract ComponentRateable getComponentRateable();
}
