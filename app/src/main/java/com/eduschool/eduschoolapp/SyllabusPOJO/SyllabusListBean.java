package com.eduschool.eduschoolapp.SyllabusPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/23/2017.
 */

public class SyllabusListBean {
    @SerializedName("teacher_name")
    @Expose
    private String teacherName;
    @SerializedName("syllabus_list")
    @Expose
    private List<SyllabusList> syllabusList = null;
    @SerializedName("status")
    @Expose
    private String status;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<SyllabusList> getSyllabusList() {
        return syllabusList;
    }

    public void setSyllabusList(List<SyllabusList> syllabusList) {
        this.syllabusList = syllabusList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
