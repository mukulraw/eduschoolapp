package com.eduschool.eduschoolapp.onlineTestQuesPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 06-02-2018.
 */

public class QuestionOption {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("option")
    @Expose
    private String option;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

}
