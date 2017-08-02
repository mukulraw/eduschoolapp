package com.eduschool.eduschoolapp.ClassWrkListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 7/31/2017.
 */

public class ClassWrkListbean {

    @SerializedName("classwork_list")
    @Expose
    private List<ClassworkList> classworkList = null;

    public List<ClassworkList> getClassworkList() {
        return classworkList;
    }

    public void setClassworkList(List<ClassworkList> classworkList) {
        this.classworkList = classworkList;
    }

}