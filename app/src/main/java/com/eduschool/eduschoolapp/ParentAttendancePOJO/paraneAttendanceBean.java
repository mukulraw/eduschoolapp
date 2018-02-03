package com.eduschool.eduschoolapp.ParentAttendancePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class paraneAttendanceBean {


    @SerializedName("attendance_list")
    @Expose
    private List<AttendanceList> attendanceList = null;

    public List<AttendanceList> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<AttendanceList> attendanceList) {
        this.attendanceList = attendanceList;
    }
}
