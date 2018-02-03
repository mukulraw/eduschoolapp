package com.eduschool.eduschoolapp.parentFeesPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class parentFeesBean {

    @SerializedName("student_fee_type")
    @Expose
    private String studentFeeType;
    @SerializedName("start_fees_month")
    @Expose
    private String startFeesMonth;
    @SerializedName("total_fees")
    @Expose
    private String totalFees;
    @SerializedName("fees_details")
    @Expose
    private List<FeesDetail> feesDetails = null;

    public String getStudentFeeType() {
        return studentFeeType;
    }

    public void setStudentFeeType(String studentFeeType) {
        this.studentFeeType = studentFeeType;
    }

    public String getStartFeesMonth() {
        return startFeesMonth;
    }

    public void setStartFeesMonth(String startFeesMonth) {
        this.startFeesMonth = startFeesMonth;
    }

    public String getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(String totalFees) {
        this.totalFees = totalFees;
    }

    public List<FeesDetail> getFeesDetails() {
        return feesDetails;
    }

    public void setFeesDetails(List<FeesDetail> feesDetails) {
        this.feesDetails = feesDetails;
    }

}
