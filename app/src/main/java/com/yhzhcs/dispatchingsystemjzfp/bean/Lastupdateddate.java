package com.yhzhcs.dispatchingsystemjzfp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2018-02-01 16:26:37
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Lastupdateddate implements Parcelable {

    private int date;
    private int day;
    private int hours;
    private int minutes;
    private int month;
    private int nanos;
    private int seconds;
    private long time;
    private int timezoneOffset;
    private int year;

    protected Lastupdateddate(Parcel in) {
        date = in.readInt();
        day = in.readInt();
        hours = in.readInt();
        minutes = in.readInt();
        month = in.readInt();
        nanos = in.readInt();
        seconds = in.readInt();
        time = in.readLong();
        timezoneOffset = in.readInt();
        year = in.readInt();
    }

    public static final Creator<Lastupdateddate> CREATOR = new Creator<Lastupdateddate>() {
        @Override
        public Lastupdateddate createFromParcel(Parcel in) {
            return new Lastupdateddate(in);
        }

        @Override
        public Lastupdateddate[] newArray(int size) {
            return new Lastupdateddate[size];
        }
    };

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
    public int getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(date);
        parcel.writeInt(day);
        parcel.writeInt(hours);
        parcel.writeInt(minutes);
        parcel.writeInt(month);
        parcel.writeInt(nanos);
        parcel.writeInt(seconds);
        parcel.writeLong(time);
        parcel.writeInt(timezoneOffset);
        parcel.writeInt(year);
    }
}