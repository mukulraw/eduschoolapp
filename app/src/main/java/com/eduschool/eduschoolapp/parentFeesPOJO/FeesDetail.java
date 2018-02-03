package com.eduschool.eduschoolapp.parentFeesPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class FeesDetail {

    @SerializedName("fees_month")
    @Expose
    private Object feesMonth;
    @SerializedName("total_fee")
    @Expose
    private String totalFee;
    @SerializedName("paid_status")
    @Expose
    private String paidStatus;
    @SerializedName("fees_description")
    @Expose
    private List<FeesDescription> feesDescription = null;

    public Object getFeesMonth() {
        return feesMonth;
    }

    public void setFeesMonth(Object feesMonth) {
        this.feesMonth = feesMonth;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public List<FeesDescription> getFeesDescription() {
        return feesDescription;
    }

    public void setFeesDescription(List<FeesDescription> feesDescription) {
        this.feesDescription = feesDescription;
    }

}
