package com.eduschool.eduschoolapp.teacherHomePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 11/13/2017.
 */

public class Accadmic {

    @SerializedName("class_id")
    @Expose
    private String classId;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("section_id")
    @Expose
    private String sectionId;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("subject_name")
    @Expose
    private Object subjectName;
    @SerializedName("total_chapter")
    @Expose
    private Integer totalChapter;
    @SerializedName("total_complete_chapter")
    @Expose
    private Integer totalCompleteChapter;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Object getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(Object subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getTotalChapter() {
        return totalChapter;
    }

    public void setTotalChapter(Integer totalChapter) {
        this.totalChapter = totalChapter;
    }

    public Integer getTotalCompleteChapter() {
        return totalCompleteChapter;
    }

    public void setTotalCompleteChapter(Integer totalCompleteChapter) {
        this.totalCompleteChapter = totalCompleteChapter;
    }

}
