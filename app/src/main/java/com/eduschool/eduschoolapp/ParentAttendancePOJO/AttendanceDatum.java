package com.eduschool.eduschoolapp.ParentAttendancePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mukul on 19/11/17.
 */

public class AttendanceDatum {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("attendance_date")
    @Expose
    private String attendanceDate;
    @SerializedName("attendance")
    @Expose
    private String attendance;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

}
