package com.yhzhcs.dispatchingsystemjzfp.bean;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
/**
 * Auto-generated: 2018-02-09 11:34:3
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class PoorFamilyBean implements Parcelable {

    private List<Poorlist> poorList;

    protected PoorFamilyBean(Parcel in) {
        poorList = in.createTypedArrayList(Poorlist.CREATOR);
    }

    public static final Creator<PoorFamilyBean> CREATOR = new Creator<PoorFamilyBean>() {
        @Override
        public PoorFamilyBean createFromParcel(Parcel in) {
            return new PoorFamilyBean(in);
        }

        @Override
        public PoorFamilyBean[] newArray(int size) {
            return new PoorFamilyBean[size];
        }
    };

    public void setPoorList(List<Poorlist> poorList) {
        this.poorList = poorList;
    }
    public List<Poorlist> getPoorList() {
        return poorList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(poorList);
    }
}