package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;

/**
 * Auto-generated: 2018-03-08 10:19:4
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Childs implements Serializable{

    private String value;
    private String id;
    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Childs{" +
                "value='" + value + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}