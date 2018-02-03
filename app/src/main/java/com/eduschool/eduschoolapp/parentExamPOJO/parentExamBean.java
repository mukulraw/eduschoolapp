package com.eduschool.eduschoolapp.parentExamPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mukul on 19/11/17.
 */

public class parentExamBean {

    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("result_detail")
    @Expose
    private List<ResultDetail> resultDetail = null;
    @SerializedName("result")
    @Expose
    private Result result;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<ResultDetail> getResultDetail() {
        return resultDetail;
    }

    public void setResultDetail(List<ResultDetail> resultDetail) {
        this.resultDetail = resultDetail;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
