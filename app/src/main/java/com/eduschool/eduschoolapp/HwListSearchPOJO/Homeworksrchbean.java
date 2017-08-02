package com.eduschool.eduschoolapp.HwListSearchPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 7/27/2017.
 */

public class Homeworksrchbean {
    @SerializedName("homework_list")
    @Expose
    private List<HomeworkList> homeworkList = null;

    public List<HomeworkList> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(List<HomeworkList> homeworkList) {
        this.homeworkList = homeworkList;
    }

}

