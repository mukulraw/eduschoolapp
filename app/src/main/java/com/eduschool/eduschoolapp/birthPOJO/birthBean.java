package com.eduschool.eduschoolapp.birthPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class birthBean {

    @SerializedName("birth_list")
    @Expose
    private List<BirthList> birthList = null;

    public List<BirthList> getBirthList() {
        return birthList;
    }

    public void setBirthList(List<BirthList> birthList) {
        this.birthList = birthList;
    }

}
