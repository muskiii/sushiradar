package org.fabiano.sushiradar.api.model;

import org.fabiano.sushiradar.api.utils.Ignore;

import java.util.ArrayList;
import java.util.List;

public class Forecast {

    private String country;
    private String city;
    private String latitude;
    private String longitude;

    @Ignore
    private List<FCDay> extended;

    public Forecast(String country, String city, String latitude, String longitude, List<FCDay> extended) {
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.extended = extended;
    }
    public Forecast(){
        this.extended = new ArrayList<>();
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

    public List<FCDay> getExtended() {
        return extended;
    }

    public void setExtended(List<FCDay> extended) {
        this.extended = extended;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", extended=" + extended +
                '}';
    }
}
