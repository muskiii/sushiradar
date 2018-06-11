package org.fabiano.sushiradar.api.factory;

import org.fabiano.sushiradar.api.utils.OOPersistStrategy;
import org.fabiano.sushiradar.api.utils.PersistStrategy;
import org.fabiano.sushiradar.api.utils.SQLPersisStrategy;

public final class PersistStrategyFactory<T> {

	private final Class<T> clazz;
	
	public PersistStrategyFactory(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public PersistStrategy<T> createStrategy(String type) {
		switch (type) {
		case "MSSQL":
			return new SQLPersisStrategy<T>(clazz);
		case "OO":
			return new OOPersistStrategy<T>(clazz);
		default:
			return null;
		}		
	}
}
