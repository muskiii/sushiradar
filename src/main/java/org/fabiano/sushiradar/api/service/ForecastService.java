package org.fabiano.sushiradar.api.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.fabiano.sushiradar.api.dao.DAO;
import org.fabiano.sushiradar.api.dao.PersistStrategy;
import org.fabiano.sushiradar.api.model.ComponentRateable;
import org.fabiano.sushiradar.api.model.Decorator;
import org.fabiano.sushiradar.api.model.FCDay;
import org.fabiano.sushiradar.api.model.Forecast;
import org.fabiano.sushiradar.api.model.filter.ForecastFilter;
import org.fabiano.sushiradar.api.utils.JsonParseable;
import org.postgresql.util.PSQLException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ForecastService implements JsonParseable<Forecast> {

	private DAO<Forecast> dao;
	ForecastFilterService forecastFilterService = new ForecastFilterService();

	public ForecastService(PersistStrategy<Forecast> strategy) {
		super();
		this.dao = new DAO<Forecast>(strategy);
	}

	@Override
	public Forecast fromJson(String json) {

		JsonElement response = new JsonParser().parse(json);

		JsonObject forecast = response.getAsJsonObject().getAsJsonObject("forecast");
		JsonObject location = response.getAsJsonObject().getAsJsonObject("location");
		JsonArray forecastday = forecast.getAsJsonObject("simpleforecast").getAsJsonArray("forecastday");

		Forecast f = new Forecast();
		f.setCity(location.get("city").getAsString());
		f.setCountry(location.get("country_name").getAsString());
		f.setLatitude(location.get("lat").getAsString());
		f.setLongitude(location.get("lon").getAsString());
		f.setTarget(response.getAsJsonObject().get("target").getAsString());

		for (JsonElement jsonArrayDay : forecastday) {
			JsonObject jsonDay = jsonArrayDay.getAsJsonObject();
			JsonObject jsonDate = jsonDay.getAsJsonObject("date");
			FCDay fcDay = new FCDay();
			fcDay.setDay(jsonDate.get("day").getAsInt());
			fcDay.setWeekday(jsonDate.get("weekday").getAsString());
			fcDay.setMonth(jsonDate.get("month").getAsInt());
			fcDay.setMonthName(jsonDate.get("monthname").getAsString());
			fcDay.setYear(jsonDate.get("year").getAsInt());
			fcDay.setYday(jsonDate.get("yday").getAsInt());
			fcDay.setHour(jsonDate.get("hour").getAsInt());
			fcDay.setAmpm(jsonDate.get("ampm").getAsString());
			fcDay.setTzLong(jsonDate.get("tz_short").getAsString());
			fcDay.setTzShort(jsonDate.get("tz_long").getAsString());

			fcDay.setHighT(jsonDay.getAsJsonObject("high").get("celsius").getAsString());
			fcDay.setLowT(jsonDay.getAsJsonObject("low").get("celsius").getAsString());

			fcDay.setIconURL(jsonDay.get("icon_url").getAsString());

			fcDay.setConditions(jsonDay.get("conditions").getAsString());
			fcDay.setAveHumidity(jsonDay.get("avehumidity").getAsFloat());
			fcDay.setPrecipAllDay(jsonDay.getAsJsonObject("qpf_day").get("mm").isJsonNull() ? "0"
					: jsonDay.getAsJsonObject("qpf_day").get("mm").getAsString());
			fcDay.setAveWindDir(jsonDay.getAsJsonObject("avewind").get("dir").getAsString());
			fcDay.setAveWindKPH(jsonDay.getAsJsonObject("avewind").get("kph").getAsFloat());
			fcDay.setAveWindDegrees(jsonDay.getAsJsonObject("avewind").get("degrees").getAsFloat());
			f.getExtended().add(fcDay);
		}
		return f;
	}

	public String save(Forecast f) {
		try {
			dao.insert(f);
		} catch (PSQLException e) {
			return "duplicate";
		} catch (SQLException e) {
			return "SQLException";
		}
		return "ok";
	}

	public List<Forecast> getAll() {
		List<Forecast> forecasts = dao.findAll();
		if (!forecasts.isEmpty()) {
			List<ForecastFilter> forecastFilters = forecastFilterService.getAll();
			for (Forecast forecast : forecasts) {
				List<Decorator> decorators = new ArrayList<>();
				for (ForecastFilter filter : forecastFilters) {
					if (filter.getForecastID() == forecast.getId()) {
						decorators.add(filter);
					}
				}
				if (decorators.size() > 0) {
					for (int i = 0; i < decorators.size() - 1; i++) {
						decorators.get(i).setComponentRateable(decorators.get(i + 1));
					}

					for (FCDay day : forecast.getExtended()) {
						decorators.get(decorators.size()-1).setComponentRateable(day);
						day.setRate(decorators.get(0).calculateFishingRate());
					}
				}
			}
		}
		return forecasts;
		// return dao.findAll();
	}

	public Forecast getById(String id) {
		return dao.findById(id);
	}

	public void deleteAll() {
		forecastFilterService.deleteALL();
		dao.deleteAll();
	}

	public List<Forecast> getWhere(String field, String value) {
		return dao.findWhere(field, value);
	}
}
