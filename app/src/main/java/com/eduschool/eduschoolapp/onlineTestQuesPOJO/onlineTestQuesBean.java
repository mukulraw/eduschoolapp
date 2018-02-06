package com.eduschool.eduschoolapp.onlineTestQuesPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 06-02-2018.
 */

public class onlineTestQuesBean {

    @SerializedName("onlinetest_list")
    @Expose
    private List<OnlinetestList> onlinetestList = null;

    public List<OnlinetestList> getOnlinetestList() {
        return onlinetestList;
    }

    public void setOnlinetestList(List<OnlinetestList> onlinetestList) {
        this.onlinetestList = onlinetestList;
    }

}
