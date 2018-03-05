package com.yhzhcs.dispatchingsystemjzfp.bean;

/**
 * Auto-generated: 2018-02-02 17:32:31
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class UserLoginBean {

    private int userId;
    private String missionId;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public String getMissionId() {
        return missionId;
    }

    @Override
    public String toString() {
        return "UserLoginBean{" +
                "userId=" + userId +
                ", missionId='" + missionId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}