package com.eduschool.eduschoolapp.studentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 11/2/2017.
 */

public class StudentList1 {

    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("stu_name")
    @Expose
    private String stuName;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

}
