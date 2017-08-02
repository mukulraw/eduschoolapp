package com.eduschool.eduschoolapp.ChapterListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 7/24/2017.
 */

public class ChapterListbean {
    @SerializedName("chapter_list")
    @Expose
    private List<ChapterList> chapterList = null;

    public List<ChapterList> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<ChapterList> chapterList) {
        this.chapterList = chapterList;
    }

}
