package org.fabiano.sushiradar.api.service;

import java.sql.SQLException;

import org.fabiano.sushiradar.api.config.SRConfiguration;
import org.fabiano.sushiradar.api.dao.DAO;
import org.fabiano.sushiradar.api.factory.ForecastFilterFactory;
import org.fabiano.sushiradar.api.factory.PersistStrategyFactory;
import org.fabiano.sushiradar.api.model.filter.ForecastFilter;
import org.fabiano.sushiradar.api.model.filter.PrecipHumidityFilter;
import org.fabiano.sushiradar.api.model.filter.TempFilter;
import org.fabiano.sushiradar.api.model.filter.WindFilter;
import org.fabiano.sushiradar.api.utils.JsonParseable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ForecastFilterService implements JsonParseable<ForecastFilter> {

	DAO<TempFilter> tempDao = new DAO<TempFilter>(new PersistStrategyFactory<TempFilter>(TempFilter.class)
			.createStrategy(SRConfiguration.getConfiguration().get("database_provider")));
	DAO<WindFilter> windDao = new DAO<WindFilter>(new PersistStrategyFactory<WindFilter>(WindFilter.class)
			.createStrategy(SRConfiguration.getConfiguration().get("database_provider")));
	DAO<PrecipHumidityFilter> PreHumDao = new DAO<PrecipHumidityFilter>(new PersistStrategyFactory<PrecipHumidityFilter>(PrecipHumidityFilter.class)
			.createStrategy(SRConfiguration.getConfiguration().get("database_provider")));
	
	@Override
	public ForecastFilter fromJson(String json) {
		JsonObject response = new JsonParser().parse(json).getAsJsonObject();
		String type = response.getAsJsonObject().get("type").getAsString();

		ForecastFilter f = ForecastFilterFactory.createForecast(type);

		if (f instanceof TempFilter) {
			TempFilter tempFilter = (TempFilter) f;
			tempFilter.setForecastID(response.getAsJsonObject().get("id").getAsInt());

			tempFilter.setMaxTempC(response.get("maxTempC").getAsFloat());
			tempFilter.setMinTempC(response.get("minTempC").getAsFloat());
			return tempFilter;
		}
		if (f instanceof WindFilter) {
			WindFilter windFilter = (WindFilter) f;
			windFilter.setForecastID(response.getAsJsonObject().get("id").getAsInt());

			windFilter.setWindDir(response.get("windDir").getAsString());
			windFilter.setMaxWindKPH(response.get("maxWind").getAsFloat());
			windFilter.setMinWindKPH(response.get("minWind").getAsFloat());
			return windFilter;		
		}
		if (f instanceof PrecipHumidityFilter) {
			PrecipHumidityFilter precipHumidityFilter = (PrecipHumidityFilter) f;
			precipHumidityFilter.setForecastID(response.getAsJsonObject().get("id").getAsInt());

			precipHumidityFilter.setPrecipitation(response.get("rain").getAsBoolean());
			precipHumidityFilter.setMaxHumidity(response.get("maxHum").getAsFloat());
			precipHumidityFilter.setMinHumidity(response.get("minHum").getAsFloat());
			return precipHumidityFilter;		
		}
		return null;
	}

	public String save(ForecastFilter f) {
		try {
		if (f instanceof TempFilter) {
			TempFilter tempFilter = (TempFilter) f;			
			tempDao.insert(tempFilter);			
		}
		if (f instanceof WindFilter) {
			WindFilter windFilter = (WindFilter) f;			
			windDao.insert(windFilter);			
		}
		if (f instanceof PrecipHumidityFilter) {
			PrecipHumidityFilter precipHumidityFilter = (PrecipHumidityFilter) f;			
			PreHumDao.insert(precipHumidityFilter);			
		}
		} catch (SQLException e) {
			return "duplicate";
		}	
		return "Class error";

	}

	public void deleteALL() {
		tempDao.deleteAll();
		windDao.deleteAll();
		PreHumDao.deleteAll();
	}
}
