package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;

/**
 * Auto-generated: 2018-01-30 15:52:27
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Createddate implements Serializable{

    private int date;
    private int day;
    private int hours;
    private int minutes;
    private int month;
    private int nanos;
    private int seconds;
    private long time;
    private int timezoneOffset;
    private Long year;
    public void setDate(int date) {
        this.date = date;
    }
    public int getDate() {
        return date;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public int getDay() {
        return day;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    public int getHours() {
        return hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public int getMinutes() {
        return minutes;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    public int getMonth() {
        return month;
    }

    public void setNanos(int nanos) {
        this.nanos = nanos;
    }
    public int getNanos() {
        return nanos;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    public int getSeconds() {
        return seconds;
    }

    public void setTime(long time) {
        this.time = time;
    }
    public long getTime() {
        return time;
    }

    public void setTimezoneoffset(int timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }
    public int getTimezoneoffset() {
        return timezoneOffset;
    }

    public void setYear(Long year) {
        this.year = year;
    }
    public Long getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Createddate{" +
                "date=" + date +
                ", day=" + day +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", month=" + month +
                ", nanos=" + nanos +
                ", seconds=" + seconds +
                ", time=" + time +
                ", timezoneOffset=" + timezoneOffset +
                ", year=" + year +
                '}';
    }
}