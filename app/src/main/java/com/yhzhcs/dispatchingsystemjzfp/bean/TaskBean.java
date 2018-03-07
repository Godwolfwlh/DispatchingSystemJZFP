package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/26.
 */

public class TaskBean implements Serializable {
    private int Type;
    private int TaskID;
    private String TaskName;
    private String TaskPublisher;
    private String StarTime;
    private String EntTime;

    public int getType() {
        return Type;
    }

    public int getTaskID() {
        return TaskID;
    }

    public String getTaskName() {
        return TaskName;
    }

    public String getTaskPublisher() {
        return TaskPublisher;
    }

    public String getStarTime() {
        return StarTime;
    }

    public String getEntTime() {
        return EntTime;
    }

    public void setType(int type) {
        Type = type;
    }

    public void setTaskID(int taskID) {
        TaskID = taskID;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public void setTaskPublisher(String taskPublisher) {
        TaskPublisher = taskPublisher;
    }

    public void setStarTime(String starTime) {
        StarTime = starTime;
    }

    public void setEntTime(String entTime) {
        EntTime = entTime;
    }

    @Override
    public String toString() {
        return "TaskBean{" +
                "Type=" + Type +
                ", TaskID=" + TaskID +
                ", TaskName='" + TaskName + '\'' +
                ", TaskPublisher='" + TaskPublisher + '\'' +
                ", StarTime='" + StarTime + '\'' +
                ", EntTime='" + EntTime + '\'' +
                '}';
    }
}
