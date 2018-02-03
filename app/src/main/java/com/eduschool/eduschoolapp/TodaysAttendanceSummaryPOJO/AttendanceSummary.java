package com.eduschool.eduschoolapp.TodaysAttendanceSummaryPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AttendanceSummary {

    @SerializedName("attendance_id")
    @Expose
    private String attendanceId;
    @SerializedName("attendance_value")
    @Expose
    private String attendanceValue;

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getAttendanceValue() {
        return attendanceValue;
    }

    public void setAttendanceValue(String attendanceValue) {
        this.attendanceValue = attendanceValue;
    }

}
