package com.eduschool.eduschoolapp.HomewrkPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/2/2017.
 */

public class PendinghomworkStudent  {
    @SerializedName("stu_id")
    @Expose
    private String stuId;
    @SerializedName("stu_name")
    @Expose
    private String stuName;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

}