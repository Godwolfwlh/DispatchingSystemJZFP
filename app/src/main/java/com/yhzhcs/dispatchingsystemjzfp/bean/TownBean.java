package com.yhzhcs.dispatchingsystemjzfp.bean;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2018-03-08 10:19:4
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TownBean implements Serializable{

    private String value;
    private String id;
    private List<Childs> childs;
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

    public void setChilds(List<Childs> childs) {
        this.childs = childs;
    }
    public List<Childs> getChilds() {
        return childs;
    }

    @Override
    public String toString() {
        return "TownBean{" +
                "value='" + value + '\'' +
                ", id='" + id + '\'' +
                ", childs=" + childs +
                '}';
    }
}