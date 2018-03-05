package com.yhzhcs.dispatchingsystemjzfp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2018-02-07 9:36:50
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class TaskBeans implements Serializable {

    private List<Tasklists> taskLists;

    public void setTaskLists(List<Tasklists> taskLists) {
        this.taskLists = taskLists;
    }

    public List<Tasklists> getTaskLists() {
        return taskLists;
    }

    @Override
    public String toString() {
        return "TaskBeans{" +
                "taskLists=" + taskLists +
                '}';
    }
}