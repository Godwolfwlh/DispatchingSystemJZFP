/**
 * Copyright 2018 bejson.com
 */
package com.yhzhcs.dispatchingsystemjzfp.bean;
import java.util.List;

/**
 * Auto-generated: 2018-03-08 10:19:4
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PoorAddBean {

    private List<TownBean> data;
    public void setData(List<TownBean> data) {
        this.data = data;
    }
    public List<TownBean> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "PoorAddBean{" +
                "data=" + data +
                '}';
    }
}