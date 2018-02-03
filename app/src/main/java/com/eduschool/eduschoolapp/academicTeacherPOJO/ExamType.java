package com.eduschool.eduschoolapp.academicTeacherPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mukul on 06/11/17.
 */

public class ExamType {

    @SerializedName("examid")
    @Expose
    private String examid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("chapter_list")
    @Expose
    private List<ChapterList> chapterList = null;

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChapterList> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<ChapterList> chapterList) {
        this.chapterList = chapterList;
    }

}
