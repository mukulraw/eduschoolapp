package com.eduschool.eduschoolapp.allEventPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 31-01-2018.
 */

public class Clas {
    @SerializedName("classid")
    @Expose
    private String classid;
    @SerializedName("classname")
    @Expose
    private String classname;

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

}
