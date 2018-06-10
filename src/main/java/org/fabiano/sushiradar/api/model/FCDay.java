package org.fabiano.sushiradar.api.model;

import org.fabiano.sushiradar.api.utils.Column;
import org.fabiano.sushiradar.api.utils.Entity;
import org.fabiano.sushiradar.api.utils.FK;
import org.fabiano.sushiradar.api.utils.Id;

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
    private float highT;
	
	@Column(name="lowT")
    private float lowT;
	
	@Column(name="aveWindHPH")
    private float aveWindHPH;
	
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

    public String getmonthName() {
        return monthName;
    }

    public void setmonthName(String monthName) {
        this.monthName = monthName;
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

    public float getHighT() {
        return highT;
    }

    public void setHighT(float highT) {
        this.highT = highT;
    }

    public float getLowT() {
        return lowT;
    }

    public void setLowT(float lowT) {
        this.lowT = lowT;
    }

    public float getAveWindHPH() {
        return aveWindHPH;
    }

    public void setAveWindHPH(float aveWindHPH) {
        this.aveWindHPH = aveWindHPH;
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
                ", aveWindHPH=" + aveWindHPH +
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
        return 0;
    }
}
