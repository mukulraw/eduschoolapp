package com.eduschool.eduschoolapp.ClassListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 7/21/2017.
 */

public class ClassList {
    @SerializedName("class_id")
    @Expose
    private String classId;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("status")
    @Expose
    private String status;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}