package com.eduschool.eduschoolapp.borrowedBookBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 10/13/2017.
 */

public class BookCategory {

    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("chapcat_name")
    @Expose
    private String chapcatName;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getChapcatName() {
        return chapcatName;
    }

    public void setChapcatName(String chapcatName) {
        this.chapcatName = chapcatName;
    }

}
