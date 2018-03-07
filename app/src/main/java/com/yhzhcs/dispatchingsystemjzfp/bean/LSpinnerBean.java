package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/19.
 */

public class LSpinnerBean implements Serializable {
    private String Times;

    public String getTimes() {
        return Times;
    }

    public void setTimes(String times) {
        Times = times;
    }

    @Override
    public String toString() {
        return "LSpinnerBean{" +
                "Times='" + Times + '\'' +
                '}';
    }
}
