package com.eduschool.eduschoolapp.sectionPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/2/2017.
 */

public class sectionBean {

    @SerializedName("section_list")
    @Expose
    private List<SectionList1> sectionList = null;

    public List<SectionList1> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<SectionList1> sectionList) {
        this.sectionList = sectionList;
    }

}
