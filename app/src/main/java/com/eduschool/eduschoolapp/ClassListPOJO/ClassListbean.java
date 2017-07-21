package com.eduschool.eduschoolapp.ClassListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 7/21/2017.
 */

public class ClassListbean {
    @SerializedName("class_list")
    @Expose
    private List<ClassList> classList = null;

    public List<ClassList> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassList> classList) {
        this.classList = classList;
    }

}
