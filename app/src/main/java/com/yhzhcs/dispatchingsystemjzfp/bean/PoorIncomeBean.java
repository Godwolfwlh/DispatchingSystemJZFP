package com.yhzhcs.dispatchingsystemjzfp.bean;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
/**
 * Auto-generated: 2018-02-08 11:47:22
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class PoorIncomeBean implements Parcelable {

    private List<Personalincome> personalIncome;

    protected PoorIncomeBean(Parcel in) {
    }

    public static final Creator<PoorIncomeBean> CREATOR = new Creator<PoorIncomeBean>() {
        @Override
        public PoorIncomeBean createFromParcel(Parcel in) {
            return new PoorIncomeBean(in);
        }

        @Override
        public PoorIncomeBean[] newArray(int size) {
            return new PoorIncomeBean[size];
        }
    };

    public List<Personalincome> getPersonalIncome() {
        return personalIncome;
    }

    public void setPersonalIncome(List<Personalincome> personalIncome) {
        this.personalIncome = personalIncome;
    }

    @Override
    public String toString() {
        return "PoorIncomeBean{" +
                "personalIncome=" + personalIncome +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}