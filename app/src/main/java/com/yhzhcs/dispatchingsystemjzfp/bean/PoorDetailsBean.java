/**
 * Copyright 2018 aTool.org
 */
package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;

/**
 * Auto-generated: 2018-02-08 14:7:8
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class PoorDetailsBean implements Serializable {

    private Poor poor;
    private Liferequire lifeRequire;

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
}