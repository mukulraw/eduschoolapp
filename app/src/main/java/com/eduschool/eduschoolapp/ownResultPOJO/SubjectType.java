package com.eduschool.eduschoolapp.ownResultPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 12/4/2017.
 */

public class SubjectType {

    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("exam_date")
    @Expose
    private String examDate;

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

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

}
