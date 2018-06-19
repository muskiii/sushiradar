package org.fabiano.sushiradar.api.dao;

import java.sql.SQLException;
import java.util.List;

public abstract class PersistStrategy<T> {

	public abstract void insert(T t)  throws SQLException;
	public abstract List<T> lazyFindAll();
	public abstract List<T> findAll();
	public abstract T findById(String id);
	public abstract T findByFkId(String fk_field, String id);
	public abstract void deleteAll();
	public abstract List<T> findWhere(String field, String value);
}
