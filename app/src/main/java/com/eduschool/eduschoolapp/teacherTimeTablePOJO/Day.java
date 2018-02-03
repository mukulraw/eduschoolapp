package com.eduschool.eduschoolapp.teacherTimeTablePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/2/2017.
 */

public class Day {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("holiday")
    @Expose
    private String holiday;
    @SerializedName("period_list")
    @Expose
    private List<PeriodList> periodList = null;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public List<PeriodList> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<PeriodList> periodList) {
        this.periodList = periodList;
    }


}
