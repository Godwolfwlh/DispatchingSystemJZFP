package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2018-02-08 10:54:54
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class PoorImageBean implements Serializable {

    private List<Inglists> ingLists;

    public List<Inglists> getIngLists() {
        return ingLists;
    }

    public void setIngLists(List<Inglists> ingLists) {
        this.ingLists = ingLists;
    }

    @Override
    public String toString() {
        return "PoorImageBean{" +
                "ingLists=" + ingLists +
                '}';
    }
}