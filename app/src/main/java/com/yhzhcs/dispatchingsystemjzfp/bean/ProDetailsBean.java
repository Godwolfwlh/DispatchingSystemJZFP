package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;

/**
 * Auto-generated: 2018-02-27 16:35:14
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class ProDetailsBean implements Serializable{

    private Produce produce;

    public Produce getProduce() {
        return produce;
    }

    public void setProduce(Produce produce) {
        this.produce = produce;
    }

    @Override
    public String toString() {
        return "ProDetailsBean{" +
                "produce=" + produce +
                '}';
    }
}