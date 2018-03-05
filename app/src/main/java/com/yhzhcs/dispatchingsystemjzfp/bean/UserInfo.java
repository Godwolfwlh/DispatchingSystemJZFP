package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;

/**
 * Auto-generated: 2018-02-06 17:9:33
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class UserInfo implements Serializable{

    private String address;
    private String companyId;
    private String createdBy;
    private String createdDate;
    private String departmentId;
    private String departmentName;
    private int enabledFlag;
    private String id;
    private String job;
    private String lastUpdatedBy;
    private String lastUpdatedDate;
    private String loginName;
    private String missionCompany;
    private String missionName;
    private String password;
    private String photo;
    private String tel;
    private String town;
    private String townId;

    public String getAddress() {
        return address;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getEnabledFlag() {
        return enabledFlag;
    }

    public String getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getMissionCompany() {
        return missionCompany;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public String getTel() {
        return tel;
    }

    public String getTown() {
        return town;
    }

    public String getTownId() {
        return townId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setEnabledFlag(int enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setMissionCompany(String missionCompany) {
        this.missionCompany = missionCompany;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "address='" + address + '\'' +
                ", companyId='" + companyId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", enabledFlag=" + enabledFlag +
                ", id='" + id + '\'' +
                ", job='" + job + '\'' +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedDate='" + lastUpdatedDate + '\'' +
                ", loginName='" + loginName + '\'' +
                ", missionCompany='" + missionCompany + '\'' +
                ", missionName='" + missionName + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", tel='" + tel + '\'' +
                ", town='" + town + '\'' +
                ", townId='" + townId + '\'' +
                '}';
    }

}