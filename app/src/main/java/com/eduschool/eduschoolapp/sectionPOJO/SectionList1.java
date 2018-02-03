package com.eduschool.eduschoolapp.sectionPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 11/2/2017.
 */

public class SectionList1 {

    @SerializedName("section_id")
    @Expose
    private String sectionId;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("for_classid")
    @Expose
    private String forClassid;
    @SerializedName("for_classname")
    @Expose
    private String forClassname;

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getForClassid() {
        return forClassid;
    }

    public void setForClassid(String forClassid) {
        this.forClassid = forClassid;
    }

    public String getForClassname() {
        return forClassname;
    }

    public void setForClassname(String forClassname) {
        this.forClassname = forClassname;
    }

}
