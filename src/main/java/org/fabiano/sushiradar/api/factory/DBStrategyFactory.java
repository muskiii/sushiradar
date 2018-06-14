package org.fabiano.sushiradar.api.factory;

import org.fabiano.sushiradar.api.dao.DBStrategy;
import org.fabiano.sushiradar.api.dao.MSSQLDBStrategy;
import org.fabiano.sushiradar.api.dao.PostgreDBStrategy;

public final class DBStrategyFactory {

	public DBStrategy createStrategy(String type) {
		switch (type) {
		case "MSSQL":
			return new MSSQLDBStrategy();
		case "PSQL":
			return new PostgreDBStrategy();
		default:
			return null;
		}		
	}

}
