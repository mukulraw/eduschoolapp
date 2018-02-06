package com.eduschool.eduschoolapp.onlinePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 9/20/2017.
 */

public class TakeTest {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("max_score")
    @Expose
    private String maxScore;
    @SerializedName("min_score")
    @Expose
    private String minScore;
    @SerializedName("your_score")
    @Expose
    private String yourScore;
    @SerializedName("your_status")
    @Expose
    private String yourStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
    }

    public String getMinScore() {
        return minScore;
    }

    public void setMinScore(String minScore) {
        this.minScore = minScore;
    }

    public String getYourScore() {
        return yourScore;
    }

    public void setYourScore(String yourScore) {
        this.yourScore = yourScore;
    }

    public String getYourStatus() {
        return yourStatus;
    }

    public void setYourStatus(String yourStatus) {
        this.yourStatus = yourStatus;
    }

}
