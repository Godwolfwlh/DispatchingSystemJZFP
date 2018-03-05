package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.util.Date;

/**
 * Auto-generated: 2018-03-01 10:38:19
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Baseannex {

    private String annexid;
    private String annextype;
    private String annexname;
    private String annexPath;
    private String annexPathDown;
    private String extension;
    private String annexsize;
    private String annexcontent;
    private Date uploaddate;
    private String checknumber;
    private String sequenceno;
    private String remark;
    private String entryperson;
    private String effectTime;
    private String invalidTime;

    public String getAnnexid() {
        return annexid;
    }

    public String getAnnextype() {
        return annextype;
    }

    public String getAnnexname() {
        return annexname;
    }

    public String getAnnexPath() {
        return annexPath;
    }

    public String getAnnexPathDown() {
        return annexPathDown;
    }

    public String getExtension() {
        return extension;
    }

    public String getAnnexsize() {
        return annexsize;
    }

    public String getAnnexcontent() {
        return annexcontent;
    }

    public Date getUploaddate() {
        return uploaddate;
    }

    public String getChecknumber() {
        return checknumber;
    }

    public String getSequenceno() {
        return sequenceno;
    }

    public String getRemark() {
        return remark;
    }

    public String getEntryperson() {
        return entryperson;
    }

    public String getEffectTime() {
        return effectTime;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setAnnexid(String annexid) {
        this.annexid = annexid;
    }

    public void setAnnextype(String annextype) {
        this.annextype = annextype;
    }

    public void setAnnexname(String annexname) {
        this.annexname = annexname;
    }

    public void setAnnexPath(String annexPath) {
        this.annexPath = annexPath;
    }

    public void setAnnexPathDown(String annexPathDown) {
        this.annexPathDown = annexPathDown;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setAnnexsize(String annexsize) {
        this.annexsize = annexsize;
    }

    public void setAnnexcontent(String annexcontent) {
        this.annexcontent = annexcontent;
    }

    public void setUploaddate(Date uploaddate) {
        this.uploaddate = uploaddate;
    }

    public void setChecknumber(String checknumber) {
        this.checknumber = checknumber;
    }

    public void setSequenceno(String sequenceno) {
        this.sequenceno = sequenceno;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setEntryperson(String entryperson) {
        this.entryperson = entryperson;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    @Override
    public String toString() {
        return "Baseannex{" +
                "annexid='" + annexid + '\'' +
                ", annextype='" + annextype + '\'' +
                ", annexname='" + annexname + '\'' +
                ", annexPath='" + annexPath + '\'' +
                ", annexPathDown='" + annexPathDown + '\'' +
                ", extension='" + extension + '\'' +
                ", annexsize='" + annexsize + '\'' +
                ", annexcontent='" + annexcontent + '\'' +
                ", uploaddate=" + uploaddate +
                ", checknumber='" + checknumber + '\'' +
                ", sequenceno='" + sequenceno + '\'' +
                ", remark='" + remark + '\'' +
                ", entryperson='" + entryperson + '\'' +
                ", effectTime='" + effectTime + '\'' +
                ", invalidTime='" + invalidTime + '\'' +
                '}';
    }
}