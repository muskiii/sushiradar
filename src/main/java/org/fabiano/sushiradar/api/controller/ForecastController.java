package org.fabiano.sushiradar.api.controller;

import org.fabiano.sushiradar.api.dao.DAO;
import org.fabiano.sushiradar.api.model.Forecast;
import org.fabiano.sushiradar.api.factory.ForecastFactory;
import org.fabiano.sushiradar.api.service.ForecastService;
import org.fabiano.sushiradar.api.utils.HttpHelper;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ForecastController {

    ForecastService forecastService = new ForecastService();

//	JSONObject jsonObject =  new JSONObject("{\"response\":{\"features\":{\"geolookup\":1,\"forecast\":1,\"conditions\":1},\"version\":\"0.1\",\"termsofService\":\"http://www.wunderground.com/weather/api/d/terms.html\"},\"location\":{\"zip\":\"00000\",\"magic\":\"1\",\"nearby_weather_stations\":{\"pws\":{\"station\":[{\"country\":\"AG\",\"distance_km\":0,\"city\":\"Puerto Madero\",\"distance_mi\":0,\"lon\":-58.362251,\"neighborhood\":\"Claypole\",\"state\":\"BA\",\"id\":\"IBAPUERT2\",\"lat\":-34.614651},{\"country\":\"AR\",\"distance_km\":1,\"city\":\"Comuna 1\",\"distance_mi\":0,\"lon\":-58.381599,\"neighborhood\":\"Villa Real\",\"state\":\"\",\"id\":\"ICOMUNA131\",\"lat\":-34.603298},{\"country\":\"AR\",\"distance_km\":2,\"city\":\"Buenos Aires\",\"distance_mi\":1,\"lon\":-58.39389,\"neighborhood\":\"Avenida San Juan\",\"state\":\"\",\"id\":\"IBUENOSA418\",\"lat\":-34.6231},{\"country\":\"AR\",\"distance_km\":2,\"city\":\"Buenos Aires\",\"distance_mi\":1,\"lon\":-58.39373,\"neighborhood\":\"José Andrés Pacheco de Melo\",\"state\":\"\",\"id\":\"IBUENOSA411\",\"lat\":-34.591721},{\"country\":\"AR\",\"distance_km\":3,\"city\":\"Buenos Aires\",\"distance_mi\":1,\"lon\":-58.389835,\"neighborhood\":\"Avenida Presidente Manuel Quintana\",\"state\":\"\",\"id\":\"IBUENOSA375\",\"lat\":-34.588451},{\"country\":\"AG\",\"distance_km\":3,\"city\":\"Barrio Norte\",\"distance_mi\":1,\"lon\":-58.397099,\"neighborhood\":\"Recoleta\",\"state\":\"DF\",\"id\":\"IDFBARRI25\",\"lat\":-34.592983},{\"country\":\"AR\",\"distance_km\":3,\"city\":\"Buenos Aires\",\"distance_mi\":1,\"lon\":-58.388767,\"neighborhood\":\"Recoleta\",\"state\":\"CABA\",\"id\":\"ICABABUE10\",\"lat\":-34.586384},{\"country\":\"AR\",\"distance_km\":3,\"city\":\"Buenos Aires\",\"distance_mi\":2,\"lon\":-58.403473,\"neighborhood\":\"Recoleta\",\"state\":\"\",\"id\":\"IBUENOSA263\",\"lat\":-34.595592},{\"country\":\"AG\",\"distance_km\":3,\"city\":\"Buenos Aires\",\"dreturn 0;istance_mi\":2,\"lon\":-58.403027,\"neighborhood\":\"Ecuador\",\"state\":\"\",\"id\":\"IBUENOSA347\",\"lat\":-34.59296},{\"country\":\"AR\",\"distance_km\":4,\"city\":\"Buenos Aires\",\"distance_mi\":2,\"lon\":-58.398247,\"neighborhood\":\"Sevilla\",\"state\":\"\",\"id\":\"IBUENOSA407\",\"lat\":-34.578251},{\"country\":\"AR\",\"distance_km\":5,\"city\":\"Buenos Aires\",\"distance_mi\":3,\"lon\":-58.413754,\"neighborhood\":\"Avenida del Libertador\",\"state\":\"\",\"id\":\"IBUENOSA397\",\"lat\":-34.575558},{\"country\":\"AR\",\"distance_km\":6,\"city\":\"Palermo\",\"distance_mi\":3,\"lon\":-58.430561,\"neighborhood\":\"Palermo\",\"state\":\"\",\"id\":\"IPALERMO89\",\"lat\":-34.58889},{\"country\":\"AR\",\"distance_km\":6,\"city\":\"Buenos Aires\",\"distance_mi\":3,\"lon\":-58.427677,\"neighborhood\":\"Fray Justo Santa Maria de Oro\",\"state\":\"\",\"id\":\"IBUENOSA409\",\"lat\":-34.583099},{\"country\":\"AG\",\"distance_km\":6,\"city\":\"Caballito\",\"distance_mi\":3,\"lon\":-58.438965,\"neighborhood\":\"Caballito\",\"state\":\"DF\",\"id\":\"IDFCABAL2\",\"lat\":-34.611687},{\"country\":\"AR\",\"distance_km\":6,\"city\":\"Caballito\",\"distance_mi\":3,\"lon\":-58.439556,\"neighborhood\":\"Colegiales\",\"state\":\"\",\"id\":\"ICABALLI6\",\"lat\":-34.617802},{\"country\":\"AR\",\"distance_km\":6,\"city\":\"Buenos Aires\",\"distance_mi\":3,\"lon\":-58.430275,\"neighborhood\":\"Parque Chacabuco\",\"state\":\"CIUDAD AUTóNOMA DE BUENOS AIRES\",\"id\":\"ICIUDADA20\",\"lat\":-34.63969},{\"country\":\"AR\",\"distance_km\":7,\"city\":\"Buenos Aires\",\"distance_mi\":4,\"lon\":-58.449188,\"neighborhood\":\"Avenida Avellaneda\",\"state\":\"\",\"id\":\"IBUENOSA450\",\"lat\":-34.618259}]},\"airport\":{\"station\":[{\"country\":\"\",\"city\":\"\",\"icao\":\"\",\"lon\":\"\",\"state\":\"\",\"lat\":\"\"},{\"country\":\"AR\",\"city\":\"Buenos Aires\",\"icao\":\"SABE\",\"lon\":\"-58.41999817\",\"state\":\"\",\"lat\":\"-34.56999969\"},{\"country\":\"\",\"city\":\"\",\"icao\":\"\",\"lon\":\"\",\"state\":\"\",\"lat\":\"\"},{\"country\":\"\",\"city\":\"\",\"icao\":\"\",\"lon\":\"\",\"state\":\"\",\"lat\":\"\"}]}},\"country\":\"AG\",\"tz_short\":\"-03\",\"country_iso3166\":\"AR\",\"city\":\"Buenos Aires\",\"lon\":\"-58.36999893\",\"type\":\"INTLCITY\",\"l\":\"/q/zmw:00000.1.87582\",\"requesturl\":\"global/stations/87582.html\",\"wuiurl\":\"https://www.wunderground.com/global/stations/87582.html\",\"country_name\":\"Argentina\",\"wmo\":\"87582\",\"tz_long\":\"America/Argentina/Cordoba\",\"state\":\"DF\",\"lat\":\"-34.61000061\"},\"forecast\":{\"simpleforecast\":{\"forecastday\":[{\"date\":{\"tz_short\":\"-03\",\"pretty\":\"7:00 PM -03 on May 14, 2018\",\"ampm\":\"PM\",\"year\":2018,\"isdst\":\"0\",\"weekday\":\"Monday\",\"weekday_short\":\"Mon\",\"epoch\":\"1526335200\",\"sec\":0,\"min\":\"00\",\"month\":5,\"hour\":19,\"monthname_short\":\"May\",\"monthname\":\"May\",\"tz_long\":\"America/Argentina/Cordoba\",\"yday\":133,\"day\":14},\"icon_url\":\"http://icons.wxug.com/i/c/k/chancerain.gif\",\"period\":1,\"maxhumidity\":0,\"skyicon\":\"\",\"avewind\":{\"kph\":19,\"mph\":12,\"dir\":\"N\",\"degrees\":355},\"icon\":\"chancerain\",\"avehumidity\":78,\"snow_allday\":{\"in\":0,\"cm\":0},\"qpf_day\":{\"mm\":1,\"in\":0.04},\"maxwind\":{\"kph\":24,\"mph\":15,\"dir\":\"N\",\"degrees\":355},\"pop\":60,\"qpf_night\":{\"mm\":2,\"in\":0.06},\"high\":{\"celsius\":\"20\",\"fahrenheit\":\"68\"},\"minhumidity\":0,\"low\":{\"celsius\":\"13\",\"fahrenheit\":\"56\"},\"snow_night\":{\"in\":0,\"cm\":0},\"snow_day\":{\"in\":0,\"cm\":0},\"conditions\":\"Chance of Rain\",\"qpf_allday\":{\"mm\":2,\"in\":0.09}},{\"date\":{\"tz_short\":\"-03\",\"pretty\":\"7:00 PM -03 on May 15, 2018\",\"ampm\":\"PM\",\"year\":2018,\"isdst\":\"0\",\"weekday\":\"Tuesday\",\"weekday_short\":\"Tue\",\"epoch\":\"1526421600\",\"sec\":0,\"min\":\"00\",\"month\":5,\"hour\":19,\"monthname_short\":\"May\",\"monthname\":\"May\",\"tz_long\":\"America/Argentina/Cordoba\",\"yday\":134,\"day\":15},\"icon_url\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\",\"period\":2,\"maxhumidity\":0,\"skyicon\":\"\",\"avewind\":{\"kph\":19,\"mph\":12,\"dir\":\"SSW\",\"degrees\":212},\"icon\":\"partlycloudy\",\"avehumidity\":61,\"snow_allday\":{\"in\":0,\"cm\":0},\"qpf_day\":{\"mm\":0,\"in\":0},\"maxwind\":{\"kph\":24,\"mph\":15,\"dir\":\"SSW\",\"degrees\":212},\"pop\":10,\"qpf_night\":{\"mm\":0,\"in\":0},\"high\":{\"celsius\":\"18\",\"fahrenheit\":\"64\"},\"minhumidity\":0,\"low\":{\"celsius\":\"9\",\"fahrenheit\":\"48\"},\"snow_night\":{\"in\":0,\"cm\":0},\"snow_day\":{\"in\":0,\"cm\":0},\"conditions\":\"Partly Cloudy\",\"qpf_allday\":{\"mm\":0,\"in\":0}},{\"date\":{\"tz_short\":\"-03\",\"pretty\":\"7:00 PM -03 on May 16, 2018\",\"ampm\":\"PM\",\"year\":2018,\"isdst\":\"0\",\"weekday\":\"Wednesday\",\"weekday_short\":\"Wed\",\"epoch\":\"1526508000\",\"sec\":0,\"min\":\"00\",\"month\":5,\"hour\":19,\"monthname_short\":\"May\",\"monthname\":\"May\",\"tz_long\":\"America/Argentina/Cordoba\",\"yday\":135,\"day\":16},\"icon_url\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\",\"period\":3,\"maxhumidity\":0,\"skyicon\":\"\",\"avewind\":{\"kph\":14,\"mph\":9,\"dir\":\"S\",\"degrees\":191},\"icon\":\"partlycloudy\",\"avehumidity\":67,\"snow_allday\":{\"in\":0,\"cm\":0},\"qpf_day\":{\"mm\":0,\"in\":0},\"maxwind\":{\"kph\":16,\"mph\":10,\"dir\":\"S\",\"degrees\":191},\"pop\":0,\"qpf_night\":{\"mm\":0,\"in\":0},\"high\":{\"celsius\":\"15\",\"fahrenheit\":\"59\"},\"minhumidity\":0,\"low\":{\"celsius\":\"10\",\"fahrenheit\":\"50\"},\"snow_night\":{\"in\":0,\"cm\":0},\"snow_day\":{\"in\":0,\"cm\":0},\"conditions\":\"Partly Cloudy\",\"qpf_allday\":{\"mm\":0,\"in\":0}},{\"date\":{\"tz_short\":\"-03\",\"pretty\":\"7:00 PM -03 on May 17, 2018\",\"ampm\":\"PM\",\"year\":2018,\"isdst\":\"0\",\"weekday\":\"Thursday\",\"weekday_short\":\"Thu\",\"epoch\":\"1526594400\",\"sec\":0,\"min\":\"00\",\"month\":5,\"hour\":19,\"monthname_short\":\"May\",\"monthname\":\"May\",\"tz_long\":\"America/Argentina/Cordoba\",\"yday\":136,\"day\":17},\"icon_url\":\"http://icons.wxug.com/i/c/k/rain.gif\",\"period\":4,\"maxhumidity\":0,\"skyicon\":\"\",\"avewind\":{\"kph\":8,\"mph\":5,\"dir\":\"NNE\",\"degrees\":18},\"icon\":\"rain\",\"avehumidity\":73,\"snow_allday\":{\"in\":0,\"cm\":0},\"qpf_day\":{\"mm\":6,\"in\":0.24},\"maxwind\":{\"kph\":16,\"mph\":10,\"dir\":\"NNE\",\"degrees\":18},\"pop\":70,\"qpf_night\":{\"mm\":0,\"in\":0},\"high\":{\"celsius\":\"13\",\"fahrenheit\":\"56\"},\"minhumidity\":0,\"low\":{\"celsius\":\"11\",\"fahrenheit\":\"51\"},\"snow_night\":{\"in\":0,\"cm\":0},\"snow_day\":{\"in\":0,\"cm\":0},\"conditions\":\"Rain\",\"qpf_allday\":{\"mm\":6,\"in\":0.25}}]},\"txt_forecast\":{\"date\":\"12:17 AM -03\",\"forecastday\":[{\"icon_url\":\"http://icons.wxug.com/i/c/k/chancerain.gif\",\"fcttext\":\"Mostly cloudy with some showers in the afternoon. High 68F. Winds N at 10 to 15 mph. Chance of rain 60%.\",\"pop\":\"60\",\"period\":0,\"icon\":\"chancerain\",\"title\":\"Monday\",\"fcttext_metric\":\"Mostly cloudy in the morning then periods of showers later in the day. High around 20C. Winds N at 15 to 25 km/h. Chance of rain 60%.\"},{\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_chancerain.gif\",\"fcttext\":\"Showers in the evening, then partly cloudy overnight. Low 56F. Winds W at 5 to 10 mph. Chance of rain 60%.\",\"pop\":\"60\",\"period\":1,\"icon\":\"nt_chancerain\",\"title\":\"Monday Night\",\"fcttext_metric\":\"Showers in the evening, then partly cloudy overnight. Low 13C. Winds W at 10 to 15 km/h. Chance of rain 60%.\"},{\"icon_url\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\",\"fcttext\":\"Partly cloudy. High 64F. Winds SSW at 10 to 15 mph.\",\"pop\":\"10\",\"period\":2,\"icon\":\"partlycloudy\",\"title\":\"Tuesday\",\"fcttext_metric\":\"Sunshine and clouds mixed. High 17C. Winds SSW at 15 to 25 km/h.\"},{\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_partlycloudy.gif\",\"fcttext\":\"Partly cloudy. Low 48F. Winds SW at 10 to 15 mph.\",\"pop\":\"0\",\"period\":3,\"icon\":\"nt_partlycloudy\",\"title\":\"Tuesday Night\",\"fcttext_metric\":\"Partly cloudy skies. Low 9C. Winds SW at 15 to 25 km/h.\"},{\"icon_url\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\",\"fcttext\":\"Partly cloudy. High 59F. Winds S at 5 to 10 mph.\",\"pop\":\"0\",\"period\":4,\"icon\":\"partlycloudy\",\"title\":\"Wednesday\",\"fcttext_metric\":\"Sunshine and clouds mixed. High around 15C. Winds S at 10 to 15 km/h.\"},{\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_mostlycloudy.gif\",\"fcttext\":\"Partly cloudy skies during the evening will give way to cloudy skies overnight. Low around 50F. Winds SSE at 5 to 10 mph.\",\"pop\":\"10\",\"period\":5,\"icon\":\"nt_mostlycloudy\",\"title\":\"Wednesday Night\",\"fcttext_metric\":\"Partly cloudy skies in the evening, then becoming cloudy overnight. Low around 10C. Winds light and variable.\"},{\"icon_url\":\"http://icons.wxug.com/i/c/k/rain.gif\",\"fcttext\":\"Rain. High 56F. Winds light and variable. Chance of rain 70%. Rainfall near a quarter of an inch.\",\"pop\":\"70\",\"period\":6,\"icon\":\"rain\",\"title\":\"Thursday\",\"fcttext_metric\":\"Cloudy with periods of rain. High 13C. Winds light and variable. Chance of rain 70%. Rainfall near 6mm.\"},{\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_mostlycloudy.gif\",\"fcttext\":\"Mostly cloudy. Low 51F. Winds NNW at 5 to 10 mph.\",\"pop\":\"20\",\"period\":7,\"icon\":\"nt_mostlycloudy\",\"title\":\"Thursday Night\",\"fcttext_metric\":\"Mostly cloudy skies. Low around 10C. Winds NNW at 10 to 15 km/h.\"}]}},\"current_observation\":{\"nowcast\":\"\",\"temp_c\":17.5,\"observation_epoch\":\"1526271643\",\"temp_f\":63.5,\"wind_kph\":7.2,\"wind_mph\":4.5,\"wind_degrees\":319,\"temperature_string\":\"63.5 F (17.5 C)\",\"weather\":\"\",\"feelslike_string\":\"63.5 F (17.5 C)\",\"precip_today_metric\":\"--\",\"precip_1hr_string\":\"0.00 in ( 0 mm)\",\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_.gif\",\"image\":{\"link\":\"http://www.wunderground.com\",\"title\":\"Weather Underground\",\"url\":\"http://icons.wxug.com/graphics/wu2/logo_130x80.png\"},\"UV\":\"0.0\",\"station_id\":\"IBAPUERT2\",\"local_epoch\":\"1526271652\",\"local_tz_short\":\"-03\",\"wind_dir\":\"NW\",\"precip_1hr_metric\":\" 0\",\"pressure_in\":\"29.89\",\"local_tz_long\":\"America/Argentina/Cordoba\",\"wind_gust_mph\":\"4.9\",\"windchill_string\":\"NA\",\"wind_gust_kph\":\"7.9\",\"wind_string\":\"From the NW at 4.5 MPH Gusting to 4.9 MPH\",\"local_time_rfc822\":\"Mon, 14 May 2018 01:20:52 -0300\",\"visibility_km\":\"\",\"relative_humidity\":\"99%\",\"pressure_mb\":\"1012.1\",\"observation_time_rfc822\":\"Mon, 14 May 2018 01:20:43 -0300\",\"precip_1hr_in\":\"0.00\",\"feelslike_c\":\"17.5\",\"observation_time\":\"Last Updated on May 14, 1:20 AM -03\",\"feelslike_f\":\"63.5\",\"history_url\":\"http://www.wunderground.com/weatherstation/WXDailyHistory.asp?ID=IBAPUERT2\",\"windchill_f\":\"NA\",\"windchill_c\":\"NA\",\"precip_today_string\":\" in ( mm)\",\"icon\":\"\",\"precip_today_in\":\"\",\"solarradiation\":\"0\",\"observation_location\":{\"elevation\":\"19 ft\",\"country\":\"AG\",\"country_iso3166\":\"AR\",\"city\":\"\",\"latitude\":\"-34.61\",\"state\":\"BA\",\"full\":\", BA\",\"longitude\":\"-58.36\"},\"dewpoint_f\":63,\"display_location\":{\"zip\":\"00000\",\"magic\":\"1\",\"elevation\":\"15.8\",\"country\":\"AG\",\"country_iso3166\":\"AR\",\"city\":\"Buenos Aires\",\"state_name\":\"Argentina\",\"latitude\":\"-34.61000061\",\"wmo\":\"87582\",\"state\":\"DF\",\"full\":\"Buenos Aires, Argentina\",\"longitude\":\"-58.36999893\"},\"dewpoint_string\":\"63 F (17 C)\",\"pressure_trend\":\"-\",\"dewpoint_c\":17,\"estimated\":{},\"forecast_url\":\"http://www.wunderground.com/global/stations/87582.html\",\"local_tz_offset\":\"-0300\",\"heat_index_f\":\"NA\",\"heat_index_c\":\"NA\",\"ob_url\":\"http://www.wunderground.com/cgi-bin/findweather/getForecast?query=-34.614651,-58.362251\",\"heat_index_string\":\"NA\",\"visibility_mi\":\"\"}}");

	@RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getForecast(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name = "country", required = true) String country) {
		String url = "http://api.wunderground.com/api/2f0a571de3193464/geolookup/conditions/forecast/q/" + country + "/"
				+ city + ".json";
		System.out.println(url);
		JSONObject jsonObject = HttpHelper.get(url);
			return jsonObject.toString();
	}
	
	@RequestMapping(value = "/forecast", method = RequestMethod.POST, produces = "application/json")
	
	public ResponseEntity<Forecast> saveForecast(@RequestBody String body) {
	    Forecast f = forecastService.fromJson(body);
        forecastService.save(f);
		return new ResponseEntity<>(f, HttpStatus.OK);
	}
	
}