package com.yhzhcs.dispatchingsystemjzfp.bean;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2018-02-27 9:22:26
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class ProBean implements Serializable{

    private List<Datas> datas;
    public void setDatas(List<Datas> datas) {
        this.datas = datas;
    }
    public List<Datas> getDatas() {
        return datas;
    }

    @Override
    public String toString() {
        return "ProBean{" +
                "datas=" + datas +
                '}';
    }
}