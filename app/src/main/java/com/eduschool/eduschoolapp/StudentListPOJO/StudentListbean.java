package com.eduschool.eduschoolapp.StudentListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 7/26/2017.
 */

public class StudentListbean {
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
