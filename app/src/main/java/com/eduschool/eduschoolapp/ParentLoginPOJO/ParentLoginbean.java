package com.eduschool.eduschoolapp.ParentLoginPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 7/14/2017.
 */

public class ParentLoginbean {
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("school_id")
    @Expose
    private String schoolId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("status")
    @Expose
    private String status;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
