package com.eduschool.eduschoolapp.StudentListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 7/26/2017.
 */

public class StudentList {
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("class_id")
    @Expose
    private String classId;
    @SerializedName("section_id")
    @Expose
    private String sectionId;
    @SerializedName("attendance")
    @Expose
    private String attendance;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
