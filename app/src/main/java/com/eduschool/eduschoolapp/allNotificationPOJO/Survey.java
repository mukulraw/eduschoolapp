package com.eduschool.eduschoolapp.allNotificationPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 07-02-2018.
 */

public class Survey {

    @SerializedName("notify_id")
    @Expose
    private String notifyId;
    @SerializedName("survey_id")
    @Expose
    private String surveyId;
    @SerializedName("survey_title")
    @Expose
    private String surveyTitle;
    @SerializedName("total_question")
    @Expose
    private String totalQuestion;
    @SerializedName("open_survey")
    @Expose
    private String openSurvey;
    @SerializedName("created_on")
    @Expose
    private String createdOn;

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(String totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public String getOpenSurvey() {
        return openSurvey;
    }

    public void setOpenSurvey(String openSurvey) {
        this.openSurvey = openSurvey;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

}
