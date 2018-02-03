package com.eduschool.eduschoolapp.notificationsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 03-02-2018.
 */

public class ToStu {

    @SerializedName("stu_id")
    @Expose
    private String stuId;
    @SerializedName("name")
    @Expose
    private String name;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
