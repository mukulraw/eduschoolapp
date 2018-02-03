package com.eduschool.eduschoolapp.onlinePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 9/20/2017.
 */

public class ChapterDatum {

    @SerializedName("chapter_id")
    @Expose
    private String chapterId;
    @SerializedName("chapter_name")
    @Expose
    private String chapterName;

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

}
