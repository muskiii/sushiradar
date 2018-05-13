package org.fabiano.sushiradar.api.model;

public class Forecast {

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
}
