package com.eduschool.eduschoolapp.parentExamPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mukul on 19/11/17.
 */

public class Result {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("percantage")
    @Expose
    private String percantage;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("total_obtaimarks")
    @Expose
    private String totalObtaimarks;
    @SerializedName("total_marks")
    @Expose
    private String totalMarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPercantage() {
        return percantage;
    }

    public void setPercantage(String percantage) {
        this.percantage = percantage;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTotalObtaimarks() {
        return totalObtaimarks;
    }

    public void setTotalObtaimarks(String totalObtaimarks) {
        this.totalObtaimarks = totalObtaimarks;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

}
