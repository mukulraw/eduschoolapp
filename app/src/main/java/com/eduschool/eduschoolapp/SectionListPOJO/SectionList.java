package com.eduschool.eduschoolapp.SectionListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 7/21/2017.
 */

public class SectionList {
    @SerializedName("section_id")
    @Expose
    private String sectionId;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("status")
    @Expose
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}