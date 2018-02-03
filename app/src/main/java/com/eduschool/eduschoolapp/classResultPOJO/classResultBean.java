package com.eduschool.eduschoolapp.classResultPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 12/5/2017.
 */

public class classResultBean {

    @SerializedName("class_result")
    @Expose
    private List<ClassResult> classResult = null;

    public List<ClassResult> getClassResult() {
        return classResult;
    }

    public void setClassResult(List<ClassResult> classResult) {
        this.classResult = classResult;
    }

}
