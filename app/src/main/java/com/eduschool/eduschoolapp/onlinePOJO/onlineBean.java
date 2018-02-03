package com.eduschool.eduschoolapp.onlinePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 9/20/2017.
 */

public class onlineBean {

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
