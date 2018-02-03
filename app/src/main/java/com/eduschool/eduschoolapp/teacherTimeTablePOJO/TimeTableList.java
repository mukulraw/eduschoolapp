package com.eduschool.eduschoolapp.teacherTimeTablePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/2/2017.
 */

public class TimeTableList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("day")
    @Expose
    private List<Day> day = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<Day> getDay() {
        return day;
    }

    public void setDay(List<Day> day) {
        this.day = day;
    }

}
