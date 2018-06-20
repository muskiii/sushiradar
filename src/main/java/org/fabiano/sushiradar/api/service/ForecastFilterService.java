package org.fabiano.sushiradar.api.service;

import java.sql.SQLException;

import org.fabiano.sushiradar.api.dao.DAO;
import org.fabiano.sushiradar.api.dao.PersistStrategy;
import org.fabiano.sushiradar.api.factory.ForecastFilterFactory;
import org.fabiano.sushiradar.api.model.filter.ForecastFilter;
import org.fabiano.sushiradar.api.model.filter.TempFilter;
import org.fabiano.sushiradar.api.utils.JsonParseable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ForecastFilterService<T> implements JsonParseable<ForecastFilter> {
	
	private DAO<T> dao;		

	public ForecastFilterService(PersistStrategy<T> strategy) {		
		super();
		this.dao = new DAO<T>(strategy);
	}
	
	@Override
	public ForecastFilter fromJson(String json) {
		System.out.println(json);
		JsonObject response  = new JsonParser().parse(json).getAsJsonObject();
		String type = response.getAsJsonObject().get("type").getAsString();
		
		ForecastFilter f = ForecastFilterFactory.createForecast(type);
		
		if (f instanceof TempFilter) {
			TempFilter tempFilter = (TempFilter) f;
			tempFilter.setForecastID(response.getAsJsonObject().get("id").getAsInt());
			
			tempFilter.setMaxTempC(response.get("maxTempC").getAsFloat());
			tempFilter.setMinTempC(response.get("minTempC").getAsFloat());
			return tempFilter;
		}		
		return null;
	}

	public String save(ForecastFilter f) {
		if (f instanceof TempFilter) {
			T tempFilter = (T) f;
			try {
				dao.insert(tempFilter);
			} catch (SQLException e) {
				return "duplicate";
			}			
		}
		return "ok";
	}

	public void deleteALL() {
		dao.deleteAll();		
	}
}
