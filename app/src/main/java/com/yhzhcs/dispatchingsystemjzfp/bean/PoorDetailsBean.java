/**
 * Copyright 2018 aTool.org
 */
package com.yhzhcs.dispatchingsystemjzfp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Auto-generated: 2018-02-08 14:7:8
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class PoorDetailsBean implements Parcelable {

    private Poor poor;
    private Liferequire lifeRequire;

    protected PoorDetailsBean(Parcel in) {
        poor = in.readParcelable(Poor.class.getClassLoader());
    }

    public static final Creator<PoorDetailsBean> CREATOR = new Creator<PoorDetailsBean>() {
        @Override
        public PoorDetailsBean createFromParcel(Parcel in) {
            return new PoorDetailsBean(in);
        }

        @Override
        public PoorDetailsBean[] newArray(int size) {
            return new PoorDetailsBean[size];
        }
    };

    public Poor getPoor() {
        return poor;
    }

    public Liferequire getLifeRequire() {
        return lifeRequire;
    }

    public void setPoor(Poor poor) {
        this.poor = poor;
    }

    public void setLifeRequire(Liferequire lifeRequire) {
        this.lifeRequire = lifeRequire;
    }

    @Override
    public String toString() {
        return "PoorDetailsBean{" +
                "poor=" + poor +
                ", lifeRequire=" + lifeRequire +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(poor, i);
    }
}