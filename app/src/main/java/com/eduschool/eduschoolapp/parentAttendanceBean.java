package com.eduschool.eduschoolapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class parentAttendanceBean {


    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("working_day")
    @Expose
    private Integer workingDay;
    @SerializedName("present")
    @Expose
    private Integer present;
    @SerializedName("absent")
    @Expose
    private Integer absent;
    @SerializedName("leave")
    @Expose
    private Integer leave;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(Integer workingDay) {
        this.workingDay = workingDay;
    }

    public Integer getPresent() {
        return present;
    }

    public void setPresent(Integer present) {
        this.present = present;
    }

    public Integer getAbsent() {
        return absent;
    }

    public void setAbsent(Integer absent) {
        this.absent = absent;
    }

    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }

}
