package com.eduschool.eduschoolapp.academicTeacherPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mukul on 06/11/17.
 */

public class academicTeacherBean {
    @SerializedName("syllabus_list")
    @Expose
    private List<SyllabusList> syllabusList = null;

    public List<SyllabusList> getSyllabusList() {
        return syllabusList;
    }

    public void setSyllabusList(List<SyllabusList> syllabusList) {
        this.syllabusList = syllabusList;
    }

}
