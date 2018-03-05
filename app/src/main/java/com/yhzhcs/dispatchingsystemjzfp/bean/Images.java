package com.yhzhcs.dispatchingsystemjzfp.bean;

/**
 * Auto-generated: 2018-03-01 10:38:19
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Images {

    private String annentid;
    private Baseannex baseAnnex;
    private String entityid;
    private String entitytype;

    public String getAnnentid() {
        return annentid;
    }

    public Baseannex getBaseAnnex() {
        return baseAnnex;
    }

    public String getEntityid() {
        return entityid;
    }

    public String getEntitytype() {
        return entitytype;
    }

    public void setAnnentid(String annentid) {
        this.annentid = annentid;
    }

    public void setBaseAnnex(Baseannex baseAnnex) {
        this.baseAnnex = baseAnnex;
    }

    public void setEntityid(String entityid) {
        this.entityid = entityid;
    }

    public void setEntitytype(String entitytype) {
        this.entitytype = entitytype;
    }

    @Override
    public String toString() {
        return "Images{" +
                "annentid='" + annentid + '\'' +
                ", baseAnnex=" + baseAnnex +
                ", entityid='" + entityid + '\'' +
                ", entitytype='" + entitytype + '\'' +
                '}';
    }
}