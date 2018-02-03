package com.eduschool.eduschoolapp.studentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/2/2017.
 */

public class studentBean {

    @SerializedName("student_list")
    @Expose
    private List<StudentList1> studentList = null;

    public List<StudentList1> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentList1> studentList) {
        this.studentList = studentList;
    }

}
