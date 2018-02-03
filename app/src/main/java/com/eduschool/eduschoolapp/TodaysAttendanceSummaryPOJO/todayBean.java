package com.eduschool.eduschoolapp.TodaysAttendanceSummaryPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class todayBean {

    @SerializedName("attendance_summary")
    @Expose
    private List<AttendanceSummary> attendanceSummary = null;

    public List<AttendanceSummary> getAttendanceSummary() {
        return attendanceSummary;
    }

    public void setAttendanceSummary(List<AttendanceSummary> attendanceSummary) {
        this.attendanceSummary = attendanceSummary;
    }

}
