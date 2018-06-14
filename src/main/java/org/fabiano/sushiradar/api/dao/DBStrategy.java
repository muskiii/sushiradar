package org.fabiano.sushiradar.api.dao;

import java.sql.Connection;

public abstract class DBStrategy {
	
	public abstract Connection getCon();

}
