package com.eduschool.eduschoolapp.teacherSurveyPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/3/2017.
 */

public class SurveyListteacher {

    @SerializedName("survey_id")
    @Expose
    private String surveyId;
    @SerializedName("survey_title")
    @Expose
    private String surveyTitle;
    @SerializedName("open_survey")
    @Expose
    private String openSurvey;
    @SerializedName("servey_data")
    @Expose
    private List<ServeyDatum> serveyData = null;

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

    public String getOpenSurvey() {
        return openSurvey;
    }

    public void setOpenSurvey(String openSurvey) {
        this.openSurvey = openSurvey;
    }

    public List<ServeyDatum> getServeyData() {
        return serveyData;
    }

    public void setServeyData(List<ServeyDatum> serveyData) {
        this.serveyData = serveyData;
    }
}
