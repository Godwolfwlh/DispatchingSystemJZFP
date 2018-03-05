package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;

/**
 * Auto-generated: 2018-02-08 10:54:54
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Inglists implements Serializable{

    private String annentid;
    private String annexid;
    private String annexPath;
    private String entityid;
    private String annexPathDown;

    public String getAnnentid() {
        return annentid;
    }

    public String getAnnexid() {
        return annexid;
    }

    public String getAnnexPath() {
        return annexPath;
    }

    public String getEntityid() {
        return entityid;
    }

    public String getAnnexPathDown() {
        return annexPathDown;
    }

    public void setAnnentid(String annentid) {
        this.annentid = annentid;
    }

    public void setAnnexid(String annexid) {
        this.annexid = annexid;
    }

    public void setAnnexPath(String annexPath) {
        this.annexPath = annexPath;
    }

    public void setEntityid(String entityid) {
        this.entityid = entityid;
    }

    public void setAnnexPathDown(String annexPathDown) {
        this.annexPathDown = annexPathDown;
    }

    @Override
    public String toString() {
        return "Inglists{" +
                "annentid='" + annentid + '\'' +
                ", annexid='" + annexid + '\'' +
                ", annexPath='" + annexPath + '\'' +
                ", entityid='" + entityid + '\'' +
                ", annexPathDown='" + annexPathDown + '\'' +
                '}';
    }
}