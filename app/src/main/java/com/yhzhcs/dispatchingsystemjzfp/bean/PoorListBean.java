package com.yhzhcs.dispatchingsystemjzfp.bean;
import java.util.List;
/**
 * Auto-generated: 2018-02-05 14:0:44
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class PoorListBean {

    private List<Poorhouses> poorHouses;
    public void setPoorhouses(List<Poorhouses> poorHouses) {
        this.poorHouses = poorHouses;
    }
    public List<Poorhouses> getPoorhouses() {
        return poorHouses;
    }

    @Override
    public String toString() {
        return "PoorListBean{" +
                "poorHouses=" + poorHouses +
                '}';
    }
}
