package org.fabiano.sushiradar.api.dao;

import static java.lang.Math.toIntExact;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.fabiano.sushiradar.api.model.Forecast;
import org.fabiano.sushiradar.api.utils.FK;
import org.fabiano.sushiradar.api.utils.Id;
import org.fabiano.sushiradar.api.utils.OneToNRealtion;
import org.fabiano.sushiradar.api.utils.SQLHelper;

	public class DAO<T> {

	private PersistStrategy<T> strategy;

	private final Class<T> type;

	public DAO(Class<T> type, PersistStrategy<T> strategy) {
		super();
		this.type = type;
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

	public T findByFkId(String fk_field, String id) {
		return strategy.findByFkId(fk_field, id);
	}
	
	public void deleteAll() {
		strategy.deleteAll();
	}

	public List<T> findWhere(String field, String value) {
		return strategy.findWhere(field, value);
	}
}
