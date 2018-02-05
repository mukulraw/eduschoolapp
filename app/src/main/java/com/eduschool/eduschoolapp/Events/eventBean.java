package com.eduschool.eduschoolapp.Events;

/**
 * Created by TBX on 05-02-2018.
 */

public class eventBean {

    String type;
    String time;
    String createdBy;
    String to;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
