package org.fabiano.sushiradar.api.dao;

import java.sql.SQLException;
import java.util.List;

	public class DAO<T> {

	private PersistStrategy<T> strategy;


	public DAO(PersistStrategy<T> strategy) {
		super();
		this.strategy =  strategy;
	}
	
	public void insert(T t) throws SQLException{
		strategy.insert(t);
	}

	public List<T> lazyFindAll() {
		return strategy.lazyFindAll();
	}

	public List<T> findAll() {
		return strategy.findAll();
	}

	public T findById(String id) {
		return strategy.findById(id);
	}
	
	public void deleteAll() {
		strategy.deleteAll();
	}

	public List<T> findWhere(String field, String value) {
		return strategy.findWhere(field, value);
	}
}
