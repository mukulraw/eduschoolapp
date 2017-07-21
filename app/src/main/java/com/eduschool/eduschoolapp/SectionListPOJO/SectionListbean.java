package com.eduschool.eduschoolapp.SectionListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 7/21/2017.
 */

public class SectionListbean {
    @SerializedName("section_list")
    @Expose
    private List<SectionList> sectionList = null;

    public List<SectionList> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<SectionList> sectionList) {
        this.sectionList = sectionList;
    }

}
