package com.eduschool.eduschoolapp.studentResultPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mukul on 16/11/17.
 */

public class ResultDetail {

    @SerializedName("exam_date")
    @Expose
    private String examDate;
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("from_time")
    @Expose
    private String fromTime;
    @SerializedName("to_time")
    @Expose
    private String toTime;
    @SerializedName("obtain_mark")
    @Expose
    private String obtainMark;
    @SerializedName("max_mark")
    @Expose
    private String maxMark;
    @SerializedName("min_mark")
    @Expose
    private String minMark;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("percantage")
    @Expose
    private String percantage;

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getObtainMark() {
        return obtainMark;
    }

    public void setObtainMark(String obtainMark) {
        this.obtainMark = obtainMark;
    }

    public String getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(String maxMark) {
        this.maxMark = maxMark;
    }

    public String getMinMark() {
        return minMark;
    }

    public void setMinMark(String minMark) {
        this.minMark = minMark;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPercantage() {
        return percantage;
    }

    public void setPercantage(String percantage) {
        this.percantage = percantage;
    }

}
