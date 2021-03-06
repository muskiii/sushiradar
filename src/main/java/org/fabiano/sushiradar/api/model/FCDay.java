package org.fabiano.sushiradar.api.model;

import org.fabiano.sushiradar.api.utils.Column;
import org.fabiano.sushiradar.api.utils.Entity;
import org.fabiano.sushiradar.api.utils.FK;
import org.fabiano.sushiradar.api.utils.Id;
import org.fabiano.sushiradar.api.utils.Ignore;

@Entity
public class FCDay implements ComponentRateable  {

	@Column(name="id")
	@Id
	private int id;
	
	@Column(name="fk_forecast")
	@FK(name="fk_forecast")
	private int forecastID;
	
	@Column(name="day")
    public int day;
	
	@Column(name="month")
    public int month;
	
	@Column(name="year")
    public int year;
	
	@Column(name="yday")
    public int yday;
	
	@Column(name="hour")
    public int hour;
	
	@Column(name="monthName")
    public String monthName;
		
	@Column(name="weekday")
    public String weekday;
	
	@Column(name="ampm")
    public String ampm;
	
	@Column(name="tzShort")
    public String tzShort;
	
	@Column(name="tzLong")
    public String tzLong;
	
	@Column(name="highT")
    private String highT;
	
	@Column(name="lowT")
    private String lowT;
	
	@Column(name="aveWindkPH")
    private float aveWindKPH;
	
	@Column(name="aveWindDir")
    private String aveWindDir;
	
	@Column(name="aveWindDegrees")
    private float aveWindDegrees;
	
	
	@Column(name="precipAllDay")
    private String precipAllDay;
	
	@Column(name="aveHumidity")
    private float aveHumidity;

	
	@Column(name="conditions")
    private String conditions;

	@Column(name="iconURL")
    private String iconURL;

	@Ignore()
	private Float rate;

    public FCDay() {
    }
    
    public int getForecastID() {
		return forecastID;
	}

	public void setForecastID(int forecastID) {
		this.forecastID = forecastID;
	}

	public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYday() {
        return yday;
    }

    public void setYday(int yday) {
        this.yday = yday;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
    
    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getTzShort() {
        return tzShort;
    }

    public void setTzShort(String tzShort) {
        this.tzShort = tzShort;
    }

    public String getTzLong() {
        return tzLong;
    }

    public void setTzLong(String tzLong) {
        this.tzLong = tzLong;
    }

    public String getHighT() {
        return highT;
    }

    public void setHighT(String highT) {
        this.highT = highT;
    }

    public String getLowT() {
        return lowT;
    }

    public void setLowT(String lowT) {
        this.lowT = lowT;
    }

    public float getAveWindKPH() {
        return aveWindKPH;
    }

    public void setAveWindKPH(float aveWindKPH) {
        this.aveWindKPH = aveWindKPH;
    }

    public String getAveWindDir() {
        return aveWindDir;
    }

    public void setAveWindDir(String aveWindDir) {
        this.aveWindDir = aveWindDir;
    }

    public float getAveWindDegrees() {
        return aveWindDegrees;
    }

    public void setAveWindDegrees(float aveWindDegrees) {
        this.aveWindDegrees = aveWindDegrees;
    }

    public float getAveHumidity() {
        return aveHumidity;
    }

    public void setAveHumidity(float aveHumidity) {
        this.aveHumidity = aveHumidity;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getPrecipAllDay() {
        return precipAllDay;
    }

    public void setPrecipAllDay(String precipAllDay) {
        this.precipAllDay = precipAllDay;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	@Override
    public String toString() {
        return "FCDay{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", yday=" + yday +
                ", hour=" + hour +
                ", monthName='" + monthName + '\'' +
                ", weekday='" + weekday + '\'' +
                ", ampm='" + ampm + '\'' +
                ", tzShort='" + tzShort + '\'' +
                ", tzLong='" + tzLong + '\'' +
                ", highT=" + highT +
                ", lowT=" + lowT +
                ", aveWindKPH=" + aveWindKPH +
                ", aveWindDir='" + aveWindDir + '\'' +
                ", aveWindDegrees=" + aveWindDegrees +
                ", precipAllDay='" + precipAllDay + '\'' +
                ", aveHumidity=" + aveHumidity +
                ", conditions='" + conditions + '\'' +
                ", iconURL='" + iconURL + '\'' +
                '}';
    }
	
	    @Override
    public float calculateFishingRate() {
        return 5;
    }
	
}
