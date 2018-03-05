/**
 * Copyright 2018 aTool.org
 */
package com.yhzhcs.dispatchingsystemjzfp.bean;
import java.util.List;
/**
 * Auto-generated: 2018-02-01 16:48:11
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class InformationBean {

    private List<Dtlist> dtList;
    private List<Zclist> zcList;
    private List<Ncplist> ncpList;

    public List<Dtlist> getDtList() {
        return dtList;
    }

    public List<Zclist> getZcList() {
        return zcList;
    }

    public List<Ncplist> getNcpList() {
        return ncpList;
    }

    public void setDtList(List<Dtlist> dtList) {
        this.dtList = dtList;
    }

    public void setZcList(List<Zclist> zcList) {
        this.zcList = zcList;
    }

    public void setNcpList(List<Ncplist> ncpList) {
        this.ncpList = ncpList;
    }

    @Override
    public String toString() {
        return "InformationBean{" +
                "dtList=" + dtList +
                ", zcList=" + zcList +
                ", ncpList=" + ncpList +
                '}';
    }
}