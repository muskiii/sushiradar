package org.fabiano.sushiradar.api.utils;

import java.util.List;

public abstract class PersistStrategy<T> {

	public abstract void insert(T t);
	public abstract List<T> lazyFindAll();
	public abstract List<T> findAll();
	public abstract T findById(String id);
	public abstract T findByFkId(String fk_field, String id);
	public abstract void deleteAll();
	
}
