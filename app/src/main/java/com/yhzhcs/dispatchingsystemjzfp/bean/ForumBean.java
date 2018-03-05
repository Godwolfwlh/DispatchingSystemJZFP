/**
 * Copyright 2018 aTool.org
 */
package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.util.List;

/**
 * Auto-generated: 2018-03-01 10:38:19
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class ForumBean {

    private int enabledFlag;
    private String createdBy;
    private long createdDate;
    private String lastUpdatedBy;
    private long lastUpdatedDate;
    private int id;
    private String articleColumn;
    private String articleColumnName;
    private String title;
    private String content;
    private String author;
    private String authorNmae;
    private String authorId;
    private String imageUrl;
    private String publishTime;
    private long checkTime;
    private String status;
    private String keyword;
    private int ordersort;
    private List<Images> images;
    private List<Replylist> replyList;
    private int disNumber;

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

    public int getId() {
        return id;
    }

    public String getArticleColumn() {
        return articleColumn;
    }

    public String getArticleColumnName() {
        return articleColumnName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorNmae() {
        return authorNmae;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public long getCheckTime() {
        return checkTime;
    }

    public String getStatus() {
        return status;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getOrdersort() {
        return ordersort;
    }

    public List<Images> getImages() {
        return images;
    }

    public List<Replylist> getReplyList() {
        return replyList;
    }

    public int getDisNumber() {
        return disNumber;
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

    public void setCheckTime(long checkTime) {
        this.checkTime = checkTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArticleColumn(String articleColumn) {
        this.articleColumn = articleColumn;
    }

    public void setArticleColumnName(String articleColumnName) {
        this.articleColumnName = articleColumnName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAuthorNmae(String authorNmae) {
        this.authorNmae = authorNmae;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setOrdersort(int ordersort) {
        this.ordersort = ordersort;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public void setReplyList(List<Replylist> replyList) {
        this.replyList = replyList;
    }

    public void setDisNumber(int disNumber) {
        this.disNumber = disNumber;
    }

    @Override
    public String toString() {
        return "ForumBean{" +
                "enabledFlag=" + enabledFlag +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedDate='" + lastUpdatedDate + '\'' +
                ", id=" + id +
                ", articleColumn='" + articleColumn + '\'' +
                ", articleColumnName='" + articleColumnName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", authorNmae='" + authorNmae + '\'' +
                ", authorId='" + authorId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", status='" + status + '\'' +
                ", keyword='" + keyword + '\'' +
                ", ordersort=" + ordersort +
                ", images=" + images +
                ", replyList=" + replyList +
                ", disNumber=" + disNumber +
                '}';
    }
}