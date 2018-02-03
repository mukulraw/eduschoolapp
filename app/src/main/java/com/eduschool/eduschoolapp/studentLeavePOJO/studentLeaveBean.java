package com.eduschool.eduschoolapp.studentLeavePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 1/9/2018.
 */

public class studentLeaveBean {

    @SerializedName("student_list")
    @Expose
    private List<StudentList> studentList = null;

    public List<StudentList> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentList> studentList) {
        this.studentList = studentList;
    }

}
