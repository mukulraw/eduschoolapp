package com.eduschool.eduschoolapp.parentAttendanceSummaryPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/6/2017.
 */

public class parentAttendanceSummaryBean {

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
