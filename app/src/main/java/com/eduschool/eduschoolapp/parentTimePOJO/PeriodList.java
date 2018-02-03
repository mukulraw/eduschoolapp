package com.eduschool.eduschoolapp.parentTimePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 10/3/2017.
 */

public class PeriodList {

    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("teacher")
    @Expose
    private String teacher;
    @SerializedName("extra")
    @Expose
    private String extra;
    @SerializedName("from_time")
    @Expose
    private String fromTime;
    @SerializedName("to_time")
    @Expose
    private String toTime;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

}
