package com.eduschool.eduschoolapp.teacherProfilePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 11/4/2017.
 */

public class WorkInfo {

    @SerializedName("empId")
    @Expose
    private String empId;
    @SerializedName("joining_date")
    @Expose
    private String joiningDate;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Total_expirence")
    @Expose
    private String totalExpirence;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTotalExpirence() {
        return totalExpirence;
    }

    public void setTotalExpirence(String totalExpirence) {
        this.totalExpirence = totalExpirence;
    }

}
