package org.fabiano.sushiradar.api.model;

import java.io.IOException;

import org.fabiano.sushiradar.api.utils.JsonParseable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Forecast{

    private String country;
    private String city;
    private String latitude;
    private String longitude;
    private String fullName;
    private float tempInC;
    private float tempInF;
    private float windKph;
    private float windDegrees;
    private String precip;
    private String weather;
    private String feelsLike;
    private String icon_url;
    private String windDir;
    private String windDescription;
    private String humidity;
    private String pressure;
    private String elevation;
    
    
    

    public Forecast(String country, String city, String latitude, String longitude, String fullName, float tempInC,
			float tempInF, float windKph, float windDegrees, String precip, String weather, String feelsLike,
			String icon_url, String windDir, String windDescription, String humidity, String pressure,
			String elevation) {
		super();
		this.country = country;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.fullName = fullName;
		this.tempInC = tempInC;
		this.tempInF = tempInF;
		this.windKph = windKph;
		this.windDegrees = windDegrees;
		this.precip = precip;
		this.weather = weather;
		this.feelsLike = feelsLike;
		this.icon_url = icon_url;
		this.windDir = windDir;
		this.windDescription = windDescription;
		this.humidity = humidity;
		this.pressure = pressure;
		this.elevation = elevation;
	}

	public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public float getTempInC() {
        return tempInC;
    }

    public void setTempInC(float tempInC) {
        this.tempInC = tempInC;
    }

    public float getTempInF() {
        return tempInF;
    }

    public void setTempInF(float tempInF) {
        this.tempInF = tempInF;
    }

    public float getWindKph() {
        return windKph;
    }

    public void setWindKph(float windKph) {
        this.windKph = windKph;
    }

    public float getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(float windDegrees) {
        this.windDegrees = windDegrees;
    }

    public String getPrecip() {
        return precip;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindDescription() {
        return windDescription;
    }

    public void setWindDescription(String windDescription) {
        this.windDescription = windDescription;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
