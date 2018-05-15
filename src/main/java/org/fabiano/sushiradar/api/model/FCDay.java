package org.fabiano.sushiradar.api.model;

public class FCDay implements ComponentRateable  {

    public int day;
    public int month;
    public int year;
    public int yday;
    public int hour;
    public String monthname;
    public String weekday;
    public String ampm;
    public String tzShort;
    public String tzLong;

    private float highT;
    private float lowT;

    private float aveWindHPH;
    private String aveWindDir;
    private float aveWinddegrees;

    private String precipAllDay;
    private float aveHumidity;

    private String conditions;

    private String iconURL;


    public FCDay() {
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

    public String getMonthname() {
        return monthname;
    }

    public void setMonthname(String monthname) {
        this.monthname = monthname;
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

    public float getAveWinddegrees() {
        return aveWinddegrees;
    }

    public void setAveWinddegrees(float aveWinddegrees) {
        this.aveWinddegrees = aveWinddegrees;
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
                ", monthname='" + monthname + '\'' +
                ", weekday='" + weekday + '\'' +
                ", ampm='" + ampm + '\'' +
                ", tzShort='" + tzShort + '\'' +
                ", tzLong='" + tzLong + '\'' +
                ", highT=" + highT +
                ", lowT=" + lowT +
                ", aveWindHPH=" + aveWindHPH +
                ", aveWindDir='" + aveWindDir + '\'' +
                ", aveWinddegrees=" + aveWinddegrees +
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
