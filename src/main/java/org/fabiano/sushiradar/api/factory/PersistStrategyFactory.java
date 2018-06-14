package org.fabiano.sushiradar.api.factory;

import org.fabiano.sushiradar.api.dao.PostgrePersistStrategy;
import org.fabiano.sushiradar.api.dao.PersistStrategy;
import org.fabiano.sushiradar.api.dao.SQLPersisStrategy;

public final class PersistStrategyFactory<T> {

	private final Class<T> clazz;
	
	public PersistStrategyFactory(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public PersistStrategy<T> createStrategy(String type) {
		switch (type) {
		case "SQL":
			return new SQLPersisStrategy<T>(clazz);
		case "OO":
			return new PostgrePersistStrategy<T>(clazz);
		default:
			return null;
		}		
	}
}
