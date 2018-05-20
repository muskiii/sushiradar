package org.fabiano.sushiradar.api.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.fabiano.sushiradar.api.dao.DAO;
import org.fabiano.sushiradar.api.model.FCDay;
import org.fabiano.sushiradar.api.model.Forecast;
import org.fabiano.sushiradar.api.model.filter.TempFilter;
import org.fabiano.sushiradar.api.utils.JsonParseable;

public class TempFilterService implements JsonParseable<TempFilter> {

	@Override
	public TempFilter fromJson(String json) {
		return null;
	}

	public void save(TempFilter t) {
		new DAO<TempFilter>(TempFilter.class).insert(t);
	}
}
