package com.eduschool.eduschoolapp.sentCommunicationTeacher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 31-01-2018.
 */

public class Section {

    @SerializedName("sectionid")
    @Expose
    private String sectionid;
    @SerializedName("sectionname")
    @Expose
    private String sectionname;
    @SerializedName("classid")
    @Expose
    private String classid;
    @SerializedName("classname")
    @Expose
    private String classname;

    public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

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
