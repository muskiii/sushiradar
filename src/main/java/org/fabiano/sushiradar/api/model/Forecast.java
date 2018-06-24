package org.fabiano.sushiradar.api.model;


import org.fabiano.sushiradar.api.utils.Column;
import org.fabiano.sushiradar.api.utils.Entity;
import org.fabiano.sushiradar.api.utils.Id;
import org.fabiano.sushiradar.api.utils.OneToNRealtion;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Forecast {
	
	@Column(name="id")
	@Id
	private int id;

	@Column(name="target")
	private String target;
	
	@Column(name="country")
    private String country;
	
	@Column(name="city")
    private String city;
	
	@Column(name="latitude")
    private String latitude;
	
	@Column(name="longitude")
    private String longitude;

    @OneToNRealtion(foreingKey="fk_forecast", clazz=FCDay.class)
    private List<FCDay> extended;

	public Forecast(int id, String target, String country, String city, String latitude, String longitude,
			List<FCDay> extended) {
		super();
		this.id = id;
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
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
//	public void calculateRates() {
//		for(FCDay day : this.extended) {
//			day.ca
//		}
//	}

	@Override
	public String toString() {
		return "Forecast [id=" + id + ", target=" + target + ", country=" + country + ", city=" + city + ", latitude="
				+ latitude + ", longitude=" + longitude + ", extended=" + extended + "]";
	}

}
