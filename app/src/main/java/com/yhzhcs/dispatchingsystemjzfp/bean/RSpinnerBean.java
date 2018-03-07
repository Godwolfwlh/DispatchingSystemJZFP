package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/19.
 */

public class RSpinnerBean implements Serializable {
    private String Plans;

    public String getPlans() {
        return Plans;
    }

    public void setPlans(String plans) {
        Plans = plans;
    }

    @Override
    public String toString() {
        return "RSpinnerBean{" +
                "Plans='" + Plans + '\'' +
                '}';
    }
}
