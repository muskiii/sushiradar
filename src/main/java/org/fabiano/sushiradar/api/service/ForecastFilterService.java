package org.fabiano.sushiradar.api.service;

import org.fabiano.sushiradar.api.dao.DAO;
import org.fabiano.sushiradar.api.factory.ForecastFilterFactory;
import org.fabiano.sushiradar.api.model.Forecast;
import org.fabiano.sushiradar.api.model.filter.ForecastFilter;
import org.fabiano.sushiradar.api.model.filter.TempFilter;
import org.fabiano.sushiradar.api.utils.JsonParseable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ForecastFilterService implements JsonParseable<ForecastFilter> {

	@Override
	public ForecastFilter fromJson(String json) {
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

	public void save(ForecastFilter f) {
		if (f instanceof TempFilter) {
			TempFilter tempFilter = (TempFilter) f;
			new DAO<TempFilter>(TempFilter.class).insert(tempFilter);
		}
	}
}
