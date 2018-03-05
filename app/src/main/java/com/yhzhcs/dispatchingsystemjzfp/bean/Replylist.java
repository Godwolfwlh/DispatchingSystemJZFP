/**
 * Copyright 2018 aTool.org
 */
package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2018-03-01 10:27:26
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Replylist implements Serializable {

    private int enabledFlag;
    private String createdBy;
    private long createdDate;
    private String lastUpdatedBy;
    private long lastUpdatedDate;
    private int replyId;
    private int articleId;
    private String userId;
    private String userName;
    private String userImg;
    private String foUserId;
    private String foUserImg;
    private String foUserName;
    private int parReplyId;
    private String content;
    private long replyTime;
    private int disNumber;
    private List<Reply> replyList;

    public int getEnabledFlag() {
        return enabledFlag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public int getReplyId() {
        return replyId;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public String getFoUserId() {
        return foUserId;
    }

    public String getFoUserImg() {
        return foUserImg;
    }

    public String getFoUserName() {
        return foUserName;
    }

    public int getParReplyId() {
        return parReplyId;
    }

    public String getContent() {
        return content;
    }

    public long getReplyTime() {
        return replyTime;
    }

    public int getDisNumber() {
        return disNumber;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setEnabledFlag(int enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdatedDate(long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public void setFoUserId(String foUserId) {
        this.foUserId = foUserId;
    }

    public void setFoUserImg(String foUserImg) {
        this.foUserImg = foUserImg;
    }

    public void setFoUserName(String foUserName) {
        this.foUserName = foUserName;
    }

    public void setParReplyId(int parReplyId) {
        this.parReplyId = parReplyId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReplyTime(long replyTime) {
        this.replyTime = replyTime;
    }

    public void setDisNumber(int disNumber) {
        this.disNumber = disNumber;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return "Replylist{" +
                "enabledFlag=" + enabledFlag +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", replyId=" + replyId +
                ", articleId=" + articleId +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userImg='" + userImg + '\'' +
                ", foUserId='" + foUserId + '\'' +
                ", foUserImg='" + foUserImg + '\'' +
                ", foUserName='" + foUserName + '\'' +
                ", parReplyId=" + parReplyId +
                ", content='" + content + '\'' +
                ", replyTime=" + replyTime +
                ", disNumber=" + disNumber +
                ", replyList=" + replyList +
                '}';
    }
}