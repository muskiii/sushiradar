package org.fabiano.sushiradar.api.model;

import org.fabiano.sushiradar.api.utils.Ignore;
import org.fabiano.sushiradar.api.utils.OneToNRealtion;

import java.util.ArrayList;
import java.util.List;

public class Forecast {

	private String target;
    private String country;
    private String city;
    private String latitude;
    private String longitude;

    @OneToNRealtion(foreingKey="fk_forecast", clazz=FCDay.class)
    private List<FCDay> extended;

    
    public Forecast(String target, String country, String city, String latitude, String longitude,
			List<FCDay> extended) {
		super();
		this.target = target;
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
    
    

    public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
    public String toString() {
        return "Forecast{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", target='" + target + '\'' +
                ", extended=" + extended +
                '}';
    }
}
