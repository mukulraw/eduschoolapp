package com.eduschool.eduschoolapp.examTypePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mukul on 20/09/17.
 */

public class examTypeBean {

    @SerializedName("exam_type_list")
    @Expose
    private List<ExamTypeList> examTypeList = null;

    public List<ExamTypeList> getExamTypeList() {
        return examTypeList;
    }

    public void setExamTypeList(List<ExamTypeList> examTypeList) {
        this.examTypeList = examTypeList;
    }


}
