package com.eduschool.eduschoolapp.parentFeesPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 11/6/2017.
 */

public class FeesDescription {

    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("amount")
    @Expose
    private String amount;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
